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

import com.google.inject.Inject
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule
import org.eclipse.emf.parsley.tests.DelegatingColumnLabelProviderTest.CustomViewerLabelProvider
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider
import org.eclipse.emf.parsley.ui.provider.DelegatingColumnLabelProvider
import org.eclipse.jface.resource.JFaceResources
import org.eclipse.jface.viewers.LabelProvider
import org.eclipse.swt.SWT
import org.eclipse.swt.graphics.Font
import org.eclipse.swt.widgets.Shell
import org.junit.Before
import org.junit.Test

import static extension org.eclipse.emf.parsley.junit4.ui.util.ImageTester.*
import static extension org.junit.Assert.*

class DelegatingColumnLabelProviderTest extends AbstractImageBasedTest {

	static class CustomViewerLabelProvider extends ViewerLabelProvider {

		@Inject
		var Shell shell

		new() {
			super(null)
		}

		def text(ClassForControls e) {
			"Test"
		}

		def image(ClassForControls e) {
			TEST_IMAGE
		}

		def font(ClassForControls e) {
			new Font(shell.display, JFaceResources.DEFAULT_FONT, 14, SWT.BOLD)
		}

		def foreground(ClassForControls e) {
			shell.display.getSystemColor(SWT.COLOR_BLUE)
		}

		def background(ClassForControls e) {
			shell.display.getSystemColor(SWT.COLOR_BLUE)
		}
	}

	@Inject
	var DelegatingColumnLabelProvider labelProvider

	@Before
	def void setupLabelProvider() {
		getOrCreateInjector.injectMembers(this)
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
	def void testCustomText() {
		val customLabelProvider = withCustomLabelProvider
		"Test".assertEquals(customLabelProvider.getText(classForControlsInstance))
	}

	@Test
	def void testDefaultGetImage() {
		labelProvider.getImage(classForControlsInstance) => [
			assertNotNull
			getDefaultEMFImageForClassForControls.assertImageIs(it)
		]
	}

	@Test
	def void testCustomImageAsString() {
		val customLabelProvider = withCustomLabelProvider
		customLabelProvider.getImage(classForControlsInstance) => [
			assertNotNull
			loadTestImage.assertImageIs(it)
		]
	}

	@Test
	def void testGetFontNull() {
		labelProvider.getFont(null).assertNull
	}

	@Test
	def void testDefaultGetFont() {
		labelProvider.getFont(classForControlsInstance).assertNull
	}

	@Test
	def void testDefaultGetFontWithBaseLabelProvider() {
		withBaseLabelProvider.getFont(classForControlsInstance).assertNull
	}

	@Test
	def void testCustomFont() {
		val customLabelProvider = withCustomLabelProvider
		customLabelProvider.getFont(classForControlsInstance) => [
			assertNotNull
		]
	}

	@Test
	def void testGetForegroundNull() {
		labelProvider.getForeground(null).assertNull
	}

	@Test
	def void testDefaultGetForeground() {
		labelProvider.getForeground(classForControlsInstance).assertNull
	}

	@Test
	def void testDefaultGetForegroundWithBaseLabelProvider() {
		withBaseLabelProvider.getForeground(classForControlsInstance).assertNull
	}

	@Test
	def void testCustomForeground() {
		val customLabelProvider = withCustomLabelProvider
		customLabelProvider.getForeground(classForControlsInstance) => [
			assertNotNull
		]
	}

	@Test
	def void testGetBackgroundNull() {
		labelProvider.getBackground(null).assertNull
	}

	@Test
	def void testDefaultGetBackground() {
		labelProvider.getBackground(classForControlsInstance).assertNull
	}

	@Test
	def void testDefaultGetBackgroundWithBaseLabelProvider() {
		withBaseLabelProvider.getBackground(classForControlsInstance).assertNull
	}

	@Test
	def void testCustomBackground() {
		val customLabelProvider = withCustomLabelProvider
		customLabelProvider.getBackground(classForControlsInstance) => [
			assertNotNull
		]
	}

	private def getDefaultEMFImageForClassForControls() {
		getEMFImageFromObject(getEMFImage(testFactory.createClassForControls))
	}

	private def withCustomLabelProvider() {
		createInjector(new EmfParsleyJavaGuiceModule() {
			
			override bindILabelProvider() {
				CustomViewerLabelProvider
			}
			
			def bindShell() {
				shell
			}
			
		}).getInstance(DelegatingColumnLabelProvider)
	}

	/**
	 * The injected ILabelProvider is not an IColorProvider nor an IFontProvider
	 */
	private def withBaseLabelProvider() {
		createInjector(new EmfParsleyJavaGuiceModule() {
			
			override bindILabelProvider() {
				LabelProvider
			}

		}).getInstance(DelegatingColumnLabelProvider)
	}

}
