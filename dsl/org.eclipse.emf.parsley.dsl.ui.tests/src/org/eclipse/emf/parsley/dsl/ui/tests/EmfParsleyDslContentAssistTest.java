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
package org.eclipse.emf.parsley.dsl.ui.tests;

import static org.junit.Assert.*;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule;
import org.eclipse.emf.parsley.dsl.tests.util.ui.ProjectImportUtil;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.ui.testing.AbstractContentAssistTest;
import org.eclipse.xtext.ui.testing.ContentAssistProcessorTestBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * @author Lorenzo Bettini
 */
@RunWith(XtextRunner.class)
@InjectWith(EmfParsleyDslUiInjectorProvider.class)
public class EmfParsleyDslContentAssistTest extends AbstractContentAssistTest {

	private static final String PROJECT_NAME = "org.eclipse.emf.parsley.dsl.ui.tests.project";

	@Inject
	private Injector injector;

	@BeforeClass
	public static void setUp() {
		try {
			javaProject = ProjectImportUtil.importJavaProject(PROJECT_NAME);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected ContentAssistProcessorTestBuilder newBuilder() throws Exception {
		return new ContentAssistProcessorTestBuilder(injector, this) {
			@Override
			public ContentAssistProcessorTestBuilder expectContent(String expectation) {
				assertEquals(expectation, getModel().replace("\r", ""));
				return this;
			}
		};
	}

	@Test
	public void testImportCompletion() throws Exception {
		newBuilder().append("import java.util.Da").assertText("java.util.Date");
	}

	@Test
	public void testImportCompletion_1() throws Exception {
		newBuilder().append("import LinkedHashSet").assertProposal("java.util.LinkedHashSet");
	}

	@Test
	public void testTypeCompletionInsertsImport() throws Exception {
		appendAndApplyProposalAndExpectContent(
			"""
			module my.parsley.project {
				
				labelProvider {
					text {
						LinkedHashSet""",
			"java.util.LinkedHashSet",
			"""
			import java.util.LinkedHashSet
			
			module my.parsley.project {
				
				labelProvider {
					text {
						LinkedHashSet"""
		);
	}

	@Test
	public void testEmfFeatureForFeatureProvider() throws Exception {
		newBuilder().append(
			"""
			import java.util.List
			
			module my.test.proj {
			
				featuresProvider {
					features {
						List ->\s"""
		).assertText("class", "empty");
		// these correspond to getClass and isEmpty
	}

	@Test
	public void testEmfFeatureForPropertyDescriptionProvider() throws Exception {
		newBuilder().append(
			"""
			import java.util.List
			
			module my.test.proj {
			
				featureCaptionProvider {
					text {
						List :\s"""
		).assertText("class", "empty");
		// these correspond to getClass and isEmpty
	}

	@Test
	public void testEmfFeatureForFormControlFactory() throws Exception {
		newBuilder().append(
			"""
			import java.util.List
			
			module my.test.proj {
			
				formControlFactory {
					control {
						List :\s"""
		).assertText("class", "empty");
		// these correspond to getClass and isEmpty
	}

	@Test
	public void testEmfFeatureForProposalSpecification() throws Exception {
		newBuilder().append(
			"""
			import java.util.List
			
			module my.test.proj {
			
				proposals {
						List :\s"""
		).assertText("class", "empty");
		// these correspond to getClass and isEmpty
	}

	@Test
	public void testProposalsForModuleExtends() throws Exception {
		newBuilder().append(
			"module my.test.proj extends "
		).assertText(
			EmfParsleyGuiceModule.class.getCanonicalName(),
			EmfParsleyJavaGuiceModule.class.getCanonicalName()
		);
		// these are the the only possible completions in this test
	}

	@Test
	public void testProposalForTypeInViewSpecification() throws Exception {
		assertProposalSolutions(newBuilder().append(
			"""
			module my.test.proj {
			\t
				parts {
					viewpart id {
						viewname "View Name"
						viewclass \s"""
		),
			"View", "E4PartWrapper", "ContentOutline", "PropertySheet"
		);
		// only IViewPart sutypes
	}

	@Test
	public void testProposalForFeatureSpecification() throws Exception {
		assertProposalSolutions(newBuilder().append(
			"""
			module my.test.proj {
			\t
				featuresProvider {
					features {\s"""
		),
			"emf.ecore", "emf.edit.tree"
		);
		// only EObject sutypes
	}

	@Test
	public void testProposalForControlFactoryFeatureSpecification() throws Exception {
		assertProposalSolutions(newBuilder().append(
			"""
			module my.test.proj {
			\t
				formControlFactory {
					control {\s"""
		),
			"emf.ecore", "emf.edit.tree"
		);
		// only EObject sutypes
	}

	@Test
	public void testProposalForFeatureAssociatedExpression() throws Exception {
		assertProposalSolutions(newBuilder().append(
			"""
			module my.test.proj {
			\t
				featureCaptionProvider {
					text {\s"""
		),
			"emf.ecore", "emf.edit.tree"
		);
		// only EObject sutypes
	}

	@Test
	public void testApplyProposalForValueBindingAlsoInsertsImport() throws Exception {
		appendAndApplyProposalAndExpectContent(
			"""
			module my.test.proj {
				
				bindings {
					value valueT""",
			"List<Integer> TableColumnWeights",
			"""
			import java.util.List
			
			module my.test.proj {
				
				bindings {
					value List<Integer> TableColumnWeights"""
		);
	}

	@Test
	public void testProposalForValueBindingType() throws Exception {
		newBuilder().append(
			"""
			module my.test.proj {
				
				bindings {
					value EObje<|>"""
		).assertProposalAtCursor(
			"org.eclipse.emf.ecore.EObject"
		).apply().expectContent(
			"""
			import org.eclipse.emf.ecore.EObject
			
			module my.test.proj {
				
				bindings {
					value EObject"""
		);
	}

	@Test
	public void testProposalForTypeBinding() throws Exception {
		appendAndApplyProposalAndExpectContent(
			"""
			module my.test.proj {
				
				bindings {
					type bindILa""",
			"ILabelProvider",
			"""
			import org.eclipse.jface.viewers.ILabelProvider
			
			module my.test.proj {
				
				bindings {
					type ILabelProvider"""
		);
	}

	@Test
	public void testProposalForProviderBinding() throws Exception {
		appendAndApplyProposalAndExpectContent(
			"""
			module my.test.proj {
				
				bindings {
					provide provideAda""",
			"AdapterFactoryEditingDomain",
			"""
			import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
			
			module my.test.proj {
				
				bindings {
					provide AdapterFactoryEditingDomain"""
		);
	}

	@Test
	public void testProposalForXFeatureCall_Feature() throws Exception {
		appendAndApplyProposalAndExpectContent(
			"""
			import org.eclipse.emf.ecore.EClass
			
			module my.test.proj {
				
				labelProvider {
					text {
						EClass e -> { e.""",
			"eAdapters",
			"""
			import org.eclipse.emf.ecore.EClass
			
			module my.test.proj {
				
				labelProvider {
					text {
						EClass e -> { e.eAdapters"""
		);
	}

	@Test
	public void testTemplateProposalForViewSpecification() throws Exception {
		newBuilder().append(
			"""
			module my.test.proj {
				parts {\s
			"""
		).applyProposal("ViewSpecification - Template for ViewSpecification")
		.expectContent(
			"""
			module my.test.proj {
				parts {\s
			viewpart id {
				viewname "View Name"
				viewclass viewclassref
				// viewcategory my.category
			}"""
		);
	}

	private void assertProposalSolutions(ContentAssistProcessorTestBuilder builder, String... acceptableParts) throws Exception {
		for (var p : builder.computeCompletionProposals()) {
			assertTrue(
				"proposal not expected: " + p.getDisplayString(),
				containsAny(p.getDisplayString(), acceptableParts)
			);
		}
	}

	private boolean containsAny(String displayString, String[] acceptableParts) {
		for (var part : acceptableParts) {
			if (displayString.contains(part)) {
				return true;
			}
		}
		return false;
	}

	private void appendAndApplyProposalAndExpectContent(String model, String proposal, String expectedContent) throws Exception {
		// we must use appendAndApplyProposal and NOT assertProposal.apply because the ContentAssistProcessorTestBuilder
		// will recreate an XtextDocument, and some ICompletionProposal proposals, like the one we use,
		// ImportOrganizingProposal and ReplacingAppendable, don't use the passed document (ReplacingAppendable
		// uses its internal stored document) and this would make our tests fail
		newBuilder()
			.append(model)
			.appendAndApplyProposal(
				"", // appendAndApplyProposals looks buggy: it does not append the passed String
				// before computing the proposals, so we first append it and then pass an empty string
				proposal
			).expectContent(expectedContent);
	}
}
