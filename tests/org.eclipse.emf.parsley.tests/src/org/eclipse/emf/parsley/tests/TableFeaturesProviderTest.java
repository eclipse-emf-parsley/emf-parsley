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
import static org.junit.Assert.assertSame;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage;
import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider;
import org.junit.Before;
import org.junit.Test;

public class TableFeaturesProviderTest extends FeaturesProviderTest {
	
	/**
	 * This will be used to test that TableFeaturesProvider delegates also to
	 * a possibly customized injected FeaturesProvider
	 */
	static class TestCustomFeaturesProviderForCustomBuildMap extends FeaturesProvider {
		@Override
		protected void buildMap(EClassToEStructuralFeatureMap map) {
			map.mapTo(TestmodelsPackage.eINSTANCE.getTestEClass(), 
				TestmodelsPackage.eINSTANCE.getTestEClass_LowercaseNameFeature()
			);
		}
	}

	/**
	 * This will be used to test that TableFeaturesProvider delegates also to
	 * a possibly customized injected FeaturesProvider
	 */
	static class TestCustomFeaturesProviderForCustomBuildStringMap extends FeaturesProvider {
		@Override
		protected void buildStringMap(EClassToEStructuralFeatureAsStringsMap stringMap) {
			stringMap.mapTo(TestmodelsPackage.eINSTANCE.getTestEClass().getInstanceClassName(), "lowercaseNameFeature");
		}
	}
	
	@Before
	@Override
	public void setUpFeaturesProvider() {
		featuresProvider = new TableFeaturesProvider();
		injectMembers(featuresProvider);
	}
	
	@Test
	public void testTableFeaturesProviderCustomBuildMap() {
		final var customFeaturesProvider = new TableFeaturesProvider() {
			@Override
			protected void buildMap(EClassToEStructuralFeatureMap map) {
				map.mapTo(fixtures.getTestPackage().getTestEClass(), 
					fixtures.getTestPackage().getTestEClass_LowercaseNameFeature()
				);
			}
		};
		var features = customFeaturesProvider.getFeatures(fixtures.getTestPackage().getTestEClass());
		assertFeatureList(features, "lowercaseNameFeature");
		// if called again, the same list is returned
		assertSame(
			features,
			customFeaturesProvider.getFeatures(fixtures.getTestPackage().getTestEClass()));
	}

	@Test
	public void testTableFeaturesProviderCustomBuildMapDelegated() {
		// the customization is not in the TableFeaturesProvider but in 
		// FeaturesProvider to which TableFeaturesProvider delegates to
		final var customFeaturesProvider = createInjector(
			new EmfParsleyGuiceModuleForTesting() {
				@Override
				public Class<? extends FeaturesProvider> bindFeaturesProvider() {
					return TestCustomFeaturesProviderForCustomBuildMap.class;
				}
			}
		).getInstance(TableFeaturesProvider.class);
		var features = customFeaturesProvider.getFeatures(fixtures.getTestPackage().getTestEClass());
		assertFeatureList(features, "lowercaseNameFeature");
		// if called again, the same list is returned
		assertSame(features, customFeaturesProvider.getFeatures(fixtures.getTestPackage().getTestEClass()));
	}

	@Test
	public void testTableFeaturesCustomBuildStringMap() {
		final var customFeaturesProvider = injectMembers(new TableFeaturesProvider() {
			@Override
			protected void buildStringMap(EClassToEStructuralFeatureAsStringsMap stringMap) {
				stringMap.mapTo(fixtures.getTestPackage().getTestEClass().getInstanceClassName(), "lowercaseNameFeature");
			}
		});
		var features = customFeaturesProvider.getFeatures(fixtures.getTestPackage().getTestEClass());
		assertFeatureList(features, "lowercaseNameFeature");
		// if called again, the same list is returned
		assertSame(
			features,
			customFeaturesProvider.getFeatures(fixtures.getTestPackage().getTestEClass()));
	}

	@Test
	public void testTableFeaturesProviderCustomBuildStringMapDelegated() {
		// the customization is not in the TableFeaturesProvider but in 
		// FeaturesProvider to which TableFeaturesProvider delegates to
		final var customFeaturesProvider = createInjector(
			new EmfParsleyGuiceModuleForTesting() {
				@Override
				public Class<? extends FeaturesProvider> bindFeaturesProvider() {
					return TestCustomFeaturesProviderForCustomBuildStringMap.class;
				}
			}
		).getInstance(TableFeaturesProvider.class);
		var features = customFeaturesProvider.getFeatures(fixtures.getTestPackage().getTestEClass());
		assertFeatureList(features, "lowercaseNameFeature");
		// if called again, the same list is returned
		assertSame(features, customFeaturesProvider.getFeatures(fixtures.getTestPackage().getTestEClass()));
	}

	protected void assertFeatureList(List<EStructuralFeature> features, String expected) {
		assertEquals(expected, features.stream().map(EStructuralFeature::getName).collect(Collectors.joining(", ")));
	}

}
