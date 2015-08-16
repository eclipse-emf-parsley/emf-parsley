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

import com.google.inject.Injector
import java.util.List
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceImpl
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
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
 * Objects, utility methods and other elements used in tests.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
class EmfParsleyFixturesAndUtilitiesTestRule implements TestRule {
	
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

	val static TEST_RESOURCE_URI = "resources/TestResource.xmi"

	val static TEST_LIBRARY_RESOURCE_URI = "resources/TestLibraryResource.xmi"
	
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
		testContainer = createTestContainer
		classForControlsInstance = createClassForControls
		testEClassInstance = testFactory.createTestEClass
	}

	def getTestPackage() {
		testPackage
	}

	def getTestContainer() {
		testContainer
	}

	def setTestContainer(TestContainer testContainer) {
		this.testContainer = testContainer
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

	def createAdapterFactoryEditingDomain(Injector injector) {
		injector.getProvider(AdapterFactoryEditingDomain).get
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

	def createTestContainerInResource() {
		testFactory.createTestContainer => [
			createResourceInResouceSet.contents += it
		]
	}

	def createTestContainer(Resource res, String name) {
		createTestContainer(res) => [
			it.name = name
		]
	}

	def createTestContainer(Resource res) {
		testFactory.createTestContainer => [
			res.contents += it
		]
	}

	def createTestContainer() {
		testFactory.createTestContainer
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

	/**
	 * Registers XMI resource factory and registers TestmodelsPackage
	 * so that testable resources can be loaded with
	 * URI.createURI("http:///My.testmodels")
	 */
	def setupResouceFactory(ResourceSet resourceSet) {
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put
			(Resource.Factory.Registry.DEFAULT_EXTENSION, 
			 new XMIResourceFactoryImpl());

		resourceSet.getPackageRegistry().put
			(TestmodelsPackage.eNS_URI, 
			 TestmodelsPackage.eINSTANCE);
		
		resourceSet.getPackageRegistry().put
			(EXTLibraryPackage.eNS_URI, 
			 EXTLibraryPackage.eINSTANCE);
		
		return resourceSet
	}

	def createAndSetupResourceSet() {
		createResourceSet.setupResouceFactory
	}

	def createTestResource() {
		val resourceSet = createAndSetupResourceSet
		val resource = resourceSet.createResource(URI.createURI(TEST_RESOURCE_URI))
		return resource
	}

	def loadTestResource() {
		val resourceSet = createAndSetupResourceSet
		val resource = resourceSet.getResource(URI.createURI(TEST_RESOURCE_URI), true)
		return resource
	}

	def createTestLibrayResource() {
		val resourceSet = createAndSetupResourceSet
		val resource = resourceSet.createResource(URI.createURI(TEST_LIBRARY_RESOURCE_URI))
		return resource
	}

	def createTestLibrayResourceAndInitialize() {
		return createTestLibrayResource => [
			contents += createTestLibrary
		]
	}

	def loadTestLibraryResource() {
		val resourceSet = createAndSetupResourceSet
		val resource = resourceSet.getResource(URI.createURI(TEST_LIBRARY_RESOURCE_URI), true)
		return resource
	}

}