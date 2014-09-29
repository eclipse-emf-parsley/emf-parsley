/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName;
import org.eclipse.emf.parsley.tests.models.testmodels.TestContainer;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.TestContainerImpl#getClassesWithName <em>Classes With Name</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.TestContainerImpl#getClassesForControls <em>Classes For Controls</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestContainerImpl extends MinimalEObjectImpl.Container implements TestContainer {
	/**
	 * The cached value of the '{@link #getClassesWithName() <em>Classes With Name</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassesWithName()
	 * @generated
	 * @ordered
	 */
	protected EList<ClassWithName> classesWithName;

	/**
	 * The cached value of the '{@link #getClassesForControls() <em>Classes For Controls</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassesForControls()
	 * @generated
	 * @ordered
	 */
	protected EList<ClassForControls> classesForControls;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestmodelsPackage.Literals.TEST_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassWithName> getClassesWithName() {
		if (classesWithName == null) {
			classesWithName = new EObjectContainmentEList<ClassWithName>(ClassWithName.class, this, TestmodelsPackage.TEST_CONTAINER__CLASSES_WITH_NAME);
		}
		return classesWithName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassForControls> getClassesForControls() {
		if (classesForControls == null) {
			classesForControls = new EObjectContainmentEList<ClassForControls>(ClassForControls.class, this, TestmodelsPackage.TEST_CONTAINER__CLASSES_FOR_CONTROLS);
		}
		return classesForControls;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TestmodelsPackage.TEST_CONTAINER__CLASSES_WITH_NAME:
				return ((InternalEList<?>)getClassesWithName()).basicRemove(otherEnd, msgs);
			case TestmodelsPackage.TEST_CONTAINER__CLASSES_FOR_CONTROLS:
				return ((InternalEList<?>)getClassesForControls()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestmodelsPackage.TEST_CONTAINER__CLASSES_WITH_NAME:
				return getClassesWithName();
			case TestmodelsPackage.TEST_CONTAINER__CLASSES_FOR_CONTROLS:
				return getClassesForControls();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TestmodelsPackage.TEST_CONTAINER__CLASSES_WITH_NAME:
				getClassesWithName().clear();
				getClassesWithName().addAll((Collection<? extends ClassWithName>)newValue);
				return;
			case TestmodelsPackage.TEST_CONTAINER__CLASSES_FOR_CONTROLS:
				getClassesForControls().clear();
				getClassesForControls().addAll((Collection<? extends ClassForControls>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TestmodelsPackage.TEST_CONTAINER__CLASSES_WITH_NAME:
				getClassesWithName().clear();
				return;
			case TestmodelsPackage.TEST_CONTAINER__CLASSES_FOR_CONTROLS:
				getClassesForControls().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TestmodelsPackage.TEST_CONTAINER__CLASSES_WITH_NAME:
				return classesWithName != null && !classesWithName.isEmpty();
			case TestmodelsPackage.TEST_CONTAINER__CLASSES_FOR_CONTROLS:
				return classesForControls != null && !classesForControls.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TestContainerImpl