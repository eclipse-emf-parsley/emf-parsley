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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.junit.Rule;
import org.junit.Test;

public class FeatureCaptionProviderTest {

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	@Test
	public void testDefault() {
		final var provider = new FeatureCaptionProvider();
		final var feature = fixtures.getTestPackage().getDerivedClass_DerivedClassFeature();
		assertEquals(feature.getName(), provider.getText(fixtures.getDerivedClass(), feature));
	}

	@Test
	public void testDerivedClassFeatureInDerivedClass() {
		final String expectedText = "DerivedClass.derivedClassFeature";

		final var provider = new FeatureCaptionProvider() {
			public String text_DerivedClass_derivedClassFeature(EStructuralFeature feature) {
				return expectedText;
			}
		};

		assertEquals(expectedText, provider.getText(
			fixtures.getDerivedClass(), fixtures.getTestPackage().getDerivedClass_DerivedClassFeature()
		));
	}

	@Test
	public void testBaseClassFeatureInBaseClass() {
		final String expectedText = "BaseClass.baseClassFeature";

		final var provider = new FeatureCaptionProvider() {
			public String text_BaseClass_baseClassFeature(EStructuralFeature feature) {
				return expectedText;
			}
		};

		assertEquals(expectedText, provider.getText(
			fixtures.getBaseClass(), fixtures.getTestPackage().getBaseClass_BaseClassFeature()
		));
	}

	@Test
	public void testBaseClassFeatureInDerivedClass() {
		final String expectedText = "DerivedClass.baseClassFeature";

		final var provider = new FeatureCaptionProvider() {
			public String text_DerivedClass_baseClassFeature(EStructuralFeature feature) {
				return expectedText;
			}
		};

		assertEquals(expectedText, provider.getText(
			fixtures.getDerivedClass(), fixtures.getTestPackage().getBaseClass_BaseClassFeature()
		));
	}

}
