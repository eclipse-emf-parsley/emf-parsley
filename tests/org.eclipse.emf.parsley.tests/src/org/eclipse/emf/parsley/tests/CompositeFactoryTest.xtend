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

import org.eclipse.emf.parsley.composite.CompositeFactory
import org.eclipse.emf.parsley.composite.TreeFormComposite
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.SashForm
import org.junit.Before
import org.junit.Test

import static extension org.junit.Assert.*
import org.eclipse.emf.ecore.EcorePackage

/**
 * Make sure we can instantiate all our composites via dependency injection.
 */
class CompositeFactoryTest extends AbstractEmfParsleyShellBasedTest {

	var CompositeFactory factory

	@Before
	def void setupFactory() {
		factory = getOrCreateInjector.getInstance(CompositeFactory)
	}

	@Test
	def void canCreateFormDetailComposite() {
		factory.createFormDetailComposite(shell, SWT.NONE)
	}

	@Test
	def void canCreateFormDetailReadOnlyComposite() {
		factory.createFormDetailReadOnlyComposite(shell, SWT.NONE)
	}

	@Test
	def void canCreateDialogDetailComposite() {
		factory.createDialogDetailComposite(shell, SWT.NONE)
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
		factory.createTableFormComposite(shell, SWT.NONE, EcorePackage.eINSTANCE.EObject)
	}

	def private getSashForm(TreeFormComposite treeFormComposite) {
		// access the private field for testing purposes
		val sashForm = treeFormComposite.class.superclass.getDeclaredField("sashForm")
		sashForm.accessible = true
		sashForm.get(treeFormComposite) as SashForm
	}
}
