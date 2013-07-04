/**
 */
package org.eclipse.emf.parsley.dsl.model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.parsley.dsl.model.ExtendsClause;
import org.eclipse.emf.parsley.dsl.model.ModelPackage;

import org.eclipse.xtext.common.types.JvmTypeReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extends Clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.ExtendsClauseImpl#getSuperType <em>Super Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExtendsClauseImpl extends MinimalEObjectImpl.Container implements ExtendsClause
{
  /**
   * The cached value of the '{@link #getSuperType() <em>Super Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSuperType()
   * @generated
   * @ordered
   */
  protected JvmTypeReference superType;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ExtendsClauseImpl()
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
    return ModelPackage.Literals.EXTENDS_CLAUSE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmTypeReference getSuperType()
  {
    return superType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSuperType(JvmTypeReference newSuperType, NotificationChain msgs)
  {
    JvmTypeReference oldSuperType = superType;
    superType = newSuperType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.EXTENDS_CLAUSE__SUPER_TYPE, oldSuperType, newSuperType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSuperType(JvmTypeReference newSuperType)
  {
    if (newSuperType != superType)
    {
      NotificationChain msgs = null;
      if (superType != null)
        msgs = ((InternalEObject)superType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.EXTENDS_CLAUSE__SUPER_TYPE, null, msgs);
      if (newSuperType != null)
        msgs = ((InternalEObject)newSuperType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.EXTENDS_CLAUSE__SUPER_TYPE, null, msgs);
      msgs = basicSetSuperType(newSuperType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.EXTENDS_CLAUSE__SUPER_TYPE, newSuperType, newSuperType));
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
      case ModelPackage.EXTENDS_CLAUSE__SUPER_TYPE:
        return basicSetSuperType(null, msgs);
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
      case ModelPackage.EXTENDS_CLAUSE__SUPER_TYPE:
        return getSuperType();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ModelPackage.EXTENDS_CLAUSE__SUPER_TYPE:
        setSuperType((JvmTypeReference)newValue);
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
      case ModelPackage.EXTENDS_CLAUSE__SUPER_TYPE:
        setSuperType((JvmTypeReference)null);
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
      case ModelPackage.EXTENDS_CLAUSE__SUPER_TYPE:
        return superType != null;
    }
    return super.eIsSet(featureID);
  }

} //ExtendsClauseImpl
