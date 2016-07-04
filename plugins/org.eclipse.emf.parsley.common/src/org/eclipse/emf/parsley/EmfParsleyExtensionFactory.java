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

package org.eclipse.emf.parsley;

import static com.google.inject.Guice.createInjector;

import org.eclipse.emf.parsley.runtime.ui.AbstractGuiceAwareExecutableExtensionFactory;

import com.google.inject.Injector;

/**
 * Base class for executable extension factories, for example, for
 * the ones generated by the DSL.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class EmfParsleyExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	private Injector injector;

	@Override
	protected Injector getInjector() throws Exception {
		if (injector == null) {
			injector = createInjector(getModule());
		}
		return injector;
	}

	protected EmfParsleyGuiceModule getModule() throws Exception {
		return new EmfParsleyGuiceModule(EmfParsleyActivator.getDefault());
	}

}
