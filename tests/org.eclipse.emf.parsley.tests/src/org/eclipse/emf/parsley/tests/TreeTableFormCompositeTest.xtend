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

import com.google.inject.AbstractModule
import com.google.inject.Injector
import com.google.inject.Key
import com.google.inject.Module
import com.google.inject.name.Names
import com.google.inject.util.Modules
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.parsley.EmfParsleyConstants
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule
import org.eclipse.emf.parsley.composite.CompositeFactory
import org.eclipse.emf.parsley.composite.TableFormComposite
import org.eclipse.emf.parsley.composite.TreeComposite
import org.eclipse.emf.parsley.composite.TreeTableFormComposite
import org.eclipse.emf.parsley.inject.parameters.CompositeParameters
import org.eclipse.emf.parsley.inject.parameters.EClassParameter
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.emf.parsley.tests.util.MockCompositeFactory
import org.eclipse.jface.viewers.ISelectionChangedListener
import org.eclipse.jface.viewers.SelectionChangedEvent
import org.eclipse.jface.viewers.StructuredSelection
import org.eclipse.swt.SWT
import org.junit.Test
import org.mockito.ArgumentCaptor

import static org.mockito.Mockito.*

class TreeTableFormCompositeTest extends AbstractEmfParsleyShellBasedTest {

	var TreeTableFormComposite treeTableFormComposite

	var TreeComposite mockTreeComposite

	var TableFormComposite mockTableFormComposite

	var ISelectionChangedListener selectionChangedListener

	val type = EcorePackage.eINSTANCE.EObject

	@Test def void testGetViewerReturnsTreeCompositeViewer() {
		val injector = getModuleForTesting.createInjector
		createSUT(injector)
		treeTableFormComposite.getViewer
		verify(mockTreeComposite).getViewer()
	}

	@Test def void testNestedMasterDetail() {
		val injector = getModuleForTesting.createInjector
		createSUT(injector)
		val selected = triggerSelectionChanged()
		verify(mockTableFormComposite).update(selected)
	}

	@Test def void testSashFormOrientationFlip() {
		val injector = getModuleForTesting(new EmfParsleyJavaGuiceModule {
			override valueTreeFormSashStyle() {
				return SWT.HORIZONTAL;
			}
		}).createInjector
		createSUT(injector)
		triggerSelectionChanged()
		verify(mockTableFormComposite).setSashFormOrientation(SWT.VERTICAL)
	}

	protected def void createSUT(Injector injector) {
		// our MockCompositeFactory returns singleton mocks
		val compositeFactory = injector.getInstance(CompositeFactory)
		mockTreeComposite = compositeFactory.createTreeComposite(null, 0)
		mockTableFormComposite = compositeFactory.createTableFormComposite(null, 0, type)
		val valueCapture = ArgumentCaptor.forClass(ISelectionChangedListener);
		doNothing().when(mockTreeComposite).addSelectionChangedListener(valueCapture.capture)
		val sashStyle = injector.getInstance(Key.get(Integer, Names.named(EmfParsleyConstants.TREE_FORM_SASH_STYLE)))
		treeTableFormComposite = new TreeTableFormComposite(new CompositeParameters(shell, SWT.NONE),
			new EClassParameter(type), sashStyle, #[])
		injector.injectMembers(treeTableFormComposite)
		selectionChangedListener = valueCapture.value
	}

	def private getModuleForTesting(Module... overrides) {
		val modules = newArrayList(overrides) + newArrayList(new AbstractModule() {
			override protected configure() {
				bind(CompositeFactory).to(MockCompositeFactory)
			}
		})
		Modules.override(new EmfParsleyJavaGuiceModule).with(modules)
	}

	private def Object triggerSelectionChanged() {
		val selected = EcoreFactory.eINSTANCE.createEClass
		val selectionEvent = mock(SelectionChangedEvent)
		when(selectionEvent.getSelection()).thenReturn(new StructuredSelection(selected))
		selectionChangedListener.selectionChanged(selectionEvent)
		return selected
	}
}
