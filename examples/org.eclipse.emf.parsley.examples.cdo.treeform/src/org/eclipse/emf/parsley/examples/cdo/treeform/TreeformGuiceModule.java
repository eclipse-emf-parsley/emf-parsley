package org.eclipse.emf.parsley.examples.cdo.treeform;

import org.eclipse.emf.parsley.cdo.CDOSessionManager;
import org.eclipse.emf.parsley.examples.cdo.treeform.customizations.TreeformCDOEmptyResourceInitializer;
import org.eclipse.emf.parsley.examples.cdo.treeform.customizations.TreeformCDOSessionManager;
import org.eclipse.emf.parsley.resource.EmptyResourceInitializer;
import org.eclipse.ui.plugin.AbstractUIPlugin;


public class TreeformGuiceModule extends EmfComponentsGuiceModuleGen {

	public TreeformGuiceModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@Override
	public Class<? extends EmptyResourceInitializer> bindEmptyResourceInitializer() {
		return TreeformCDOEmptyResourceInitializer.class;
	}
	
	@Override
	public Class<? extends CDOSessionManager> bindCDOSessionManager() {
		return TreeformCDOSessionManager.class;
	}
}
