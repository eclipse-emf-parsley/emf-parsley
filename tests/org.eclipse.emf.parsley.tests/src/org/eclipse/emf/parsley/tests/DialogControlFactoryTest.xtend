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

import org.eclipse.emf.parsley.composite.DialogControlFactory
import org.eclipse.emf.parsley.composite.MultipleFeatureControl
import org.eclipse.emf.parsley.composite.ProposalCreator
import org.eclipse.emf.parsley.tests.DialogControlFactoryTest.CustomProposalCreator1
import org.eclipse.emf.parsley.tests.DialogControlFactoryTest.CustomProposalCreator2
import org.eclipse.emf.parsley.tests.DialogControlFactoryTest.CustomProposalCreator3
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls
import org.eclipse.emf.parsley.tests.models.testmodels.EnumForControls
import org.junit.After
import org.junit.Before
import org.junit.Test

import static extension org.junit.Assert.*

class DialogControlFactoryTest extends AbstractControlFactoryTest {

	/**
	 * An instance to use for testing the creation of a Control
	 * using an AbstractControlFactory
	 */
	var protected ClassForControls classForControlsInstance

	var protected DialogControlFactory factory

	@Before
	def void setupEObject() {
		classForControlsInstance = testFactory.createClassForControls
		factory = createAndInitializeFactory
	}

	@After
	def void disposeFactory() {
		syncExecInRealm[|
			factory.dispose();
			return null
		]
	}

	@Test def void testBooleanFeature() {
		val control = factory.createControl(testPackage.classForControls_BooleanFeature)
		control.assertCheckbox(false)
		classForControlsInstance.booleanFeature = true
		control.assertCheckbox(true)
	}

	@Test def void testBooleanObjectFeature() {
		val control = factory.createControl(testPackage.classForControls_BooleanObjectFeature)
		control.assertCheckbox(false)
		classForControlsInstance.booleanObjectFeature = true
		control.assertCheckbox(true)
	}

	@Test def void testBooleanDataTypeFeature() {
		val control = factory.createControl(testPackage.classForControls_BooleanDataTypeFeature)
		control.assertCheckbox(false)
		classForControlsInstance.booleanDataTypeFeature = true
		control.assertCheckbox(true)
	}

	@Test def void testBooleanPrimitiveDataTypeFeature() {
		val control = factory.createControl(testPackage.classForControls_BooleanPrimitiveDataTypeFeature)
		control.assertCheckbox(false)
		classForControlsInstance.booleanPrimitiveDataTypeFeature = true
		control.assertCheckbox(true)
	}

	@Test def void testBooleanFeatureReadOnly() {
		factory.readonly = true
		val control = factory.createControl(testPackage.classForControls_BooleanFeature)
		control.assertEnabled(false)
		control.assertCheckbox(false)
		classForControlsInstance.booleanFeature = true
		control.assertCheckbox(true)
	}

	@Test def void testEnumFeature() {
		val control = factory.createControl(testPackage.classForControls_EnumFeature)
		control.assertCombo("FIRST, SECOND, THIRD", 0)
		classForControlsInstance.enumFeature = EnumForControls.THIRD
		control.assertCombo("FIRST, SECOND, THIRD", 2)
	}

	@Test def void testStringFeature() {
		val control = factory.createControl(testPackage.classForControls_StringFeature)
		control.assertTextEditable(true)
		control.assertText("")
		classForControlsInstance.stringFeature = "Foo"
		control.assertText("Foo")
	}

	@Test def void testStringFeatureReadOnly() {
		factory.readonly = true
		val control = factory.createControl(testPackage.classForControls_StringFeature)
		control.assertTextEditable(false)
		control.assertText("")
		classForControlsInstance.stringFeature = "Foo"
		control.assertText("Foo")
	}

	@Test def void testStringFeatureUnchangeable() {
		val control = factory.createControl(testPackage.classForControls_UnchangeableStringFeature)
		control.assertTextEnabled(false)
		control.assertText("")
	}

	@Test def void testStringFeatureDerived() {
		val control = factory.createControl(testPackage.classForControls_DerivedStringFeature)
		control.assertTextEnabled(false)
		control.assertText("")
	}

