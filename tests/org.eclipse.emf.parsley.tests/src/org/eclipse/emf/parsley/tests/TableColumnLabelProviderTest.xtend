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

import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.parsley.EmfParsleyActivator
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule
import org.eclipse.emf.parsley.inject.EStructuralFeatureParameter
import org.eclipse.emf.parsley.junit4.util.LogAppenderTestRule
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls
import org.eclipse.emf.parsley.tests.models.testmodels.DerivedClass
import org.eclipse.emf.parsley.tests.util.ViewerLabelProviderForList
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider
import org.eclipse.jface.resource.ImageDescriptor
import org.eclipse.jface.resource.JFaceResources
import org.eclipse.swt.SWT
import org.eclipse.swt.graphics.Font
import org.junit.Rule
import org.junit.Test

import static extension org.eclipse.emf.parsley.junit4.ui.util.ImageTester.*
import static extension org.junit.Assert.*

class TableColumnLabelProviderTest extends AbstractImageBasedTest {

	@Rule public val LogAppenderTestRule logAppender = new LogAppenderTestRule(EmfParsleyActivator);

	@Test
	def void testGetTextNull() {
		"".assertEquals(tableColumnLabelProvider(testPackage.classForControls_StringFeature).getText(null))
	}

	@Test
	def void testDefaultGetText() {
		classForControlsInstance.stringFeature = "Test"
		"Test".assertEquals(
			tableColumnLabelProvider(testPackage.classForControls_StringFeature).getText(classForControlsInstance)
		)
	}

	@Test
	def void testGetTextUsesLabelProvider() {
		val prov = new TableColumnLabelProvider(param(testPackage.testContainer_ClassesWithName))
			.injectMembers(new EmfParsleyJavaGuiceModule() {
				override bindILabelProvider() {
					ViewerLabelProviderForList
				}
			})
		testContainer.classesWithName += createClassWithName("Test1")
		testContainer.classesWithName += createClassWithName("Test2")
		"Test1, Test2".assertEquals(prov.getText(testContainer))
	}

	@Test
	def void testGetTextWhenFeatureValueIsNullReturnsEmptyString() {
		classForControlsInstance.stringFeature = null
		"".assertEquals(
			tableColumnLabelProvider(testPackage.classForControls_StringFeature).getText(classForControlsInstance)
		)
	}

	@Test
	def void testGetTextWhenOjectClassDoesNotHaveFeature() {
		// testContainer, TestContainer, does not have the feature multiReferencFeature
		// and it simply takes the value of the feature with the same ID in the
		// EObject's class, which does not exist,
		// but the TableColumnLabelProvider gracefully defaults to an empty string
		"".assertEquals(
			tableColumnLabelProvider(testPackage.classForControls_MultiReferenceFeature).getText(testContainer)
		)
	}
	
	@Test
	def void testCustomGetTextWithAssertionError() {
		"".assertEquals(new TableColumnLabelProvider(param(testPackage.classForControls_StringFeature)) {
			override protected getFeatureValue(Object element) {
				throw new AssertionError("TEST") 
			}
		}.injectMembers.getText(classForControlsInstance))
		assertExceptionInLog()
	}

	@Test
	def void testCustomGetTextWithRuntimeException() {
		"".assertEquals(new TableColumnLabelProvider(param(testPackage.classForControls_StringFeature)) {
			override protected getFeatureValue(Object element) {
				throw new NullPointerException("TEST")
			}
		}.injectMembers.getText(classForControlsInstance))
		assertExceptionInLog()
	}

	@Test
	def void testCustomText() {
		val customProvider = new TableColumnLabelProvider(param(testPackage.classForControls_StringFeature)) {
			def text_ClassForControls_stringFeature(ClassForControls e) {
				"Test"
			}
		}.injectMembers
		"Test".assertEquals(customProvider.getText(classForControlsInstance))
	}

	@Test
	def void testCustomTextNotEObject() {
		val customProvider = new TableColumnLabelProvider(param(testPackage.classForControls_StringFeature)) {
			def text_ClassForControls_stringFeature(String s) {
				"Test"
			}
		}.injectMembers
		"Test".assertEquals(customProvider.getText("aString"))
	}

