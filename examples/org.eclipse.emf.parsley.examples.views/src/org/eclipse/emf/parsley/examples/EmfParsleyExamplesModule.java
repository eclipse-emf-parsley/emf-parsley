package org.eclipse.emf.parsley.examples;


import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.builders.TableViewerColumnBuilder;
import org.eclipse.emf.parsley.builders.TableViewerEditableColumnBuilder;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class EmfParsleyExamplesModule extends EmfParsleyGuiceModule {

	public EmfParsleyExamplesModule(AbstractUIPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public Class<? extends TableViewerColumnBuilder> bindTableViewerColumnBuilder() {
		return TableViewerEditableColumnBuilder.class;
	}


}
