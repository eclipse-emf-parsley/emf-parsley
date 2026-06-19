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

import static org.eclipse.emf.parsley.junit4.ui.util.ImageTester.assertImageIs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.EmfParsleyActivator;
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule;
import org.eclipse.emf.parsley.inject.parameters.EStructuralFeatureParameter;
import org.eclipse.emf.parsley.junit4.util.LogAppenderTestRule;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls;
import org.eclipse.emf.parsley.tests.models.testmodels.DerivedClass;
import org.eclipse.emf.parsley.tests.util.ViewerLabelProviderForList;
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.junit.Rule;
import org.junit.Test;

public class TableColumnLabelProviderTest extends AbstractImageBasedTest {

	@Rule
	public final LogAppenderTestRule logAppender = new LogAppenderTestRule(EmfParsleyActivator.class);

	@Test
	public void testGetTextNull() {
		assertEquals("", tableColumnLabelProvider(fixtures.getTestPackage().getClassForControls_StringFeature()).getText(null));
	}

	@Test
	public void testDefaultGetText() {
		fixtures.getClassForControlsInstance().setStringFeature("Test");
		assertEquals("Test",
			tableColumnLabelProvider(fixtures.getTestPackage().getClassForControls_StringFeature()).getText(fixtures.getClassForControlsInstance())
		);
	}

	@Test
	public void testGetTextUsesLabelProvider() {
		var prov = injectMembers(
			new TableColumnLabelProvider(param(fixtures.getTestPackage().getTestContainer_ClassesWithName())),
			new EmfParsleyJavaGuiceModule() {
				@Override
				public Class<? extends ILabelProvider> bindILabelProvider() {
					return ViewerLabelProviderForList.class;
				}
			}
		);
		fixtures.getTestContainer().getClassesWithName().add(fixtures.createClassWithName("Test1"));
		fixtures.getTestContainer().getClassesWithName().add(fixtures.createClassWithName("Test2"));
		assertEquals("Test1, Test2", prov.getText(fixtures.getTestContainer()));
	}

	@Test
	public void testGetTextWhenFeatureValueIsNullReturnsEmptyString() {
		fixtures.getClassForControlsInstance().setStringFeature(null);
		assertEquals("",
			tableColumnLabelProvider(fixtures.getTestPackage().getClassForControls_StringFeature()).getText(fixtures.getClassForControlsInstance())
		);
	}

	@Test
	public void testGetTextWhenOjectClassDoesNotHaveFeature() {
		// testContainer, TestContainer, does not have the feature multiReferencFeature
		// and it simply takes the value of the feature with the same ID in the
		// EObject's class, which does not exist,
		// but the TableColumnLabelProvider gracefully defaults to an empty string
		assertEquals("",
			tableColumnLabelProvider(fixtures.getTestPackage().getClassForControls_MultiReferenceFeature()).getText(fixtures.getTestContainer())
		);
	}

	@Test
	public void testCustomGetTextWithAssertionError() {
		assertEquals("", injectMembers(new TableColumnLabelProvider(param(fixtures.getTestPackage().getClassForControls_StringFeature())) {
			@Override
			protected Object getFeatureValue(Object element) {
				throw new AssertionError("TEST");
			}
		}).getText(fixtures.getClassForControlsInstance()));
		assertExceptionInLog();
	}

	@Test
	public void testCustomGetTextWithRuntimeException() {
		assertEquals("", injectMembers(new TableColumnLabelProvider(param(fixtures.getTestPackage().getClassForControls_StringFeature())) {
			@Override
			protected Object getFeatureValue(Object element) {
				throw new NullPointerException("TEST");
			}
		}).getText(fixtures.getClassForControlsInstance()));
		assertExceptionInLog();
	}

