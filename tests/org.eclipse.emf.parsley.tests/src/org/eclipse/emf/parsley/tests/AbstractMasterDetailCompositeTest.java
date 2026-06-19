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
package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.parsley.composite.AbstractMasterDetailComposite;
import org.eclipse.emf.parsley.composite.IDetailComposite;
import org.eclipse.emf.parsley.composite.IMasterComposite;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameters;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class AbstractMasterDetailCompositeTest extends AbstractEmfParsleyShellBasedTest {

	public static class MasterDetailComposite extends AbstractMasterDetailComposite {
		public IMasterComposite mockMasterComposite = mock(IMasterComposite.class);

		public IDetailComposite mockDetailComposite;

		public ISelectionChangedListener selectionChangedListener;

		public MasterDetailComposite(CompositeParameters params, int sashStyle, int[] weights) {
			super(params, sashStyle, weights);
		}

		@Override
		protected IDetailComposite createDetailComposite(Composite parent, EObject selectedObject) {
			mockDetailComposite = mock(IDetailComposite.class);
			return mockDetailComposite;
		}

		@Override
		protected IMasterComposite createMasterComposite(Composite parent) {
			// capture the selection changed listener for later use
			doAnswer(invocation -> {
				selectionChangedListener = (ISelectionChangedListener) invocation.getArguments()[0];
				return null;
			}).when(mockMasterComposite).addSelectionChangedListener(any(ISelectionChangedListener.class));
			return mockMasterComposite;
		}

		@Override
		public SashForm getSashForm() {
			return super.getSashForm();
		}
	}

	private MasterDetailComposite SUT;

	@Test
	public void testSashFormWeigths() {
		SUT = createSUT(0, new int[] {});
		assertEquals("200, 200",
			Arrays.stream(SUT.getSashForm().getWeights()).mapToObj(Integer::toString).collect(Collectors.joining(", ")));
	}

	@Test
	public void testCustomSashFormWeigths() {
		SUT = createSUT(0, new int[] { 1, 2 });
		assertEquals("333, 666",
			Arrays.stream(SUT.getSashForm().getWeights()).mapToObj(Integer::toString).collect(Collectors.joining(", ")));
	}

	@Test
	public void testSetSashFormOrientation() {
		SUT = createSUT(SWT.VERTICAL, new int[] {});
		SUT.setSashFormOrientation(SWT.HORIZONTAL);
		assertTrue((SWT.HORIZONTAL & SUT.getSashForm().getStyle()) != 0);
	}

	@Test
	public void testUpdateDelegatesToMasterComposite() {
		SUT = createSUT(SWT.VERTICAL, new int[] {});
		var o = new Object();
		SUT.update(o);
		verify(SUT.mockMasterComposite).update(o);
	}

	@Test
	public void testSelectionChangeCreatesDetailComposite() {
		SUT = createSUT(0, new int[] {});
		assertNull(SUT.mockDetailComposite);
		triggerSelectionChanged(SUT.selectionChangedListener, EcoreFactory.eINSTANCE.createEClass());
		assertNotNull(SUT.mockDetailComposite);
	}

	@Test
	public void testSelectionChangeCreatesDetailCompositeAfterDispose() {
		SUT = createSUT(0, new int[] {});
		triggerSelectionChanged(SUT.selectionChangedListener, EcoreFactory.eINSTANCE.createEClass());
		var previousDetailComposite = SUT.mockDetailComposite;
		assertNotNull(previousDetailComposite);
		triggerSelectionChanged(SUT.selectionChangedListener, EcoreFactory.eINSTANCE.createEClass());
		verify(previousDetailComposite).dispose();
	}

	@Test
	public void testSelectionChangeWithNonEObjectDoesNotCreateDetailComposite() {
		SUT = createSUT(0, new int[] {});
		assertNull(SUT.mockDetailComposite);
		triggerSelectionChanged(SUT.selectionChangedListener, "noEObject");
		assertNull(SUT.mockDetailComposite);
	}

	private Object triggerSelectionChanged(ISelectionChangedListener selectionChangedListener, Object selected) {
		var selectionEvent = mock(SelectionChangedEvent.class);
		when(selectionEvent.getSelection()).thenReturn(new StructuredSelection(selected));
		selectionChangedListener.selectionChanged(selectionEvent);
		return selected;
	}

	private MasterDetailComposite createSUT(int sashStyle, int[] weights) {
		return injectMembers(new MasterDetailComposite(new CompositeParameters(getShell(), 0), sashStyle, weights));
	}
}
