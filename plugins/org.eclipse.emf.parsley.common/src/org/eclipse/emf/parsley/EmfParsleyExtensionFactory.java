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
package org.eclipse.emf.parsley;

import static com.google.inject.Guice.createInjector;
import org.eclipse.emf.parsley.runtime.ui.AbstractGuiceAwareExecutableExtensionFactory;

import org.osgi.framework.Bundle;

import com.google.inject.Injector;

/**
 * @author Lorenzo Bettini
 * 
 */
public class EmfParsleyExtensionFactory extends
		AbstractGuiceAwareExecutableExtensionFactory {

	/**
	 * Remember to override this method in your plugin, if you plan to create an
	 * instance of a class of your plugin (otherwise the classloader will not
	 * find it); if in your plugin you use guice only to inject your specific
	 * implementation into an instance of a class from this very plugin, then
	 * you do not need to override this method.
	 * 
	 * @see org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory#getBundle()
	 */
	@Override
	protected Bundle getBundle() {
		return EmfParsleyActivator.getDefault().getBundle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory
	 * #getInjector()
	 */
	@Override
	protected Injector getInjector() {
		return createInjector(getModule());
	}

	protected EmfParsleyGuiceModule getModule() {
		return new EmfParsleyGuiceModule(EmfParsleyActivator.getDefault());
	}

}
