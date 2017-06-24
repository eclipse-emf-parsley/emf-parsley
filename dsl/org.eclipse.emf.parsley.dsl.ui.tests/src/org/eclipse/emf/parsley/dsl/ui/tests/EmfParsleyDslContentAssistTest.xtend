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
package org.eclipse.emf.parsley.dsl.ui.tests

import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.parsley.EmfParsleyGuiceModule
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule
import org.eclipse.emf.parsley.dsl.tests.util.ui.PluginProjectHelper
import org.eclipse.emf.parsley.dsl.ui.internal.DslActivator
import org.eclipse.emf.parsley.tests.pde.utils.PDETargetPlatformUtils
import org.eclipse.emf.parsley.views.EmfParsleyViewsActivator
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.ui.testing.ContentAssistProcessorTestBuilder
import org.eclipse.xtext.ui.testing.AbstractContentAssistTest
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*

/**
 * @author Lorenzo Bettini
 */
@RunWith(typeof(XtextRunner))
@InjectWith(typeof(EmfParsleyDslUiInjectorProvider))
class EmfParsleyDslContentAssistTest extends AbstractContentAssistTest {
	
	static IJavaProject pluginJavaProject
	
	val static PROJECT_NAME = "customPluginProject"
	
	@BeforeClass
	def static void setUp() {
		PDETargetPlatformUtils.setTargetPlatform();
		
		val injector = DslActivator.getInstance().getInjector
			(DslActivator.ORG_ECLIPSE_EMF_PARSLEY_DSL_EMFPARSLEYDSL);
		
		val projectHelper = injector.getInstance(PluginProjectHelper)
		
		pluginJavaProject = projectHelper.createJavaPluginProject
			(PROJECT_NAME, newArrayList(
				"org.eclipse.core.runtime",
				"org.eclipse.ui",
				EmfParsleyViewsActivator.PLUGIN_ID,
				"org.eclipse.xtext.xbase.lib"))
	}
	
	@AfterClass
	def static void tearDown() {
		pluginJavaProject.project.delete(true, new NullProgressMonitor)
	}
	
	override getJavaProject(ResourceSet resourceSet) {
		pluginJavaProject
	}
	
	@Test def void testImportCompletion() {
		newBuilder.append('import java.util.Da').assertText('java.util.Date')
	}
	
	@Test def void testImportCompletion_1() {
		newBuilder.append('import LinkedHashSet').assertProposal('java.util.LinkedHashSet')
	}
	
	@Test def void testTypeCompletionInsertsImport() {
		appendAndApplyProposalAndExpectContent(
'''
module my.parsley.project {
	
	labelProvider {
		text {
			LinkedHashSet''',
'java.util.LinkedHashSet',
'''
import java.util.LinkedHashSet

module my.parsley.project {
	
	labelProvider {
		text {
			LinkedHashSet'''
)
	}

	@Test def void testEmfFeatureForFeatureProvider() {
		newBuilder.append(
'''
import java.util.List

module my.test.proj {

	featuresProvider {
		features {
			List -> '''			
		).assertText('class', 'empty')
		// these correspond to getClass and isEmpty
	}

	@Test def void testEmfFeatureForPropertyDescriptionProvider() {
		newBuilder.append(
'''
import java.util.List

module my.test.proj {

	featureCaptionProvider {
		text {
			List : '''			
		).assertText('class', 'empty')
		// these correspond to getClass and isEmpty
	}

	@Test def void testEmfFeatureForFormControlFactory() {
		newBuilder.append(
'''
import java.util.List

module my.test.proj {

	formControlFactory {
		control {
			List : '''			
		).assertText('class', 'empty')
		// these correspond to getClass and isEmpty
	}

	@Test def void testEmfFeatureForProposalSpecification() {
		newBuilder.append(
'''
import java.util.List

module my.test.proj {

	proposals {
			List : '''			
		).assertText('class', 'empty')
		// these correspond to getClass and isEmpty
	}

	@Test def void testProposalsForModuleExtends() {
		newBuilder.append(
'''module my.test.proj extends '''
		).assertText(
			EmfParsleyGuiceModule.canonicalName,
			EmfParsleyJavaGuiceModule.canonicalName
		)
		// these are the the only possible completions in this test
	}