	static class CustomProposalCreator1 extends ProposalCreator {
		def proposals_ClassForControls_stringFeature(ClassForControls e) {
			return #["First Proposal", "Second Proposal"]
		}
	}

	@Test def void testStringFeatureWithProposals() {
		val injector = createInjector(new EmfParsleyGuiceModuleForTesting() {

			override bindProposalCreator() {
				CustomProposalCreator1
			}
		})
		factory = injector.getInstance(DialogControlFactory)
		factory.init(editingDomain, classForControlsInstance, shell)
		val control = factory.createControl(testPackage.classForControls_StringFeature)
		control.assertTextEditable(true)
		control.assertText("")
		classForControlsInstance.stringFeature = "Foo"
		control.assertText("Foo")
	}

	static class CustomProposalCreator2 extends ProposalCreator {
		def proposals_ClassForControls_stringFeature(ClassForControls e) {
			return null
		}
	}

	@Test def void testStringFeatureWithNullProposals() {
		val injector = createInjector(new EmfParsleyGuiceModuleForTesting() {

			override bindProposalCreator() {
				CustomProposalCreator2
			}
		})
		factory = injector.getInstance(DialogControlFactory)
		factory.init(editingDomain, classForControlsInstance, shell)
		val control = factory.createControl(testPackage.classForControls_StringFeature)
		control.assertTextEditable(true)
	}

	static class CustomProposalCreator3 extends ProposalCreator {
		def proposals_ClassForControls_stringFeature(ClassForControls e) {
			return emptyList
		}
	}

	@Test def void testStringFeatureWithEmptyProposals() {
		val injector = createInjector(new EmfParsleyGuiceModuleForTesting() {

			override bindProposalCreator() {
				CustomProposalCreator3
			}
		})
		factory = injector.getInstance(DialogControlFactory)
		factory.init(editingDomain, classForControlsInstance, shell)
		val control = factory.createControl(testPackage.classForControls_StringFeature)
		control.assertTextEditable(true)
	}

	@Test def void testFeatureMap() {
		classForControlsInstance.featureMapEntries1 += createClassForFeatureMapEntry1("1")
		classForControlsInstance.featureMapEntries2 += createClassForFeatureMapEntry2("2")
		val control = factory.createControl(testPackage.classForControls_FeatureMapEntries)
		// button is visible but not enabled
		control.assertMultipleFeatureControl("Class For Feature Map Entry1 1, Class For Feature Map Entry2 2", true, false)
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
		classForControlsInstance.multiReferenceFeature += createClassWithName("Res1")
		classForControlsInstance.multiReferenceFeature += createClassWithName("Res2")
		val control = factory.createControl(testPackage.classForControls_MultiReferenceFeature)
		control.assertMultipleFeatureControl("Class With Name Res1, Class With Name Res2", true)
	}

	@Test def void testMultiReferenceFeatureReadOnlyWithInitialValues() {
		factory.readonly = true
		classForControlsInstance.multiReferenceFeature += createClassWithName("Res1")
		classForControlsInstance.multiReferenceFeature += createClassWithName("Res2")
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
		ref1.assertSame(classForControlsInstance.multiReferenceFeature.get(0))
		ref2.assertSame(classForControlsInstance.multiReferenceFeature.get(1))
		// force the selection in the control...
		mfc.internalSelectionProvider.setSelectionProgrammatically(ref2)
		control.assertMultipleFeatureControl("Class With Name Res2", true)
		// and verify that the object has the selected references
		ref2.assertSame(classForControlsInstance.multiReferenceFeature.get(0))
		1.assertEquals(classForControlsInstance.multiReferenceFeature.size)
		// remove all selections...
		mfc.internalSelectionProvider.setSelectionProgrammatically()
		control.assertMultipleFeatureControl("", true)
		// and verify that the object has the selected references
		0.assertEquals(classForControlsInstance.multiReferenceFeature.size)
	}

