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

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.EmfParsleyActivator;
import org.eclipse.emf.parsley.junit4.util.LogAppenderTestRule;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName;
import org.eclipse.emf.parsley.tests.models.testmodels.DerivedClass;
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class TableColumnLabelProviderTest extends AbstractImageBasedTest {

	private TableColumnLabelProvider tableColumnLabelProvider;
	
	@Rule
	public final LogAppenderTestRule logAppender = new LogAppenderTestRule(EmfParsleyActivator.class);
	
	@Before
	public void setupLabelProvider() {
		tableColumnLabelProvider = getOrCreateInjector().getInstance(TableColumnLabelProvider.class);
	}

	@Test
	public void testGetTextNull() {
		assertEquals("", tableColumnLabelProvider.getText(null));
	}

	@Test
	public void testDefaultGetText() {
		fixtures.getClassForControlsInstance().setStringFeature("Test");
		assertEquals(
			"Test",
			forFeature(tableColumnLabelProvider, fixtures.getTestPackage().getClassForControls_StringFeature()).getText(fixtures.getClassForControlsInstance())
		);
	}

	@Test
	public void testGetTextUsesLabelProvider() {
		tableColumnLabelProvider.setLabelProvider(new ViewerLabelProvider(getDelegateLabelProvider()) {
			@SuppressWarnings("unused")
			public String text(List<ClassWithName> l) {
				return l.stream().map(ClassWithName::getName).collect(Collectors.joining(", "));
			}
		});
		fixtures.getTestContainer().getClassesWithName().add(fixtures.createClassWithName("Test1"));
		fixtures.getTestContainer().getClassesWithName().add(fixtures.createClassWithName("Test2"));
		assertEquals(
			"Test1, Test2",
			forFeature(tableColumnLabelProvider, fixtures.getTestPackage().getTestContainer_ClassesWithName()).getText(fixtures.getTestContainer())
		);
	}

	@Test
	public void testGetTextWhenFeatureValueIsNullReturnsEmptyString() {
		fixtures.getClassForControlsInstance().setStringFeature(null);
		assertEquals(
			"",
			forFeature(tableColumnLabelProvider, fixtures.getTestPackage().getClassForControls_StringFeature()).getText(fixtures.getClassForControlsInstance())
		);
	}

	@Test
	public void testGetTextWhenOjectClassDoesNotHaveFeature() {
		// testContainer, TestContainer, does not have the feature multiReferencFeature
		// and it simply takes the value of the feature with the same ID in the
		// EObject's class, which does not exist,
		// but the TableColumnLabelProvider gracefully defaults to an empty string
		assertEquals(
			"",
			forFeature(tableColumnLabelProvider, fixtures.getTestPackage().getClassForControls_MultiReferenceFeature()).getText(fixtures.getTestContainer())
		);
	}
	
	@Test
	public void testCustomGetTextWithAssertionError() {
		var provider = new TableColumnLabelProvider() {
			@Override
			protected Object getFeatureValue(Object element) {
				throw new AssertionError("TEST"); 
			}
		};
		assertEquals("", initialize(provider, fixtures.getTestPackage().getClassForControls_StringFeature()).getText(fixtures.getClassForControlsInstance()));
		assertExceptionInLog();
	}

	@Test
	public void testCustomGetTextWithRuntimeException() {
		var provider = new TableColumnLabelProvider() {
			@Override
			protected Object getFeatureValue(Object element) {
				throw new NullPointerException("TEST");
			}
		};
		assertEquals("", initialize(provider, fixtures.getTestPackage().getClassForControls_StringFeature()).getText(fixtures.getClassForControlsInstance()));
		assertExceptionInLog();
	}

	@Test
	public void testCustomText() {
		final var customProvider = new TableColumnLabelProvider() {
			@SuppressWarnings("unused")
			public String text_ClassForControls_stringFeature(ClassForControls e) {
				return "Test";
			}
		};
		assertEquals("Test", initialize(customProvider, fixtures.getTestPackage().getClassForControls_StringFeature()).getText(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testCustomTextNotEObject() {
		final var customProvider = new TableColumnLabelProvider() {
			@SuppressWarnings("unused")
			public String text_ClassForControls_stringFeature(String s) {
				return "Test";
			}
		};
		assertEquals("Test", initialize(customProvider, fixtures.getTestPackage().getClassForControls_StringFeature()).getText("aString"));
	}

	@Test
	public void testCustomTextDerivedClassFeatureInDerivedClass() {
		final var customProvider = new TableColumnLabelProvider() {
			@SuppressWarnings("unused")
			public String text_DerivedClass_derivedClassFeature(DerivedClass e) {
				return "Test";
			}
		};
		assertEquals("Test", initialize(customProvider, fixtures.getTestPackage().getDerivedClass_DerivedClassFeature()).getText(fixtures.getDerivedClassInstance()));
	}

	@Test
	public void testCustomTextBaseClassFeatureInDerivedClass() {
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=480749
		final var customProvider = new TableColumnLabelProvider() {
			@SuppressWarnings("unused")
			public String text_DerivedClass_baseClassFeature(DerivedClass e) {
				return "Test";
			}
		};
		assertEquals("Test", initialize(customProvider, fixtures.getTestPackage().getBaseClass_BaseClassFeature()).getText(fixtures.getDerivedClassInstance()));
	}

	@Test
	public void testGetImageNull() {
		assertNull(tableColumnLabelProvider.getImage(null));
	}

	@Test
	public void testDefaultGetImage() {
		Image image = forFeature(tableColumnLabelProvider, fixtures.getTestPackage().getTestContainer_ClassesForControls())
			.getImage(fixtures.getTestContainer());
		assertNull(image);
	}

	@Test
	public void testCustomImageAsString() {
		final var customLabelProvider = new TableColumnLabelProvider() {
			@SuppressWarnings("unused")
			public String image_ClassForControls_stringFeature(ClassForControls e) {
				return TEST_IMAGE;
			}
		};
		Image image = initialize(customLabelProvider, fixtures.getTestPackage().getClassForControls_StringFeature())
			.getImage(fixtures.getClassForControlsInstance());
		assertNotNull(image);
		assertImageIs(loadTestImage(), image);
	}

	@Test
	public void testCustomImageNotEObject() {
		final var customProvider = new TableColumnLabelProvider() {
			@SuppressWarnings("unused")
			public String image_ClassForControls_stringFeature(String s) {
				return TEST_IMAGE;
			}
		};
		Image image = initialize(customProvider, fixtures.getTestPackage().getClassForControls_StringFeature())
			.getImage("aString");
		assertNotNull(image);
		assertImageIs(loadTestImage(), image);
	}

	@Test
	public void testCustomImageDerivedClassFeatureInDerivedClass() {
		final var customProvider = new TableColumnLabelProvider() {
			@SuppressWarnings("unused")
			public String image_DerivedClass_derivedClassFeature(DerivedClass e) {
				return TEST_IMAGE;
			}
		};
		Image image = initialize(customProvider, fixtures.getTestPackage().getDerivedClass_DerivedClassFeature())
			.getImage(fixtures.getDerivedClassInstance());
		assertNotNull(image);
		assertImageIs(loadTestImage(), image);
	}

	@Test
	public void testCustomImageBaseClassFeatureInDerivedClass() {
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=480749
		final var customProvider = new TableColumnLabelProvider() {
			@SuppressWarnings("unused")
			public String image_DerivedClass_baseClassFeature(DerivedClass e) {
				return TEST_IMAGE;
			}
		};
		Image image = initialize(customProvider, fixtures.getTestPackage().getBaseClass_BaseClassFeature())
			.getImage(fixtures.getDerivedClassInstance());
		assertNotNull(image);
		assertImageIs(loadTestImage(), image);
	}

	@Test
	public void testCustomImageAsImage() {
		final var customLabelProvider = new TableColumnLabelProvider() {
			@SuppressWarnings("unused")
			public Image image_ClassForControls_stringFeature(ClassForControls e) {
				return loadTestImage();
			}
		};
		Image image = initialize(customLabelProvider, fixtures.getTestPackage().getClassForControls_StringFeature())
			.getImage(fixtures.getClassForControlsInstance());
		assertNotNull(image);
		assertImageIs(loadTestImage(), image);
	}

	@Test
	public void testCustomImageAsImageDescriptor() {
		final var customLabelProvider = new TableColumnLabelProvider() {
			@SuppressWarnings("unused")
			public ImageDescriptor image_ClassForControls_stringFeature(ClassForControls e) {
				return ImageDescriptor.createFromImage(loadTestImage());
			}
		};
		Image image = initialize(customLabelProvider, fixtures.getTestPackage().getClassForControls_StringFeature())
			.getImage(fixtures.getClassForControlsInstance());
		assertNotNull(image);
		assertImageIs(loadTestImage(), image);
	}

	@Test
	public void testWrongCustomImageAsInteger() {
		final var customLabelProvider = new TableColumnLabelProvider() {
			@SuppressWarnings("unused")
			public Integer image_ClassForControls_stringFeature(ClassForControls e) {
				return 0;
			}
		};
		// this will default to null
		Image image = initialize(customLabelProvider, fixtures.getTestPackage().getClassForControls_StringFeature())
			.getImage(fixtures.getClassForControlsInstance());
		assertNull(image);
	}

	@Test
	public void testGetFontNull() {
		assertEquals(null, tableColumnLabelProvider.getFont(null));
	}

	@Test
	public void testDefaultGetFont() {
		assertEquals(
			null,
			forFeature(tableColumnLabelProvider, fixtures.getTestPackage().getClassForControls_StringFeature()).getFont(fixtures.getClassForControlsInstance())
		);
	}

	@Test
	public void testCustomRowFont() {
		final var customLabelProvider = new TableColumnLabelProvider() {
			@SuppressWarnings("unused")
			public Font rowFont(ClassForControls e) {
				return new Font(getShell().getDisplay(), JFaceResources.DEFAULT_FONT, 14, SWT.BOLD);
			}
		};
		assertNotNull(initialize(customLabelProvider, fixtures.getTestPackage().getClassForControls_StringFeature()).getFont(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testCustomFont() {
		// customization for feature has precedence
		final var bold = new Font(getShell().getDisplay(), JFaceResources.DEFAULT_FONT, 14, SWT.BOLD);
		final var customLabelProvider = new TableColumnLabelProvider() {
			@SuppressWarnings("unused")
			public Font font_ClassForControls_stringFeature(ClassForControls e) {
				return bold;
			}
			
			@SuppressWarnings("unused")
			public Font rowFont(ClassForControls e) {
				return new Font(getShell().getDisplay(), JFaceResources.DEFAULT_FONT, 14, SWT.ITALIC);
			}
		};
		assertSame(bold, initialize(customLabelProvider, fixtures.getTestPackage().getClassForControls_StringFeature()).getFont(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testGetForegroundNull() {
		assertEquals(null, tableColumnLabelProvider.getForeground(null));
	}

	@Test
	public void testDefaultGetForeground() {
		assertEquals(
			null,
			forFeature(tableColumnLabelProvider, fixtures.getTestPackage().getClassForControls_StringFeature()).getForeground(fixtures.getClassForControlsInstance())
		);
	}

	@Test
	public void testCustomRowForeground() {
		final var customLabelProvider = new TableColumnLabelProvider() {
			@SuppressWarnings("unused")
			public org.eclipse.swt.graphics.Color rowForeground(ClassForControls e) {
				return getShell().getDisplay().getSystemColor(SWT.COLOR_BLUE);
			}
		};
		assertNotNull(initialize(customLabelProvider, fixtures.getTestPackage().getClassForControls_StringFeature()).getForeground(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testCustomForeground() {
		// customization for feature has precedence
		final var color = getShell().getDisplay().getSystemColor(SWT.COLOR_GREEN);
		final var customLabelProvider = new TableColumnLabelProvider() {
			@SuppressWarnings("unused")
			public org.eclipse.swt.graphics.Color foreground_ClassForControls_stringFeature(ClassForControls e) {
				return color;
			}
			
			@SuppressWarnings("unused")
			public org.eclipse.swt.graphics.Color rowForeground(ClassForControls e) {
				return getShell().getDisplay().getSystemColor(SWT.COLOR_BLUE);
			}
		};
		assertSame(color, initialize(customLabelProvider, fixtures.getTestPackage().getClassForControls_StringFeature()).getForeground(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testGetBackgroundNull() {
		assertEquals(null, tableColumnLabelProvider.getBackground(null));
	}

	@Test
	public void testDefaultGetBackground() {
		assertEquals(
			null,
			forFeature(tableColumnLabelProvider, fixtures.getTestPackage().getClassForControls_StringFeature()).getBackground(fixtures.getClassForControlsInstance())
		);
	}

	@Test
	public void testCustomRowBackground() {
		final var customLabelProvider = new TableColumnLabelProvider() {
			@SuppressWarnings("unused")
			public org.eclipse.swt.graphics.Color rowBackground(ClassForControls e) {
				return getShell().getDisplay().getSystemColor(SWT.COLOR_BLUE);
			}
		};
		assertNotNull(initialize(customLabelProvider, fixtures.getTestPackage().getClassForControls_StringFeature()).getBackground(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testCustomBackground() {
		// customization for feature has precedence
		final var color = getShell().getDisplay().getSystemColor(SWT.COLOR_GREEN);
		final var customLabelProvider = new TableColumnLabelProvider() {
			@SuppressWarnings("unused")
			public org.eclipse.swt.graphics.Color background_ClassForControls_stringFeature(ClassForControls e) {
				return color;
			}
			
			@SuppressWarnings("unused")
			public org.eclipse.swt.graphics.Color rowBackground(ClassForControls e) {
				return getShell().getDisplay().getSystemColor(SWT.COLOR_BLUE);
			}
		};
		assertSame(color, initialize(customLabelProvider, fixtures.getTestPackage().getClassForControls_StringFeature()).getBackground(fixtures.getClassForControlsInstance()));
	}

	private TableColumnLabelProvider initialize(TableColumnLabelProvider p, EStructuralFeature f) {
		return forFeature(injectMembers(p), f);
	}

	private TableColumnLabelProvider forFeature(TableColumnLabelProvider p, EStructuralFeature f) {
		p.seteStructuralFeature(f);
		return p;
	}
	
	private void assertExceptionInLog() {
		logAppender.assertContainsMessage("TableColumnLabelProvider.getText");
	}
}
