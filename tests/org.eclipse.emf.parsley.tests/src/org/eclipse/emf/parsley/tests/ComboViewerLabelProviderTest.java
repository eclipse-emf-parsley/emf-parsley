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

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.parsley.ui.provider.ComboViewerLabelProvider;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Inject;

public class ComboViewerLabelProviderTest extends AbstractImageBasedTest {

	@Inject
	private ComboViewerLabelProvider labelProvider;

	@Before
	public void setupLabelProvider() {
		getOrCreateInjector().injectMembers(this);
	}

	@Test
	public void testDefaultGetText() {
		assertEquals("Class For Controls", labelProvider.getText(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testGetTextWithElementForSettingReferenceToNull() {
		assertEquals("", labelProvider.getText(SetCommand.UNSET_VALUE));
	}

}
