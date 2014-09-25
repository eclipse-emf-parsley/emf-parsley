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

import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider
import org.junit.Before
import org.junit.Test

import static extension org.junit.Assert.*

class TableFeaturesProviderTest extends FeaturesProviderTest {
	
	var TableFeaturesProvider tableFeaturesProvider
	
	@Before
	override void setUpFeaturesProvider() {
		featuresProvider = new TableFeaturesProvider
		injectMembers(featuresProvider)
		tableFeaturesProvider = featuresProvider as TableFeaturesProvider
	}
	
	@Test
	def void testGetWeightsDefaultsToNull() {
		tableFeaturesProvider.weights.assertNull
	}

	@Test
	def void testSetWeightsCreatesList() {
		tableFeaturesProvider.setWeights(0, 1)
		new Integer(0).assertEquals(tableFeaturesProvider.weights.head)
		new Integer(1).assertEquals(tableFeaturesProvider.weights.last)
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

}