package org.eclipse.emf.parsley.dsl.tests.inputs;

import org.eclipse.emf.parsley.inject.EStructuralFeatureParameter;
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider;

import com.google.inject.Inject;

public class TestTableColumnLabelProvider extends TableColumnLabelProvider {

	@Inject
	public TestTableColumnLabelProvider(EStructuralFeatureParameter param) {
		super(param);
	}

}
