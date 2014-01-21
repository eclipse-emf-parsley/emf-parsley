/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
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