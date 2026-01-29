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
package org.eclipse.emf.parsley.tests;

import static org.eclipse.emf.parsley.examples.library.EXTLibraryFactory.eINSTANCE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.EmfParsleyActivator;
import org.eclipse.emf.parsley.ecore.FeatureNamePath;
import org.eclipse.emf.parsley.ecore.FeatureResolver;
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage;
import org.eclipse.emf.parsley.examples.library.Employee;
import org.eclipse.emf.parsley.examples.library.Library;
import org.eclipse.emf.parsley.examples.library.Writer;
import org.eclipse.emf.parsley.junit4.util.LogAppenderTestRule;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage;
import org.junit.Rule;
import org.junit.Test;

public class FeatureResolverTest {

	private FeatureResolver resolver = new FeatureResolver();

	@Rule
	public LogAppenderTestRule logAppender = new LogAppenderTestRule(EmfParsleyActivator.class);

	protected final TestmodelsPackage testPackage = TestmodelsPackage.eINSTANCE;

	private static final String NON_EXISTANT_FEATURE_ERROR = "cannot find feature 'nonExistantNameFeature' in EClass 'TestEClass" +
					" (org.eclipse.emf.parsley.tests.models.testmodels.TestEClass)'";

	@Test
	public void testResolver1() {
		final var paths = Arrays.asList(
			new FeatureNamePath("title"),
			new FeatureNamePath("author",
				Arrays.asList(
					new FeatureNamePath("firstName"),
					new FeatureNamePath("lastName")
				)
			)
		);
		assertFeatureList(EXTLibraryPackage.eINSTANCE.getBook(), paths, "title, firstName, lastName");
	}

	@Test
	public void testResolver2() {
		final var paths = Arrays.asList(
			new FeatureNamePath("title"),
			new FeatureNamePath("minutesLength"),
			new FeatureNamePath("reader",
				Arrays.asList(
					new FeatureNamePath("firstName"),
					new FeatureNamePath("lastName")
				)
			),
			new FeatureNamePath("author",
				Arrays.asList(
					new FeatureNamePath("name"),
					new FeatureNamePath("books")
				)
			)
		);
		assertFeatureList(EXTLibraryPackage.eINSTANCE.getBookOnTape(), paths, "title, minutesLength, firstName, lastName, name, books");
	}

	@Test
	public void testResolver3() {
		// Employee:manager is an Employee
		final var paths = Arrays.asList(
			new FeatureNamePath("firstName"),
			new FeatureNamePath("lastName"),
			new FeatureNamePath("manager",
				Arrays.asList(
					new FeatureNamePath("firstName"),
					new FeatureNamePath("lastName"),
					new FeatureNamePath("manager",
						Arrays.asList(
							new FeatureNamePath("firstName"),
							new FeatureNamePath("lastName")
						)
					)
				)
			)
		);
		assertFeatureList(EXTLibraryPackage.eINSTANCE.getEmployee(), paths, "firstName, lastName, firstName, lastName, firstName, lastName");
	}

	@Test
	public void testResolveFeatureWithLowerCaseName() {
		assertFeature(TestmodelsPackage.eINSTANCE.getTestEClass(), "lowercaseNameFeature", "lowercaseNameFeature");
	}

	@Test
	public void testResolveFeatureWithUpperCaseName() {
		assertFeature(TestmodelsPackage.eINSTANCE.getTestEClass(), "upperCaseNameFeature", "UpperCaseNameFeature");
	}

	@Test
	public void testNonExistantFeatureInEClass() {
		assertNull(resolver.getFeature(testPackage.getTestEClass(), "nonExistantNameFeature"));
		logAppender.assertContainsMessage(NON_EXISTANT_FEATURE_ERROR);
	}

	@Test
	public void testNonExistantFeatureInEClassInPath() {
		assertEquals(0,
			resolver.getFeatures(testPackage.getTestEClass(), Arrays.asList(new FeatureNamePath("nonExistantNameFeature"))).size());
		logAppender.assertContainsMessage(NON_EXISTANT_FEATURE_ERROR);
	}

	@Test
	public void testClassifierNotEClassInPath() {
		assertEquals(0,
			resolver.getFeatures(testPackage.getClassForControls(),
				Arrays.asList(new FeatureNamePath(
					testPackage.getClassForControls_BooleanFeature().getName(),
					Arrays.asList(
						new FeatureNamePath("notImportant")
					)
				))
			).size());
		logAppender.assertContainsMessage(
			"feature 'booleanFeature' in EClass 'ClassForControls (org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls)' is not an EClass"
		);
	}

	@SuppressWarnings("unused")
	public Library createModel() {
		final Library library = eINSTANCE.createLibrary();
		final Writer writer = eINSTANCE.createWriter();
		writer.setFirstName("Test");
		writer.setLastName("Writer");
		final Writer writer2 = eINSTANCE.createWriter();
		writer2.setFirstName("Test2");
		writer2.setLastName("Writer2");
		library.getWriters().add(writer);
		library.getWriters().add(writer2);
		final var book = eINSTANCE.createBook();
		book.setTitle("Test Book");
		book.setAuthor(writer);
		library.getBooks().add(book);

		final var bookOnTape = eINSTANCE.createBookOnTape();
		bookOnTape.setReader(writer);
		bookOnTape.setAuthor(writer2);
		library.getStock().add(bookOnTape);

		final Employee employee1 = eINSTANCE.createEmployee();
		employee1.setFirstName("E");
		employee1.setLastName("1");
		final Employee employee2 = eINSTANCE.createEmployee();
		employee2.setFirstName("E");
		employee2.setLastName("2");
		employee2.setManager(employee1);
		library.getEmployees().add(employee1);
		library.getEmployees().add(employee2);
		return library;
	}

	private void assertFeatureList(EClass eClass, List<FeatureNamePath> paths, CharSequence expected) {
		assertFeatureList(resolver.getFeatures(eClass, paths), expected);
	}

	private void assertFeatureList(List<EStructuralFeature> features, CharSequence expected) {
		assertEquals(expected.toString(), features.stream().map(EStructuralFeature::getName).collect(Collectors.joining(", ")));
	}

	private void assertFeature(EClass eClass, String queryName, String realName) {
		final var feature = resolver.getFeature(eClass, queryName);
		assertNotNull(feature);
		assertEquals(realName, feature.getName());
	}
}
