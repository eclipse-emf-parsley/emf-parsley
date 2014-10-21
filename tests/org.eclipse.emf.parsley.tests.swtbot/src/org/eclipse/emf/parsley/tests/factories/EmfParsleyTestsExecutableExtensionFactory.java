/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.factories;


import org.eclipse.emf.parsley.EmfParsleyExtensionFactory;
import org.eclipse.emf.parsley.tests.EmfParsleySwtBotTestsActivator;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

/**
 * Uses a custom label provider
 * 
 * @author Lorenzo Bettini
 * 
 */
public class EmfParsleyTestsExecutableExtensionFactory extends
		EmfParsleyExtensionFactory {

	/**
	 * We override it since we use guice to load a class from this very plugin.
	 * 
	 * @see org.eclipse.emf.parsley.EmfParsleyExtensionFactory#getBundle()
	 */
	@Override
	protected Bundle getBundle() {
		return EmfParsleySwtBotTestsActivator.getDefault().getBundle();
	}

	@Override
	public Injector getInjector() {
		return super.getInjector();
	}

}
