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

import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.edit.EMFEditPlugin
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls
import org.eclipse.emf.parsley.tests.models.testmodels.TestEClass
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider
import org.eclipse.jface.viewers.ILabelProvider
import org.junit.Before
import org.junit.Test

import static org.eclipse.emf.parsley.tests.ui.util.TestImageHelper.*

import static extension org.eclipse.emf.parsley.tests.ui.util.ImageTester.*
import static extension org.junit.Assert.*
import org.eclipse.jface.resource.ImageDescriptor

class ViewerLabelProviderTest extends AbstractShellBasedTest {

	var protected ClassForControls eobj

	var protected TestEClass eobj2

	var ILabelProvider labelProvider
	
	val TEST_IMAGE = "test_image.png"

	@Before
	def void setupEObject() {
		eobj = testFactory.createClassForControls
		eobj2 = testFactory.createTestEClass
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
			getDefaultEMFImage.assertImageIs(it)
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
			getDefaultEMFImage.assertImageIs(it)
		]
	}

	def private getDelegateLabelProvider() {
		getOrCreateInjector.getInstance(AdapterFactoryLabelProvider)
	}
	
	private def loadTestImage() {
		loadImageFromLocalTest(TEST_IMAGE)
	}

	private def getDefaultEMFImage() {
		getEMFImageFromObject(getEMFImage(eobj))
	}

	def private getEMFImageFromObject(Object object) {
		return ExtendedImageRegistry.INSTANCE.getImage(object);
	}

	def private getEMFImage(EObject eObject) {
		val eClass = eObject.eClass();
		return URI.createURI(getEMFResourceLocator().getImage("full/obj16/Item").toString() + "#" + eClass.getName());
	}

	def private getEMFResourceLocator() {
		return EMFEditPlugin.INSTANCE;
	}
}
