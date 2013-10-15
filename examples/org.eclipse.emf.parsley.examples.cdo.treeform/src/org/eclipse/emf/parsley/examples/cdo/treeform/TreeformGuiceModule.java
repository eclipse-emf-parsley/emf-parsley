package org.eclipse.emf.parsley.examples.cdo.treeform;

import org.eclipse.emf.parsley.examples.cdo.treeform.customizations.TreeformCDOEmptyResourceInitializer;
import org.eclipse.emf.parsley.resource.EmptyResourceInitializer;
import org.eclipse.ui.plugin.AbstractUIPlugin;


public class TreeformGuiceModule extends EmfParsleyGuiceModuleGen {

	public TreeformGuiceModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@Override
	public Class<? extends EmptyResourceInitializer> bindEmptyResourceInitializer() {
		return TreeformCDOEmptyResourceInitializer.class;
	}

}
