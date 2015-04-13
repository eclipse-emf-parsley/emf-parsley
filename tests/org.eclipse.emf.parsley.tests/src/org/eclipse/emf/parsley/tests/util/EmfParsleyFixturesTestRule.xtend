/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.util

import java.util.List
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceImpl
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.edit.domain.EditingDomain
import org.eclipse.emf.parsley.examples.library.Book
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls
import org.eclipse.emf.parsley.tests.models.testmodels.TestContainer
import org.eclipse.emf.parsley.tests.models.testmodels.TestEClass
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsFactory
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

import static org.eclipse.emf.parsley.examples.library.EXTLibraryFactory.*

import static extension org.junit.Assert.*

/**
 * Objects and other elements used in tests.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
class EmfParsleyFixturesTestRule implements TestRule {
	
	protected Library lib = null
	
	protected Writer wr = null
	
	protected Book b = null
	
	var protected ResourceSet resourceSet;
	
	val protected testPackage = TestmodelsPackage.eINSTANCE
	
	val protected testFactory = TestmodelsFactory.eINSTANCE
	
	val protected libraryFactory = EXTLibraryFactory.eINSTANCE

	val protected baseClass = testPackage.baseClass
	
	val protected derivedClass = testPackage.derivedClass
	
	var protected TestContainer testContainer

	/**
	 * An instance to use for testing the creation of a Control
	 * using an AbstractControlFactory
	 */
	var protected ClassForControls classForControlsInstance

	/**
	 * An instance to use for testing Ecore related mechanisms
	 */
	var protected TestEClass testEClassInstance
	
	override apply(Statement base, Description description) {
		return [
			setup();
			base.evaluate();
		]
	}
	
	def void setup() {
		resourceSet = createResourceSet
		lib = createTestLibrary
		wr = lib.writers.head
		b = lib.books.head
		testContainer = testFactory.createTestContainer
		classForControlsInstance = createClassForControls
		testEClassInstance = testFactory.createTestEClass
	}

	def getTestPackage() {
		testPackage
	}

	def getTestContainer() {
		testContainer
	}

	def getClassForControlsInstance() {
		classForControlsInstance
	}

	def getLibrary() {
		lib
	}

	def getWriter() {
		wr
	}

	def getBook() {
		b
	}

	def getLibraryFactory() {
		libraryFactory
	}

	def getDerivedClass() {
		derivedClass
	}

	def getBaseClass() {
		baseClass
	}

	def getTestFactory() {
		testFactory
	}

	def ResourceSet createResourceSet() {
		new ResourceSetImpl
	}

	def createResourceInResouceSet() {
		createResource => [
			resourceSet.resources += it
		]
	}

	def createResource() {
		new ResourceImpl
	}

	def EditingDomain getEditingDomain() {
		return null
	}

	def assertBooks(Writer w, int expectedSize) {
		expectedSize.assertEquals(w.books.size)
	}

	def booksByReflection(Writer w) {
		w.eGet(EXTLibraryPackage.eINSTANCE.writer_Books) as List<Book>
	}

	def addBooksByReflection(Writer w, List<Book> books) {
		w.eSet(EXTLibraryPackage.eINSTANCE.writer_Books, books)
	}

	def createTestLibrary() {
		eINSTANCE.createLibrary() => [
			name = "TEST"
			val writer = createTestWriter("Writer")
			val book = createTestBook("Book")
			writers += writer
			books += book
			writer.books += book
		]
	}

	def createTestWriter(String t) {
		eINSTANCE.createWriter() => [ 
			firstName = t
		]
	}
	
	def createTestBook(String t) {
		eINSTANCE.createBook() => [ title = t ]
	}

	def addTestBook(String t) {
		lib.books += createTestBook(t)
	}

	def createClassWithName(Resource res, String n) {
		createClassWithName(n) => [
			res.contents += it
		]
	}

	def createClassWithName(String n) {
		testFactory.createClassWithName => [name = n]
	}

	def createClassForTable(String n) {
		testFactory.createClassForTable => [
			classWithName1 = createClassWithName(n)
			classWithName2 = createClassWithName(n)
		]
	}

	def createClassForControls() {
		testFactory.createClassForControls
	}

	def createClassForFeatureMapEntry1(String n) {
		testFactory.createClassForFeatureMapEntry1 => [name = n]
	}

	def createClassForFeatureMapEntry2(String n) {
		testFactory.createClassForFeatureMapEntry2 => [name = n]
	}
	
}