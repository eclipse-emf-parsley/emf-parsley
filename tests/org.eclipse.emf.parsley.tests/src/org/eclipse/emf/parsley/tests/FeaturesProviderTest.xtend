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
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider
import org.junit.Before
import org.junit.Test

import static extension org.junit.Assert.*
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage
import org.eclipse.emf.parsley.ecore.FeatureResolver

class FeaturesProviderTest {
	
	var featuresProvider = new FeaturesProvider;
	
	@Before
	def void setUp() {
		featuresProvider = new FeaturesProvider
		featuresProvider.featureResolver = new FeatureResolver
	}
	
	def private void buildStringMap(EClass eClass, String...featuresNames) {
		featuresProvider.addToStringMap(eClass, featuresNames);
	}

	@Test def void testDefaultImplementationWithTestmodel() {
		TestmodelsPackage::eINSTANCE.testEClass => [
			assertFeatureList("lowercaseNameFeature, UpperCaseNameFeature")
		]
	}

	@Test def void testFilterNotAppliedToCustomImplementation() {
		TestmodelsPackage::eINSTANCE.testEClass => [
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

	@Test def void testEmployeeFeatures() {
		EXTLibraryPackage::eINSTANCE.employee => [
			buildStringMap("firstName", "manager")
			assertFeatureList("firstName, manager")
		]
	}

	@Test def void testUpperCaseFeatureNames() {
		TestmodelsPackage::eINSTANCE.testEClass => [
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
}