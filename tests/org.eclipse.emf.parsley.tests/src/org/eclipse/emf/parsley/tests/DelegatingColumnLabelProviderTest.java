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

import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls;
import org.eclipse.emf.parsley.ui.provider.DelegatingColumnLabelProvider;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Inject;

public class DelegatingColumnLabelProviderTest extends AbstractImageBasedTest {

	public static class CustomViewerLabelProvider extends ViewerLabelProvider {

		@Inject
		private Shell shell;

		public CustomViewerLabelProvider() {
			super(null);
		}

		public String text(ClassForControls e) {
			return "Test";
		}

		public String image(ClassForControls e) {
			return TEST_IMAGE;
		}

		public Font font(ClassForControls e) {
			return new Font(shell.getDisplay(), JFaceResources.DEFAULT_FONT, 14, SWT.BOLD);
		}

		public Color foreground(ClassForControls e) {
			return shell.getDisplay().getSystemColor(SWT.COLOR_BLUE);
		}

		public Color background(ClassForControls e) {
			return shell.getDisplay().getSystemColor(SWT.COLOR_BLUE);
		}
	}

	@Inject
	private DelegatingColumnLabelProvider labelProvider;

	@Before
	public void setupLabelProvider() {
		getOrCreateInjector().injectMembers(this);
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
	public void testCustomText() {
		DelegatingColumnLabelProvider customLabelProvider = withCustomLabelProvider();
		assertEquals("Test", customLabelProvider.getText(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testDefaultGetImage() {
		Image image = labelProvider.getImage(fixtures.getClassForControlsInstance());
		assertNotNull(image);
		assertImageIs(getDefaultEMFImageForClassForControls(), image);
	}

	@Test
	public void testCustomImageAsString() {
		DelegatingColumnLabelProvider customLabelProvider = withCustomLabelProvider();
		Image image = customLabelProvider.getImage(fixtures.getClassForControlsInstance());
		assertNotNull(image);
		assertImageIs(loadTestImage(), image);
	}

	@Test
	public void testGetFontNull() {
		assertNull(labelProvider.getFont(null));
	}

	@Test
	public void testDefaultGetFont() {
		assertNull(labelProvider.getFont(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testDefaultGetFontWithBaseLabelProvider() {
		assertNull(withBaseLabelProvider().getFont(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testCustomFont() {
		DelegatingColumnLabelProvider customLabelProvider = withCustomLabelProvider();
		Font font = customLabelProvider.getFont(fixtures.getClassForControlsInstance());
		assertNotNull(font);
	}

	@Test
	public void testGetForegroundNull() {
		assertNull(labelProvider.getForeground(null));
	}

	@Test
	public void testDefaultGetForeground() {
		assertNull(labelProvider.getForeground(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testDefaultGetForegroundWithBaseLabelProvider() {
		assertNull(withBaseLabelProvider().getForeground(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testCustomForeground() {
		DelegatingColumnLabelProvider customLabelProvider = withCustomLabelProvider();
		Color color = customLabelProvider.getForeground(fixtures.getClassForControlsInstance());
		assertNotNull(color);
	}

	@Test
	public void testGetBackgroundNull() {
		assertNull(labelProvider.getBackground(null));
	}

	@Test
	public void testDefaultGetBackground() {
		assertNull(labelProvider.getBackground(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testDefaultGetBackgroundWithBaseLabelProvider() {
		assertNull(withBaseLabelProvider().getBackground(fixtures.getClassForControlsInstance()));
	}

	@Test
	public void testCustomBackground() {
		DelegatingColumnLabelProvider customLabelProvider = withCustomLabelProvider();
		Color color = customLabelProvider.getBackground(fixtures.getClassForControlsInstance());
		assertNotNull(color);
	}

	private Image getDefaultEMFImageForClassForControls() {
		return getEMFImageFromObject(getEMFImage(fixtures.getTestFactory().createClassForControls()));
	}

	private DelegatingColumnLabelProvider withCustomLabelProvider() {
		return createInjector(new EmfParsleyJavaGuiceModule() {

			@Override
			public Class<? extends ILabelProvider> bindILabelProvider() {
				return CustomViewerLabelProvider.class;
			}

			public Shell bindShell() {
				return getShell();
			}

		}).getInstance(DelegatingColumnLabelProvider.class);
	}

	/**
	 * The injected ILabelProvider is not an IColorProvider nor an IFontProvider
	 */
	private DelegatingColumnLabelProvider withBaseLabelProvider() {
		return createInjector(new EmfParsleyJavaGuiceModule() {

			@Override
			public Class<? extends ILabelProvider> bindILabelProvider() {
				return LabelProvider.class;
			}

		}).getInstance(DelegatingColumnLabelProvider.class);
	}

}
