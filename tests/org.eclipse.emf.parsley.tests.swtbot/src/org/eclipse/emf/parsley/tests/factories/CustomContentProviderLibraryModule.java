package org.eclipse.emf.parsley.tests.factories;


import org.eclipse.emf.parsley.tests.providers.CustomLibraryViewerContentProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public final class CustomContentProviderLibraryModule extends
		CustomLibraryModule {
	public CustomContentProviderLibraryModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@Override
	public Class<? extends IContentProvider> bindIContentProvider() {
		return CustomLibraryViewerContentProvider.class;
	}
}