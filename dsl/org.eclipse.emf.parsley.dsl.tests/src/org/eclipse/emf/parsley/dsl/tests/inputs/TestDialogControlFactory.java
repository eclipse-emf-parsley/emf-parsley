package org.eclipse.emf.parsley.dsl.tests.inputs;

import org.eclipse.emf.parsley.composite.DialogControlFactory;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameter;
import org.eclipse.emf.parsley.inject.parameters.EObjectParameter;

import com.google.inject.Inject;

public class TestDialogControlFactory extends DialogControlFactory {

	@Inject
	public TestDialogControlFactory(CompositeParameter compositeParameter, EObjectParameter eObjectParameter) {
		super(compositeParameter, eObjectParameter);
	}

}
