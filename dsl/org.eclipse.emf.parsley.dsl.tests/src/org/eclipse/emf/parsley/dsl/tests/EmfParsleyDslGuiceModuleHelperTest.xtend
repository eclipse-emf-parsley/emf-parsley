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
package org.eclipse.emf.parsley.dsl.tests

import com.google.inject.Inject
import org.eclipse.emf.parsley.dsl.model.Model
import org.eclipse.emf.parsley.dsl.util.EmfParsleyDslGuiceModuleHelper
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Test
import org.junit.runner.RunWith

import static extension org.junit.Assert.*

@RunWith(XtextRunner)
@InjectWith(EmfParsleyDslInjectorProvider)
class EmfParsleyDslGuiceModuleHelperTest extends EmfParsleyDslAbstractTest {

	@Inject extension ParseHelper<Model>
	@Inject extension EmfParsleyDslGuiceModuleHelper

	@Test
	def void testModuleWithNoNameInferredType() {
		'''
		module
		'''.parse.module.moduleInferredType.assertNull
	}

	@Test
	def void testModuleWithNoNameGuiceValueBindingsMethodsInSuperclass() {
		assertTrue(
		'''
		module
		'''.parse.module.allGuiceValueBindingsMethodsInSuperclass.empty)
	}

	@Test
	def void testModuleGuiceValueBindingsMethodsInSuperclass() {
		// ATTENTION: if you add a new value method in EmfParsleyGuiceModule this test
		// will fail: you must fix it by updating the expected list in the CharSequence
		assertEqualsStrings(
		'''
valueContentAssistShortcut
valueIterableStringSeparator
valueIterableStringEllipses
valueIterableStringMaxLength
valueDefaultTableColumnWeight
valueTableColumnWeights
valueTreeFormSashStyle
valueTreeFormSashWeights''',
		'''
		module my.test.mod {}
		'''.parse.module.allGuiceValueBindingsMethodsInSuperclass.map[simpleName].join("\n"))
	}

	@Test
	def void testModuleGuiceTypeBindingsMethodsInSuperclass() {
		// ATTENTION: if you add a new bind method in EmfParsleyGuiceModule this test
		// will fail: you must fix it by updating the expected list in the CharSequence
		// These are only the bind methods that return a Class<? extends Something>
		assertEqualsStrings(
		'''
bindIImageHelper
bindResourceLoader
bindIEditorMouseListener
bindIViewerMouseListener
bindILabelProvider
bindFeatureCaptionProvider
bindFormFeatureCaptionProvider
bindDialogFeatureCaptionProvider
bindOutlineSelectionHandler
bindViewerFactory
bindTreeFormFactory
bindFormFactory
bindViewerContextMenuHelper
bindViewerDragAndDropHelper
bindFormControlFactory
bindDialogControlFactory
bindColumnLabelProviderFactory
bindTableColumnLabelProvider
bindTableViewerColumnBuilder
bindWorkbenchActionBarContributor
bindLightweightActionBarContributor
bindEditingActionManager
bindEditingMenuBuilder
bindEditingDomainFinder
bindFeaturesProvider
bindFeatureResolver
bindTableFeaturesProvider
bindEmfSelectionHelper
bindEmfEventHelper
bindResourceSaveStrategy
bindResourceManager
bindIContentProvider
bindTableViewerContentProvider
bindProposalCreator
bindIEditingStrategy
bindConfigurator
bindIssueReporter
bindValidationRunner
bindDiagnosticUtil
bindFeatureHelper
bindAsyncCommandStackListener
bindAdapterFactory
bindAdapterFactoryLabelProvider''',
		'''
		module my.test.mod {}
		'''.parse.module.allGuiceTypeBindingsMethodsInSuperclass.map[simpleName].join("\n"))
	}

	@Test
	def void testModuleGuiceProviderBindingsMethodsInSuperclass() {
		// ATTENTION: if you add a new provide method in EmfParsleyGuiceModule this test
		// will fail: you must fix it by updating the expected list in the CharSequence
		assertEqualsStrings(
		'''
provideAdapterFactoryEditingDomain
provideEditingDomain''',
		'''
		module my.test.mod {}
		'''.parse.module.allGuiceProviderBindingsMethodsInSuperclass.map[simpleName].join("\n"))
	}

	@Test
	def void testContainsConstructorAcceptingSinglePluginParameter() {
		val module = '''
		module my.empty extends org.eclipse.emf.parsley.dsl.tests.inputs.TestIntermediateEmfParsleyJavaGuiceModule {
			
		}
		'''.parse.module
		module
			.containsConstructorAcceptingPluginParameter(module.extendsClause.superType)
			.assertTrue
	}

	@Test
	def void testDoesNotContainConstructorAcceptingSinglePluginParameter() {
		val module = '''
		module my.empty extends org.eclipse.emf.parsley.dsl.tests.inputs.TestIntermediateEmfParsleyGuiceModuleWithTwoParameterConstructor {
			
		}
		'''.parse.module
		module
			.containsConstructorAcceptingPluginParameter(module.extendsClause.superType)
			.assertFalse
	}

	@Test
	def void testDoesNotContainConstructorAcceptingSinglePluginParameter2() {
		val module = '''
		module my.empty extends org.eclipse.emf.parsley.dsl.tests.inputs.TestIntermediateEmfParsleyGuiceModuleWithOneParameterConstructor {
			
		}
		'''.parse.module
		module
			.containsConstructorAcceptingPluginParameter(module.extendsClause.superType)
			.assertFalse
	}

	@Test
	def void testDoesNotContainConstructorAcceptingSinglePluginParameter3() {
		val module = '''
		module my.empty extends NoExistant {
			
		}
		'''.parse.module
		module
			.containsConstructorAcceptingPluginParameter(module.extendsClause.superType)
			.assertFalse
	}
}