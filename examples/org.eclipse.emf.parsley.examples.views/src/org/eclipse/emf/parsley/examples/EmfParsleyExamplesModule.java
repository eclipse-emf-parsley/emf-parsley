/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
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
