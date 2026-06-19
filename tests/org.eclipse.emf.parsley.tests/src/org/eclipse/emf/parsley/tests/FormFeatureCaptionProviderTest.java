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

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class FormFeatureCaptionProviderTest extends AbstractEmfParsleyShellBasedTest {
	
	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();
	
	private final TestmodelsPackage testPackage = TestmodelsPackage.eINSTANCE;
	
	private FormColors formColors = null;
	
	@Before
	public void createFormColors() {
		syncExecVoid(() -> formColors = new FormColors(getDisplay()));
	}
	
	@Test
	public void testDefaultText() {
		FormFeatureCaptionProvider provider = new FormFeatureCaptionProvider();
		initialize(provider);
		EStructuralFeature feature = testPackage.getDerivedClass_DerivedClassFeature();
		assertEquals(feature.getName(), provider.getText(fixtures.getDerivedClass(), feature));
	}

	@Test
	public void testDefaultLabel() {
		FormFeatureCaptionProvider provider = new FormFeatureCaptionProvider();
		initialize(provider);
		EStructuralFeature feature = testPackage.getDerivedClass_DerivedClassFeature();
		assertEquals("Derived Class Feature", syncExec(() -> 
			provider.getLabel(getShell(), createInstance(fixtures.getDerivedClass()), feature).getText()
		));
	}
	
	@Test
	public void testLabelTextDifferentFromText() {
		final String expectedText = "DerivedClass.derivedClassFeature";
		final String expectedLabelText = "Label.DerivedClass.derivedClassFeature";
		final EStructuralFeature testFeature = testPackage.getDerivedClass_DerivedClassFeature();
		
		FormFeatureCaptionProvider provider = new FormFeatureCaptionProvider() {
			public String text_DerivedClass_derivedClassFeature(EStructuralFeature feature) {
				return expectedText;
			}
			
			public Label label_DerivedClass_derivedClassFeature(Composite parent, EStructuralFeature feature) {
				return createLabel(parent, expectedLabelText);
			}
		};
		initialize(provider);
		
		assertEquals(expectedText, provider.getText(createInstance(fixtures.getDerivedClass()), testFeature));
		assertEquals(expectedLabelText, syncExec(() -> 
			provider.getLabel(getShell(), createInstance(fixtures.getDerivedClass()), testFeature).getText()
		));
	}

	@Test
	public void testCreatedLabelUsesFormToolkit() {
		final String expectedLabelText = "Label.DerivedClass.derivedClassFeature";
		final EStructuralFeature testFeature = testPackage.getDerivedClass_DerivedClassFeature();
		
		FormFeatureCaptionProvider provider = new FormFeatureCaptionProvider() {
			public Label label_DerivedClass_derivedClassFeature(Composite parent, EStructuralFeature feature) {
				return createLabel(parent, expectedLabelText);
			}
		};
		initialize(provider);
		
		// the FormToolkit adapts the label using FormColors
		// so we check that it is actually adapted
		assertEquals(formColors.getBackground(), syncExec(() -> 
			provider.getLabel(getShell(), createInstance(fixtures.getDerivedClass()), testFeature).getBackground()
		));
	}

	@Test
	public void testDefaultLabelUsesCustomText() {
		final String expectedText = "DerivedClass.derivedClassFeature";
		final EStructuralFeature testFeature = testPackage.getDerivedClass_DerivedClassFeature();
		
		FormFeatureCaptionProvider provider = new FormFeatureCaptionProvider() {
			public String text_DerivedClass_derivedClassFeature(EStructuralFeature feature) {
				return expectedText;
			}
		};
		initialize(provider);
		
		assertEquals(expectedText, provider.getText(createInstance(fixtures.getDerivedClass()), testFeature));
		assertEquals(expectedText, syncExec(() -> 
			provider.getLabel(getShell(), createInstance(fixtures.getDerivedClass()), testFeature).getText()
		));
	}

	@Test
	public void testBaseClassFeatureInBaseClass() {
		final String expectedText = "BaseClass.baseClassFeature";
		
		FormFeatureCaptionProvider provider = new FormFeatureCaptionProvider() {
			public String text_BaseClass_baseClassFeature(EStructuralFeature feature) {
				return expectedText;
			}
		};
		initialize(provider);
		
		assertEquals(expectedText, provider.getText(
			createInstance(fixtures.getBaseClass()), testPackage.getBaseClass_BaseClassFeature()
		));
	}

	@Test
	public void testBaseClassFeatureInDerivedClass() {
		final String expectedText = "BaseClass.baseClassFeature";
		
		FormFeatureCaptionProvider provider = new FormFeatureCaptionProvider() {
			public String text_BaseClass_baseClassFeature(EStructuralFeature feature) {
				return expectedText;
			}
		};
		initialize(provider);
		
		assertEquals(expectedText, provider.getText(
			createInstance(fixtures.getDerivedClass()), testPackage.getBaseClass_BaseClassFeature()
		));
	}

	private void initialize(FormFeatureCaptionProvider provider) {
		syncExecVoid(() -> 
			provider.setFormToolkit(new FormToolkit(getDisplay()))
		);
		injectMembers(provider);
	}

	private EObject createInstance(EClass eClass) {
		return EcoreUtil.create(eClass);
	}
}
