package org.eclipse.emf.parsley.dsl.validation;

import org.eclipse.emf.parsley.dsl.model.EmfFeatureAccess;
import org.eclipse.emf.parsley.dsl.model.ModelPackage;
import org.eclipse.emf.parsley.dsl.model.Module;
import org.eclipse.emf.parsley.dsl.model.ViewSpecification;
import org.eclipse.xtext.validation.Check;

import com.google.inject.Inject;
 

public class EmfParsleyDslJavaValidator extends AbstractEmfParsleyDslJavaValidator {

	public static final String NOT_I_VIEW_PART = "org.eclipse.emf.parsley.dsl.NotIViewPart";

	public static final String NOT_EOBJECT = "org.eclipse.emf.parsley.dsl.NotEObject";

	public static final String NOT_EMFCOMPONENTS_MODULE = "org.eclipse.emf.parsley.dsl.NotEmfParsleyGuiceModule";

	@Inject
	private EmfParsleyDslTypeSystem typeSystem;

	@Check
	public void checkViewSpecification(ViewSpecification viewSpecification) {
		if (viewSpecification.getType() != null
				&& !typeSystem.isViewPart(viewSpecification.getType(),
						viewSpecification)) {
			error("Must be an IViewPart",
					ModelPackage.Literals.VIEW_SPECIFICATION__TYPE,
					NOT_I_VIEW_PART);
		}
	}

	@Check
	public void checkEmfFeatureAccess(EmfFeatureAccess emfFeatureAccess) {
		if (emfFeatureAccess.getParameterType() != null
				&& !typeSystem.isEObject(emfFeatureAccess.getParameterType(),
						emfFeatureAccess)) {
			error("Must be an EObject derived class",
					ModelPackage.Literals.EMF_FEATURE_ACCESS__PARAMETER_TYPE,
					NOT_EOBJECT);
		}
	}

	@Check
	public void checkModuleExtends(Module module) {
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