	@Test
	public void testCustomText() {
		var customProvider = injectMembers(new TableColumnLabelProvider(param(fixtures.getTestPackage().getClassForControls_StringFeature())) {
			public String text_ClassForControls_stringFeature(ClassForControls e) {
				return "Test";
			}
		});
		assertEquals("Test", customProvider.getText(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testCustomTextNotEObject() {
		var customProvider = injectMembers(new TableColumnLabelProvider(param(fixtures.getTestPackage().getClassForControls_StringFeature())) {
			public String text_ClassForControls_stringFeature(String s) {
				return "Test";
			}
		});
		assertEquals("Test", customProvider.getText("aString"));
	}

	@Test
	public void testCustomTextDerivedClassFeatureInDerivedClass() {
		var customProvider = injectMembers(new TableColumnLabelProvider(param(fixtures.getTestPackage().getDerivedClass_DerivedClassFeature())) {
			public String text_DerivedClass_derivedClassFeature(DerivedClass e) {
				return "Test";
			}
		});
		assertEquals("Test", customProvider.getText(fixtures.getDerivedClassInstance()));
	}

	@Test
	public void testCustomTextBaseClassFeatureInDerivedClass() {
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=480749
		var customProvider = injectMembers(new TableColumnLabelProvider(param(fixtures.getTestPackage().getBaseClass_BaseClassFeature())) {
			public String text_DerivedClass_baseClassFeature(DerivedClass e) {
				return "Test";
			}
		});
		assertEquals("Test", customProvider.getText(fixtures.getDerivedClassInstance()));
	}

	@Test
	public void testGetImageNull() {
		assertNull(tableColumnLabelProvider(fixtures.getTestPackage().getTestContainer_ClassesForControls()).getImage(null));
	}

	@Test
	public void testDefaultGetImage() {
		var image = tableColumnLabelProvider(fixtures.getTestPackage().getTestContainer_ClassesForControls())
			.getImage(fixtures.getTestContainer());
		assertNull(image);
	}

	@Test
	public void testCustomImageAsString() {
		var customLabelProvider = injectMembers(new TableColumnLabelProvider(param(fixtures.getTestPackage().getClassForControls_StringFeature())) {
			public String image_ClassForControls_stringFeature(ClassForControls e) {
				return TEST_IMAGE;
			}
		});
		var image = customLabelProvider.getImage(fixtures.getClassForControlsInstance());
		assertNotNull(image);
		assertImageIs(loadTestImage(), image);
	}

	@Test
	public void testCustomImageNotEObject() {
		var customProvider = injectMembers(new TableColumnLabelProvider(param(fixtures.getTestPackage().getClassForControls_StringFeature())) {
			public String image_ClassForControls_stringFeature(String s) {
				return TEST_IMAGE;
			}
		});
		var image = customProvider.getImage("aString");
		assertNotNull(image);
		assertImageIs(loadTestImage(), image);
	}

	@Test
	public void testCustomImageDerivedClassFeatureInDerivedClass() {
		var customProvider = injectMembers(new TableColumnLabelProvider(param(fixtures.getTestPackage().getDerivedClass_DerivedClassFeature())) {
			public String image_DerivedClass_derivedClassFeature(DerivedClass e) {
				return TEST_IMAGE;
			}
		});
		var image = customProvider.getImage(fixtures.getDerivedClassInstance());
		assertNotNull(image);
		assertImageIs(loadTestImage(), image);
	}

	@Test
	public void testCustomImageBaseClassFeatureInDerivedClass() {
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=480749
		var customProvider = injectMembers(new TableColumnLabelProvider(param(fixtures.getTestPackage().getBaseClass_BaseClassFeature())) {
			public String image_DerivedClass_baseClassFeature(DerivedClass e) {
				return TEST_IMAGE;
			}
		});
		var image = customProvider.getImage(fixtures.getDerivedClassInstance());
		assertNotNull(image);
		assertImageIs(loadTestImage(), image);
	}

	@Test
	public void testCustomImageAsImage() {
		var customLabelProvider = injectMembers(new TableColumnLabelProvider(param(fixtures.getTestPackage().getClassForControls_StringFeature())) {
			public Object image_ClassForControls_stringFeature(ClassForControls e) {
				return loadTestImage();
			}
		});
		var image = customLabelProvider.getImage(fixtures.getClassForControlsInstance());
		assertNotNull(image);
		assertImageIs(loadTestImage(), image);
	}

	@Test
	public void testCustomImageAsImageDescriptor() {
		var customLabelProvider = injectMembers(new TableColumnLabelProvider(param(fixtures.getTestPackage().getClassForControls_StringFeature())) {
			public Object image_ClassForControls_stringFeature(ClassForControls e) {
				return ImageDescriptor.createFromImage(loadTestImage());
			}
		});
		var image = customLabelProvider.getImage(fixtures.getClassForControlsInstance());
		assertNotNull(image);
		assertImageIs(loadTestImage(), image);
	}

	@Test
	public void testWrongCustomImageAsInteger() {
		var customLabelProvider = injectMembers(new TableColumnLabelProvider(param(fixtures.getTestPackage().getClassForControls_StringFeature())) {
			public Object image_ClassForControls_stringFeature(ClassForControls e) {
				return 0;
			}
		});
		// this will default to null
		assertNull(customLabelProvider.getImage(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testGetFontNull() {
		assertNull(tableColumnLabelProvider(fixtures.getTestPackage().getClassForControls_StringFeature()).getFont(null));
	}

	@Test
	public void testDefaultGetFont() {
		assertNull(
			tableColumnLabelProvider(fixtures.getTestPackage().getClassForControls_StringFeature()).getFont(fixtures.getClassForControlsInstance())
		);
	}

	@Test
	public void testCustomRowFont() {
		var customLabelProvider = injectMembers(new TableColumnLabelProvider(param(fixtures.getTestPackage().getClassForControls_StringFeature())) {
			public Font rowFont(ClassForControls e) {
				return new Font(getShell().getDisplay(), JFaceResources.DEFAULT_FONT, 14, SWT.BOLD);
			}
		});
		assertNotNull(customLabelProvider.getFont(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testCustomFont() {
		// customization for feature has precedence
		var bold = new Font(getShell().getDisplay(), JFaceResources.DEFAULT_FONT, 14, SWT.BOLD);
		var customLabelProvider = injectMembers(new TableColumnLabelProvider(param(fixtures.getTestPackage().getClassForControls_StringFeature())) {
			public Font font_ClassForControls_stringFeature(ClassForControls e) {
				return bold;
			}

			public Font rowFont(ClassForControls e) {
				return new Font(getShell().getDisplay(), JFaceResources.DEFAULT_FONT, 14, SWT.ITALIC);
			}
		});
		assertSame(bold, customLabelProvider.getFont(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testGetForegroundNull() {
		assertNull(tableColumnLabelProvider(fixtures.getTestPackage().getClassForControls_StringFeature()).getForeground(null));
	}

	@Test
	public void testDefaultGetForeground() {
		assertNull(
			tableColumnLabelProvider(fixtures.getTestPackage().getClassForControls_StringFeature()).getForeground(fixtures.getClassForControlsInstance())
		);
	}

	@Test
	public void testCustomRowForeground() {
		var customLabelProvider = injectMembers(new TableColumnLabelProvider(param(fixtures.getTestPackage().getClassForControls_StringFeature())) {
			public Object rowForeground(ClassForControls e) {
				return getShell().getDisplay().getSystemColor(SWT.COLOR_BLUE);
			}
		});
		assertNotNull(customLabelProvider.getForeground(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testCustomForeground() {
		// customization for feature has precedence
		var color = getShell().getDisplay().getSystemColor(SWT.COLOR_GREEN);
		var customLabelProvider = injectMembers(new TableColumnLabelProvider(param(fixtures.getTestPackage().getClassForControls_StringFeature())) {
			public Object foreground_ClassForControls_stringFeature(ClassForControls e) {
				return color;
			}

			public Object rowForeground(ClassForControls e) {
				return getShell().getDisplay().getSystemColor(SWT.COLOR_BLUE);
			}
		});
		assertSame(color, customLabelProvider.getForeground(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testGetBackgroundNull() {
		assertNull(tableColumnLabelProvider(fixtures.getTestPackage().getClassForControls_StringFeature()).getBackground(null));
	}

	@Test
	public void testDefaultGetBackground() {
		assertNull(
			tableColumnLabelProvider(fixtures.getTestPackage().getClassForControls_StringFeature()).getBackground(fixtures.getClassForControlsInstance())
		);
	}

	@Test
	public void testCustomRowBackground() {
		var customLabelProvider = injectMembers(new TableColumnLabelProvider(param(fixtures.getTestPackage().getClassForControls_StringFeature())) {
			public Object rowBackground(ClassForControls e) {
				return getShell().getDisplay().getSystemColor(SWT.COLOR_BLUE);
			}
		});
		assertNotNull(customLabelProvider.getBackground(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testCustomBackground() {
		// customization for feature has precedence
		var color = getShell().getDisplay().getSystemColor(SWT.COLOR_GREEN);
		var customLabelProvider = injectMembers(new TableColumnLabelProvider(param(fixtures.getTestPackage().getClassForControls_StringFeature())) {
			public Object background_ClassForControls_stringFeature(ClassForControls e) {
				return color;
			}

			public Object rowBackground(ClassForControls e) {
				return getShell().getDisplay().getSystemColor(SWT.COLOR_BLUE);
			}
		});
		assertSame(color, customLabelProvider.getBackground(fixtures.getClassForControlsInstance()));
	}

	private TableColumnLabelProvider tableColumnLabelProvider(EStructuralFeature f) {
		return injectMembers(new TableColumnLabelProvider(param(f)));
	}

	private EStructuralFeatureParameter param(EStructuralFeature f) {
		return new EStructuralFeatureParameter(f);
	}

	private void assertExceptionInLog() {
		logAppender.assertContainsMessage("TableColumnLabelProvider.getText");
	}
}
