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
import org.eclipse.emf.parsley.dsl.EmfParsleyDslUiInjectorProvider
import org.eclipse.emf.parsley.dsl.tests.util.ui.PluginProjectHelper
import org.eclipse.emf.parsley.dsl.ui.internal.EmfParsleyDslActivator
import org.eclipse.emf.parsley.tests.pde.utils.PDETargetPlatformUtils
import org.eclipse.emf.parsley.views.EmfParsleyViewsActivator
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.xbase.junit.ui.AbstractContentAssistTest
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.eclipse.emf.parsley.EmfParsleyGuiceModule
import org.eclipse.xtext.junit4.ui.ContentAssistProcessorTestBuilder
import static extension org.junit.Assert.*

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(EmfParsleyDslUiInjectorProvider))
/**
 * @author Lorenzo Bettini
 */
class EmfParsleyDslContentAssistTest extends AbstractContentAssistTest {
	
	static IJavaProject pluginJavaProject
	
	val static PROJECT_NAME = "customPluginProject"
	
	@BeforeClass
	def static void setUp() {
		PDETargetPlatformUtils.setTargetPlatform();
		
		val injector = EmfParsleyDslActivator.getInstance().getInjector
			(EmfParsleyDslActivator.ORG_ECLIPSE_EMF_PARSLEY_DSL_EMFPARSLEYDSL);
		
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
		newBuilder.append('import LinkedHashSet').assertText('java.util.LinkedHashSet')
	}
	
	@Test def void testTypeCompletion() {
		newBuilder.append(
'''
module my.parsley.project {
	
	labelProvider {
		text {
			LinkedHashSet'''			
		).assertText('java.util.LinkedHashSet')
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
		).assertText(EmfParsleyGuiceModule.canonicalName)
		// that's the only possible completion in this test
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

	@Test def void testProposalForValueBinding() {
		newBuilder.append(
'''
module my.test.proj {
	
	bindings {
		value valueT<|>'''
		).assertProposalAtCursor(
'''List<Integer> TableColumnWeights'''
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
		value EObject<|>'''
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

//	def private assertProposals(ContentAssistProcessorTestBuilder builder, CharSequence expected) {
//		builder.assertText(expected.toString.split("\\r?\\n"))
//	}
}