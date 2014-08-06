package org.eclipse.emf.parsley.dsl.tests.inputs;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;

public class TestLabelProvider extends ViewerLabelProvider {

	public TestLabelProvider(AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}

}
