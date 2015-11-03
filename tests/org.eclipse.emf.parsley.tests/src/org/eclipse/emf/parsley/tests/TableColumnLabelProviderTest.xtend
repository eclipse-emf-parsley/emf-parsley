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

import java.util.List
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.parsley.EmfParsleyActivator
import org.eclipse.emf.parsley.junit4.util.LogAppenderTestRule
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName
import org.eclipse.emf.parsley.tests.models.testmodels.DerivedClass
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider
import org.eclipse.jface.resource.ImageDescriptor
import org.eclipse.jface.resource.JFaceResources
import org.eclipse.swt.SWT
import org.eclipse.swt.graphics.Font
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static extension org.eclipse.emf.parsley.junit4.ui.util.ImageTester.*
import static extension org.junit.Assert.*

class TableColumnLabelProviderTest extends AbstractImageBasedTest {

	var TableColumnLabelProvider tableColumnLabelProvider
	
	@Rule public val LogAppenderTestRule logAppender = new LogAppenderTestRule(EmfParsleyActivator);
	
	@Before
	def void setupLabelProvider() {
		tableColumnLabelProvider = getOrCreateInjector.getInstance(TableColumnLabelProvider)
	}

	@Test
	def void testGetTextNull() {
		"".assertEquals(tableColumnLabelProvider.getText(null))
	}

	@Test
	def void testDefaultGetText() {
		classForControlsInstance.stringFeature = "Test"
		"Test".assertEquals(
			tableColumnLabelProvider.
				forFeature(testPackage.classForControls_StringFeature).getText(classForControlsInstance)
		)
	}

	@Test
	def void testGetTextUsesLabelProvider() {
		tableColumnLabelProvider.labelProvider = new ViewerLabelProvider(delegateLabelProvider) {
			def text(List<ClassWithName> l) {
				l.map[name].join(", ")
			}
		}
		testContainer.classesWithName += createClassWithName("Test1")
		testContainer.classesWithName += createClassWithName("Test2")
		"Test1, Test2".assertEquals(
			tableColumnLabelProvider.
				forFeature(testPackage.testContainer_ClassesWithName).getText(testContainer)
		)
	}

	@Test
	def void testGetTextWhenFeatureValueIsNullReturnsEmptyString() {
		classForControlsInstance.stringFeature = null
		"".assertEquals(
			tableColumnLabelProvider.
				forFeature(testPackage.classForControls_StringFeature).getText(classForControlsInstance)
		)
	}

	@Test
	def void testGetTextWhenOjectClassDoesNotHaveFeature() {
		// testContainer, TestContainer, does not have the feature multiReferencFeature
		// and it simply takes the value of the feature with the same ID in the
		// EObject's class, which does not exist, throwing an exception
		// but the TableColumnLabelProvider gracefully defaults to an empty string
		"".assertEquals(
			tableColumnLabelProvider.
				forFeature(testPackage.classForControls_MultiReferenceFeature).getText(testContainer)
		)
		assertExceptionInLog()
	}
	
	@Test
	def void testCustomGetTextWithAssertionError() {
		"".assertEquals(new TableColumnLabelProvider {
			override protected getFeatureValue(Object element) {
				throw new AssertionError("TEST") 
			}
		}.initialize(testPackage.classForControls_StringFeature).getText(classForControlsInstance))
		assertExceptionInLog()
	}

	@Test
	def void testCustomGetTextWithRuntimeException() {
		"".assertEquals(new TableColumnLabelProvider {
			override protected getFeatureValue(Object element) {
				throw new NullPointerException("TEST")
			}
		}.initialize(testPackage.classForControls_StringFeature).getText(classForControlsInstance))
		assertExceptionInLog()
	}

	@Test
	def void testCustomText() {
		val customProvider = new TableColumnLabelProvider {
			def text_ClassForControls_stringFeature(ClassForControls e) {
				"Test"
			}
		}.initialize(testPackage.classForControls_StringFeature)
		"Test".assertEquals(customProvider.getText(classForControlsInstance))
	}

	@Test
	def void testCustomTextNotEObject() {
		val customProvider = new TableColumnLabelProvider {
			def text_ClassForControls_stringFeature(String s) {
				"Test"
			}
		}.initialize(testPackage.classForControls_StringFeature)
		"Test".assertEquals(customProvider.getText("aString"))
	}

