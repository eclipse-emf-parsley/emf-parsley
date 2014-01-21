/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.emf.parsley.tests.factories;

import org.eclipse.emf.parsley.tests.EmfParsleyTestsActivator;

import com.google.inject.Injector;

/**
 * Uses a custom label provider
 * 
 * @author Lorenzo Bettini
 * 
 */
public class CustomContentProviderLibraryExecutableExtensionFactory extends
		EmfParsleyTestsExecutableExtensionFactory {

	@Override
	public Injector getInjector() {
		return EmfParsleyTestsActivator.getDefault().getInjector(
				this.getClass());
	}

}
