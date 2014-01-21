/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.validation

import com.google.inject.Inject
import org.eclipse.xtext.validation.Check
import org.eclipse.emf.parsley.dsl.model.ViewSpecification
import org.eclipse.emf.parsley.dsl.model.ModelPackage
import org.eclipse.emf.parsley.dsl.model.EmfFeatureAccess
import org.eclipse.emf.parsley.dsl.model.Module

//import org.eclipse.xtext.validation.Check

/**
 * Custom validation rules. 
 *
 * see http://www.eclipse.org/Xtext/documentation.html#validation
 */
class EmfParsleyDslValidator extends AbstractEmfParsleyDslValidator {

	public static val NOT_I_VIEW_PART = "org.eclipse.emf.parsley.dsl.NotIViewPart";

	public static val NOT_EOBJECT = "org.eclipse.emf.parsley.dsl.NotEObject";

	public static val NOT_EMFCOMPONENTS_MODULE = "org.eclipse.emf.parsley.dsl.NotEmfParsleyGuiceModule";

	@Inject EmfParsleyDslTypeSystem typeSystem

	@Check
	def void checkViewSpecification(ViewSpecification viewSpecification) {
		if (viewSpecification.getType() != null
				&& !typeSystem.isViewPart(viewSpecification.getType(),
						viewSpecification)) {
			error("Must be an IViewPart",
					ModelPackage.Literals.VIEW_SPECIFICATION__TYPE,
					NOT_I_VIEW_PART);
		}
	}

	@Check
	def void checkEmfFeatureAccess(EmfFeatureAccess emfFeatureAccess) {
		if (emfFeatureAccess.getParameterType() != null
				&& !typeSystem.isEObject(emfFeatureAccess.getParameterType(),
						emfFeatureAccess)) {
			error("Must be an EObject derived class",
					ModelPackage.Literals.EMF_FEATURE_ACCESS__PARAMETER_TYPE,
					NOT_EOBJECT);
		}
	}

	@Check
	def void checkModuleExtends(Module module) {
		if (module.getExtendsClause() != null
				&& module.getExtendsClause().getSuperType() != null
				&& !typeSystem.isEmfParsleyGuiceModule(module
						.getExtendsClause().getSuperType(), module)) {
			error("Must be an EmfParsleyGuiceModule derived class",
					module.getExtendsClause(),
					ModelPackage.Literals.EXTENDS_CLAUSE__SUPER_TYPE,
					NOT_EMFCOMPONENTS_MODULE);
		}
	}
}
