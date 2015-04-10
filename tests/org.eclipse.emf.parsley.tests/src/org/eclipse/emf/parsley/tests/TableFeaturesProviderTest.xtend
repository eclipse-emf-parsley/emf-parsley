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
package org.eclipse.emf.parsley.tests

import org.eclipse.emf.parsley.tests.TableFeaturesProviderTest.TestCustomFeaturesProviderForCustomBuildMap
import org.eclipse.emf.parsley.tests.TableFeaturesProviderTest.TestCustomFeaturesProviderForCustomBuildStringMap
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage
import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider
import org.junit.Before
import org.junit.Test

import static extension org.junit.Assert.*

class TableFeaturesProviderTest extends FeaturesProviderTest {
	
	var TableFeaturesProvider tableFeaturesProvider
	
	/**
	 * This will be used to test that TableFeaturesProvider delegates also to
	 * a possibly customized injected FeaturesProvider
	 */
	static class TestCustomFeaturesProviderForCustomBuildMap extends FeaturesProvider {
		override protected buildMap(EClassToEStructuralFeatureMap map) {
			map.mapTo(TestmodelsPackage.eINSTANCE.testEClass, 
				TestmodelsPackage.eINSTANCE.testEClass_LowercaseNameFeature
			)
		}
	}

	/**
	 * This will be used to test that TableFeaturesProvider delegates also to
	 * a possibly customized injected FeaturesProvider
	 */
	static class TestCustomFeaturesProviderForCustomBuildStringMap extends FeaturesProvider {
		override protected buildStringMap(EClassToEStructuralFeatureAsStringsMap stringMap) {
			stringMap.mapTo(TestmodelsPackage.eINSTANCE.testEClass.instanceClassName, "lowercaseNameFeature")
		}
	}
	
	@Before
	override void setUpFeaturesProvider() {
		featuresProvider = new TableFeaturesProvider
		injectMembers(featuresProvider)
		tableFeaturesProvider = featuresProvider as TableFeaturesProvider
	}
	
	@Test def void testTableFeaturesProviderCustomBuildMap() {
		val customFeaturesProvider = new TableFeaturesProvider() {
			override protected buildMap(EClassToEStructuralFeatureMap map) {
				map.mapTo(testPackage.testEClass, 
					testPackage.testEClass_LowercaseNameFeature
				)
			}
		}
		customFeaturesProvider.getFeatures(testPackage.testEClass) => [
			assertFeatureList("lowercaseNameFeature")
			// if called again, the same list is returned
			assertSame(
				customFeaturesProvider.getFeatures(testPackage.testEClass))
		]
	}

	@Test def void testTableFeaturesProviderCustomBuildMapDelegated() {
		// the customization is not in the TableFeaturesProvider but in 
		// FeaturesProvider to which TableFeaturesProvider delegates to
		val customFeaturesProvider = createInjector(
			new EmfParsleyGuiceModuleForTesting() {
				override bindFeaturesProvider() {
					TestCustomFeaturesProviderForCustomBuildMap
				}
			}
		).getInstance(TableFeaturesProvider)
		customFeaturesProvider.getFeatures(testPackage.testEClass) => [
			assertFeatureList("lowercaseNameFeature")
			// if called again, the same list is returned
			assertSame(customFeaturesProvider.getFeatures(testPackage.testEClass))
		]
	}

	@Test def void testTableFeaturesCustomBuildStringMap() {
		val customFeaturesProvider = new TableFeaturesProvider() {
			override protected buildStringMap(EClassToEStructuralFeatureAsStringsMap stringMap) {
				stringMap.mapTo(testPackage.testEClass.instanceClassName, "lowercaseNameFeature")
			}
		}.injectMembers
		customFeaturesProvider.getFeatures(testPackage.testEClass) => [
			assertFeatureList("lowercaseNameFeature")
			// if called again, the same list is returned
			assertSame(
				customFeaturesProvider.getFeatures(testPackage.testEClass))
		]
	}

	@Test def void testTableFeaturesProviderCustomBuildStringMapDelegated() {
		// the customization is not in the TableFeaturesProvider but in 
		// FeaturesProvider to which TableFeaturesProvider delegates to
		val customFeaturesProvider = createInjector(
			new EmfParsleyGuiceModuleForTesting() {
				override bindFeaturesProvider() {
					TestCustomFeaturesProviderForCustomBuildStringMap
				}
			}
		).getInstance(TableFeaturesProvider)
		customFeaturesProvider.getFeatures(testPackage.testEClass) => [
			assertFeatureList("lowercaseNameFeature")
			// if called again, the same list is returned
			assertSame(customFeaturesProvider.getFeatures(testPackage.testEClass))
		]
	}

}