	@Test
	def void testCustomTextDerivedClassFeatureInDerivedClass() {
		val customProvider = new TableColumnLabelProvider {
			def text_DerivedClass_derivedClassFeature(DerivedClass e) {
				"Test"
			}
		}.initialize(testPackage.derivedClass_DerivedClassFeature)
		"Test".assertEquals(customProvider.getText(derivedClassInstance))
	}

	@Test
	def void testCustomTextBaseClassFeatureInDerivedClass() {
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=480749
		val customProvider = new TableColumnLabelProvider {
			def text_DerivedClass_baseClassFeature(DerivedClass e) {
				"Test"
			}
		}.initialize(testPackage.baseClass_BaseClassFeature)
		"Test".assertEquals(customProvider.getText(derivedClassInstance))
	}

	@Test
	def void testGetImageNull() {
		tableColumnLabelProvider.getImage(null).assertNull
	}

	@Test
	def void testDefaultGetImage() {
		tableColumnLabelProvider.
		forFeature(testPackage.testContainer_ClassesForControls).
		getImage(testContainer) => [
			assertNull
		]
	}

	@Test
	def void testCustomImageAsString() {
		val customLabelProvider = new TableColumnLabelProvider {
			def image_ClassForControls_stringFeature(ClassForControls e) {
				TEST_IMAGE
			}
		}.initialize(testPackage.classForControls_StringFeature)
		customLabelProvider.getImage(classForControlsInstance) => [
			assertNotNull
			loadTestImage.assertImageIs(it)
		]
	}

	@Test
	def void testCustomImageNotEObject() {
		val customProvider = new TableColumnLabelProvider {
			def image_ClassForControls_stringFeature(String s) {
				TEST_IMAGE
			}
		}.initialize(testPackage.classForControls_StringFeature)
		customProvider.getImage("aString") => [
			assertNotNull
			loadTestImage.assertImageIs(it)
		]
	}

	@Test
	def void testCustomImageDerivedClassFeatureInDerivedClass() {
		val customProvider = new TableColumnLabelProvider {
			def image_DerivedClass_derivedClassFeature(DerivedClass e) {
				TEST_IMAGE
			}
		}.initialize(testPackage.derivedClass_DerivedClassFeature)
		customProvider.getImage(derivedClassInstance) => [
			assertNotNull
			loadTestImage.assertImageIs(it)
		]
	}

	@Test
	def void testCustomImageBaseClassFeatureInDerivedClass() {
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=480749
		val customProvider = new TableColumnLabelProvider {
			def image_DerivedClass_baseClassFeature(DerivedClass e) {
				TEST_IMAGE
			}
		}.initialize(testPackage.baseClass_BaseClassFeature)
		customProvider.getImage(derivedClassInstance) => [
			assertNotNull
			loadTestImage.assertImageIs(it)
		]
	}

	@Test
	def void testCustomImageAsImage() {
		val customLabelProvider = new TableColumnLabelProvider {
			def image_ClassForControls_stringFeature(ClassForControls e) {
				loadTestImage
			}
		}.initialize(testPackage.classForControls_StringFeature)
		customLabelProvider.getImage(classForControlsInstance) => [
			assertNotNull
			loadTestImage.assertImageIs(it)
		]
	}

	@Test
	def void testCustomImageAsImageDescriptor() {
		val customLabelProvider = new TableColumnLabelProvider {
			def image_ClassForControls_stringFeature(ClassForControls e) {
				ImageDescriptor.createFromImage(loadTestImage)
			}
		}.initialize(testPackage.classForControls_StringFeature)
		customLabelProvider.getImage(classForControlsInstance) => [
			assertNotNull
			loadTestImage.assertImageIs(it)
		]
	}

	@Test
	def void testWrongCustomImageAsInteger() {
		val customLabelProvider = new TableColumnLabelProvider {
			def image_ClassForControls_stringFeature(ClassForControls e) {
				0
			}
		}.initialize(testPackage.classForControls_StringFeature)
		// this will default to null
		customLabelProvider.getImage(classForControlsInstance) => [
			assertNull
		]
	}

	@Test
	def void testGetFontNull() {
		null.assertEquals(tableColumnLabelProvider.getFont(null))
	}

