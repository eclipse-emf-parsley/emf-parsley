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

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.parsley.composite.AbstractMasterDetailComposite
import org.eclipse.emf.parsley.composite.IDetailComposite
import org.eclipse.emf.parsley.composite.IMasterComposite
import org.eclipse.emf.parsley.inject.parameters.CompositeParameters
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.jface.viewers.ISelectionChangedListener
import org.eclipse.jface.viewers.SelectionChangedEvent
import org.eclipse.jface.viewers.StructuredSelection
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Composite
import org.junit.Test

import static org.mockito.Mockito.*

import static extension org.junit.Assert.*

abstract class AbstractMasterDetailCompositeTest extends AbstractEmfParsleyShellBasedTest {

	static class MasterDetailComposite extends AbstractMasterDetailComposite {
		public var IMasterComposite mockMasterComposite = mock(IMasterComposite);

		public var IDetailComposite mockDetailComposite

		public var ISelectionChangedListener selectionChangedListener

		new(CompositeParameters params, int sashStyle, int[] weights) {
			super(params, sashStyle, weights)
		}

		override protected createDetailComposite(Composite parent, EObject selectedObject) {
			mockDetailComposite = mock(IDetailComposite)
			return mockDetailComposite
		}

		override protected createMasterComposite(Composite parent) {
			// capture the selection changed listener for later use
			doAnswer[ invocation |
				selectionChangedListener = invocation.arguments.head as ISelectionChangedListener;
				return null
			].when(mockMasterComposite).addSelectionChangedListener(any(ISelectionChangedListener))
			return mockMasterComposite
		}

		override getSashForm() {
			super.getSashForm()
		}

	}

	var MasterDetailComposite SUT;

	@Test def void testSashFormWeigths() {
		SUT = createSUT(0, #[])
		"200, 200".assertEquals(SUT.sashForm.weights.map[toString].join(", "))
	}

	@Test def void testCustomSashFormWeigths() {
		SUT = createSUT(0, #[1, 2])
		"333, 666".assertEquals(SUT.sashForm.weights.map[toString].join(", "))
	}

	@Test def void testSetSashFormOrientation() {
		SUT = createSUT(SWT.VERTICAL, #[])
		SUT.setSashFormOrientation(SWT.HORIZONTAL)
		assertTrue(SWT.HORIZONTAL.bitwiseAnd(SUT.sashForm.style) != 0)
	}

	@Test def void testUpdateDelegatesToMasterComposite() {
		SUT = createSUT(SWT.VERTICAL, #[])
		val o = new Object
		SUT.update(o)
		verify(SUT.mockMasterComposite).update(o)
	}

	@Test def void testSelectionChangeCreatesDetailComposite() {
		SUT = createSUT(0, #[])
		SUT.mockDetailComposite.assertNull
		triggerSelectionChanged(SUT.selectionChangedListener, EcoreFactory.eINSTANCE.createEClass)
		SUT.mockDetailComposite.assertNotNull
	}

	@Test def void testSelectionChangeCreatesDetailCompositeAfterDispose() {
		SUT = createSUT(0, #[])
		triggerSelectionChanged(SUT.selectionChangedListener, EcoreFactory.eINSTANCE.createEClass)
		val previousDetailComposite = SUT.mockDetailComposite
		previousDetailComposite.assertNotNull
		triggerSelectionChanged(SUT.selectionChangedListener, EcoreFactory.eINSTANCE.createEClass)
		verify(previousDetailComposite).dispose
	}

	@Test def void testSelectionChangeWithNonEObjectDoesNotCreateDetailComposite() {
		SUT = createSUT(0, #[])
		SUT.mockDetailComposite.assertNull
		triggerSelectionChanged(SUT.selectionChangedListener, "noEObject")
		SUT.mockDetailComposite.assertNull
	}

	private def Object triggerSelectionChanged(ISelectionChangedListener selectionChangedListener, Object selected) {
		val selectionEvent = mock(SelectionChangedEvent)
		when(selectionEvent.getSelection()).thenReturn(new StructuredSelection(selected))
		selectionChangedListener.selectionChanged(selectionEvent)
		return selected
	}

	private def createSUT(int sashStyle, int[] weights) {
		return new MasterDetailComposite(new CompositeParameters(shell, 0), sashStyle, weights).injectMembers
	}
}
