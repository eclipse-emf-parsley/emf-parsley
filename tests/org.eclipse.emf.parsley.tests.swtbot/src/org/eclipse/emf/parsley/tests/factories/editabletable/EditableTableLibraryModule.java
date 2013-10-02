package org.eclipse.emf.parsley.tests.factories.editabletable;
import org.eclipse.emf.parsley.builders.TableViewerColumnBuilder;
import org.eclipse.emf.parsley.builders.TableViewerEditableColumnBuilder;
import org.eclipse.emf.parsley.tests.factories.CustomLibraryModule;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public final class EditableTableLibraryModule extends
		CustomLibraryModule {
	
	public EditableTableLibraryModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@Override
	public Class<? extends TableViewerColumnBuilder> bindTableViewerColumnBuilder() {
		return TableViewerEditableColumnBuilder.class;
	}
	
}