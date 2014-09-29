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

import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider
import org.eclipse.jface.resource.ImageDescriptor
import org.eclipse.jface.viewers.ILabelProvider
import org.junit.Before
import org.junit.Test

import static extension org.eclipse.emf.parsley.tests.ui.util.ImageTester.*
import static extension org.junit.Assert.*

class ViewerLabelProviderTest extends AbstractImageBasedTest {

	var ILabelProvider labelProvider
	
	@Before
	def void setupLabelProvider() {
		labelProvider = getOrCreateInjector.getInstance(ILabelProvider)
	}

	@Test
	def void testGetTextNull() {
		"".assertEquals(labelProvider.getText(null))
	}

	@Test
	def void testDefaultGetText() {
		"Class For Controls".assertEquals(labelProvider.getText(eobj))
	}

	@Test
	def void testDefaultGetTextForFeatureMapEntry() {
		eobj.featureMapEntries1 += createClassForFeatureMapEntry1("1")
		eobj.featureMapEntries2 += createClassForFeatureMapEntry2("2")
		"Class For Feature Map Entry1 1".
			assertEquals(labelProvider.getText(eobj.featureMapEntries.get(0)))
		"Class For Feature Map Entry2 2".
			assertEquals(labelProvider.getText(eobj.featureMapEntries.get(1)))
	}

	@Test
	def void testCustomText() {
		val customLabelProvider = new ViewerLabelProvider(null) {
			def text(ClassForControls e) {
				"Test"
			}
		}.injectMembers
		"Test".assertEquals(customLabelProvider.getText(eobj))
	}

	@Test
	def void testGetImageNull() {
		labelProvider.getImage(null).assertNull
	}

	@Test
	def void testDefaultGetImage() {
		labelProvider.getImage(eobj) => [
			assertNotNull
			getDefaultEMFImageForClassForControls.assertImageIs(it)
		]
	}

	@Test
	def void testDefaultGetImageForFeatureMapEntry() {
		eobj.featureMapEntries1 += createClassForFeatureMapEntry1("1")
		labelProvider.getImage(eobj.featureMapEntries.get(0)) => [
			assertNotNull
			getDefaultEMFImageForClassForFeatureMapEntry1.assertImageIs(it)
		]
	}
	
	@Test
	def void testCustomImageAsString() {
		val customLabelProvider = new ViewerLabelProvider(null) {
			def image(ClassForControls e) {
				TEST_IMAGE
			}
		}.injectMembers
		customLabelProvider.getImage(eobj) => [
			assertNotNull
			loadTestImage.assertImageIs(it)
		]
	}

	@Test
	def void testCustomImageAsImage() {
		val customLabelProvider = new ViewerLabelProvider(null) {
			def image(ClassForControls e) {
				loadTestImage
			}
		}.injectMembers
		customLabelProvider.getImage(eobj) => [
			assertNotNull
			loadTestImage.assertImageIs(it)
		]
	}

	@Test
	def void testCustomImageAsImageDescriptor() {
		val customLabelProvider = new ViewerLabelProvider(null) {
			def image(ClassForControls e) {
				ImageDescriptor.createFromImage(loadTestImage)
			}
		}.injectMembers
		customLabelProvider.getImage(eobj) => [
			assertNotNull
			loadTestImage.assertImageIs(it)
		]
	}

	@Test
	def void testWrongCustomImageAsInteger() {
		val customLabelProvider = new ViewerLabelProvider(delegateLabelProvider) {
			def image(ClassForControls e) {
				0
			}
		}.injectMembers
		// this will default to EMF image
		customLabelProvider.getImage(eobj) => [
			assertNotNull
			getDefaultEMFImageForClassForControls.assertImageIs(it)
		]
	}

}