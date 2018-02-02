/*******************************************************************************
 * Copyright (c) 2018 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.edit.domain.EditingDomain
import org.eclipse.emf.parsley.composite.CompositeFactory
import org.eclipse.emf.parsley.composite.TreeFormComposite
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.SashForm
import org.junit.Before
import org.junit.Test

import static extension org.junit.Assert.*

/**
 * Make sure we can instantiate all our composites via dependency injection.
 */
class CompositeFactoryTest extends AbstractControlFactoryTest {

	var CompositeFactory factory

	var EClass eClass

	@Before
	def void setupFactory() {
		factory = getOrCreateInjector.getInstance(CompositeFactory)
		eClass = EcorePackage.eINSTANCE.EObject
	}

	@Test
	def void canCreateFormDetailComposite() {
		factory.createFormDetailComposite(shell, SWT.NONE, eClass)
	}

	@Test
	def void canCreateFormDetailCompositeWithEditingDomain() {
		factory.createFormDetailComposite(shell, SWT.NONE, eClass, editingDomain)
	}

	@Test
	def void canCreateFormDetailCompositeWithNullEditingDomain() {
		factory.createFormDetailComposite(shell, SWT.NONE, eClass, null)
	}

	@Test
	def void canCreateFormDetailReadOnlyComposite() {
		factory.createFormDetailReadOnlyComposite(shell, SWT.NONE, eClass)
	}

	@Test
	def void canCreateDialogDetailComposite() {
		factory.createDialogDetailComposite(shell, SWT.NONE, eClass, editingDomain)
	}

	@Test
	def void canCreateDialogDetailCompositeWithNullEditingDomain() {
		factory.createDialogDetailComposite(shell, SWT.NONE, eClass, null)
	}

	@Test
	def void canCreateTreeFormComposite() {
		factory.createTreeFormComposite(shell, SWT.NONE)
	}

	@Test
	def void testDefaultSashProperties() {
		syncExecVoid[
			val treeFormComposite = getOrCreateInjector.getInstance(CompositeFactory).
				createTreeFormComposite(shell, 0)
			val sashForm = treeFormComposite.sashForm
			assertTrue(SWT.VERTICAL.bitwiseAnd(sashForm.style) != 0)
			"200, 200".assertEquals(sashForm.weights.map[toString].join(", "))
		]
	}

	@Test
	def void testCustomSashProperties() {
		syncExecVoid[
			val treeFormComposite = createInjector(
				new EmfParsleyGuiceModuleForTesting {
					override valueTreeFormSashStyle() {
						return SWT.HORIZONTAL;
					}
				
					override int[] valueTreeFormSashWeights() {
						#[1, 2]
					}
				}
			).
			getInstance(CompositeFactory).
			createTreeFormComposite(shell, 0)
			val sashForm = treeFormComposite.sashForm
			assertTrue(SWT.HORIZONTAL.bitwiseAnd(sashForm.style) != 0)
			"333, 666".assertEquals(sashForm.weights.map[toString].join(", "))
		]
	}

	@Test
	def void canCreateTableFormComposite() {
		factory.createTableFormComposite(shell, SWT.NONE, eClass)
	}

	@Test
	def void canCreateDialogWidgetFactory() {
		factory.createDialogWidgetFactory(shell)
	}

	@Test
	def void canCreateFormWidgetFactory() {
		factory.createFormWidgetFactory(shell, formToolkit)
	}

	@Test
	def void canCreateFeatureLabelCaptionProvider() {
		factory.createFeatureLabelCaptionProvider
	}

	@Test
	def void canCreateFormFeatureCaptionProvider() {
		factory.createFormFeatureCaptionProvider(formToolkit)
	}

	@Test
	def void canCreateDialogControlFactory() {
		factory.createDialogControlFactory(shell, EcorePackage.eINSTANCE.EObject, editingDomain)
	}

	@Test
	def void canCreateFormControlFactory() {
		factory.createFormControlFactory(shell, EcorePackage.eINSTANCE.EObject, editingDomain, formToolkit)
	}

	def private getSashForm(TreeFormComposite treeFormComposite) {
		// access the private field for testing purposes
		val sashForm = treeFormComposite.class.superclass.getDeclaredField("sashForm")
		sashForm.accessible = true
		sashForm.get(treeFormComposite) as SashForm
	}

	override protected getEditingDomain() {
		getOrCreateInjector.getInstance(EditingDomain)
	}
}
