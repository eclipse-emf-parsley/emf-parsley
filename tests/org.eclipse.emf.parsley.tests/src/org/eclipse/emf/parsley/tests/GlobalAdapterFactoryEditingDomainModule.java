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
package org.eclipse.emf.parsley.tests;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.parsley.edit.domain.GlobalAdapterFactoryEditingDomainProvider;
import org.eclipse.emf.parsley.resource.ResourceManager;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsFactory;

import com.google.inject.Provider;

public class GlobalAdapterFactoryEditingDomainModule extends
		EmfParsleyGuiceModuleForTesting {
	protected static class TestResourceManager extends ResourceManager {
		public void initialize(Resource resource) {
			resource.getContents().add(
					TestmodelsFactory.eINSTANCE.createClassWithName());
		}
	}

	@Override
	public Class<? extends Provider<AdapterFactoryEditingDomain>> provideAdapterFactoryEditingDomain() {
		return GlobalAdapterFactoryEditingDomainProvider.class;
	}

	@Override
	public Class<? extends ResourceManager> bindResourceManager() {
		return TestResourceManager.class;
	}
}