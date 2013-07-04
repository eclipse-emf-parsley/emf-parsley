package org.eclipse.emf.parsley.examples.firstexample;


import org.eclipse.emf.parsley.examples.firstexample.EmfComponentsGuiceModuleGen;

import org.eclipse.emf.parsley.resource.EmptyResourceInitializer;
import org.eclipse.ui.plugin.AbstractUIPlugin;


public class FirstexampleGuiceModule extends EmfComponentsGuiceModuleGen {

	public FirstexampleGuiceModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	public Class<? extends EmptyResourceInitializer> bindEmptyResourceInitializer() {
		return LibraryEmptyResourceInitializer.class;
	}
}
