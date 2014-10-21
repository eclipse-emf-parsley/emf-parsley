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

import com.google.inject.Guice
import com.google.inject.Injector
import java.util.List
import org.eclipse.emf.common.notify.AdapterFactory
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceImpl
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.edit.domain.EditingDomain
import org.eclipse.emf.parsley.EmfParsleyConstants
import org.eclipse.emf.parsley.examples.library.Book
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage
import org.eclipse.emf.parsley.examples.library.Library
import org.eclipse.emf.parsley.examples.library.Writer
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls
import org.eclipse.emf.parsley.tests.models.testmodels.TestEClass
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsFactory
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage
import org.eclipse.emf.parsley.tests.models.testmodels.TestContainer
import org.junit.Before

import static org.eclipse.emf.parsley.examples.library.EXTLibraryFactory.*

import static extension org.junit.Assert.*
import org.eclipse.emf.parsley.util.EcoreUtil2
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory

abstract class EmfParsleyAbstractTest {

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

	var protected ClassForControls eobj

	var protected TestEClass eobj2
	
	/**
	 * This will be created on demand using the method getOrCreateInjector
	 */
	var private Injector injector = null;
	
	new() {
		// the following are useless... but it's just to have coverage
		// for the protected constructor of EmfParsleyConstants
		// and the protected constructor is "required" by sonar...
		new EmfParsleyConstants() {
			
		}
		new EcoreUtil2() {
			
		}
	}
	
	@Before
	def void setUp() {
		injector = null
		resourceSet = createResourceSet
		lib = createTestLibrary
		wr = lib.writers.head
		b = lib.books.head
		testContainer = testFactory.createTestContainer
		eobj = createClassForControls
		eobj2 = testFactory.createTestEClass
	}

	def protected ResourceSet createResourceSet() {
		new ResourceSetImpl
	}

	def protected createResourceInResouceSet() {
		createResource => [
			resourceSet.resources += it
		]
	}

	def protected createResource() {
		new ResourceImpl
	}

	def protected EditingDomain getEditingDomain() {
		return null
	}

	def protected <T> injectMembers(T o) {
		getOrCreateInjector.injectMembers(o)
		return o
	}

	def protected getOrCreateInjector() {
		if (injector === null) {
			injector = Guice.createInjector(new EmfParsleyGuiceModuleForTesting())
		}
		return injector
	}

	def protected getAdapterFactory() {
		getOrCreateInjector.getInstance(AdapterFactory)
	}
	
	def protected assertBooks(Writer w, int expectedSize) {
		expectedSize.assertEquals(w.books.size)
	}

	def protected booksByReflection(Writer w) {
		w.eGet(EXTLibraryPackage.eINSTANCE.writer_Books) as List<Book>
	}

	def protected addBooksByReflection(Writer w, List<Book> books) {
		w.eSet(EXTLibraryPackage.eINSTANCE.writer_Books, books)
	}

	def protected createTestLibrary() {
		eINSTANCE.createLibrary() => [
			name = "TEST"
			val writer = createTestWriter("Writer")
			val book = createTestBook("Book")
			writers += writer
			books += book
			writer.books += book
		]
	}

	def protected createTestWriter(String t) {
		eINSTANCE.createWriter() => [ 
			firstName = t
		]
	}
	
	def protected createTestBook(String t) {
		eINSTANCE.createBook() => [ title = t ]
	}

	def protected addTestBook(String t) {
		lib.books += createTestBook(t)
	}

	def protected createClassWithName(Resource res, String n) {
		createClassWithName(n) => [
			res.contents += it
		]
	}

	def protected createClassWithName(String n) {
		testFactory.createClassWithName => [name = n]
	}

	def protected createClassForTable(String n) {
		testFactory.createClassForTable => [
			classWithName1 = createClassWithName(n)
			classWithName2 = createClassWithName(n)
		]
	}

	def protected createClassForControls() {
		testFactory.createClassForControls
	}

	def protected createClassForFeatureMapEntry1(String n) {
		testFactory.createClassForFeatureMapEntry1 => [name = n]
	}

	def protected createClassForFeatureMapEntry2(String n) {
		testFactory.createClassForFeatureMapEntry2 => [name = n]
	}

}