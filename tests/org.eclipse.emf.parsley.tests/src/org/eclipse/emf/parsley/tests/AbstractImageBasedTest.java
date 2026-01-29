/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.eclipse.emf.parsley.runtime.ui.IImageHelper;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.swt.graphics.Image;
import org.junit.Rule;

public abstract class AbstractImageBasedTest extends AbstractEmfParsleyShellBasedTest {

	public static final String TEST_IMAGE = "test_image.png";

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	protected AdapterFactoryLabelProvider getDelegateLabelProvider() {
		return getOrCreateInjector().getInstance(AdapterFactoryLabelProvider.class);
	}

	protected IImageHelper getImageHelper() {
		return getOrCreateInjector().getInstance(IImageHelper.class);
	}

	protected Image loadTestImage() {
		return getImageHelper().getImage(TEST_IMAGE);
	}

}
