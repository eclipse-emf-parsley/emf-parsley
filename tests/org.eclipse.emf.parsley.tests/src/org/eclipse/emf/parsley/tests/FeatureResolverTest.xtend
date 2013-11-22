package org.eclipse.emf.parsley.tests

import org.junit.Test

import static org.eclipse.emf.parsley.examples.library.EXTLibraryFactory.*
import org.eclipse.emf.parsley.ecore.FeatureNamePath
import org.eclipse.emf.parsley.ecore.FeatureResolver
import java.util.List
import org.eclipse.emf.ecore.EStructuralFeature
import static extension org.junit.Assert.*
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage

class FeatureResolverTest {
	
	FeatureResolver resolver = new FeatureResolver;
	
	@Test def void testResolver1() {
		val paths = newArrayList(
			new FeatureNamePath("title"),
			new FeatureNamePath("author",
				newArrayList(
					new FeatureNamePath("firstName"),
					new FeatureNamePath("lastName")
				)
			)
		)
		EXTLibraryPackage::eINSTANCE.book.
			assertFeatureList(paths, "title, firstName, lastName")
	}

	@Test def void testResolver2() {
		val paths = newArrayList(
			new FeatureNamePath("title"),
			new FeatureNamePath("minutesLength"),
			new FeatureNamePath("reader",
				newArrayList(
					new FeatureNamePath("firstName"),
					new FeatureNamePath("lastName")
				)
			),
			new FeatureNamePath("author",
				newArrayList(
					new FeatureNamePath("name"),
					new FeatureNamePath("books")
				)
			)
		)
		EXTLibraryPackage::eINSTANCE.bookOnTape.
			assertFeatureList(paths, "title, minutesLength, firstName, lastName, name, books")
	}

	@Test def void testResolver3() {
		// Employee:manager is an Employee
		val paths = newArrayList(
			new FeatureNamePath("firstName"),
			new FeatureNamePath("lastName"),
			new FeatureNamePath("manager",
				newArrayList(
					new FeatureNamePath("firstName"),
					new FeatureNamePath("lastName"),
					new FeatureNamePath("manager",
						newArrayList(
							new FeatureNamePath("firstName"),
							new FeatureNamePath("lastName")
						)
					)
				)
			)
		)
		EXTLibraryPackage::eINSTANCE.employee.
			assertFeatureList(paths, "firstName, lastName, firstName, lastName, firstName, lastName")
	}

	@Test def void testResolveFeatureWithLowerCaseName() {
		TestmodelsPackage.eINSTANCE.testEClass.
			assertFeature("lowercaseNameFeature", "lowercaseNameFeature")
	}

	@Test def void testResolveFeatureWithUpperCaseName() {
		TestmodelsPackage.eINSTANCE.testEClass.
			assertFeature("upperCaseNameFeature", "UpperCaseNameFeature")
	}
	
	def createModel() {
		eINSTANCE.createLibrary => [
			val writer = eINSTANCE.createWriter => [
				firstName = "Test"
				lastName = "Writer"
			]
			val writer2 = eINSTANCE.createWriter => [
				firstName = "Test2"
				lastName = "Writer2"
			]
			writers += writer
			writers += writer2
			books += eINSTANCE.createBook => [
				title = "Test Book"
				author = writer
			]
			
			stock += eINSTANCE.createBookOnTape => [
				reader = writer
				author = writer2
			]
			
			val employee1 = eINSTANCE.createEmployee => [
				firstName = "E"
				lastName = "1"
			]
			val employee2 = eINSTANCE.createEmployee => [
				firstName = "E"
				lastName = "2"
				manager = employee1
			]
			employees += employee1
			employees += employee2
		]
	}
	
	def assertFeatureList(EClass eClass, List<FeatureNamePath> paths, CharSequence expected) {
		resolver.getFeatures(eClass, paths).assertFeatureList(expected)
	}
	
	def assertFeatureList(List<EStructuralFeature> features, CharSequence expected) {
		expected.toString.assertEquals(features.map[name].join(", "))
	}

	def assertFeature(EClass eClass, String queryName, String realName) {
		resolver.getFeature(eClass, queryName) => [
			assertNotNull
			realName.assertEquals(name)
		]
	}
}