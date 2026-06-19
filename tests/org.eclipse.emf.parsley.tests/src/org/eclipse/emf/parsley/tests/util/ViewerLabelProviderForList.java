package org.eclipse.emf.parsley.tests.util;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;

import com.google.inject.Inject;

public class ViewerLabelProviderForList extends ViewerLabelProvider {
	@Inject
	public ViewerLabelProviderForList(final AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}

	public String text(final List<ClassWithName> l) {
		return l.stream().map(ClassWithName::getName).collect(Collectors.joining(", "));
	}
}
