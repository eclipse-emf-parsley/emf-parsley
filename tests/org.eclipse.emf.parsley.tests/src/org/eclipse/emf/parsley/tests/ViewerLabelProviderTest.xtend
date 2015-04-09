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

import com.google.inject.Guice
import java.util.List
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider
import org.eclipse.jface.resource.ImageDescriptor
import org.eclipse.jface.viewers.ILabelProvider
import org.junit.Before
import org.junit.Test

import static extension org.eclipse.emf.parsley.junit4.ui.util.ImageTester.*
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
		"Class For Controls".assertEquals(labelProvider.getText(classForControlsInstance))
	}

	@Test
	def void testDefaultGetTextForFeatureMapEntry() {
		classForControlsInstance.featureMapEntries1 += createClassForFeatureMapEntry1("1")
		classForControlsInstance.featureMapEntries2 += createClassForFeatureMapEntry2("2")
		"Class For Feature Map Entry1 1".
			assertEquals(labelProvider.getText(classForControlsInstance.featureMapEntries.get(0)))
		"Class For Feature Map Entry2 2".
			assertEquals(labelProvider.getText(classForControlsInstance.featureMapEntries.get(1)))
	}

	@Test
	def void testDefaultGetTextForIterable() {
		testContainer.classesWithName => [
			it += createClassWithName("1")
			it += createClassWithName("2")
			it += createClassWithName("3")
		]
		"Class With Name 1, Class With Name 2, Class With Name 3".
			assertEquals(labelProvider.getText(testContainer.classesWithName))
	}

	@Test
	def void testDefaultGetTextForIterableTooLong() {
		setupContainerWith10Elems
		"Class With Name 0, Class With Name 1, Class With Name 2, Class With Name 3, Clas...".
			assertEquals(labelProvider.getText(testContainer.classesWithName))
	}
	

	@Test
	def void testDefaultGetTextForIterableWithCustomConstants() {
		setupContainerWith10Elems
		"Class With Name 0 - Class With Name 1 - Class With <continued>...".
		assertEquals(
			Guice.createInjector(
				new EmfParsleyGuiceModuleForTesting() {
					override valueIterableStringSeparator() {
						" - "
					}
					override valueIterableStringEllipses() {
						" <continued>..."
					}
					override valueIterableStringMaxLength() {
						50
					}
				}
			).getInstance(ILabelProvider)
			.getText(testContainer.classesWithName)
		)
	}

	@Test
	def void testCustomText() {
		val customLabelProvider = new ViewerLabelProvider(null) {
			def text(ClassForControls e) {
				"Test"
			}
		}.injectMembers
		"Test".assertEquals(customLabelProvider.getText(classForControlsInstance))
	}

	@Test
	def void testCustomTextForList() {
		setupContainerWith10Elems
		val customLabelProvider = new ViewerLabelProvider(null) {
			def text(List<ClassWithName> l) {
				l.map[name].join(",")
			}
		}.injectMembers
		"0,1,2,3,4,5,6,7,8,9".assertEquals(customLabelProvider.getText(testContainer.classesWithName))
	}

	@Test
	def void testGetImageNull() {
		labelProvider.getImage(null).assertNull
	}

	@Test
	def void testDefaultGetImage() {
		labelProvider.getImage(classForControlsInstance) => [
			assertNotNull
			getDefaultEMFImageForClassForControls.assertImageIs(it)
		]
	}

	@Test
	def void testDefaultGetImageForFeatureMapEntry() {
		classForControlsInstance.featureMapEntries1 += createClassForFeatureMapEntry1("1")
		labelProvider.getImage(classForControlsInstance.featureMapEntries.get(0)) => [
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
		customLabelProvider.getImage(classForControlsInstance) => [
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
		customLabelProvider.getImage(classForControlsInstance) => [
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
		customLabelProvider.getImage(classForControlsInstance) => [
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
		customLabelProvider.getImage(classForControlsInstance) => [
			assertNotNull
			getDefaultEMFImageForClassForControls.assertImageIs(it)
		]
	}

	private def setupContainerWith10Elems() {
		testContainer.classesWithName => [
			for (i : 0..<10) {
				it += createClassWithName("" + i)
			}
		]
	}
	
	private def getDefaultEMFImageForClassForControls() {
		getEMFImageFromObject(getEMFImage(testFactory.createClassForControls))
	}

	private def getDefaultEMFImageForClassForFeatureMapEntry1() {
		getEMFImageFromObject(getEMFImage(testFactory.createClassForFeatureMapEntry1))
	}

}