	@Test
	def void testCustomTextDerivedClassFeatureInDerivedClass() {
		val customProvider = new TableColumnLabelProvider(param(testPackage.derivedClass_DerivedClassFeature)) {
			def text_DerivedClass_derivedClassFeature(DerivedClass e) {
				"Test"
			}
		}.injectMembers
		"Test".assertEquals(customProvider.getText(derivedClassInstance))
	}

	@Test
	def void testCustomTextBaseClassFeatureInDerivedClass() {
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=480749
		val customProvider = new TableColumnLabelProvider(param(testPackage.baseClass_BaseClassFeature)) {
			def text_DerivedClass_baseClassFeature(DerivedClass e) {
				"Test"
			}
		}.injectMembers
		"Test".assertEquals(customProvider.getText(derivedClassInstance))
	}

	@Test
	def void testGetImageNull() {
		tableColumnLabelProvider(testPackage.testContainer_ClassesForControls).getImage(null).assertNull
	}

	@Test
	def void testDefaultGetImage() {
		tableColumnLabelProvider(testPackage.testContainer_ClassesForControls).
		getImage(testContainer) => [
			assertNull
		]
	}

	@Test
	def void testCustomImageAsString() {
		val customLabelProvider = new TableColumnLabelProvider(param(testPackage.classForControls_StringFeature)) {
			def image_ClassForControls_stringFeature(ClassForControls e) {
				TEST_IMAGE
			}
		}.injectMembers
		customLabelProvider.getImage(classForControlsInstance) => [
			assertNotNull
			loadTestImage.assertImageIs(it)
		]
	}

	@Test
	def void testCustomImageNotEObject() {
		val customProvider = new TableColumnLabelProvider(param(testPackage.classForControls_StringFeature)) {
			def image_ClassForControls_stringFeature(String s) {
				TEST_IMAGE
			}
		}.injectMembers
		customProvider.getImage("aString") => [
			assertNotNull
			loadTestImage.assertImageIs(it)
		]
	}

	@Test
	def void testCustomImageDerivedClassFeatureInDerivedClass() {
		val customProvider = new TableColumnLabelProvider(param(testPackage.derivedClass_DerivedClassFeature)) {
			def image_DerivedClass_derivedClassFeature(DerivedClass e) {
				TEST_IMAGE
			}
		}.injectMembers
		customProvider.getImage(derivedClassInstance) => [
			assertNotNull
			loadTestImage.assertImageIs(it)
		]
	}

	@Test
	def void testCustomImageBaseClassFeatureInDerivedClass() {
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=480749
		val customProvider = new TableColumnLabelProvider(param(testPackage.baseClass_BaseClassFeature)) {
			def image_DerivedClass_baseClassFeature(DerivedClass e) {
				TEST_IMAGE
			}
		}.injectMembers
		customProvider.getImage(derivedClassInstance) => [
			assertNotNull
			loadTestImage.assertImageIs(it)
		]
	}

