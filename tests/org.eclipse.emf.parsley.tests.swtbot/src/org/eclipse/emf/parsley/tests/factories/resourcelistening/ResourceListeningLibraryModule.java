package org.eclipse.emf.parsley.tests.factories.resourcelistening;


import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.emf.parsley.tests.factories.CustomLibraryModule;
import org.eclipse.emf.parsley.views.resource.OservableResourceLoader;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public final class ResourceListeningLibraryModule extends
		CustomLibraryModule {
	
	public ResourceListeningLibraryModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@Override
	public Class<? extends ResourceLoader> bindResourceLoader() {
		return OservableResourceLoader.class;
	}
	
}