	@Test def void testReferenceFeatureWithoutResourceSet() {
		val control = factory.createControl(testPackage.classForControls_ReferenceToClassWithName)
		control.assertCombo("", -1)
	}

	@Test def void testReferenceFeatureWithResource() {
		val res = createResource
		res.contents += classForControlsInstance
		
		createClassWithName(res, "Ref1")
		val referred2 = createClassWithName(res, "Ref2")
		val referred3 = createClassWithName(res, "Ref3")
		
		classForControlsInstance.referenceToClassWithName = referred2
		var control = factory.createControl(testPackage.classForControls_ReferenceToClassWithName)
		control.assertCombo(", Class With Name Ref1, Class With Name Ref2, Class With Name Ref3", 2)
		
		classForControlsInstance.referenceToClassWithName = referred3
		control = factory.createControl(testPackage.classForControls_ReferenceToClassWithName)
		control.assertCombo(", Class With Name Ref1, Class With Name Ref2, Class With Name Ref3", 3)
	}

	@Test def void testReferenceFeatureWithResourceSet() {
		val res = createResourceInResouceSet
		res.contents += classForControlsInstance
		
		createClassWithName(res, "Ref1")
		val referred2 = createClassWithName(res, "Ref2")
		val referred3 = createClassWithName(res, "Ref3")
		
		classForControlsInstance.referenceToClassWithName = referred2
		var control = factory.createControl(testPackage.classForControls_ReferenceToClassWithName)
		control.assertCombo(", Class With Name Ref1, Class With Name Ref2, Class With Name Ref3", 2)
		
		classForControlsInstance.referenceToClassWithName = referred3
		control = factory.createControl(testPackage.classForControls_ReferenceToClassWithName)
		control.assertCombo(", Class With Name Ref1, Class With Name Ref2, Class With Name Ref3", 3)
	}

	@Test def void testReferenceFeatureInDifferentResources() {
		val res = createResourceInResouceSet
		val res2 = createResourceInResouceSet
		
		res.contents += classForControlsInstance
		
		createClassWithName(res, "Ref1")
		val referred2 = createClassWithName(res2, "Ref2")
		val referred3 = createClassWithName(res, "Ref3")
		
		// note that the proposals are ordered differently w.r.t. the previous test
		//  since they are in different resources.
		classForControlsInstance.referenceToClassWithName = referred2
		var control = factory.createControl(testPackage.classForControls_ReferenceToClassWithName)
		control.assertCombo(", Class With Name Ref1, Class With Name Ref3, Class With Name Ref2", 3)
		
		classForControlsInstance.referenceToClassWithName = referred3
		control = factory.createControl(testPackage.classForControls_ReferenceToClassWithName)
		control.assertCombo(", Class With Name Ref1, Class With Name Ref3, Class With Name Ref2", 2)
	}

	@Test def void testReferenceFeatureReadOnly() {
		factory.readonly = true
		val control = factory.createControl(testPackage.classForControls_ReferenceToClassWithName)
		control.assertText("")
	}

	@Test def void testReferenceFeatureNull() {
		val res = createResource
		res.contents += classForControlsInstance

		createClassWithName(res, "Ref1")

		// see https://bugs.eclipse.org/bugs/show_bug.cgi?id=490463
		classForControlsInstance.referenceToClassWithName = null
		var control = factory.createControl(testPackage.classForControls_ReferenceToClassWithName)
		control.assertCombo(", Class With Name Ref1", -1)
	}

	@Test def void testReferenceFeatureNotNullAndThenNull() {
		val res = createResource
		res.contents += classForControlsInstance

		val referred = createClassWithName(res, "Ref1")

		classForControlsInstance.referenceToClassWithName = referred
		var control = factory.createControl(testPackage.classForControls_ReferenceToClassWithName)
		control.assertCombo(", Class With Name Ref1", 1)

		// see https://bugs.eclipse.org/bugs/show_bug.cgi?id=490463
		classForControlsInstance.referenceToClassWithName = null
		control.assertCombo(", Class With Name Ref1", -1)
	}

	def protected createAndInitializeFactory() {
		new DialogControlFactory() => [initialize(classForControlsInstance)]
	}

}