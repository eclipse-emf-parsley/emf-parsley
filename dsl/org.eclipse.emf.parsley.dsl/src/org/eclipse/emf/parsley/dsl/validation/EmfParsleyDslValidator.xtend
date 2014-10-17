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

import com.google.inject.Inject
import java.util.Set
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.parsley.dsl.model.EmfFeatureAccess
import org.eclipse.emf.parsley.dsl.model.FieldSpecification
import org.eclipse.emf.parsley.dsl.model.ModelPackage
import org.eclipse.emf.parsley.dsl.model.Module
import org.eclipse.emf.parsley.dsl.model.ProviderBinding
import org.eclipse.emf.parsley.dsl.model.TypeBinding
import org.eclipse.emf.parsley.dsl.model.ValueBinding
import org.eclipse.emf.parsley.dsl.model.ViewSpecification
import org.eclipse.emf.parsley.dsl.model.WithExtendsClause
import org.eclipse.emf.parsley.dsl.typing.EmfParsleyDslTypeSystem
import org.eclipse.emf.parsley.dsl.util.EmfParsleyDslGuiceModuleHelper
import org.eclipse.xtext.common.types.JvmGenericType
import org.eclipse.xtext.common.types.JvmOperation
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelAssociations
import org.eclipse.xtext.xbase.typesystem.util.Multimaps2

//import org.eclipse.xtext.validation.Check

/**
 * Custom validation rules. 
 *
 * see http://www.eclipse.org/Xtext/documentation.html#validation
 */
class EmfParsleyDslValidator extends AbstractEmfParsleyDslValidator {

	public static val TYPE_MISMATCH = "org.eclipse.emf.parsley.dsl.TypeMismatch";
	
	public static val CYCLIC_INHERITANCE = "org.eclipse.emf.parsley.dsl.CyclicInheritance";

	public static val FINAL_FIELD_NOT_INITIALIZED = "org.eclipse.emf.parsley.dsl.FinalFieldNotInitialized";

	public static val DUPLICATE_BINDING = "org.eclipse.emf.parsley.dsl.DuplicateBinding";

	public static val NON_COMPLIANT_BINDING = "org.eclipse.emf.parsley.dsl.NonCompliantBinding";

	@Inject EmfParsleyDslTypeSystem typeSystem
	@Inject extension EmfParsleyDslExpectedSuperTypes
	@Inject extension EmfParsleyDslGuiceModuleHelper
	@Inject extension IJvmModelAssociations
	
	val modelPackage = ModelPackage.eINSTANCE

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
		if (withExtendsClause.getExtendsClause() != null && !withExtendsClause.hasCycleInHierarchy()) {
			// it makes no sense to check for type conformance if there's a cycle in the
			// hierarchy: there will always be a type mismatch in that case
			checkType(withExtendsClause.extendsClause, 
				withExtendsClause.extendsClause.superType, withExtendsClause.expectedSupertype, 
				ModelPackage.Literals.EXTENDS_CLAUSE__SUPER_TYPE)
		}
	}

	@Check
	def void checkFinalFieldIsInitialized(FieldSpecification f) {
		if (!f.writeable && f.right == null) {
			error("The blank final field " + f.name + " may not have been initialized",
				ModelPackage.Literals.FIELD_SPECIFICATION__NAME,
				FINAL_FIELD_NOT_INITIALIZED
			)
		}
	}

	@Check
	def void checkModule(Module module) {
		// the inferred Guice module for this DSL Module element
		// we create a single class for the Module and it is a Guice module
		// so we can take the first element of the filter
		val guiceModuleClass = module.moduleInferredType
		if (guiceModuleClass == null) {
			return
		}
		
		val methods = guiceModuleClass.declaredOperations
		if (methods.empty) {
			return
		}
		
		checkDuplicateBindings(methods)
		
		checkCorrectValueBindings(guiceModuleClass, methods, module)
	}
	
	protected def checkDuplicateBindings(Iterable<JvmOperation> methods) {
		val map = duplicatesMultimap
		
		// create a multimap using method names
		for (m : methods) {
			map.put(m.simpleName, m)
		}
		
		// check if there are duplicates
		for (entry : map.asMap.entrySet) {
			val duplicates = entry.value
			if (duplicates.size > 1) {
				for (d : duplicates) {
					val source = d.sourceElements.head
					error(
						duplicateBindingMessage(source, d),
						source,
						source.duplicateBindingFeature,
						DUPLICATE_BINDING
					);
				}
			}
		}
	}

	def checkCorrectValueBindings(JvmGenericType guiceModuleClass, Iterable<JvmOperation> methods, Module module) {
		// These are all the value bindings in the superclass
		val superClassValueBindings = guiceModuleClass.allGuiceValueBindingsMethodsInSuperclass
		// check that the return type of the value bindings in this module
		// are compliant (they can be subtypes)
		for (superBinding : superClassValueBindings) {
			val matching = methods.findFirst[simpleName == superBinding.simpleName]
			if (matching != null && !(typeSystem.isConformant(module, superBinding.returnType, matching.returnType))) {
				error("Incorrect value binding: " + matching.returnType.simpleName +
					" is not compliant with inherited binding's type " + superBinding.returnType.simpleName,
					matching.sourceElements.head,
					modelPackage.valueBinding_TypeDecl,
					NON_COMPLIANT_BINDING);
			}
		}
	}
	
	def protected checkType(EObject context, JvmTypeReference actualType, Class<?> expectedType,
			EStructuralFeature feature) {
		if (actualType != null) {
			if (!typeSystem.isConformant(context, expectedType, actualType)) {
				error("Type mismatch: cannot convert from " + actualType.simpleName +
					" to " + expectedType.simpleName,
					context,
					feature,
					TYPE_MISMATCH);
			}
		}
	}

	def protected boolean hasCycleInHierarchy(WithExtendsClause withExtendsClause) {
		val superType = withExtendsClause.extendsClause.superType?.type
		
		if (superType instanceof JvmGenericType) {
			if (superType.hasCycleInHierarchy(newHashSet())) {
				error("The inheritance hierarchy of " + superType.simpleName + " contains cycles",
					withExtendsClause.extendsClause,
					ModelPackage.Literals.EXTENDS_CLAUSE__SUPER_TYPE,
					CYCLIC_INHERITANCE);
				return true
			}
		}
		
		return false
	}

	def protected boolean hasCycleInHierarchy(JvmGenericType type, Set<JvmGenericType> processedSuperTypes) {
		if (processedSuperTypes.contains(type)) {
			return true;
		}
		processedSuperTypes.add(type);
		for (JvmTypeReference superTypeRef : type.getSuperTypes()) {
			if (superTypeRef.getType() instanceof JvmGenericType) {
				if (hasCycleInHierarchy(superTypeRef.getType() as JvmGenericType, processedSuperTypes))
					return true;
			}
		}
		processedSuperTypes.remove(type);
		return false;
	}

	def private <K, T> duplicatesMultimap() {
		return Multimaps2.<K, T> newLinkedHashListMultimap();
	}

	def private duplicateBindingMessage(EObject source, JvmOperation method) {
		"Duplicate binding for: " +
		switch (source) {
			TypeBinding: method.returnType.simpleName
			ProviderBinding: method.returnType.simpleName
			ValueBinding: source.id
			default: method.returnType.simpleName
		}
	}

	def private duplicateBindingFeature(EObject e) {
		switch (e) {
			TypeBinding: modelPackage.typeBinding_TypeToBind
			ProviderBinding: modelPackage.providerBinding_Type
			ValueBinding: modelPackage.valueBinding_Id
			default: null
		}
	}

}
