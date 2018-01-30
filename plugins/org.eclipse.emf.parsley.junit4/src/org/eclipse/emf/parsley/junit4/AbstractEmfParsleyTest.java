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
package org.eclipse.emf.parsley.junit4;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule;
import org.junit.Before;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * Base class for Junit tests and EMF Parsley.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public abstract class AbstractEmfParsleyTest {

	/**
	 * This will be created on demand using the method getOrCreateInjector
	 */
	private Injector injector = null;

	@Before
	public void setup() {
		this.injector = null;
	}

	public <T extends Object> T injectMembers(final T o) {
		this.getOrCreateInjector().injectMembers(o);
		return o;
	}

	public <T extends Object> T injectMembers(final T o, Module module) {
		createInjector(module).injectMembers(o);
		return o;
	}

	public Injector getOrCreateInjector() {
		if ((this.injector == null)) {
			this.injector = createInjector();
		}
		return this.injector;
	}

	/**
	 * Creates an {@link Injector} using {@link EmfParsleyJavaGuiceModule}, which
	 * is appropriate for Junit tests.
	 */
	protected Injector createInjector() {
		return createInjector(new EmfParsleyJavaGuiceModule());
	}

	/**
	 * Creates an {@link Injector} with the specified {@link Module}.
	 * 
	 * @param module
	 * @return
	 */
	protected Injector createInjector(Module module) {
		return Guice.createInjector(module);
	}

	public AdapterFactory getAdapterFactory() {
		return this.getOrCreateInjector().getInstance(AdapterFactory.class);
	}

}
