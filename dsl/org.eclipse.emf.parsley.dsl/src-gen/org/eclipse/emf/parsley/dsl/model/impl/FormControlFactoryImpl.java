/**
 */
package org.eclipse.emf.parsley.dsl.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.parsley.dsl.model.FormControlFactory;
import org.eclipse.emf.parsley.dsl.model.FormControlSpecification;
import org.eclipse.emf.parsley.dsl.model.ModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Form Control Factory</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.FormControlFactoryImpl#getControlSpecifications <em>Control Specifications</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FormControlFactoryImpl extends MinimalEObjectImpl.Container implements FormControlFactory
{
  /**
   * The cached value of the '{@link #getControlSpecifications() <em>Control Specifications</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getControlSpecifications()
   * @generated
   * @ordered
   */
  protected EList<FormControlSpecification> controlSpecifications;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FormControlFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return ModelPackage.Literals.FORM_CONTROL_FACTORY;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<FormControlSpecification> getControlSpecifications()
  {
    if (controlSpecifications == null)
    {
      controlSpecifications = new EObjectContainmentEList<FormControlSpecification>(FormControlSpecification.class, this, ModelPackage.FORM_CONTROL_FACTORY__CONTROL_SPECIFICATIONS);
    }
    return controlSpecifications;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case ModelPackage.FORM_CONTROL_FACTORY__CONTROL_SPECIFICATIONS:
        return ((InternalEList<?>)getControlSpecifications()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case ModelPackage.FORM_CONTROL_FACTORY__CONTROL_SPECIFICATIONS:
        return getControlSpecifications();
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
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ModelPackage.FORM_CONTROL_FACTORY__CONTROL_SPECIFICATIONS:
        getControlSpecifications().clear();
        getControlSpecifications().addAll((Collection<? extends FormControlSpecification>)newValue);
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
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case ModelPackage.FORM_CONTROL_FACTORY__CONTROL_SPECIFICATIONS:
        getControlSpecifications().clear();
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
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case ModelPackage.FORM_CONTROL_FACTORY__CONTROL_SPECIFICATIONS:
        return controlSpecifications != null && !controlSpecifications.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //FormControlFactoryImpl
