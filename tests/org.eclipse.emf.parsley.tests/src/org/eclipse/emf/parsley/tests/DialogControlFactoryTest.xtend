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

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.parsley.binding.DialogControlFactory
import org.eclipse.emf.parsley.binding.MultipleFeatureControl
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls
import org.eclipse.emf.parsley.tests.models.testmodels.EnumForControls
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsFactory
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage
import org.junit.Before
import org.junit.Test

import static extension org.junit.Assert.*
import org.eclipse.emf.parsley.binding.ProposalCreator

class DialogControlFactoryTest extends AbstractControlFactoryTest {
	
	val testPackage = TestmodelsPackage.eINSTANCE
	
	val testFactory = TestmodelsFactory.eINSTANCE
	
	var protected ClassForControls eobj
	
	var DialogControlFactory factory
	
	@Before
	def void setupEObject() {
		eobj = testFactory.createClassForControls
		factory = createAndInitializeFactory
	}

	def override protected createResourceSet() {
		new ResourceSetImpl
	}
	
	@Test def void testBooleanFeature() {
		val control = factory.createControl(testPackage.classForControls_BooleanFeature)
		control.assertCheckbox(false)
		eobj.booleanFeature = true
		control.assertCheckbox(true)
	}

	@Test def void testBooleanObjectFeature() {
		val control = factory.createControl(testPackage.classForControls_BooleanObjectFeature)
		control.assertCheckbox(false)
		eobj.booleanObjectFeature = true
		control.assertCheckbox(true)
	}

	@Test def void testBooleanDataTypeFeature() {
		val control = factory.createControl(testPackage.classForControls_BooleanDataTypeFeature)
		control.assertCheckbox(false)
		eobj.booleanDataTypeFeature = true
		control.assertCheckbox(true)
	}

	@Test def void testBooleanPrimitiveDataTypeFeature() {
		val control = factory.createControl(testPackage.classForControls_BooleanPrimitiveDataTypeFeature)
		control.assertCheckbox(false)
		eobj.booleanPrimitiveDataTypeFeature = true
		control.assertCheckbox(true)
	}

	@Test def void testBooleanFeatureReadOnly() {
		factory.readonly = true
		val control = factory.createControl(testPackage.classForControls_BooleanFeature)
		control.assertEnabled(false)
		control.assertCheckbox(false)
		eobj.booleanFeature = true
		control.assertCheckbox(true)
	}

	@Test def void testEnumFeature() {
		val control = factory.createControl(testPackage.classForControls_EnumFeature)
		control.assertCombo("FIRST, SECOND, THIRD", 0)
		eobj.enumFeature = EnumForControls.THIRD
		control.assertCombo("FIRST, SECOND, THIRD", 2)
	}

	@Test def void testStringFeature() {
		val control = factory.createControl(testPackage.classForControls_StringFeature)
		control.assertTextEditable(true)
		control.assertText("")
		eobj.stringFeature = "Foo"
		control.assertText("Foo")
	}

	@Test def void testStringFeatureReadOnly() {
		factory.readonly = true
		val control = factory.createControl(testPackage.classForControls_StringFeature)
		control.assertTextEditable(false)
		control.assertText("")
		eobj.stringFeature = "Foo"
		control.assertText("Foo")
	}

	@Test def void testStringFeatureWithProposals() {
		factory.proposalCreator = new ProposalCreator() {
			def proposals_ClassForControls_stringFeature(ClassForControls e) {
				return #["First Proposal", "Second Proposal"]
			}
		}
		val control = factory.createControl(testPackage.classForControls_StringFeature)
		control.assertTextEditable(true)
		control.assertText("")
		eobj.stringFeature = "Foo"
		control.assertText("Foo")
	}

	@Test def void testMultiReferenceFeatureEmpty() {
		val control = factory.createControl(testPackage.classForControls_MultiReferenceFeature)
		control.assertMultipleFeatureControl("", true)
	}

	@Test def void testMultiReferenceFeatureReadOnly() {
		factory.readonly = true
		val control = factory.createControl(testPackage.classForControls_MultiReferenceFeature)
		control.assertMultipleFeatureControl("", false)
	}

	@Test def void testMultiReferenceFeatureWithInitialValues() {
		eobj.multiReferenceFeature += createClassWithName("Res1")
		eobj.multiReferenceFeature += createClassWithName("Res2")
		val control = factory.createControl(testPackage.classForControls_MultiReferenceFeature)
		control.assertMultipleFeatureControl("Class With Name Res1, Class With Name Res2", true)
	}

	@Test def void testMultiReferenceFeatureReadOnlyWithInitialValues() {
		factory.readonly = true
		eobj.multiReferenceFeature += createClassWithName("Res1")
		eobj.multiReferenceFeature += createClassWithName("Res2")
		val control = factory.createControl(testPackage.classForControls_MultiReferenceFeature)
		// the button is not visible, but the label is still shown
		control.assertMultipleFeatureControl("Class With Name Res1, Class With Name Res2", false)
	}

