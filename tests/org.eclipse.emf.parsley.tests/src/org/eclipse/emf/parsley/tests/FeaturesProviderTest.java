/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.EmfParsleyActivator;
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest;
import org.eclipse.emf.parsley.junit4.util.LogAppenderTestRule;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class FeaturesProviderTest extends AbstractEmfParsleyTest {

	protected FeaturesProvider featuresProvider;

	@Rule
	public LogAppenderTestRule logAppender = new LogAppenderTestRule(EmfParsleyActivator.class);

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	@Before
	public void setUpFeaturesProvider() {
		featuresProvider = new FeaturesProvider();
		injectMembers(featuresProvider);
	}

	@Test
	public void testNullEObject() {
		assertFeatureList(featuresProvider.getEObjectFeatures(null), "");
	}

	@Test
	public void testNullEClass() {
		assertFeatureList(featuresProvider.getFeatures(null), "");
	}

	@Test
	public void testMapCreationHappensOnlyOnce() {
		final var map = featuresProvider.getMap();
		assertNotNull(map);
		assertSame(map, featuresProvider.getMap());
		final var stringMap = featuresProvider.getStringMap();
		assertNotNull(stringMap);
		assertSame(stringMap, featuresProvider.getStringMap());
	}

	@Test
	public void testEObjectFeatures() {
		assertFeatureList(featuresProvider.getEObjectFeatures(fixtures.getTestFactory().createTestEClass()),
			"lowercaseNameFeature, UpperCaseNameFeature");
	}

	@Test
	public void testDefaultImplementationWithTestmodel() {
		assertFeatureList(fixtures.getTestPackage().getTestEClass(), "lowercaseNameFeature, UpperCaseNameFeature");
	}

	@Test
	public void testDefaultImplementationWithTwoEClasses() {
		assertFeatureList(fixtures.getTestPackage().getTestEClass(), "lowercaseNameFeature, UpperCaseNameFeature");
		assertFeatureList(EXTLibraryPackage.eINSTANCE.getLibrary(), "address, name, parentBranch, people");
	}

	@Test
	public void testCustomBuildMap() {
		final var customFeaturesProvider = new FeaturesProvider() {
			@Override
			protected void buildMap(EClassToEStructuralFeatureMap map) {
				map.mapTo(fixtures.getTestPackage().getTestEClass(),
					fixtures.getTestPackage().getTestEClass_LowercaseNameFeature()
				);
			}
		};
		final var features = customFeaturesProvider.getFeatures(fixtures.getTestPackage().getTestEClass());
		assertFeatureList(features, "lowercaseNameFeature");
		// if called again, the same list is returned
		assertSame(features, customFeaturesProvider.getFeatures(fixtures.getTestPackage().getTestEClass()));
	}

	@Test
	public void testCustomBuildStringMap() {
		final var customFeaturesProvider = new FeaturesProvider() {
			@Override
			protected void buildStringMap(EClassToEStructuralFeatureAsStringsMap stringMap) {
				stringMap.mapTo(fixtures.getTestPackage().getTestEClass().getInstanceClassName(), "lowercaseNameFeature");
			}
		};
		injectMembers(customFeaturesProvider);
		final var features = customFeaturesProvider.getFeatures(fixtures.getTestPackage().getTestEClass());
		assertFeatureList(features, "lowercaseNameFeature");
		// if called again, the same list is returned
		assertSame(features, customFeaturesProvider.getFeatures(fixtures.getTestPackage().getTestEClass()));
	}

	@Test
	public void testCustomBuildMapHasPrecedenceOverBuildStringMap() {
		final var customFeaturesProvider = new FeaturesProvider() {
			@Override
			protected void buildMap(EClassToEStructuralFeatureMap map) {
				map.mapTo(fixtures.getTestPackage().getTestEClass(),
					fixtures.getTestPackage().getTestEClass_LowercaseNameFeature()
				);
			}
			@Override
			protected void buildStringMap(EClassToEStructuralFeatureAsStringsMap stringMap) {
				// this won't be called, since we already provide buildMap
				stringMap.mapTo("TestEClass", "upperCaseNameFeature");
			}
		};
		final var features = customFeaturesProvider.getFeatures(fixtures.getTestPackage().getTestEClass());
		assertFeatureList(features, "lowercaseNameFeature");
		// if called again, the same list is returned
		assertSame(features, customFeaturesProvider.getFeatures(fixtures.getTestPackage().getTestEClass()));
	}

	@Test
	public void testNonExistantFeatureInEClass() {
		final var customFeaturesProvider = new FeaturesProvider() {
			@Override
			protected void buildStringMap(EClassToEStructuralFeatureAsStringsMap stringMap) {
				stringMap.mapTo(fixtures.getTestPackage().getTestEClass().getInstanceClassName(), "nonExistantNameFeature");
			}
		};
		injectMembers(customFeaturesProvider);
		// the provider gracefully deals with non existent features
		// and it logs the problem
		assertFeatureList(customFeaturesProvider.getFeatures(fixtures.getTestPackage().getTestEClass()), "");
		logAppender.assertContainsMessage(
			"cannot find feature 'nonExistantNameFeature' in EClass 'TestEClass" +
				" (org.eclipse.emf.parsley.tests.models.testmodels.TestEClass)'");
	}

	@Test
	public void testFilterNotAppliedToCustomImplementation() {
		buildStringMap(fixtures.getTestPackage().getTestEClass(), "lowercaseNameFeature", "notChangeableFeature");
		// notChangeableFeature would be discarded by the default implementation
		// but since we customized the feature provider, the filter is not applied
		// NOTE: it is responsibility of the programmer to return a list of feature
		// that makes sense
		assertFeatureList(fixtures.getTestPackage().getTestEClass(), "lowercaseNameFeature, notChangeableFeature");
	}

	@Test
	public void testLibraryFeatures() {
		buildStringMap(EXTLibraryPackage.eINSTANCE.getLibrary(), "name", "address");
		assertFeatureList(EXTLibraryPackage.eINSTANCE.getLibrary(), "name, address");
	}

	@Test
	public void testEmployeeFeaturesWithBuildStringMap() {
		buildStringMap(EXTLibraryPackage.eINSTANCE.getEmployee(), "firstName", "manager");
		assertFeatureList(EXTLibraryPackage.eINSTANCE.getEmployee(), "firstName, manager");
	}

	@Test
	public void testEmployeeFeaturesWithBuildMap() {
		buildMap(EXTLibraryPackage.eINSTANCE.getEmployee(),
			EXTLibraryPackage.eINSTANCE.getPerson_FirstName(),
			EXTLibraryPackage.eINSTANCE.getEmployee_Manager()
		);
		assertFeatureList(EXTLibraryPackage.eINSTANCE.getEmployee(), "firstName, manager");
	}

	@Test
	public void testUpperCaseFeatureNames() {
		buildStringMap(fixtures.getTestPackage().getTestEClass(), "lowercaseNameFeature", "upperCaseNameFeature");
		assertFeatureList(fixtures.getTestPackage().getTestEClass(), "lowercaseNameFeature, UpperCaseNameFeature");
	}

	private void assertFeatureList(EClass eClass, CharSequence expected) {
		assertFeatureList(featuresProvider.getFeatures(eClass), expected);
	}

	private void assertFeatureList(List<EStructuralFeature> features, CharSequence expected) {
		assertEquals(expected.toString(), features.stream().map(EStructuralFeature::getName).collect(Collectors.joining(", ")));
	}

	private void buildStringMap(EClass eClass, String... featuresNames) {
		featuresProvider.addToStringMap(eClass, featuresNames);
	}

	private void buildMap(EClass eClass, EStructuralFeature... features) {
		featuresProvider.getMap().put(eClass, Arrays.asList(features));
	}
}