	@Test
	def void testDefaultGetFont() {
		null.assertEquals(
			tableColumnLabelProvider.
				forFeature(testPackage.classForControls_StringFeature).getFont(classForControlsInstance)
		)
	}

	@Test
	def void testCustomRowFont() {
		val customLabelProvider = new TableColumnLabelProvider {
			def rowFont(ClassForControls e) {
				new Font(shell.display, JFaceResources.DEFAULT_FONT, 14, SWT.BOLD)
			}
		}.initialize(testPackage.classForControls_StringFeature)
		customLabelProvider.getFont(classForControlsInstance).assertNotNull
	}

	@Test
	def void testCustomFont() {
		// customization for feature has precedence
		val bold = new Font(shell.display, JFaceResources.DEFAULT_FONT, 14, SWT.BOLD)
		val customLabelProvider = new TableColumnLabelProvider {
			def font_ClassForControls_stringFeature(ClassForControls e) {
				bold
			}
			
			def rowFont(ClassForControls e) {
				new Font(shell.display, JFaceResources.DEFAULT_FONT, 14, SWT.ITALIC)
			}
		}.initialize(testPackage.classForControls_StringFeature)
		bold.assertSame(customLabelProvider.getFont(classForControlsInstance))
	}

	@Test
	def void testGetForegroundNull() {
		null.assertEquals(tableColumnLabelProvider.getForeground(null))
	}

	@Test
	def void testDefaultGetForeground() {
		null.assertEquals(
			tableColumnLabelProvider.
				forFeature(testPackage.classForControls_StringFeature).getForeground(classForControlsInstance)
		)
	}

	@Test
	def void testCustomRowForeground() {
		val customLabelProvider = new TableColumnLabelProvider {
			def rowForeground(ClassForControls e) {
				shell.display.getSystemColor(SWT.COLOR_BLUE)
			}
		}.initialize(testPackage.classForControls_StringFeature)
		customLabelProvider.getForeground(classForControlsInstance).assertNotNull
	}

	@Test
	def void testCustomForeground() {
		// customization for feature has precedence
		val color = shell.display.getSystemColor(SWT.COLOR_GREEN)
		val customLabelProvider = new TableColumnLabelProvider {
			def foreground_ClassForControls_stringFeature(ClassForControls e) {
				color
			}
			
			def rowForeground(ClassForControls e) {
				shell.display.getSystemColor(SWT.COLOR_BLUE)
			}
		}.initialize(testPackage.classForControls_StringFeature)
		color.assertSame(customLabelProvider.getForeground(classForControlsInstance))
	}

	@Test
	def void testGetBackgroundNull() {
		null.assertEquals(tableColumnLabelProvider.getBackground(null))
	}

	@Test
	def void testDefaultGetBackground() {
		null.assertEquals(
			tableColumnLabelProvider.
				forFeature(testPackage.classForControls_StringFeature).getBackground(classForControlsInstance)
		)
	}

	@Test
	def void testCustomRowBackground() {
		val customLabelProvider = new TableColumnLabelProvider {
			def rowBackground(ClassForControls e) {
				shell.display.getSystemColor(SWT.COLOR_BLUE)
			}
		}.initialize(testPackage.classForControls_StringFeature)
		customLabelProvider.getBackground(classForControlsInstance).assertNotNull
	}

	@Test
	def void testCustomBackground() {
		// customization for feature has precedence
		val color = shell.display.getSystemColor(SWT.COLOR_GREEN)
		val customLabelProvider = new TableColumnLabelProvider {
			def background_ClassForControls_stringFeature(ClassForControls e) {
				color
			}
			
			def rowBackground(ClassForControls e) {
				shell.display.getSystemColor(SWT.COLOR_BLUE)
			}
		}.initialize(testPackage.classForControls_StringFeature)
		color.assertSame(customLabelProvider.getBackground(classForControlsInstance))
	}

	def private initialize(TableColumnLabelProvider p, EStructuralFeature f) {
		p.injectMembers.forFeature(f)
	}

	def private forFeature(TableColumnLabelProvider p, EStructuralFeature f) {
		p => [
			seteStructuralFeature(f)
		]
	}
	
	private def assertExceptionInLog() {
		logAppender.assertContainsMessage("TableColumnLabelProvider.getText")
	}
}
