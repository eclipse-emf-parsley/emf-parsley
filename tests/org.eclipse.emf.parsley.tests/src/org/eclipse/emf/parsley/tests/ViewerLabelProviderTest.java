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
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ViewerLabelProviderTest extends AbstractImageBasedTest {

	private ViewerLabelProvider labelProvider;

	@Before
	public void setupLabelProvider() {
		labelProvider = getOrCreateInjector().getInstance(ViewerLabelProvider.class);
	}

	@After
	public void disposeLabelProvider() {
		labelProvider.dispose();
	}

	@Test
	public void testGetTextNull() {
		assertEquals("", labelProvider.getText(null));
	}

	@Test
	public void testDefaultGetText() {
		assertEquals("Class For Controls", labelProvider.getText(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testDefaultGetTextForFeatureMapEntry() {
		fixtures.getClassForControlsInstance().getFeatureMapEntries1().add(fixtures.createClassForFeatureMapEntry1("1"));
		fixtures.getClassForControlsInstance().getFeatureMapEntries2().add(fixtures.createClassForFeatureMapEntry2("2"));
		assertEquals("Class For Feature Map Entry1 1",
			labelProvider.getText(fixtures.getClassForControlsInstance().getFeatureMapEntries().get(0)));
		assertEquals("Class For Feature Map Entry2 2",
			labelProvider.getText(fixtures.getClassForControlsInstance().getFeatureMapEntries().get(1)));
	}

	@Test
	public void testDefaultGetTextForIterable() {
		var classesWithName = fixtures.getTestContainer().getClassesWithName();
		classesWithName.add(fixtures.createClassWithName("1"));
		classesWithName.add(fixtures.createClassWithName("2"));
		classesWithName.add(fixtures.createClassWithName("3"));
		assertEquals("Class With Name 1, Class With Name 2, Class With Name 3",
			labelProvider.getText(fixtures.getTestContainer().getClassesWithName()));
	}

	@Test
	public void testDefaultGetTextForIterableTooLong() {
		setupContainerWith10Elems();
		assertEquals("Class With Name 0, Class With Name 1, Class With Name 2, Class With Name 3, Clas...",
			labelProvider.getText(fixtures.getTestContainer().getClassesWithName()));
	}
	

	@Test
	public void testDefaultGetTextForIterableWithCustomConstants() {
		setupContainerWith10Elems();
		assertEquals("Class With Name 0 - Class With Name 1 - Class With <continued>...",
			createInjector(
				new EmfParsleyGuiceModuleForTesting() {
					@Override
					public String valueIterableStringSeparator() {
						return " - ";
					}
					@Override
					public String valueIterableStringEllipses() {
						return " <continued>...";
					}
					@Override
					public Integer valueIterableStringMaxLength() {
						return 50;
					}
				}
			).getInstance(ILabelProvider.class)
			.getText(fixtures.getTestContainer().getClassesWithName())
		);
	}

	@Test
	public void testCustomText() {
		var customLabelProvider = injectMembers(new ViewerLabelProvider(null) {
			public String text(ClassForControls e) {
				return "Test";
			}
		});
		assertEquals("Test", customLabelProvider.getText(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testCustomTextForList() {
		setupContainerWith10Elems();
		var customLabelProvider = injectMembers(new ViewerLabelProvider(null) {
			public String text(List<ClassWithName> l) {
				return l.stream().map(ClassWithName::getName).collect(Collectors.joining(","));
			}
		});
		assertEquals("0,1,2,3,4,5,6,7,8,9", customLabelProvider.getText(fixtures.getTestContainer().getClassesWithName()));
	}

	@Test
	public void testGetImageNull() {
		assertNull(labelProvider.getImage(null));
	}

	@Test
	public void testDefaultGetImage() {
		Image image = labelProvider.getImage(fixtures.getClassForControlsInstance());
		assertNotNull(image);
		assertImageIs(getDefaultEMFImageForClassForControls(), image);
	}

	@Test
	public void testDefaultGetImageForFeatureMapEntry() {
		fixtures.getClassForControlsInstance().getFeatureMapEntries1().add(fixtures.createClassForFeatureMapEntry1("1"));
		Image image = labelProvider.getImage(fixtures.getClassForControlsInstance().getFeatureMapEntries().get(0));
		assertNotNull(image);
		assertImageIs(getDefaultEMFImageForClassForFeatureMapEntry1(), image);
	}
	
	@Test
	public void testCustomImageAsString() {
		var customLabelProvider = injectMembers(new ViewerLabelProvider(null) {
			public String image(ClassForControls e) {
				return TEST_IMAGE;
			}
		});
		Image image = customLabelProvider.getImage(fixtures.getClassForControlsInstance());
		assertNotNull(image);
		assertImageIs(loadTestImage(), image);
	}

	@Test
	public void testCustomImageAsImage() {
		var customLabelProvider = injectMembers(new ViewerLabelProvider(null) {
			public Image image(ClassForControls e) {
				return loadTestImage();
			}
		});
		Image image = customLabelProvider.getImage(fixtures.getClassForControlsInstance());
		assertNotNull(image);
		assertImageIs(loadTestImage(), image);
	}

	@Test
	public void testCustomImageAsImageDescriptor() {
		var customLabelProvider = injectMembers(new ViewerLabelProvider(null) {
			public ImageDescriptor image(ClassForControls e) {
				return ImageDescriptor.createFromImage(loadTestImage());
			}
		});
		Image image = customLabelProvider.getImage(fixtures.getClassForControlsInstance());
		assertNotNull(image);
		assertImageIs(loadTestImage(), image);
	}

	@Test
	public void testWrongCustomImageAsInteger() {
		var customLabelProvider = injectMembers(new ViewerLabelProvider(getDelegateLabelProvider()) {
			public int image(ClassForControls e) {
				return 0;
			}
		});
		// this will default to EMF image
		Image image = customLabelProvider.getImage(fixtures.getClassForControlsInstance());
		assertNotNull(image);
		assertImageIs(getDefaultEMFImageForClassForControls(), image);
	}

	@Test
	public void testGetFontNull() {
		assertEquals(null, labelProvider.getFont(null));
	}

	@Test
	public void testDefaultGetFont() {
		assertEquals(null, labelProvider.getFont(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testCustomFont() {
		var customLabelProvider = injectMembers(new ViewerLabelProvider(null) {
			public Font font(ClassForControls e) {
				return new Font(getShell().getDisplay(), JFaceResources.DEFAULT_FONT, 14, SWT.BOLD);
			}
		});
		assertNotNull(customLabelProvider.getFont(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testGetForegroundNull() {
		assertEquals(null, labelProvider.getForeground(null));
	}

	@Test
	public void testDefaultGetForeground() {
		assertEquals(null, labelProvider.getForeground(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testCustomForeground() {
		var customLabelProvider = injectMembers(new ViewerLabelProvider(null) {
			public org.eclipse.swt.graphics.Color foreground(ClassForControls e) {
				return getShell().getDisplay().getSystemColor(SWT.COLOR_BLUE);
			}
		});
		assertNotNull(customLabelProvider.getForeground(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testGetBackgroundNull() {
		assertEquals(null, labelProvider.getBackground(null));
	}

	@Test
	public void testDefaultGetBackground() {
		assertEquals(null, labelProvider.getBackground(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testCustomBackground() {
		var customLabelProvider = injectMembers(new ViewerLabelProvider(null) {
			public org.eclipse.swt.graphics.Color background(ClassForControls e) {
				return getShell().getDisplay().getSystemColor(SWT.COLOR_BLUE);
			}
		});
		assertNotNull(customLabelProvider.getBackground(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testIsValueProperty() {
		assertTrue(labelProvider.isLabelProperty(fixtures.getLibrary(), fixtures.getLibraryPackage().getLibrary_Name().getName()));
	}

	@Test
	public void testListeners() { // NOSONAR: we just ensure it doesn't throw
		var listener = mock(ILabelProviderListener.class);
		labelProvider.addListener(listener);
		labelProvider.removeListener(listener);
	}

	private void setupContainerWith10Elems() {
		var classesWithName = fixtures.getTestContainer().getClassesWithName();
		for (int i = 0; i < 10; i++) {
			classesWithName.add(fixtures.createClassWithName("" + i));
		}
	}
	
	private Image getDefaultEMFImageForClassForControls() {
		return getEMFImageFromObject(getEMFImage(fixtures.getTestFactory().createClassForControls()));
	}

	private Image getDefaultEMFImageForClassForFeatureMapEntry1() {
		return getEMFImageFromObject(getEMFImage(fixtures.getTestFactory().createClassForFeatureMapEntry1()));
	}

}