	@Test def void testProposalForTypeInViewSpecification() {
		newBuilder.append(
'''
module my.test.proj {
	
	parts {
		viewpart id {
			viewname "View Name"
			viewclass '''
		).assertProposalSolutions(
"View", "E4PartWrapper", "ContentOutline", "PropertySheet"
		)
		// only IViewPart sutypes
	}

	@Test def void testProposalForFeatureSpecification() {
		newBuilder.append(
'''
module my.test.proj {
	
	featuresProvider {
		features { '''
		).assertProposalSolutions(
"emf.ecore", "emf.edit.tree"
		)
		// only EObject sutypes
	}

	@Test def void testProposalForControlFactoryFeatureSpecification() {
		newBuilder.append(
'''
module my.test.proj {
	
	formControlFactory {
		control { '''
		).assertProposalSolutions(
"emf.ecore", "emf.edit.tree"
		)
		// only EObject sutypes
	}

	@Test def void testProposalForFeatureAssociatedExpression() {
		newBuilder.append(
'''
module my.test.proj {
	
	featureCaptionProvider {
		text { '''
		).assertProposalSolutions(
"emf.ecore", "emf.edit.tree"
		)
		// only EObject sutypes
	}

	@Test def void testApplyProposalForValueBindingAlsoInsertsImport() {
		appendAndApplyProposalAndExpectContent(
'''
module my.test.proj {
	
	bindings {
		value valueT''',
'''List<Integer> TableColumnWeights''',
'''
import java.util.List

module my.test.proj {
	
	bindings {
		value List<Integer> TableColumnWeights'''
		)
	}

	@Test def void testProposalForValueBindingType() {
		newBuilder.append(
'''
module my.test.proj {
	
	bindings {
		value EObje<|>'''
		).assertProposalAtCursor(
'''org.eclipse.emf.ecore.EObject'''
		).apply.expectContent(
'''
import org.eclipse.emf.ecore.EObject

module my.test.proj {
	
	bindings {
		value EObject'''
		)
	}

	@Test def void testProposalForTypeBinding() {
		appendAndApplyProposalAndExpectContent(
'''
module my.test.proj {
	
	bindings {
		type bindILa''',
'''ILabelProvider''',
'''
import org.eclipse.jface.viewers.ILabelProvider

module my.test.proj {
	
	bindings {
		type ILabelProvider'''
		)
	}

	@Test def void testProposalForProviderBinding() {
		appendAndApplyProposalAndExpectContent(
'''
module my.test.proj {
	
	bindings {
		provide provideAda''',
'''AdapterFactoryEditingDomain''',
'''
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain

module my.test.proj {
	
	bindings {
		provide AdapterFactoryEditingDomain'''
		)
	}

	@Test def void testProposalForXFeatureCall_Feature() {
		appendAndApplyProposalAndExpectContent(
'''
import org.eclipse.emf.ecore.EClass

module my.test.proj {
	
	labelProvider {
		text {
			EClass e -> { e.''',
'''eAdapters''',
'''
import org.eclipse.emf.ecore.EClass

module my.test.proj {
	
	labelProvider {
		text {
			EClass e -> { e.eAdapters'''
		)
	}

	def private assertProposalSolutions(ContentAssistProcessorTestBuilder builder, String...acceptableParts) {
		for (p : builder.computeCompletionProposals) {
			assertTrue(
				"proposal not expected: " + p.displayString,
				acceptableParts.exists[p.displayString.contains(it)]
			)
		}
	}

	def private appendAndApplyProposalAndExpectContent(String model, String proposal, String expectedContent) {
		// we must use appendAndApplyProposal and NOT assertProposal.apply because the ContentAssistProcessorTestBuilder
		// will recreate an XtextDocument, and some ICompletionProposal proposals, like the one we use,
		// ImportOrganizingProposal and ReplacingAppendable, don't use the passed document (ReplacingAppendable
		// uses its internal stored document) and this would make our tests fail
		newBuilder.
		append(model).
		appendAndApplyProposal(
			"", // appendAndApplyProposals looks buggy: it does not append the passed String
			// before computing the proposals, so we first append it and then pass an empty string
			proposal
		).expectContent(expectedContent)
	}

//	def private assertProposals(ContentAssistProcessorTestBuilder builder, CharSequence expected) {
//		builder.assertText(expected.toString.split("\\r?\\n"))
//	}
}