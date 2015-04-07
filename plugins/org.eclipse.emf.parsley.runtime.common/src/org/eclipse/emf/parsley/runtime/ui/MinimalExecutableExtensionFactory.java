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
package org.eclipse.emf.parsley.runtime.ui;

import org.eclipse.emf.parsley.runtime.service.AbstractGenericModule;

import com.google.inject.Injector;
import static com.google.inject.Guice.createInjector;

/**
 * @author Lorenzo Bettini
 *
 */
public abstract class MinimalExecutableExtensionFactory extends
		AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Injector getInjector() {
		return createInjector(getModule());
	}

	protected abstract AbstractGenericModule getModule();

}
