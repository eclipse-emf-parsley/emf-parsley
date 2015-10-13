/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.factories;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.edit.action.EditingMenuBuilder;
import org.eclipse.emf.parsley.tests.EmfParsleyGuiceModuleWithConfigurator;
import org.eclipse.emf.parsley.tests.EmfParsleySwtBotTestsActivator;
import org.eclipse.emf.parsley.tests.menu.CustomLibraryMenuBuilder;

/**
 * Uses a custom menu builder
 * 
 * @author Lorenzo Bettini
 * 
 */
public class CustomMenuBuilderLibraryExecutableExtensionFactory extends
		EmfParsleyTestsExecutableExtensionFactory {

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return new EmfParsleyGuiceModuleWithConfigurator(EmfParsleySwtBotTestsActivator.getDefault()) {

			@Override
			public Class<? extends EditingMenuBuilder> bindEditingMenuBuilder() {
				return CustomLibraryMenuBuilder.class;
			}

		};
	}

}