	@Test def void testMultiReferenceFeatureAndSelections() {
		val control = factory.createControl(testPackage.classForControls_MultiReferenceFeature)
		control.assertMultipleFeatureControl("", true)
		
		val mfc = control as MultipleFeatureControl
		val ref1 = createClassWithName("Res1")
		val ref2 = createClassWithName("Res2")
		// force the selection in the control...
		mfc.internalSelectionProvider.setSelectionProgrammatically(ref1, ref2)
		control.assertMultipleFeatureControl("Class With Name Res1, Class With Name Res2", true)
		// and verify that the object has the selected references
		ref1.assertSame(eobj.multiReferenceFeature.get(0))
		ref2.assertSame(eobj.multiReferenceFeature.get(1))
		// force the selection in the control...
		mfc.internalSelectionProvider.setSelectionProgrammatically(ref2)
		control.assertMultipleFeatureControl("Class With Name Res2", true)
		// and verify that the object has the selected references
		ref2.assertSame(eobj.multiReferenceFeature.get(0))
		1.assertEquals(eobj.multiReferenceFeature.size)
		// remove all selections...
		mfc.internalSelectionProvider.setSelectionProgrammatically()
		control.assertMultipleFeatureControl("", true)
		// and verify that the object has the selected references
		0.assertEquals(eobj.multiReferenceFeature.size)
	}

	@Test def void testReferenceFeatureWithoutResourceSet() {
		val control = factory.createControl(testPackage.classForControls_ReferenceToClassWithName)
		control.assertCombo("", -1)
	}

	@Test def void testReferenceFeatureWithResource() {
		val res = createResource
		res.contents += eobj
		
		createClassWithName(res, "Ref1")
		val referred2 = createClassWithName(res, "Ref2")
		val referred3 = createClassWithName(res, "Ref3")
		
		eobj.referenceToClassWithName = referred2
		var control = factory.createControl(testPackage.classForControls_ReferenceToClassWithName)
		control.assertCombo("Class With Name Ref1, Class With Name Ref2, Class With Name Ref3", 1)
		
		eobj.referenceToClassWithName = referred3
		control = factory.createControl(testPackage.classForControls_ReferenceToClassWithName)
		control.assertCombo("Class With Name Ref1, Class With Name Ref2, Class With Name Ref3", 2)
	}

	@Test def void testReferenceFeatureWithResourceSet() {
		val res = createResourceInResouceSet
		res.contents += eobj
		
		createClassWithName(res, "Ref1")
		val referred2 = createClassWithName(res, "Ref2")
		val referred3 = createClassWithName(res, "Ref3")
		
		eobj.referenceToClassWithName = referred2
		var control = factory.createControl(testPackage.classForControls_ReferenceToClassWithName)
		control.assertCombo("Class With Name Ref1, Class With Name Ref2, Class With Name Ref3", 1)
		
		eobj.referenceToClassWithName = referred3
		control = factory.createControl(testPackage.classForControls_ReferenceToClassWithName)
		control.assertCombo("Class With Name Ref1, Class With Name Ref2, Class With Name Ref3", 2)
	}

	@Test def void testReferenceFeatureInDifferentResources() {
		val res = createResourceInResouceSet
		val res2 = createResourceInResouceSet
		
		res.contents += eobj
		
		createClassWithName(res, "Ref1")
		val referred2 = createClassWithName(res2, "Ref2")
		val referred3 = createClassWithName(res, "Ref3")
		
		// note that the proposals are ordered differently w.r.t. the previous test
		//  since they are in different resources.
		eobj.referenceToClassWithName = referred2
		var control = factory.createControl(testPackage.classForControls_ReferenceToClassWithName)
		control.assertCombo("Class With Name Ref1, Class With Name Ref3, Class With Name Ref2", 2)
		
		eobj.referenceToClassWithName = referred3
		control = factory.createControl(testPackage.classForControls_ReferenceToClassWithName)
		control.assertCombo("Class With Name Ref1, Class With Name Ref3, Class With Name Ref2", 1)
	}

	@Test def void testReferenceFeatureReadOnly() {
		factory.readonly = true
		val control = factory.createControl(testPackage.classForControls_ReferenceToClassWithName)
		control.assertText("")
	}

	def protected createAndInitializeFactory() {
		new DialogControlFactory() => [initialize(eobj)]
	}

	def protected createClassWithName(Resource res, String n) {
		createClassWithName(n) => [
			res.contents += it
		]
	}

	def protected createClassWithName(String n) {
		testFactory.createClassWithName => [name = n]
	}
}