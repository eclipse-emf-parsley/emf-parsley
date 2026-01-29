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
package org.eclipse.emf.parsley.tests.util;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.EXTLibraryFactory;
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.examples.library.Writer;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForFeatureMapEntry1;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForFeatureMapEntry2;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForTable;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName;
import org.eclipse.emf.parsley.tests.models.testmodels.DerivedClass;
import org.eclipse.emf.parsley.tests.models.testmodels.TestContainer;
import org.eclipse.emf.parsley.tests.models.testmodels.TestEClass;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsFactory;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import com.google.inject.Injector;

/**
 * Objects, utility methods and other elements used in tests.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class EmfParsleyFixturesAndUtilitiesTestRule implements TestRule {
	
	protected Library lib = null;

	protected Writer wr = null;

	protected Book b = null;

	protected ResourceSet resourceSet;

	protected final TestmodelsPackage testPackage = TestmodelsPackage.eINSTANCE;

	protected final TestmodelsFactory testFactory = TestmodelsFactory.eINSTANCE;

	protected final EXTLibraryFactory libraryFactory = EXTLibraryFactory.eINSTANCE;

	protected final EXTLibraryPackage libraryPackage = EXTLibraryPackage.eINSTANCE;

	protected final EClass baseClass = testPackage.getBaseClass();

	protected final EClass derivedClass = testPackage.getDerivedClass();

	protected TestContainer testContainer;

	/**
	 * An instance to use for testing the creation of a Control
	 * using an AbstractControlFactory
	 */
	protected ClassForControls classForControlsInstance;

	/**
	 * An instance to use for testing Ecore related mechanisms
	 */
	protected TestEClass testEClassInstance;

	/**
	 * An instance to use for testing Ecore related mechanisms
	 */
	protected DerivedClass derivedClassInstance;

	private static final String TEST_RESOURCE_URI = "resources/TestResource.xmi";

	private static final String TEST_LIBRARY_RESOURCE_URI = "resources/TestLibraryResource.xmi";

	@Override
	public Statement apply(Statement base, Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				setup();
				base.evaluate();
			}
		};
	}

	public void setup() {
		resourceSet = createResourceSet();
		lib = createTestLibrary();
		wr = lib.getWriters().isEmpty() ? null : lib.getWriters().get(0);
		b = lib.getBooks().isEmpty() ? null : lib.getBooks().get(0);
		testContainer = createTestContainer();
		classForControlsInstance = createClassForControls();
		derivedClassInstance = createDerivedClassInstance();
		testEClassInstance = testFactory.createTestEClass();
	}

	public TestmodelsPackage getTestPackage() {
		return testPackage;
	}

	public TestContainer getTestContainer() {
		return testContainer;
	}

	public void setTestContainer(TestContainer testContainer) {
		this.testContainer = testContainer;
	}

	public ClassForControls getClassForControlsInstance() {
		return classForControlsInstance;
	}

	public DerivedClass getDerivedClassInstance() {
		return derivedClassInstance;
	}

	public Library getLibrary() {
		return lib;
	}

	public EXTLibraryPackage getLibraryPackage() {
		return libraryPackage;
	}

	public Writer getWriter() {
		return wr;
	}

	public Book getBook() {
		return b;
	}

	public EXTLibraryFactory getLibraryFactory() {
		return libraryFactory;
	}

	public EClass getDerivedClass() {
		return derivedClass;
	}

	public EClass getBaseClass() {
		return baseClass;
	}

	public TestmodelsFactory getTestFactory() {
		return testFactory;
	}

	public ResourceSet createResourceSet() {
		return new ResourceSetImpl();
	}

	public Resource createResourceInResouceSet() {
		var resource = createResource();
		resourceSet.getResources().add(resource);
		return resource;
	}

	public Resource createResourceInResouceSet(ResourceSet resourceSet) {
		var resource = createResource();
		resourceSet.getResources().add(resource);
		return resource;
	}

	public Resource createResource() {
		return new ResourceImpl();
	}

	public EditingDomain getEditingDomain() {
		return null;
	}

	public EditingDomain createEditingDomain(Injector injector) {
		return injector.getInstance(EditingDomain.class);
	}

	public void assertBooks(Writer w, int expectedSize) {
		assertEquals(expectedSize, w.getBooks().size());
	}

	@SuppressWarnings("unchecked")
	public List<Book> booksByReflection(Writer w) {
		return (List<Book>) w.eGet(EXTLibraryPackage.eINSTANCE.getWriter_Books());
	}

	public void addBooksByReflection(Writer w, List<Book> books) {
		w.eSet(EXTLibraryPackage.eINSTANCE.getWriter_Books(), books);
	}

	public Library createTestLibrary() {
		var library = EXTLibraryFactory.eINSTANCE.createLibrary();
		library.setName("TEST");
		var writer = createTestWriter("Writer");
		var book = createTestBook("Book");
		library.getWriters().add(writer);
		library.getBooks().add(book);
		writer.getBooks().add(book);
		return library;
	}

	public Writer createTestWriter(String t) {
		var writer = EXTLibraryFactory.eINSTANCE.createWriter();
		writer.setFirstName(t);
		return writer;
	}
	
	public Book createTestBook(String t) {
		var book = EXTLibraryFactory.eINSTANCE.createBook();
		book.setTitle(t);
		return book;
	}

	public void addTestBook(String t) {
		lib.getBooks().add(createTestBook(t));
	}

	public ClassWithName createClassWithName(Resource res, String n) {
		var classWithName = createClassWithName(n);
		res.getContents().add(classWithName);
		return classWithName;
	}

	public ClassWithName createClassWithName(String n) {
		var classWithName = testFactory.createClassWithName();
		classWithName.setName(n);
		return classWithName;
	}

	public ClassForTable createClassForTable(String n) {
		var classForTable = testFactory.createClassForTable();
		classForTable.setClassWithName1(createClassWithName(n));
		classForTable.setClassWithName2(createClassWithName(n));
		return classForTable;
	}

	public TestContainer createTestContainerInResource() {
		var container = testFactory.createTestContainer();
		createResourceInResouceSet().getContents().add(container);
		return container;
	}

	public TestContainer createTestContainer(Resource res, String name) {
		var container = createTestContainer(res);
		container.setName(name);
		return container;
	}

	public TestContainer createTestContainer(Resource res) {
		var container = testFactory.createTestContainer();
		res.getContents().add(container);
		return container;
	}

	public TestContainer createTestContainer() {
		return testFactory.createTestContainer();
	}

	public ClassForControls createClassForControls() {
		return testFactory.createClassForControls();
	}

	public DerivedClass createDerivedClassInstance() {
		return testFactory.createDerivedClass();
	}

	public ClassForFeatureMapEntry1 createClassForFeatureMapEntry1(String n) {
		var entry = testFactory.createClassForFeatureMapEntry1();
		entry.setName(n);
		return entry;
	}

	public ClassForFeatureMapEntry2 createClassForFeatureMapEntry2(String n) {
		var entry = testFactory.createClassForFeatureMapEntry2();
		entry.setName(n);
		return entry;
	}

	/**
	 * Registers XMI resource factory and registers TestmodelsPackage
	 * so that testable resources can be loaded with
	 * URI.createURI("http:///My.testmodels")
	 */
	public ResourceSet setupResouceFactory(ResourceSet resourceSet) {
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
			Resource.Factory.Registry.DEFAULT_EXTENSION, 
			new XMIResourceFactoryImpl());

		resourceSet.getPackageRegistry().put(
			TestmodelsPackage.eNS_URI, 
			TestmodelsPackage.eINSTANCE);
		
		resourceSet.getPackageRegistry().put(
			EXTLibraryPackage.eNS_URI, 
			EXTLibraryPackage.eINSTANCE);
		
		return resourceSet;
	}

	public ResourceSet createAndSetupResourceSet() {
		return setupResouceFactory(createResourceSet());
	}

	public Resource createTestResource() {
		var resourceSet = createAndSetupResourceSet();
		var resource = resourceSet.createResource(createTestResourceURI());
		return resource;
	}
	
	public URI createTestResourceURI() {
		return URI.createURI(TEST_RESOURCE_URI);
	}

	public Resource loadTestResource() {
		return loadTestResource(createAndSetupResourceSet());
	}

	public Resource loadTestResource(ResourceSet resourceSet) {
		var resource = resourceSet.getResource(createTestResourceURI(), true);
		return resource;
	}

	public Resource createTestLibrayResource() {
		var resourceSet = createAndSetupResourceSet();
		return createTestLibrayResource(resourceSet);
	}

	public Resource createTestLibrayResource(ResourceSet resourceSet) {
		var resource = resourceSet.createResource(URI.createURI(TEST_LIBRARY_RESOURCE_URI));
		return resource;
	}

	public Resource createTestLibrayResourceAndInitialize() {
		var resource = createTestLibrayResource();
		resource.getContents().add(createTestLibrary());
		return resource;
	}

	public Resource createTestLibrayResourceAndInitialize(EditingDomain editingDomain) {
		return createTestLibrayResourceAndInitialize(editingDomain.getResourceSet());
	}

	public Resource createTestLibrayResourceAndInitialize(ResourceSet resourceSet) {
		var resource = createTestLibrayResource(resourceSet);
		resource.getContents().add(lib);
		return resource;
	}

	public Resource loadTestLibraryResource() {
		var resourceSet = createAndSetupResourceSet();
		var resource = resourceSet.getResource(URI.createURI(TEST_LIBRARY_RESOURCE_URI), true);
		return resource;
	}

}
