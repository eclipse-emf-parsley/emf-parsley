/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.ui;

import static com.google.inject.Guice.createInjector;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.inject.Injector;

/**
 * An abstract activator useful for wizards to be used as the base class for
 * generated Java classes.
 *
 * @author Lorenzo Bettini
 *
 */
public abstract class EmfParsleyAbstractActivator extends AbstractUIPlugin {

	// the singleton Injector instance for this plugin
	private Injector injector;

	/**
	 * The constructor
	 */
	public EmfParsleyAbstractActivator() {
	}

	/**
	 * Returns the singleton Injector for this plugin
	 *
	 * @return the singleton Injector for this plugin
	 */
	public Injector getInjector() {
		if (injector == null) {
			injector = createInjector(createModule());
		}
		return injector;
	}

	/**
	 * Creates the EmfParsleyGuiceModule for this this plugin
	 *
	 * @return the EmfParsleyGuiceModule for this this plugin
	 */
	protected abstract EmfParsleyGuiceModule createModule();
}
