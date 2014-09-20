/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests

import com.google.inject.Binder
import com.google.inject.Guice
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceImpl
import org.eclipse.emf.parsley.EmfParsleyGuiceModule
import org.eclipse.emf.parsley.binding.AbstractControlFactory
import org.eclipse.emf.parsley.binding.DialogControlFactory
import org.eclipse.emf.parsley.binding.FormControlFactory
import org.eclipse.emf.parsley.binding.ProposalCreator
import org.eclipse.emf.parsley.runtime.ui.IImageHelper
import org.eclipse.emf.parsley.tests.util.TestDefaultRealm
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Combo
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.Text
import org.eclipse.ui.forms.widgets.FormToolkit
import org.junit.After
import org.junit.Before

import static org.junit.Assert.*
import org.eclipse.emf.edit.domain.EditingDomain
import com.google.inject.Injector

abstract class AbstractControlFactoryTest extends AbstractShellBasedTest {
	
	var TestDefaultRealm realm;
	
	var protected ResourceSet resourceSet;
	
	/**
	 * This will be created on demand using the method getOrCreateInjector
	 */
	var private Injector injector = null;
	
	@Before
	def void setupRealm() {
		injector = null
		realm = new TestDefaultRealm();
		resourceSet = createResourceSet
	}

	@After
	def void disposeRealm() {
		realm.dispose();
	}

	def protected abstract ResourceSet createResourceSet()

	def protected createResourceInResouceSet() {
		createResource => [
			resourceSet.resources += it
		]
	}

	def protected createResource() {
		new ResourceImpl
	}

	def protected EditingDomain getEditingDomain() {
		return null
	}
	
	def protected initialize(DialogControlFactory controlFactory, EObject obj) {
		controlFactory.initializeCommon(obj)
		controlFactory.init(getEditingDomain(), obj, shell)
	}

	def protected initialize(FormControlFactory controlFactory, EObject obj) {
		controlFactory.initializeCommon(obj)
		// FormToolkit must be created in the UI thread
		// and the initialization requires databinding, and thus the Realm
		syncExecInRealm[|
			controlFactory.init(getEditingDomain(), obj, shell, new FormToolkit(display))
			true
		]
	}

	def protected initializeCommon(AbstractControlFactory controlFactory, EObject obj) {
		controlFactory.proposalCreator = new ProposalCreator
		getOrCreateInjector.injectMembers(controlFactory)
	}

	def protected getOrCreateInjector() {
		if (injector === null) {
			injector = Guice.createInjector(new EmfParsleyGuiceModule(EmfParsleyTestsActivator.getDefault) {

				override configure(Binder binder) {
					val compound = getBindings();
					compound.configure(binder);
				}
				
				override bindIImageHelper() {
					IImageHelper.NullImageHelper
				}
				
			})
		}
		return injector
	}

	def protected createControl(AbstractControlFactory factory, EStructuralFeature feature) {
		syncExecInRealm(|factory.create(feature))
	}

	def protected assertCheckbox(Control control, boolean checked) {
		control.assertControlClass(Button)
		val button = control as Button;
		syncExecVoid[|
			assertTrue("not a checkbox", (button.style.bitwiseAnd(SWT.CHECK) != 0));
			assertEquals(checked, button.selection)
		]
	}

	def protected assertEnabled(Control control, boolean expectedEnabled) {
		syncExecVoid[|
			assertEquals(expectedEnabled, control.getEnabled)
		]
	}

	/**
	 * The expectedElements is of the shape (not the space after the comma)
	 * "elem1, elem2, elem3"
	 */
	def protected assertCombo(Control control, String expectedElements, int selected) {
		control.assertControlClass(Combo)
		val combo = control as Combo;
		syncExecVoid[|
			assertEquals(expectedElements, combo.items.map[toString].join(", "))
			assertEquals(selected, combo.selectionIndex)
		]
	}

	def protected assertText(Control control, String expectedText) {
		control.assertControlClass(Text)
		val text = control as Text;
		syncExecVoid[|
			assertEquals(expectedText, text.text)
		]
	}

	def protected assertTextEditable(Control control, boolean expectedEditable) {
		control.assertControlClass(Text)
		val text = control as Text;
		syncExecVoid[|
			assertEquals(expectedEditable, text.editable)
		]
	}

	def protected assertControlClass(Control control, Class<? extends Control> clazz) {
		assertTrue("expected class: " + clazz.simpleName + 
			", actual class: " + control.class.simpleName, 
			clazz.isAssignableFrom(control.class)
		)
	}
}