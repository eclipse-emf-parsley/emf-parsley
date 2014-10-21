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
package org.eclipse.emf.parsley.tests;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.tests.views.TestConfigurator;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * The base module for all the tests that want to use a {@link Configurator}.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class EmfParsleyGuiceModuleWithConfigurator extends
		EmfParsleyGuiceModule {

	public EmfParsleyGuiceModuleWithConfigurator(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@Override
	public Class<? extends Configurator> bindConfigurator() {
		return TestConfigurator.class;
	}
}
