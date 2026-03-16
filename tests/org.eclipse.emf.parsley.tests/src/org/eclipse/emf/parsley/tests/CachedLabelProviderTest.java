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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.emf.parsley.composite.CachedLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CachedLabelProviderTest extends AbstractImageBasedTest {

	private CachedLabelProvider labelProvider;
	private ILabelProvider delegate;
	private static final String TEST_VAL = "TEST";

	@Before
	public void setupLabelProvider() {
		delegate = mock(ILabelProvider.class);
		Image mockImage = mock(Image.class);
		when(delegate.getText(any())).thenReturn(TEST_VAL);
		when(delegate.getImage(any())).thenReturn(mockImage);
		labelProvider = new CachedLabelProvider(delegate);
	}

	@After
	public void disposeLabelProvider() {
		labelProvider.dispose();
	}

	@Test
	public void testGetText() {
		assertEquals(TEST_VAL, labelProvider.getText(fixtures.getClassForControlsInstance()));
		assertEquals(TEST_VAL, labelProvider.getText(fixtures.getClassForControlsInstance()));
		verify(delegate, times(1)).getText(any());
	}

	@Test
	public void testGetImage() {
		assertNotNull(labelProvider.getImage(fixtures.getClassForControlsInstance()));
		assertNotNull(labelProvider.getImage(fixtures.getClassForControlsInstance()));
		verify(delegate, times(1)).getImage(any());
	}

	@Test
	public void testIsValueProperty() {
		assertFalse(labelProvider.isLabelProperty(fixtures.getClassForControlsInstance(), "foo"));
		verify(delegate, times(1)).isLabelProperty(fixtures.getClassForControlsInstance(), "foo");
	}

	@Test
	public void testListeners() {
		ILabelProviderListener listener = mock(ILabelProviderListener.class);
		labelProvider.addListener(listener);
		labelProvider.removeListener(listener);
		verify(delegate, times(1)).addListener(listener);
		verify(delegate, times(1)).removeListener(listener);
	}
}
