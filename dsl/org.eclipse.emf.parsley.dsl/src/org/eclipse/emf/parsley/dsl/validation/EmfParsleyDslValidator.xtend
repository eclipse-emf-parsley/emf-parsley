/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.validation

import com.google.common.collect.ListMultimap
import com.google.inject.Inject
import java.util.List
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.parsley.dsl.model.EmfFeatureAccess
import org.eclipse.emf.parsley.dsl.model.FieldSpecification
import org.eclipse.emf.parsley.dsl.model.ModelPackage
import org.eclipse.emf.parsley.dsl.model.Module
import org.eclipse.emf.parsley.dsl.model.PartSpecification
import org.eclipse.emf.parsley.dsl.model.ViewSpecification
import org.eclipse.emf.parsley.dsl.util.EmfParsleyDslGuiceModuleHelper
import org.eclipse.xtext.common.types.JvmGenericType
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.resource.IContainer
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.CheckType
import org.eclipse.xtext.validation.ComposedChecks
import org.eclipse.xtext.xbase.typesystem.util.Multimaps2
import org.eclipse.xtext.xbase.validation.JvmGenericTypeValidator
import org.eclipse.emf.parsley.dsl.model.WithExtendsClause

//import org.eclipse.xtext.validation.Check

/**
 * Custom validation rules. 
 *
 * see http://www.eclipse.org/Xtext/documentation.html#validation
 */
@ComposedChecks(validators = JvmGenericTypeValidator)
class EmfParsleyDslValidator extends AbstractEmfParsleyDslValidator {

	public static val TYPE_MISMATCH = "org.eclipse.emf.parsley.dsl.TypeMismatch";

	public static val FINAL_FIELD_NOT_INITIALIZED = "org.eclipse.emf.parsley.dsl.FinalFieldNotInitialized";

	public static val TOO_LITTLE_TYPE_INFORMATION = "org.eclipse.emf.parsley.dsl.TooLittleTypeInformation";

	public static val DUPLICATE_ELEMENT = "org.eclipse.emf.parsley.dsl.DuplicateElement";

	@Inject extension EmfParsleyDslExpectedSuperTypes
	@Inject extension EmfParsleyDslGuiceModuleHelper

	@Inject ResourceDescriptionsProvider rdp
	@Inject IContainer.Manager cm

	val modelPackage = ModelPackage.eINSTANCE

	// perform this check only on file save
	@Check(CheckType.NORMAL)
	def void checkDuplicateViewSpecificationAcrossFiles(ViewSpecification viewSpecification) {
		val descriptions = getVisibleEObjectDescriptions(viewSpecification, 
			ModelPackage.Literals.VIEW_SPECIFICATION)
		for (desc : descriptions) {
			if (desc.qualifiedName.toString == viewSpecification.id && 
					desc.EObjectOrProxy != viewSpecification && 
					desc.EObjectURI.trimFragment != viewSpecification.eResource.URI) {
				error(
					"The part id " + viewSpecification.id + " is already defined",
					modelPackage.viewSpecification_Id,
					DUPLICATE_ELEMENT
				)
				return
			}
		}
	}

	def private getVisibleEObjectDescriptions(EObject o, EClass type) {
		o.getVisibleContainers.map[
			container |
			container.getExportedObjectsByType(type)
		].flatten
	}

	def private getVisibleContainers(EObject o) {
		val index = rdp.getResourceDescriptions(o.eResource)
		val rd = index.getResourceDescription(o.eResource.URI)
		cm.getVisibleContainers(rd, index)
	}

	@Check
	def void checkViewSpecification(ViewSpecification viewSpecification) {
		checkType(viewSpecification, 
			viewSpecification.type, viewSpecification.expectedSupertype, 
			ModelPackage.Literals.VIEW_SPECIFICATION__TYPE)
	}

	@Check
	def void checkEmfFeatureAccess(EmfFeatureAccess emfFeatureAccess) {
		checkType(emfFeatureAccess, 
			emfFeatureAccess.parameterType, emfFeatureAccess.expectedSupertype, 
			ModelPackage.Literals.EMF_FEATURE_ACCESS__PARAMETER_TYPE)
	}

	@Check
	def void checkExtendsClause(WithExtendsClause withExtendsClause) {
		if (withExtendsClause.getExtendsClause() !== null) {
			checkType(withExtendsClause.extendsClause, 
				withExtendsClause.extendsClause.superType, withExtendsClause.expectedSupertype, 
				ModelPackage.Literals.EXTENDS_CLAUSE__SUPER_TYPE)
		}
	}

	@Check
	def void checkFieldInitialization(FieldSpecification f) {
		if (!f.writeable && f.right === null) {
			error("The blank final field " + f.name + " may not have been initialized",
				ModelPackage.Literals.FIELD_SPECIFICATION__NAME,
				FINAL_FIELD_NOT_INITIALIZED
			)
		}
		if (f.type === null && f.right === null) {
			error("The field "+f.name+" needs an explicit type since there is no initialization expression to infer the type from.", 
				f, ModelPackage.Literals.FIELD_SPECIFICATION__NAME,
				TOO_LITTLE_TYPE_INFORMATION
			);
		}
	}

	@Check
	def void checkModule(Module module) {
		// the inferred Guice module for this DSL Module element
		// we create a single class for the Module and it is a Guice module
		// so we can take the first element of the filter
		val guiceModuleClass = module.moduleInferredType
		if (guiceModuleClass === null) {
			return
		}
		
		val partsSpecifications = module.partsSpecifications
		if (partsSpecifications !== null) {
			checkDuplicateViewSpecifications(partsSpecifications.parts)
		}
		
		val methods = guiceModuleClass.declaredOperations
		if (methods.empty) {
			return
		}

	}

	private def checkDuplicateViewSpecifications(List<PartSpecification> parts) {
		val map = duplicatesMultimap

		for (p : parts.filter(ViewSpecification)) {
			map.put(p.id, p)
		}

		checkDuplicates(map) [ d |
			error(
				"Duplicate element",
				d,
				modelPackage.viewSpecification_Id,
				DUPLICATE_ELEMENT
			);
		]
	}

	private def <T> checkDuplicates(ListMultimap<String, T> map, (T)=>void errorReporter) {
		// check if there are duplicates
		for (entry : map.asMap.entrySet) {
			val duplicates = entry.value
			if (duplicates.size > 1) {
				for (d : duplicates) {
					errorReporter.apply(d)
				}
			}
		}
	}

	def protected checkType(EObject context, JvmTypeReference actualType, Class<?> expectedType,
			EStructuralFeature feature) {
		if (actualType !== null) {
			val jvmType = actualType.type
			if (jvmType instanceof JvmGenericType) {
				val allSuperTypesAreObject = jvmType.superTypes
					.forall[
						t |
						// this means that the hierarchy has not been
						// completely resolved...
						t.identifier == "java.lang.Object"
					]
				// ... so we don't perform other checks
				// otherwise when everything is resolved, the old
				// error markers is not removed
				// https://github.com/eclipse-emf-parsley/emf-parsley/issues/42
				if (allSuperTypesAreObject)
					return
			}
			val lightweightActualType = toLightweightTypeReference(actualType)
			// val superType = lightweightActualType.getSuperType(expectedType)
			if (!lightweightActualType.isSubtypeOf(expectedType)) {
				error("Type mismatch: cannot convert from " + actualType.simpleName +
					" to " + expectedType.simpleName,
					context,
					feature,
					TYPE_MISMATCH);
			}
		}
	}

	def private <K, T> duplicatesMultimap() {
		return Multimaps2.<K, T> newLinkedHashListMultimap();
	}

}
