This project contains empty stub Java types (interfaces and classes) that are used ONLY
by the DSL when running in standalone mode, i.e., through the xtext-maven-plugin.

These are the types that are referred by our DSL validation phase
(org.eclipse.emf.parsley.dsl.validation.EmfParsleyDslExpectedSuperTypes)
and generation phase
(org.eclipse.emf.parsley.dsl.jvmmodel.EmfParsleyDslJvmModelInferrer).

In particular, they can also be the supertypes (superclass or implemented interface) of
our of the classes in the EMF Parsley core bundle.
For example, org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider implements
org.eclipse.jface.viewers.ILabelProvider thus the DSL compiler needs to find and load the class
org.eclipse.jface.viewers.ILabelProvider even if it does not use it.

These types are part of eclipse ui bundles that are not available as Maven artifacts.

DO NOT USE THIS BUNDLE FOR ANYTHING ELSE.
