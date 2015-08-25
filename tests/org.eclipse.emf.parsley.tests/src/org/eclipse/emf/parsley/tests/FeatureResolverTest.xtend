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
import org.eclipse.emf.parsley.ecore.FeatureNamePath
import org.eclipse.emf.parsley.ecore.FeatureResolver
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage
import org.eclipse.emf.parsley.junit4.util.LogAppenderTestRule
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage
import org.junit.Rule
import org.junit.Test

import static org.eclipse.emf.parsley.examples.library.EXTLibraryFactory.*

import static extension org.junit.Assert.*

class FeatureResolverTest {
	
	FeatureResolver resolver = new FeatureResolver;

	@Rule public val LogAppenderTestRule logAppender = new LogAppenderTestRule(EmfParsleyActivator);

	val protected testPackage = TestmodelsPackage.eINSTANCE

	val static NON_EXISTANT_FEATURE_ERROR = "cannot find feature 'nonExistantNameFeature' in EClass 'TestEClass" +
					" (org.eclipse.emf.parsley.tests.models.testmodels.TestEClass)'"

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

	@Test def void testNonExistantFeatureInEClass() {
		resolver.getFeature(testPackage.testEClass, "nonExistantNameFeature").
			assertNull
		logAppender.assertContainsMessage(NON_EXISTANT_FEATURE_ERROR)
	}

	@Test def void testNonExistantFeatureInEClassInPath() {
		0.assertEquals(
			resolver.getFeatures(testPackage.testEClass, #[new FeatureNamePath("nonExistantNameFeature")]).
			size)
		logAppender.assertContainsMessage(NON_EXISTANT_FEATURE_ERROR)
	}

	@Test def void testClassifierNotEClassInPath() {
		0.assertEquals(
			resolver.getFeatures(testPackage.classForControls,
				#[new FeatureNamePath(
					testPackage.classForControls_BooleanFeature.name,
					#[
						new FeatureNamePath("notImportant")
					]
				)]
			).
			size)
		logAppender.assertContainsMessage(
			"feature 'booleanFeature' in EClass 'ClassForControls (org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls)' is not an EClass"
		)
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