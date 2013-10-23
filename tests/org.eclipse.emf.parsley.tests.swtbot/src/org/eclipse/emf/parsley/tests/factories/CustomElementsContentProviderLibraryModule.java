package org.eclipse.emf.parsley.tests.factories;


import org.eclipse.emf.parsley.tests.providers.CustomElementsLibraryViewerContentProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public final class CustomElementsContentProviderLibraryModule extends
		CustomLibraryModule {
	public CustomElementsContentProviderLibraryModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@Override
	public Class<? extends IContentProvider> bindIContentProvider() {
		return CustomElementsLibraryViewerContentProvider.class;
	}
}