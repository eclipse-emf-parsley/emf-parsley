/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.StreamSupport;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.parsley.dsl.model.ModelPackage;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

@RunWith(XtextRunner.class)
@InjectWith(EmfParsleyDslInjectorProvider.class)
public class EmfParsleyDslScopingTest extends EmfParsleyDslAbstractTest {

	@Inject
	private IScopeProvider scopeProvider;

	@Test
	public void testFeaturesForLabelSpecifications() throws Exception {
		var spec = parseModel(inputs.tableLabelProvider())
			.getModule().getTableLabelProvider().getFeatureTexts().getSpecifications().get(0);
		assertScope(spec,
			ModelPackage.eINSTANCE.getFeatureAssociatedExpression_Feature(),
			"name, books, borrowers, writers, employees, stock, branches, parentBranch, people, address, class"
		);
	}

	@Test
	public void testFeaturesForPropertyDescriptionSpecifications() throws Exception {
		var spec = parseModel(inputs.featureCaptionProvider())
			.getModule().getFeatureCaptionProvider().getFeatureTexts().getSpecifications().get(0);
		assertScope(spec,
			ModelPackage.eINSTANCE.getFeatureAssociatedExpression_Feature(),
			"name, books, borrowers, writers, employees, stock, branches, parentBranch, people, address, class"
		);
	}

	private void assertScope(EObject o, EReference ref, String expected) {
		List<String> listExpected = List.of(expected.split(", "));
		List<QualifiedName> scope = 
			StreamSupport.stream(scopeProvider.getScope(o, ref).getAllElements().spliterator(), false)
				.map(it -> it.getName())
				.toList();
		for (String exp : listExpected) {
			assertTrue("not found: " + exp, 
				com.google.common.collect.Iterables.tryFind(scope, 
					it -> exp.equals(it.toString())).isPresent());
		}
		for (QualifiedName sc : scope) {
			assertTrue("not expected: " + sc.toString(), 
				listExpected.stream().anyMatch(it -> sc.toString().equals(it)));
		}
	}
}
