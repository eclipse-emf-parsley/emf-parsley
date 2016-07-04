/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.parsley.tests.models.testmodels.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage
 * @generated
 */
public class TestmodelsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TestmodelsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestmodelsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = TestmodelsPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestmodelsSwitch<Adapter> modelSwitch =
		new TestmodelsSwitch<Adapter>() {
			@Override
			public Adapter caseTestContainer(TestContainer object) {
				return createTestContainerAdapter();
			}
			@Override
			public Adapter caseTestEClass(TestEClass object) {
				return createTestEClassAdapter();
			}
			@Override
			public Adapter caseTestEClassForFeatureName(TestEClassForFeatureName object) {
				return createTestEClassForFeatureNameAdapter();
			}
			@Override
			public Adapter caseABaseClass(ABaseClass object) {
				return createABaseClassAdapter();
			}
			@Override
			public Adapter caseBaseClass(BaseClass object) {
				return createBaseClassAdapter();
			}
			@Override
			public Adapter caseDerivedClass(DerivedClass object) {
				return createDerivedClassAdapter();
			}
			@Override
			public Adapter caseDerivedDerivedClass(DerivedDerivedClass object) {
				return createDerivedDerivedClassAdapter();
			}
			@Override
			public Adapter caseMultipleInheritanceClass(MultipleInheritanceClass object) {
				return createMultipleInheritanceClassAdapter();
			}
			@Override
			public Adapter caseClassForControls(ClassForControls object) {
				return createClassForControlsAdapter();
			}
			@Override
			public Adapter caseClassWithName(ClassWithName object) {
				return createClassWithNameAdapter();
			}
			@Override
			public Adapter caseBaseClassForFeatureMapEntry(BaseClassForFeatureMapEntry object) {
				return createBaseClassForFeatureMapEntryAdapter();
			}
			@Override
			public Adapter caseClassForFeatureMapEntry1(ClassForFeatureMapEntry1 object) {
				return createClassForFeatureMapEntry1Adapter();
			}
			@Override
			public Adapter caseClassForFeatureMapEntry2(ClassForFeatureMapEntry2 object) {
				return createClassForFeatureMapEntry2Adapter();
			}
			@Override
			public Adapter caseClassForTable(ClassForTable object) {
				return createClassForTableAdapter();
			}
			@Override
			public Adapter caseClassForValidation(ClassForValidation object) {
				return createClassForValidationAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestEClass <em>Test EClass</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestEClass
	 * @generated
	 */
	public Adapter createTestEClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestEClassForFeatureName <em>Test EClass For Feature Name</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestEClassForFeatureName
	 * @generated
	 */
	public Adapter createTestEClassForFeatureNameAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.parsley.tests.models.testmodels.ABaseClass <em>ABase Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ABaseClass
	 * @generated
	 */
	public Adapter createABaseClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.parsley.tests.models.testmodels.BaseClass <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.BaseClass
	 * @generated
	 */
	public Adapter createBaseClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.parsley.tests.models.testmodels.DerivedClass <em>Derived Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.DerivedClass
	 * @generated
	 */
	public Adapter createDerivedClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.parsley.tests.models.testmodels.DerivedDerivedClass <em>Derived Derived Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.DerivedDerivedClass
	 * @generated
	 */
	public Adapter createDerivedDerivedClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.parsley.tests.models.testmodels.MultipleInheritanceClass <em>Multiple Inheritance Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.MultipleInheritanceClass
	 * @generated
	 */
	public Adapter createMultipleInheritanceClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls <em>Class For Controls</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls
	 * @generated
	 */
	public Adapter createClassForControlsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName <em>Class With Name</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName
	 * @generated
	 */
	public Adapter createClassWithNameAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.parsley.tests.models.testmodels.BaseClassForFeatureMapEntry <em>Base Class For Feature Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.BaseClassForFeatureMapEntry
	 * @generated
	 */
	public Adapter createBaseClassForFeatureMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForFeatureMapEntry1 <em>Class For Feature Map Entry1</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForFeatureMapEntry1
	 * @generated
	 */
	public Adapter createClassForFeatureMapEntry1Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForFeatureMapEntry2 <em>Class For Feature Map Entry2</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForFeatureMapEntry2
	 * @generated
	 */
	public Adapter createClassForFeatureMapEntry2Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForTable <em>Class For Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForTable
	 * @generated
	 */
	public Adapter createClassForTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.parsley.tests.models.testmodels.ClassForValidation <em>Class For Validation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.ClassForValidation
	 * @generated
	 */
	public Adapter createClassForValidationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.parsley.tests.models.testmodels.TestContainer <em>Test Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.parsley.tests.models.testmodels.TestContainer
	 * @generated
	 */
	public Adapter createTestContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //TestmodelsAdapterFactory
