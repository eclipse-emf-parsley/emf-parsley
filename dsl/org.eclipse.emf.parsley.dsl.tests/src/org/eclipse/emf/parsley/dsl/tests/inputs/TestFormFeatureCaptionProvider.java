package org.eclipse.emf.parsley.dsl.tests.inputs;

import org.eclipse.emf.parsley.inject.parameters.FormToolkitParameter;
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider;

import com.google.inject.Inject;

public class TestFormFeatureCaptionProvider extends FormFeatureCaptionProvider {

	@Inject
	public TestFormFeatureCaptionProvider(FormToolkitParameter param) {
		super(param);
	}

}
