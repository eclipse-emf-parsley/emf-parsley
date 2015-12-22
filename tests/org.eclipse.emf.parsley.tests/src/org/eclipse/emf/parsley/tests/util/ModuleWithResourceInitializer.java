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
package org.eclipse.emf.parsley.tests.util;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.resource.ResourceManager;
import org.eclipse.emf.parsley.tests.EmfParsleyGuiceModuleForTesting;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsFactory;

public class ModuleWithResourceInitializer extends
		EmfParsleyGuiceModuleForTesting {
	protected static class TestResourceManager extends ResourceManager {
		@Override
		public void initialize(Resource resource) {
			resource.getContents().add(
					TestmodelsFactory.eINSTANCE.createClassWithName());
		}
	}

	@Override
	public Class<? extends ResourceManager> bindResourceManager() {
		return TestResourceManager.class;
	}
}