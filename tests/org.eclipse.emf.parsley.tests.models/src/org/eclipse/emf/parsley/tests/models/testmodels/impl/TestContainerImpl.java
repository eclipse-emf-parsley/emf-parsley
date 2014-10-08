/**
 */
package org.eclipse.emf.parsley.tests.models.testmodels.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForTable;
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
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.TestContainerImpl#getContained <em>Contained</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.TestContainerImpl#getClassesForTable <em>Classes For Table</em>}</li>
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
	 * The cached value of the '{@link #getContained() <em>Contained</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContained()
	 * @generated
	 * @ordered
	 */
	protected TestContainer contained;

	/**
	 * The cached value of the '{@link #getClassesForTable() <em>Classes For Table</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassesForTable()
	 * @generated
	 * @ordered
	 */
	protected EList<ClassForTable> classesForTable;

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
	public TestContainer getContained() {
		return contained;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContained(TestContainer newContained, NotificationChain msgs) {
		TestContainer oldContained = contained;
		contained = newContained;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TestmodelsPackage.TEST_CONTAINER__CONTAINED, oldContained, newContained);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContained(TestContainer newContained) {
		if (newContained != contained) {
			NotificationChain msgs = null;
			if (contained != null)
				msgs = ((InternalEObject)contained).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TestmodelsPackage.TEST_CONTAINER__CONTAINED, null, msgs);
			if (newContained != null)
				msgs = ((InternalEObject)newContained).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TestmodelsPackage.TEST_CONTAINER__CONTAINED, null, msgs);
			msgs = basicSetContained(newContained, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.TEST_CONTAINER__CONTAINED, newContained, newContained));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassForTable> getClassesForTable() {
		if (classesForTable == null) {
			classesForTable = new EObjectContainmentEList<ClassForTable>(ClassForTable.class, this, TestmodelsPackage.TEST_CONTAINER__CLASSES_FOR_TABLE);
		}
		return classesForTable;
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
			case TestmodelsPackage.TEST_CONTAINER__CONTAINED:
				return basicSetContained(null, msgs);
			case TestmodelsPackage.TEST_CONTAINER__CLASSES_FOR_TABLE:
				return ((InternalEList<?>)getClassesForTable()).basicRemove(otherEnd, msgs);
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
			case TestmodelsPackage.TEST_CONTAINER__CONTAINED:
				return getContained();
			case TestmodelsPackage.TEST_CONTAINER__CLASSES_FOR_TABLE:
				return getClassesForTable();
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
			case TestmodelsPackage.TEST_CONTAINER__CONTAINED:
				setContained((TestContainer)newValue);
				return;
			case TestmodelsPackage.TEST_CONTAINER__CLASSES_FOR_TABLE:
				getClassesForTable().clear();
				getClassesForTable().addAll((Collection<? extends ClassForTable>)newValue);
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
			case TestmodelsPackage.TEST_CONTAINER__CONTAINED:
				setContained((TestContainer)null);
				return;
			case TestmodelsPackage.TEST_CONTAINER__CLASSES_FOR_TABLE:
				getClassesForTable().clear();
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
			case TestmodelsPackage.TEST_CONTAINER__CONTAINED:
				return contained != null;
			case TestmodelsPackage.TEST_CONTAINER__CLASSES_FOR_TABLE:
				return classesForTable != null && !classesForTable.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TestContainerImpl
