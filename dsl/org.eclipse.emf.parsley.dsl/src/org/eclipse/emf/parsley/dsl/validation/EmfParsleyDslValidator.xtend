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
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.parsley.dsl.model.EmfFeatureAccess
import org.eclipse.emf.parsley.dsl.model.ModelPackage
import org.eclipse.emf.parsley.dsl.model.ViewSpecification
import org.eclipse.emf.parsley.dsl.model.WithExtendsClause
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.validation.Check

import static extension org.eclipse.emf.parsley.dsl.validation.EmfParsleyDslExpectedSuperTypes.*

//import org.eclipse.xtext.validation.Check

/**
 * Custom validation rules. 
 *
 * see http://www.eclipse.org/Xtext/documentation.html#validation
 */
class EmfParsleyDslValidator extends AbstractEmfParsleyDslValidator {

	public static val TYPE_MISMATCH = "org.eclipse.emf.parsley.dsl.TypeMismatch";

	@Inject EmfParsleyDslTypeSystem typeSystem

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
		if (withExtendsClause.getExtendsClause() != null) {
			checkType(withExtendsClause.extendsClause, 
				withExtendsClause.extendsClause.superType, withExtendsClause.expectedSupertype, 
				ModelPackage.Literals.EXTENDS_CLAUSE__SUPER_TYPE)
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
}
