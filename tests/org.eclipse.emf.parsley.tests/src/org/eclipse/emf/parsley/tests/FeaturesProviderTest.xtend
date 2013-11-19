package org.eclipse.emf.parsley.tests

import java.util.List
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider
import org.junit.Before
import org.junit.Test

import static extension org.junit.Assert.*

class FeaturesProviderTest {
	
	var featuresProvider = new FeaturesProvider;
	
	@Before
	def void setUp() {
		featuresProvider = new FeaturesProvider
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
	
	def assertFeatureList(EClass eClass, CharSequence expected) {
		featuresProvider.getFeatures(eClass).assertFeatureList(expected)
	}
	
	
	def assertFeatureList(List<EStructuralFeature> features, CharSequence expected) {
		expected.toString.assertEquals(features.map[name].join(", "))
	}
}