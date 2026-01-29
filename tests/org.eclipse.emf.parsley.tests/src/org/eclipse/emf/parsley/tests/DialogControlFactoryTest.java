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
package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.composite.DialogControlFactory;
import org.eclipse.emf.parsley.composite.MultipleFeatureControl;
import org.eclipse.emf.parsley.composite.ProposalCreator;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName;
import org.eclipse.emf.parsley.tests.models.testmodels.EnumForControls;
import org.eclipse.swt.widgets.Control;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Injector;

import java.util.Arrays;

import org.eclipse.jface.viewers.StructuredSelection;

public class DialogControlFactoryTest extends AbstractControlFactoryTest {

	/**
	 * An instance to use for testing the creation of a Control
	 * using an AbstractControlFactory
	 */
	protected ClassForControls classForControlsInstance;

	protected DialogControlFactory factory;

	@Before
	public void setupEObject() {
		classForControlsInstance = fixtures.getTestFactory().createClassForControls();
		factory = createAndInitializeFactory();
	}

	@After
	public void disposeFactory() {
		syncExecInRealm(() -> {
			factory.dispose();
			return null;
		});
	}

	@Test
	public void testBooleanFeature() {
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_BooleanFeature());
		assertCheckbox(control, false);
		classForControlsInstance.setBooleanFeature(true);
		assertCheckbox(control, true);
	}

	@Test
	public void testBooleanObjectFeature() {
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_BooleanObjectFeature());
		assertCheckbox(control, false);
		classForControlsInstance.setBooleanObjectFeature(true);
		assertCheckbox(control, true);
	}

	@Test
	public void testBooleanDataTypeFeature() {
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_BooleanDataTypeFeature());
		assertCheckbox(control, false);
		classForControlsInstance.setBooleanDataTypeFeature(true);
		assertCheckbox(control, true);
	}

	@Test
	public void testBooleanPrimitiveDataTypeFeature() {
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_BooleanPrimitiveDataTypeFeature());
		assertCheckbox(control, false);
		classForControlsInstance.setBooleanPrimitiveDataTypeFeature(true);
		assertCheckbox(control, true);
	}

	@Test
	public void testBooleanFeatureReadOnly() {
		factory.setReadonly(true);
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_BooleanFeature());
		assertEnabled(control, false);
		assertCheckbox(control, false);
		classForControlsInstance.setBooleanFeature(true);
		assertCheckbox(control, true);
	}

	@Test
	public void testEnumFeature() {
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_EnumFeature());
		assertCombo(control, "FIRST, SECOND, THIRD", 0);
		classForControlsInstance.setEnumFeature(EnumForControls.THIRD);
		assertCombo(control, "FIRST, SECOND, THIRD", 2);
	}

	@Test
	public void testStringFeature() {
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_StringFeature());
		assertTextEditable(control, true);
		assertText(control, "");
		classForControlsInstance.setStringFeature("Foo");
		assertText(control, "Foo");
	}

	@Test
	public void testStringFeatureReadOnly() {
		factory.setReadonly(true);
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_StringFeature());
		assertTextEditable(control, false);
		assertText(control, "");
		classForControlsInstance.setStringFeature("Foo");
		assertText(control, "Foo");
	}

	@Test
	public void testStringFeatureUnchangeable() {
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_UnchangeableStringFeature());
		assertTextEnabled(control, false);
		assertText(control, "");
	}

	@Test
	public void testStringFeatureDerived() {
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_DerivedStringFeature());
		assertTextEnabled(control, false);
		assertText(control, "");
	}

	public static class CustomProposalCreator1 extends ProposalCreator {
		public List<String> proposals_ClassForControls_stringFeature(ClassForControls e) {
			return Arrays.asList("First Proposal", "Second Proposal");
		}
	}

	@Test
	public void testStringFeatureWithProposals() {
		Injector injector = createInjector(new EmfParsleyGuiceModuleForTesting() {

			@Override
			public Class<? extends ProposalCreator> bindProposalCreator() {
				return CustomProposalCreator1.class;
			}
		});
		factory = injector.getInstance(DialogControlFactory.class);
		factory.init(getEditingDomain(), classForControlsInstance, getShell());
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_StringFeature());
		assertTextEditable(control, true);
		assertText(control, "");
		classForControlsInstance.setStringFeature("Foo");
		assertText(control, "Foo");
	}

	public static class CustomProposalCreator2 extends ProposalCreator {
		public List<String> proposals_ClassForControls_stringFeature(ClassForControls e) {
			return null;
		}
	}

	@Test
	public void testStringFeatureWithNullProposals() {
		Injector injector = createInjector(new EmfParsleyGuiceModuleForTesting() {

			@Override
			public Class<? extends ProposalCreator> bindProposalCreator() {
				return CustomProposalCreator2.class;
			}
		});
		factory = injector.getInstance(DialogControlFactory.class);
		factory.init(getEditingDomain(), classForControlsInstance, getShell());
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_StringFeature());
		assertTextEditable(control, true);
	}

	public static class CustomProposalCreator3 extends ProposalCreator {
		public List<String> proposals_ClassForControls_stringFeature(ClassForControls e) {
			return Collections.emptyList();
		}
	}

	@Test
	public void testStringFeatureWithEmptyProposals() {
		Injector injector = createInjector(new EmfParsleyGuiceModuleForTesting() {

			@Override
			public Class<? extends ProposalCreator> bindProposalCreator() {
				return CustomProposalCreator3.class;
			}
		});
		factory = injector.getInstance(DialogControlFactory.class);
		factory.init(getEditingDomain(), classForControlsInstance, getShell());
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_StringFeature());
		assertTextEditable(control, true);
	}

	@Test
	public void testFeatureMap() {
		classForControlsInstance.getFeatureMapEntries1().add(fixtures.createClassForFeatureMapEntry1("1"));
		classForControlsInstance.getFeatureMapEntries2().add(fixtures.createClassForFeatureMapEntry2("2"));
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_FeatureMapEntries());
		// button is visible but not enabled
		assertMultipleFeatureControl(control, "Class For Feature Map Entry1 1, Class For Feature Map Entry2 2", true, false);
	}

	@Test
	public void testMultiReferenceFeatureEmpty() {
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_MultiReferenceFeature());
		assertMultipleFeatureControl(control, "", true);
	}

	@Test
	public void testMultiReferenceFeatureReadOnly() {
		factory.setReadonly(true);
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_MultiReferenceFeature());
		assertMultipleFeatureControl(control, "", false);
	}

	@Test
	public void testMultiReferenceFeatureWithInitialValues() {
		classForControlsInstance.getMultiReferenceFeature().add(fixtures.createClassWithName("Res1"));
		classForControlsInstance.getMultiReferenceFeature().add(fixtures.createClassWithName("Res2"));
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_MultiReferenceFeature());
		assertMultipleFeatureControl(control, "Class With Name Res1, Class With Name Res2", true);
	}

	@Test
	public void testMultiReferenceFeatureReadOnlyWithInitialValues() {
		factory.setReadonly(true);
		classForControlsInstance.getMultiReferenceFeature().add(fixtures.createClassWithName("Res1"));
		classForControlsInstance.getMultiReferenceFeature().add(fixtures.createClassWithName("Res2"));
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_MultiReferenceFeature());
		// the button is not visible, but the label is still shown
		assertMultipleFeatureControl(control, "Class With Name Res1, Class With Name Res2", false);
	}

	@Test
	public void testMultiReferenceFeatureAndSelections() {
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_MultiReferenceFeature());
		assertMultipleFeatureControl(control, "", true);

		MultipleFeatureControl mfc = (MultipleFeatureControl) control;
		ClassWithName ref1 = fixtures.createClassWithName("Res1");
		ClassWithName ref2 = fixtures.createClassWithName("Res2");
		// force the selection in the control...
		mfc.getInternalSelectionProvider().setSelection(new StructuredSelection(new Object[] { ref1, ref2 }));
		assertMultipleFeatureControl(control, "Class With Name Res1, Class With Name Res2", true);
		// and verify that the object has the selected references
		assertSame(ref1, classForControlsInstance.getMultiReferenceFeature().get(0));
		assertSame(ref2, classForControlsInstance.getMultiReferenceFeature().get(1));
		// force the selection in the control...
		mfc.getInternalSelectionProvider().setSelection(new StructuredSelection(ref2));
		assertMultipleFeatureControl(control, "Class With Name Res2", true);
		// and verify that the object has the selected references
		assertSame(ref2, classForControlsInstance.getMultiReferenceFeature().get(0));
		assertEquals(1, classForControlsInstance.getMultiReferenceFeature().size());
		// remove all selections...
		mfc.getInternalSelectionProvider().setSelection(StructuredSelection.EMPTY);
		assertMultipleFeatureControl(control, "", true);
		// and verify that the object has the selected references
		assertEquals(0, classForControlsInstance.getMultiReferenceFeature().size());
	}

	@Test
	public void testReferenceFeatureWithoutResourceSet() {
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_ReferenceToClassWithName());
		assertCombo(control, "", -1);
	}

	@Test
	public void testReferenceFeatureWithResource() {
		Resource res = fixtures.createResource();
		res.getContents().add(classForControlsInstance);

		fixtures.createClassWithName(res, "Ref1");
		ClassWithName referred2 = fixtures.createClassWithName(res, "Ref2");
		ClassWithName referred3 = fixtures.createClassWithName(res, "Ref3");

		classForControlsInstance.setReferenceToClassWithName(referred2);
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_ReferenceToClassWithName());
		assertCombo(control, ", Class With Name Ref1, Class With Name Ref2, Class With Name Ref3", 2);

		classForControlsInstance.setReferenceToClassWithName(referred3);
		control = createControl(factory, fixtures.getTestPackage().getClassForControls_ReferenceToClassWithName());
		assertCombo(control, ", Class With Name Ref1, Class With Name Ref2, Class With Name Ref3", 3);
	}

	@Test
	public void testReferenceFeatureWithResourceSet() {
		Resource res = fixtures.createResourceInResouceSet();
		res.getContents().add(classForControlsInstance);

		fixtures.createClassWithName(res, "Ref1");
		ClassWithName referred2 = fixtures.createClassWithName(res, "Ref2");
		ClassWithName referred3 = fixtures.createClassWithName(res, "Ref3");

		classForControlsInstance.setReferenceToClassWithName(referred2);
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_ReferenceToClassWithName());
		assertCombo(control, ", Class With Name Ref1, Class With Name Ref2, Class With Name Ref3", 2);

		classForControlsInstance.setReferenceToClassWithName(referred3);
		control = createControl(factory, fixtures.getTestPackage().getClassForControls_ReferenceToClassWithName());
		assertCombo(control, ", Class With Name Ref1, Class With Name Ref2, Class With Name Ref3", 3);
	}

	@Test
	public void testReferenceFeatureInDifferentResources() {
		Resource res = fixtures.createResourceInResouceSet();
		Resource res2 = fixtures.createResourceInResouceSet();

		res.getContents().add(classForControlsInstance);

		fixtures.createClassWithName(res, "Ref1");
		ClassWithName referred2 = fixtures.createClassWithName(res2, "Ref2");
		ClassWithName referred3 = fixtures.createClassWithName(res, "Ref3");

		// note that the proposals are ordered differently w.r.t. the previous test
		//  since they are in different resources.
		classForControlsInstance.setReferenceToClassWithName(referred2);
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_ReferenceToClassWithName());
		assertCombo(control, ", Class With Name Ref1, Class With Name Ref3, Class With Name Ref2", 3);

		classForControlsInstance.setReferenceToClassWithName(referred3);
		control = createControl(factory, fixtures.getTestPackage().getClassForControls_ReferenceToClassWithName());
		assertCombo(control, ", Class With Name Ref1, Class With Name Ref3, Class With Name Ref2", 2);
	}

	@Test
	public void testReferenceFeatureReadOnly() {
		factory.setReadonly(true);
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_ReferenceToClassWithName());
		assertText(control, "");
	}

	@Test
	public void testReferenceFeatureNull() {
		Resource res = fixtures.createResource();
		res.getContents().add(classForControlsInstance);

		fixtures.createClassWithName(res, "Ref1");

		// see https://bugs.eclipse.org/bugs/show_bug.cgi?id=490463
		classForControlsInstance.setReferenceToClassWithName(null);
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_ReferenceToClassWithName());
		assertCombo(control, ", Class With Name Ref1", -1);
	}

	@Test
	public void testReferenceFeatureNotNullAndThenNull() {
		Resource res = fixtures.createResource();
		res.getContents().add(classForControlsInstance);

		ClassWithName referred = fixtures.createClassWithName(res, "Ref1");

		classForControlsInstance.setReferenceToClassWithName(referred);
		Control control = createControl(factory, fixtures.getTestPackage().getClassForControls_ReferenceToClassWithName());
		assertCombo(control, ", Class With Name Ref1", 1);

		// see https://bugs.eclipse.org/bugs/show_bug.cgi?id=490463
		classForControlsInstance.setReferenceToClassWithName(null);
		assertCombo(control, ", Class With Name Ref1", -1);
	}

	protected DialogControlFactory createAndInitializeFactory() {
		DialogControlFactory factory = new DialogControlFactory();
		initialize(factory, classForControlsInstance);
		return factory;
	}

}
