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
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.ui.provider.DialogFeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.junit.Rule;
import org.junit.Test;

public class DialogFeatureCaptionProviderTest extends AbstractEmfParsleyShellBasedTest {

	@Rule public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	private static final String DERIVED_CLASS_FEATURE_PROP_DESCRIPTION = "Derived Class Feature";

	@Test public void testDefaultTextWithInstance() {
		final var provider = createCaptionProvider();
		final var feature = fixtures.getTestPackage().getDerivedClass_DerivedClassFeature();
		assertEquals(DERIVED_CLASS_FEATURE_PROP_DESCRIPTION,
			provider.getText(createInstance(fixtures.getDerivedClass()), feature));
	}
	
	@Test public void testDefaultText() {
		final var provider = createCaptionProvider();
		final var feature = fixtures.getTestPackage().getDerivedClass_DerivedClassFeature();
		assertEquals(feature.getName(), provider.getText(fixtures.getDerivedClass(), feature));
	}

	@Test public void testCustomDefaultText() {
		// custom defaultText should have precedence over the
		// delegated FeatureCaptionProvider.defaultText
		final var provider = injectMembers(new DialogFeatureCaptionProvider() {
			@Override
			protected String defaultText(EStructuralFeature feature) {
				return "default";
			}
		});
		final var feature = fixtures.getTestPackage().getDerivedClass_DerivedClassFeature();
		assertEquals("default", provider.getText(fixtures.getDerivedClass(), feature));
	}

	@Test public void testDelegatedCustomGetText() {
		// the DialogFeatureCaptionProvider has no customization,
		// but the custom FeatureCaptionProvider has a customization
		// and we check that we delegate to it
		final var provider = createCaptionProvider();
		provider.setDelegate(injectMembers(new FeatureCaptionProvider() {
			public String text_DerivedClass_derivedClassFeature(EStructuralFeature feature) {
				return "custom";
			}
		}));
		final var feature = fixtures.getTestPackage().getDerivedClass_DerivedClassFeature();
		assertEquals("custom", provider.getText(createInstance(fixtures.getDerivedClass()), feature));
	}

	@Test public void testDefaultLabel() {
		final var provider = createCaptionProvider();
		final var feature = fixtures.getTestPackage().getDerivedClass_DerivedClassFeature();
		assertEquals(DERIVED_CLASS_FEATURE_PROP_DESCRIPTION, syncExec(() ->
			provider.getLabel(getShell(), createInstance(fixtures.getDerivedClass()), feature).getText()
		));
	}
	
	@Test public void testLabelTextDifferentFromText() {
		final var expectedText = "DerivedClass.derivedClassFeature";
		final var expectedLabelText = "Label.DerivedClass.derivedClassFeature";
		final var testFeature = fixtures.getTestPackage().getDerivedClass_DerivedClassFeature();
		
		final var provider = new DialogFeatureCaptionProvider() {
			public String text_DerivedClass_derivedClassFeature(EStructuralFeature feature) {
				return expectedText;
			}
			
			public Label label_DerivedClass_derivedClassFeature(Composite parent, EStructuralFeature feature) {
				return createLabel(parent, expectedLabelText);
			}
		};
		
		assertEquals(expectedText, provider.getText(createInstance(fixtures.getDerivedClass()), testFeature));
		assertEquals(expectedLabelText, syncExec(() ->
			provider.getLabel(getShell(), createInstance(fixtures.getDerivedClass()), testFeature).getText()
		));
	}

	@Test public void testDefaultLabelUsesCustomText() {
		final var expectedText = "DerivedClass.derivedClassFeature";
		final var testFeature = fixtures.getTestPackage().getDerivedClass_DerivedClassFeature();
		
		final var provider = new DialogFeatureCaptionProvider() {
			public String text_DerivedClass_derivedClassFeature(EStructuralFeature feature) {
				return expectedText;
			}
		};
		
		assertEquals(expectedText, provider.getText(createInstance(fixtures.getDerivedClass()), testFeature));
		assertEquals(expectedText, syncExec(() ->
			provider.getLabel(getShell(), createInstance(fixtures.getDerivedClass()), testFeature).getText()
		));
	}

	@Test public void testBaseClassFeatureInBaseClass() {
		final var expectedText = "BaseClass.baseClassFeature";
		
		final var provider = new DialogFeatureCaptionProvider() {
			public String text_BaseClass_baseClassFeature(EStructuralFeature feature) {
				return expectedText;
			}
		};
		
		assertEquals(expectedText, provider.getText(
			createInstance(fixtures.getBaseClass()), fixtures.getTestPackage().getBaseClass_BaseClassFeature()
		));
	}

	@Test public void testBaseClassFeatureInDerivedClass() {
		final var expectedText = "BaseClass.baseClassFeature";
		
		final var provider = new DialogFeatureCaptionProvider() {
			public String text_BaseClass_baseClassFeature(EStructuralFeature feature) {
				return expectedText;
			}
		};
		
		assertEquals(expectedText, provider.getText(
			createInstance(fixtures.getDerivedClass()), fixtures.getTestPackage().getBaseClass_BaseClassFeature()
		));
	}

	@Test public void testNoPropertyDescriptionFallsBackToFeatureName() {
		final var provider = createCaptionProvider();
		
		// we simulate the absence of a property description specifying
		// a feature that is not present in the object's EClass
		final var feature = fixtures.getTestPackage().getTestContainer_ClassesForControls();
		assertEquals(feature.getName(), provider.getText(
			createInstance(fixtures.getDerivedClass()), feature
		));
	}

	private DialogFeatureCaptionProvider createCaptionProvider() {
		return getOrCreateInjector().getInstance(DialogFeatureCaptionProvider.class);
	}

	private EObject createInstance(EClass eClass) {
		return EcoreUtil.create(eClass);
	}
}
