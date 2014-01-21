/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.factories;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.edit.domain.GlobalAdapterFactoryEditingDomainProvider;
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory;
import org.eclipse.emf.parsley.resource.EmptyResourceInitializer;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.inject.Provider;

public class GlobalAdapterFactoryEditingDomainModule extends
		EmfParsleyGuiceModule {
	protected static class TestEmptyResourceInitializer extends
			EmptyResourceInitializer {
		public void initialize(Resource resource) {
			resource.getContents().add(
					EXTLibraryFactory.eINSTANCE.createLibrary());
		}
	}

	public GlobalAdapterFactoryEditingDomainModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@Override
	public Class<? extends Provider<AdapterFactoryEditingDomain>> provideAdapterFactoryEditingDomain() {
		return GlobalAdapterFactoryEditingDomainProvider.class;
	}

	@Override
	public Class<? extends EmptyResourceInitializer> bindEmptyResourceInitializer() {
		return TestEmptyResourceInitializer.class;
	}
}