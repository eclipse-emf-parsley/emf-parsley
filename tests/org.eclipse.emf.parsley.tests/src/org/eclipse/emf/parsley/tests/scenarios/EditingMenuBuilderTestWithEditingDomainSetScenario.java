/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.scenarios;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.tests.AbstractEditingMenuBuilderTest;

/**
 * In this scenario the EditingDomain is set from outside
 */
public class EditingMenuBuilderTestWithEditingDomainSetScenario extends AbstractEditingMenuBuilderTest {

	@Override
	protected Resource createResourceForTest() {
		return fixtures.createTestLibrayResourceAndInitialize();
	}

}
