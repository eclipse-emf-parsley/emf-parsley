package org.eclipse.emf.parsley.dsl.tests.inputs;

import org.eclipse.emf.parsley.composite.FormControlFactory;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameter;
import org.eclipse.emf.parsley.inject.parameters.EObjectParameter;
import org.eclipse.emf.parsley.inject.parameters.FormToolkitParameter;

import com.google.inject.Inject;

public class TestFormControlFactory extends FormControlFactory {

	@Inject
	public TestFormControlFactory(CompositeParameter compositeParameter, EObjectParameter eObjectParameter,
			FormToolkitParameter formToolkitParameter) {
		super(compositeParameter, eObjectParameter, formToolkitParameter);
	}

}
