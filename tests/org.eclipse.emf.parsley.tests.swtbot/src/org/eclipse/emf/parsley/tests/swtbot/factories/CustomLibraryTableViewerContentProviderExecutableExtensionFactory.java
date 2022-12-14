/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.swtbot.factories;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.edit.ui.provider.TableViewerContentProvider;
import org.eclipse.emf.parsley.tests.swtbot.EmfParsleyGuiceModuleWithConfigurator;
import org.eclipse.emf.parsley.tests.swtbot.activator.EmfParsleySwtBotTestsActivator;
import org.eclipse.emf.parsley.tests.swtbot.providers.CustomLibraryTableViewerContentProvider;

/**
 * Uses a custom table viewer content provider
 * 
 * @author Lorenzo Bettini
 * 
 */
public class CustomLibraryTableViewerContentProviderExecutableExtensionFactory
		extends EmfParsleyTestsExecutableExtensionFactory {

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return new EmfParsleyGuiceModuleWithConfigurator(EmfParsleySwtBotTestsActivator.getDefault()) {

			@Override
			public Class<? extends TableViewerContentProvider> bindTableViewerContentProvider() {
				return CustomLibraryTableViewerContentProvider.class;
			}

		};
	}

}
