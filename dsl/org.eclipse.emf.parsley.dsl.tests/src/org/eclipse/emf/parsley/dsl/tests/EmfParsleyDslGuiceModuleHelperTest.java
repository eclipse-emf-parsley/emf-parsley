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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.stream.StreamSupport;

import org.eclipse.emf.parsley.dsl.model.Model;
import org.eclipse.emf.parsley.dsl.util.EmfParsleyDslGuiceModuleHelper;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

@RunWith(XtextRunner.class)
@InjectWith(EmfParsleyDslInjectorProvider.class)
public class EmfParsleyDslGuiceModuleHelperTest extends EmfParsleyDslAbstractTest {

	@Inject
	private ParseHelper<Model> parseHelper;

	@Inject
	private EmfParsleyDslGuiceModuleHelper guiceModuleHelper;

	@Test
	public void testModuleWithNoNameInferredType() throws Exception {
		assertNull(guiceModuleHelper.getModuleInferredType(
			parseHelper.parse("""
			module
			""").getModule()));
	}

	@Test
	public void testModuleWithNoNameGuiceValueBindingsMethodsInSuperclass() throws Exception {
		assertFalse(
			guiceModuleHelper.getAllGuiceValueBindingsMethodsInSuperclass(
				parseHelper.parse("""
				module
				""").getModule()).iterator().hasNext());
	}

	@Test
	public void testModuleGuiceValueBindingsMethodsInSuperclass() throws Exception {
		// ATTENTION: if you add a new value method in EmfParsleyGuiceModule this test
		// will fail: you must fix it by updating the expected list in the CharSequence
		assertEqualsStrings(
		"""
valueContentAssistShortcut
valueIterableStringSeparator
valueIterableStringEllipses
valueIterableStringMaxLength
valueDefaultTableColumnWeight
valueTableColumnWeights
valueTreeFormSashStyle
valueTreeFormSashWeights""",
		String.join("\n",
			StreamSupport.stream(guiceModuleHelper.getAllGuiceValueBindingsMethodsInSuperclass(
				parseHelper.parse("""
				module my.test.mod {}
				""").getModule()
			).spliterator(), false).map(it -> it.getSimpleName()).toList()));
	}

	@Test
	public void testModuleGuiceTypeBindingsMethodsInSuperclass() throws Exception {
		// ATTENTION: if you add a new bind method in EmfParsleyGuiceModule this test
		// will fail: you must fix it by updating the expected list in the CharSequence
		// These are only the bind methods that return a Class<? extends Something>
		assertEqualsStrings(
		"""
bindIImageHelper
bindResourceLoader
bindIEditorMouseListener
bindIViewerMouseListener
bindILabelProvider
bindFeatureCaptionProvider
bindFormFeatureCaptionProvider
bindDialogFeatureCaptionProvider
bindOutlineSelectionHandler
bindViewerContextMenuHelper
bindViewerDragAndDropHelper
bindFormControlFactory
bindDialogControlFactory
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
bindAdapterFactoryLabelProvider""",
		String.join("\n",
			StreamSupport.stream(guiceModuleHelper.getAllGuiceTypeBindingsMethodsInSuperclass(
				parseHelper.parse("""
				module my.test.mod {}
				""").getModule()
			).spliterator(), false).map(it -> it.getSimpleName()).toList()));
	}

	@Test
	public void testModuleGuiceProviderBindingsMethodsInSuperclass() throws Exception {
		// ATTENTION: if you add a new provide method in EmfParsleyGuiceModule this test
		// will fail: you must fix it by updating the expected list in the CharSequence
		assertEqualsStrings(
		"""
provideAdapterFactoryEditingDomain
provideEditingDomain""",
		String.join("\n",
			StreamSupport.stream(guiceModuleHelper.getAllGuiceProviderBindingsMethodsInSuperclass(
				parseHelper.parse("""
				module my.test.mod {}
				""").getModule()
			).spliterator(), false).map(it -> it.getSimpleName()).toList()));
	}

	@Test
	public void testContainsConstructorAcceptingSinglePluginParameter() throws Exception {
		var module = parseHelper.parse("""
		module my.empty extends org.eclipse.emf.parsley.dsl.tests.inputs.TestIntermediateEmfParsleyJavaGuiceModule {
			
		}
		""").getModule();
		assertTrue(
			guiceModuleHelper.containsConstructorAcceptingPluginParameter(module, module.getExtendsClause().getSuperType())
		);
	}

	@Test
	public void testDoesNotContainConstructorAcceptingSinglePluginParameter() throws Exception {
		var module = parseHelper.parse("""
		module my.empty extends org.eclipse.emf.parsley.dsl.tests.inputs.TestIntermediateEmfParsleyGuiceModuleWithTwoParameterConstructor {
			
		}
		""").getModule();
		assertFalse(
			guiceModuleHelper.containsConstructorAcceptingPluginParameter(module, module.getExtendsClause().getSuperType())
		);
	}

	@Test
	public void testDoesNotContainConstructorAcceptingSinglePluginParameter2() throws Exception {
		var module = parseHelper.parse("""
		module my.empty extends org.eclipse.emf.parsley.dsl.tests.inputs.TestIntermediateEmfParsleyGuiceModuleWithOneParameterConstructor {
			
		}
		""").getModule();
		assertFalse(
			guiceModuleHelper.containsConstructorAcceptingPluginParameter(module, module.getExtendsClause().getSuperType())
		);
	}

	@Test
	public void testDoesNotContainConstructorAcceptingSinglePluginParameter3() throws Exception {
		var module = parseHelper.parse("""
		module my.empty extends NoExistant {
			
		}
		""").getModule();
		assertFalse(
			guiceModuleHelper.containsConstructorAcceptingPluginParameter(module, module.getExtendsClause().getSuperType())
		);
	}
}