	@Test
	def void testCustomImageAsImage() {
		val customLabelProvider = new TableColumnLabelProvider(param(testPackage.classForControls_StringFeature)) {
			def image_ClassForControls_stringFeature(ClassForControls e) {
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
		val customLabelProvider = new TableColumnLabelProvider(param(testPackage.classForControls_StringFeature)) {
			def image_ClassForControls_stringFeature(ClassForControls e) {
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
		val customLabelProvider = new TableColumnLabelProvider(param(testPackage.classForControls_StringFeature)) {
			def image_ClassForControls_stringFeature(ClassForControls e) {
				0
			}
		}.injectMembers
		// this will default to null
		customLabelProvider.getImage(classForControlsInstance) => [
			assertNull
		]
	}

	@Test
	def void testGetFontNull() {
		null.assertEquals(tableColumnLabelProvider(testPackage.classForControls_StringFeature).getFont(null))
	}

	@Test
	def void testDefaultGetFont() {
		null.assertEquals(
			tableColumnLabelProvider(testPackage.classForControls_StringFeature).getFont(classForControlsInstance)
		)
	}

	@Test
	def void testCustomRowFont() {
		val customLabelProvider = new TableColumnLabelProvider(param(testPackage.classForControls_StringFeature)) {
			def rowFont(ClassForControls e) {
				new Font(shell.display, JFaceResources.DEFAULT_FONT, 14, SWT.BOLD)
			}
		}.injectMembers
		customLabelProvider.getFont(classForControlsInstance).assertNotNull
	}

	@Test
	def void testCustomFont() {
		// customization for feature has precedence
		val bold = new Font(shell.display, JFaceResources.DEFAULT_FONT, 14, SWT.BOLD)
		val customLabelProvider = new TableColumnLabelProvider(param(testPackage.classForControls_StringFeature)) {
			def font_ClassForControls_stringFeature(ClassForControls e) {
				bold
			}
			
			def rowFont(ClassForControls e) {
				new Font(shell.display, JFaceResources.DEFAULT_FONT, 14, SWT.ITALIC)
			}
		}.injectMembers
		bold.assertSame(customLabelProvider.getFont(classForControlsInstance))
	}

	@Test
	def void testGetForegroundNull() {
		null.assertEquals(tableColumnLabelProvider(testPackage.classForControls_StringFeature).getForeground(null))
	}

	@Test
	def void testDefaultGetForeground() {
		null.assertEquals(
			tableColumnLabelProvider(testPackage.classForControls_StringFeature).getForeground(classForControlsInstance)
		)
	}

	@Test
	def void testCustomRowForeground() {
		val customLabelProvider = new TableColumnLabelProvider(param(testPackage.classForControls_StringFeature)) {
			def rowForeground(ClassForControls e) {
				shell.display.getSystemColor(SWT.COLOR_BLUE)
			}
		}.injectMembers
		customLabelProvider.getForeground(classForControlsInstance).assertNotNull
	}

	@Test
	def void testCustomForeground() {
		// customization for feature has precedence
		val color = shell.display.getSystemColor(SWT.COLOR_GREEN)
		val customLabelProvider = new TableColumnLabelProvider(param(testPackage.classForControls_StringFeature)) {
			def foreground_ClassForControls_stringFeature(ClassForControls e) {
				color
			}
			
			def rowForeground(ClassForControls e) {
				shell.display.getSystemColor(SWT.COLOR_BLUE)
			}
		}.injectMembers
		color.assertSame(customLabelProvider.getForeground(classForControlsInstance))
	}

	@Test
	def void testGetBackgroundNull() {
		null.assertEquals(tableColumnLabelProvider(testPackage.classForControls_StringFeature).getBackground(null))
	}

	@Test
	def void testDefaultGetBackground() {
		null.assertEquals(
			tableColumnLabelProvider(testPackage.classForControls_StringFeature).getBackground(classForControlsInstance)
		)
	}

	@Test
	def void testCustomRowBackground() {
		val customLabelProvider = new TableColumnLabelProvider(param(testPackage.classForControls_StringFeature)) {
			def rowBackground(ClassForControls e) {
				shell.display.getSystemColor(SWT.COLOR_BLUE)
			}
		}.injectMembers
		customLabelProvider.getBackground(classForControlsInstance).assertNotNull
	}

	@Test
	def void testCustomBackground() {
		// customization for feature has precedence
		val color = shell.display.getSystemColor(SWT.COLOR_GREEN)
		val customLabelProvider = new TableColumnLabelProvider(param(testPackage.classForControls_StringFeature)) {
			def background_ClassForControls_stringFeature(ClassForControls e) {
				color
			}
			
			def rowBackground(ClassForControls e) {
				shell.display.getSystemColor(SWT.COLOR_BLUE)
			}
		}.injectMembers
		color.assertSame(customLabelProvider.getBackground(classForControlsInstance))
	}

	def private tableColumnLabelProvider(EStructuralFeature f) {
		new TableColumnLabelProvider(param(f)).injectMembers
	}

	def private param(EStructuralFeature f) {
		new EStructuralFeatureParameter(f)
	}

	private def assertExceptionInLog() {
		logAppender.assertContainsMessage("TableColumnLabelProvider.getText")
	}
}
