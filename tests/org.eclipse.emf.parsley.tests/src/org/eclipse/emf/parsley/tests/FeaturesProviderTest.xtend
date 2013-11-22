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