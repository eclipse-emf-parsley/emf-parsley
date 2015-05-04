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

import java.util.List
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.parsley.EmfParsleyActivator
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest
import org.eclipse.emf.parsley.junit4.util.LogAppenderTestRule
import org.eclipse.emf.parsley.ui.provider.EClassToEStructuralFeatureAsStringsMap
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule

class FeaturesProviderTest extends AbstractEmfParsleyTest {
	
	var protected FeaturesProvider featuresProvider;
	
	@Rule public val LogAppenderTestRule logAppender = new LogAppenderTestRule(EmfParsleyActivator);
	
	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()
	
	@Before
	def void setUpFeaturesProvider() {
		featuresProvider = new FeaturesProvider
		injectMembers(featuresProvider)
	}
	
	@Test def void testNullEObject() {
		featuresProvider.getEObjectFeatures(null).assertFeatureList("")
	}

	@Test def void testNullEClass() {
		featuresProvider.getFeatures(null).assertFeatureList("")
	}

	@Test def void testMapCreationHappensOnlyOnce() {
		featuresProvider.map => [
			assertNotNull
			assertSame(featuresProvider.map)
		]
		featuresProvider.stringMap => [
			assertNotNull
			assertSame(featuresProvider.stringMap)
		]
	}

	@Test def void testEObjectFeatures() {
		featuresProvider.getEObjectFeatures(testFactory.createTestEClass).
			assertFeatureList("lowercaseNameFeature, UpperCaseNameFeature")
	}

	@Test def void testDefaultImplementationWithTestmodel() {
		testPackage.testEClass => [
			assertFeatureList("lowercaseNameFeature, UpperCaseNameFeature")
		]
	}

	@Test def void testDefaultImplementationWithTwoEClasses() {
		testPackage.testEClass => [
			assertFeatureList("lowercaseNameFeature, UpperCaseNameFeature")
		]
		EXTLibraryPackage::eINSTANCE.library => [
			assertFeatureList("address, name, parentBranch, people")
		]
	}

	@Test def void testCustomBuildMap() {
		val customFeaturesProvider = new FeaturesProvider() {
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

	@Test def void testCustomBuildStringMap() {
		val customFeaturesProvider = new FeaturesProvider() {
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

	@Test def void testCustomBuildMapHasPrecedenceOverBuildStringMap() {
		val customFeaturesProvider = new FeaturesProvider() {
			override protected buildMap(EClassToEStructuralFeatureMap map) {
				map.mapTo(testPackage.testEClass, 
					testPackage.testEClass_LowercaseNameFeature
				)
			}
			override protected buildStringMap(EClassToEStructuralFeatureAsStringsMap stringMap) {
				// this won't be called, since we already provide buildMap
				stringMap.mapTo("TestEClass", "upperCaseNameFeature")
			}
			
		}
		customFeaturesProvider.getFeatures(testPackage.testEClass) => [
			assertFeatureList("lowercaseNameFeature")
			// if called again, the same list is returned
			assertSame(
				customFeaturesProvider.getFeatures(testPackage.testEClass))
		]
	}

	@Test def void testNonExistantFeatureInEClass() {
		val customFeaturesProvider = new FeaturesProvider() {
			override protected buildStringMap(EClassToEStructuralFeatureAsStringsMap stringMap) {
				stringMap.mapTo(testPackage.testEClass.instanceClassName, "nonExistantNameFeature")
			}
		}.injectMembers
		// the provider gracefully deals with non existent features
		// and it logs the problem
		customFeaturesProvider.getFeatures(testPackage.testEClass) => [
			assertFeatureList("")
		]
		logAppender.assertContainsMessage("cannot find feature 'nonExistantNameFeature' in EClass 'TestEClass'")
	}

	@Test def void testFilterNotAppliedToCustomImplementation() {
		testPackage.testEClass => [
			buildStringMap("lowercaseNameFeature", "notChangeableFeature")
			// notChangeableFeature would be discarded by the default implementation
			// but since we customized the feature provider, the filter is not applied
			// NOTE: it is responsibility of the programmer to return a list of feature
			// that makes sense
			assertFeatureList("lowercaseNameFeature, notChangeableFeature")
		]
	}
	
	@Test def void testLibraryFeatures() {
		EXTLibraryPackage::eINSTANCE.library => [
			buildStringMap("name", "address")
			assertFeatureList("name, address")
		]
	}

	@Test def void testEmployeeFeaturesWithBuildStringMap() {
		EXTLibraryPackage::eINSTANCE.employee => [
			buildStringMap("firstName", "manager")
			assertFeatureList("firstName, manager")
		]
	}

	@Test def void testEmployeeFeaturesWithBuildMap() {
		EXTLibraryPackage.eINSTANCE.employee => [
			buildMap(
				EXTLibraryPackage.eINSTANCE.person_FirstName, 
				EXTLibraryPackage.eINSTANCE.employee_Manager
			)
			assertFeatureList("firstName, manager")
		]
	}

	@Test def void testUpperCaseFeatureNames() {
		testPackage.testEClass => [
			buildStringMap("lowercaseNameFeature", "upperCaseNameFeature")
			assertFeatureList("lowercaseNameFeature, UpperCaseNameFeature")
		]
	}
	
	def assertFeatureList(EClass eClass, CharSequence expected) {
		featuresProvider.getFeatures(eClass).assertFeatureList(expected)
	}
	
	def assertFeatureList(List<EStructuralFeature> features, CharSequence expected) {
		expected.toString.assertEquals(features.map[name].join(", "))
	}

	def private void buildStringMap(EClass eClass, String...featuresNames) {
		featuresProvider.addToStringMap(eClass, featuresNames);
	}

	def private void buildMap(EClass eClass, EStructuralFeature...features) {
		featuresProvider.getMap.put(eClass, features);
	}

}