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
package org.eclipse.emf.parsley.tests

import org.eclipse.emf.parsley.composite.CachedLabelProvider
import org.eclipse.jface.viewers.ILabelProvider
import org.eclipse.jface.viewers.ILabelProviderListener
import org.junit.After
import org.junit.Before
import org.junit.Test

import static org.mockito.Mockito.*

import static extension org.junit.Assert.*
import org.eclipse.swt.graphics.Image

class CachedLabelProviderTest extends AbstractImageBasedTest {

	var CachedLabelProvider labelProvider
	var ILabelProvider delegate
	val TEST_VAL = "TEST"

	@Before
	def void setupLabelProvider() {
		delegate = mock(ILabelProvider)
		val Image mockImage = mock(Image)
		when(delegate.getText(any)).thenReturn(TEST_VAL)
		when(delegate.getImage(any)).thenReturn(mockImage)
		labelProvider = new CachedLabelProvider(delegate)
	}

	@After
	def void disposeLabelProvider() {
		labelProvider.dispose
	}

	@Test
	def void testGetText() {
		TEST_VAL.assertEquals(labelProvider.getText(classForControlsInstance))
		TEST_VAL.assertEquals(labelProvider.getText(classForControlsInstance))
		verify(delegate, times(1)).getText(any)
	}

	@Test
	def void testGetImage() {
		assertNotNull(labelProvider.getImage(classForControlsInstance))
		assertNotNull(labelProvider.getImage(classForControlsInstance))
		verify(delegate, times(1)).getImage(any)
	}

	@Test
	def void testIsValueProperty() {
		assertFalse(labelProvider.isLabelProperty(classForControlsInstance, "foo"))
		verify(delegate, times(1)).isLabelProperty(classForControlsInstance, "foo")
	}

	@Test
	def void testListeners() {
		val listener = mock(ILabelProviderListener)
		labelProvider.addListener(listener)
		labelProvider.removeListener(listener)
		verify(delegate, times(1)).addListener(listener)
		verify(delegate, times(1)).removeListener(listener)
	}
}
