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
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForTable;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForValidation;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName;
import org.eclipse.emf.parsley.tests.models.testmodels.TestContainer;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.TestContainerImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.TestContainerImpl#getClassesWithName <em>Classes With Name</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.TestContainerImpl#getClassesForControls <em>Classes For Controls</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.TestContainerImpl#getContained <em>Contained</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.TestContainerImpl#getClassesForTable <em>Classes For Table</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.TestContainerImpl#getObjectsForValidation <em>Objects For Validation</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.TestContainerImpl#getMultipleContained <em>Multiple Contained</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.tests.models.testmodels.impl.TestContainerImpl#getObjectsForDefaultValidation <em>Objects For Default Validation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestContainerImpl extends MinimalEObjectImpl.Container implements TestContainer {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

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
	 * The cached value of the '{@link #getObjectsForValidation() <em>Objects For Validation</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObjectsForValidation()
	 * @generated
	 * @ordered
	 */
	protected EList<ClassForValidation> objectsForValidation;

	/**
	 * The cached value of the '{@link #getMultipleContained() <em>Multiple Contained</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMultipleContained()
	 * @generated
	 * @ordered
	 */
	protected EList<TestContainer> multipleContained;

	/**
	 * The cached value of the '{@link #getObjectsForDefaultValidation() <em>Objects For Default Validation</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObjectsForDefaultValidation()
	 * @generated
	 * @ordered
	 */
	protected EList<ClassForDefaultValidation> objectsForDefaultValidation;

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
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelsPackage.TEST_CONTAINER__NAME, oldName, name));
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
	public EList<ClassForValidation> getObjectsForValidation() {
		if (objectsForValidation == null) {
			objectsForValidation = new EObjectContainmentEList<ClassForValidation>(ClassForValidation.class, this, TestmodelsPackage.TEST_CONTAINER__OBJECTS_FOR_VALIDATION);
		}
		return objectsForValidation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestContainer> getMultipleContained() {
		if (multipleContained == null) {
			multipleContained = new EObjectContainmentEList<TestContainer>(TestContainer.class, this, TestmodelsPackage.TEST_CONTAINER__MULTIPLE_CONTAINED);
		}
		return multipleContained;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassForDefaultValidation> getObjectsForDefaultValidation() {
		if (objectsForDefaultValidation == null) {
			objectsForDefaultValidation = new EObjectContainmentEList<ClassForDefaultValidation>(ClassForDefaultValidation.class, this, TestmodelsPackage.TEST_CONTAINER__OBJECTS_FOR_DEFAULT_VALIDATION);
		}
		return objectsForDefaultValidation;
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
			case TestmodelsPackage.TEST_CONTAINER__OBJECTS_FOR_VALIDATION:
				return ((InternalEList<?>)getObjectsForValidation()).basicRemove(otherEnd, msgs);
			case TestmodelsPackage.TEST_CONTAINER__MULTIPLE_CONTAINED:
				return ((InternalEList<?>)getMultipleContained()).basicRemove(otherEnd, msgs);
			case TestmodelsPackage.TEST_CONTAINER__OBJECTS_FOR_DEFAULT_VALIDATION:
				return ((InternalEList<?>)getObjectsForDefaultValidation()).basicRemove(otherEnd, msgs);
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
			case TestmodelsPackage.TEST_CONTAINER__NAME:
				return getName();
			case TestmodelsPackage.TEST_CONTAINER__CLASSES_WITH_NAME:
				return getClassesWithName();
			case TestmodelsPackage.TEST_CONTAINER__CLASSES_FOR_CONTROLS:
				return getClassesForControls();
			case TestmodelsPackage.TEST_CONTAINER__CONTAINED:
				return getContained();
			case TestmodelsPackage.TEST_CONTAINER__CLASSES_FOR_TABLE:
				return getClassesForTable();
			case TestmodelsPackage.TEST_CONTAINER__OBJECTS_FOR_VALIDATION:
				return getObjectsForValidation();
			case TestmodelsPackage.TEST_CONTAINER__MULTIPLE_CONTAINED:
				return getMultipleContained();
			case TestmodelsPackage.TEST_CONTAINER__OBJECTS_FOR_DEFAULT_VALIDATION:
				return getObjectsForDefaultValidation();
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
			case TestmodelsPackage.TEST_CONTAINER__NAME:
				setName((String)newValue);
				return;
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
			case TestmodelsPackage.TEST_CONTAINER__OBJECTS_FOR_VALIDATION:
				getObjectsForValidation().clear();
				getObjectsForValidation().addAll((Collection<? extends ClassForValidation>)newValue);
				return;
			case TestmodelsPackage.TEST_CONTAINER__MULTIPLE_CONTAINED:
				getMultipleContained().clear();
				getMultipleContained().addAll((Collection<? extends TestContainer>)newValue);
				return;
			case TestmodelsPackage.TEST_CONTAINER__OBJECTS_FOR_DEFAULT_VALIDATION:
				getObjectsForDefaultValidation().clear();
				getObjectsForDefaultValidation().addAll((Collection<? extends ClassForDefaultValidation>)newValue);
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
			case TestmodelsPackage.TEST_CONTAINER__NAME:
				setName(NAME_EDEFAULT);
				return;
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
			case TestmodelsPackage.TEST_CONTAINER__OBJECTS_FOR_VALIDATION:
				getObjectsForValidation().clear();
				return;
			case TestmodelsPackage.TEST_CONTAINER__MULTIPLE_CONTAINED:
				getMultipleContained().clear();
				return;
			case TestmodelsPackage.TEST_CONTAINER__OBJECTS_FOR_DEFAULT_VALIDATION:
				getObjectsForDefaultValidation().clear();
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
			case TestmodelsPackage.TEST_CONTAINER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TestmodelsPackage.TEST_CONTAINER__CLASSES_WITH_NAME:
				return classesWithName != null && !classesWithName.isEmpty();
			case TestmodelsPackage.TEST_CONTAINER__CLASSES_FOR_CONTROLS:
				return classesForControls != null && !classesForControls.isEmpty();
			case TestmodelsPackage.TEST_CONTAINER__CONTAINED:
				return contained != null;
			case TestmodelsPackage.TEST_CONTAINER__CLASSES_FOR_TABLE:
				return classesForTable != null && !classesForTable.isEmpty();
			case TestmodelsPackage.TEST_CONTAINER__OBJECTS_FOR_VALIDATION:
				return objectsForValidation != null && !objectsForValidation.isEmpty();
			case TestmodelsPackage.TEST_CONTAINER__MULTIPLE_CONTAINED:
				return multipleContained != null && !multipleContained.isEmpty();
			case TestmodelsPackage.TEST_CONTAINER__OBJECTS_FOR_DEFAULT_VALIDATION:
				return objectsForDefaultValidation != null && !objectsForDefaultValidation.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //TestContainerImpl
