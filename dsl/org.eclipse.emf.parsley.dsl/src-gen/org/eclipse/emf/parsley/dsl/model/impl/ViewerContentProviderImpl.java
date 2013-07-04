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

import org.eclipse.emf.parsley.dsl.model.LabelSpecification;
import org.eclipse.emf.parsley.dsl.model.ModelPackage;
import org.eclipse.emf.parsley.dsl.model.ViewerContentProvider;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Viewer Content Provider</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.ViewerContentProviderImpl#getChildrenSpecifications <em>Children Specifications</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ViewerContentProviderImpl extends MinimalEObjectImpl.Container implements ViewerContentProvider
{
  /**
   * The cached value of the '{@link #getChildrenSpecifications() <em>Children Specifications</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getChildrenSpecifications()
   * @generated
   * @ordered
   */
  protected EList<LabelSpecification> childrenSpecifications;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ViewerContentProviderImpl()
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
    return ModelPackage.Literals.VIEWER_CONTENT_PROVIDER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<LabelSpecification> getChildrenSpecifications()
  {
    if (childrenSpecifications == null)
    {
      childrenSpecifications = new EObjectContainmentEList<LabelSpecification>(LabelSpecification.class, this, ModelPackage.VIEWER_CONTENT_PROVIDER__CHILDREN_SPECIFICATIONS);
    }
    return childrenSpecifications;
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
      case ModelPackage.VIEWER_CONTENT_PROVIDER__CHILDREN_SPECIFICATIONS:
        return ((InternalEList<?>)getChildrenSpecifications()).basicRemove(otherEnd, msgs);
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
      case ModelPackage.VIEWER_CONTENT_PROVIDER__CHILDREN_SPECIFICATIONS:
        return getChildrenSpecifications();
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
      case ModelPackage.VIEWER_CONTENT_PROVIDER__CHILDREN_SPECIFICATIONS:
        getChildrenSpecifications().clear();
        getChildrenSpecifications().addAll((Collection<? extends LabelSpecification>)newValue);
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
      case ModelPackage.VIEWER_CONTENT_PROVIDER__CHILDREN_SPECIFICATIONS:
        getChildrenSpecifications().clear();
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
      case ModelPackage.VIEWER_CONTENT_PROVIDER__CHILDREN_SPECIFICATIONS:
        return childrenSpecifications != null && !childrenSpecifications.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ViewerContentProviderImpl
