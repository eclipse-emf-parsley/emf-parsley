package org.eclipse.emf.parsley.examples.mail.unifiedfoldersview;

import org.eclipse.emf.parsley.examples.mail.unifiedfoldersview.edit.ui.UnifiedFoldersContentProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;


public class UnifiedfoldersviewGuiceModule extends EmfParsleyGuiceModuleGen {

	public UnifiedfoldersviewGuiceModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@Override
	public Class<? extends IContentProvider> bindIContentProvider() {
		return UnifiedFoldersContentProvider.class;
	}
}
