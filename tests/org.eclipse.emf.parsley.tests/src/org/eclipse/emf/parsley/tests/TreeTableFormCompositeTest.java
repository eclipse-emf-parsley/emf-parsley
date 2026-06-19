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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.google.inject.util.Modules;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.parsley.EmfParsleyConstants;
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule;
import org.eclipse.emf.parsley.composite.CompositeFactory;
import org.eclipse.emf.parsley.composite.TableFormComposite;
import org.eclipse.emf.parsley.composite.TreeComposite;
import org.eclipse.emf.parsley.composite.TreeTableFormComposite;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameters;
import org.eclipse.emf.parsley.inject.parameters.EClassParameter;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.eclipse.emf.parsley.tests.util.MockCompositeFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class TreeTableFormCompositeTest extends AbstractEmfParsleyShellBasedTest {

	private TreeTableFormComposite treeTableFormComposite;

	private TreeComposite mockTreeComposite;

	private TableFormComposite mockTableFormComposite;

	private ISelectionChangedListener selectionChangedListener;

	private final EClass type = EcorePackage.eINSTANCE.getEObject();

	@Test
	public void testGetViewerReturnsTreeCompositeViewer() {
		var injector = createInjector(getModuleForTesting());
		createSUT(injector);
		treeTableFormComposite.getViewer();
		verify(mockTreeComposite).getViewer();
	}

	@Test
	public void testNestedMasterDetail() {
		var injector = createInjector(getModuleForTesting());
		createSUT(injector);
		var selected = triggerSelectionChanged();
		verify(mockTableFormComposite).update(selected);
	}

	@Test
	public void testSashFormOrientationFlip() {
		var injector = createInjector(getModuleForTesting(new EmfParsleyJavaGuiceModule() {
			@Override
			public int valueTreeFormSashStyle() {
				return SWT.HORIZONTAL;
			}
		}));
		createSUT(injector);
		triggerSelectionChanged();
		verify(mockTableFormComposite).setSashFormOrientation(SWT.VERTICAL);
	}

	protected void createSUT(Injector injector) {
		// our MockCompositeFactory returns singleton mocks
		var compositeFactory = injector.getInstance(CompositeFactory.class);
		mockTreeComposite = compositeFactory.createTreeComposite(null, 0);
		mockTableFormComposite = compositeFactory.createTableFormComposite(null, 0, type);
		var valueCapture = ArgumentCaptor.forClass(ISelectionChangedListener.class);
		doNothing().when(mockTreeComposite).addSelectionChangedListener(valueCapture.capture());
		var sashStyle = injector.getInstance(Key.get(Integer.class, Names.named(EmfParsleyConstants.TREE_FORM_SASH_STYLE)));
		treeTableFormComposite = new TreeTableFormComposite(new CompositeParameters(getShell(), SWT.NONE),
			new EClassParameter(type), sashStyle, new int[] {});
		injector.injectMembers(treeTableFormComposite);
		selectionChangedListener = valueCapture.getValue();
	}

	private Module getModuleForTesting(Module... overrides) {
		var modules = new ArrayList<Module>();
		for (var override : overrides) {
			modules.add(override);
		}
		modules.add(new AbstractModule() {
			@Override
			protected void configure() {
				bind(CompositeFactory.class).to(MockCompositeFactory.class);
			}
		});
		return Modules.override(new EmfParsleyJavaGuiceModule()).with(modules);
	}

	private Object triggerSelectionChanged() {
		var selected = EcoreFactory.eINSTANCE.createEClass();
		var selectionEvent = mock(SelectionChangedEvent.class);
		when(selectionEvent.getSelection()).thenReturn(new StructuredSelection(selected));
		selectionChangedListener.selectionChanged(selectionEvent);
		return selected;
	}
}
