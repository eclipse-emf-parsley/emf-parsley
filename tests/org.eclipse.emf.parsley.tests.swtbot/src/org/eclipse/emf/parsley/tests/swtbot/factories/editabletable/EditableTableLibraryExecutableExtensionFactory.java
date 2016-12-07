/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.swtbot.factories.editabletable;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.tests.swtbot.activator.EmfParsleySwtBotTestsActivator;
import org.eclipse.emf.parsley.tests.swtbot.factories.CustomLibraryModule;
import org.eclipse.emf.parsley.tests.swtbot.factories.EmfParsleyTestsExecutableExtensionFactory;
import org.eclipse.emf.parsley.viewers.TableViewerColumnBuilder;
import org.eclipse.emf.parsley.viewers.TableViewerEditableColumnBuilder;

/**
 * Uses an editable table
 * 
 * @author Francesco Guidieri
 * 
 */
public class EditableTableLibraryExecutableExtensionFactory extends EmfParsleyTestsExecutableExtensionFactory {

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return new CustomLibraryModule(EmfParsleySwtBotTestsActivator.getDefault()) {

			@Override
			public Class<? extends TableViewerColumnBuilder> bindTableViewerColumnBuilder() {
				return TableViewerEditableColumnBuilder.class;
			}

		};
	}

}
