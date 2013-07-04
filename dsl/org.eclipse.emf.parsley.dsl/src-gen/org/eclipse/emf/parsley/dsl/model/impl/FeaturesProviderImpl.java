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

import org.eclipse.emf.parsley.dsl.model.FeatureSpecification;
import org.eclipse.emf.parsley.dsl.model.FeaturesProvider;
import org.eclipse.emf.parsley.dsl.model.ModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Features Provider</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.FeaturesProviderImpl#getFeatureSpecifications <em>Feature Specifications</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeaturesProviderImpl extends MinimalEObjectImpl.Container implements FeaturesProvider
{
  /**
   * The cached value of the '{@link #getFeatureSpecifications() <em>Feature Specifications</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeatureSpecifications()
   * @generated
   * @ordered
   */
  protected EList<FeatureSpecification> featureSpecifications;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FeaturesProviderImpl()
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
    return ModelPackage.Literals.FEATURES_PROVIDER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<FeatureSpecification> getFeatureSpecifications()
  {
    if (featureSpecifications == null)
    {
      featureSpecifications = new EObjectContainmentEList<FeatureSpecification>(FeatureSpecification.class, this, ModelPackage.FEATURES_PROVIDER__FEATURE_SPECIFICATIONS);
    }
    return featureSpecifications;
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
      case ModelPackage.FEATURES_PROVIDER__FEATURE_SPECIFICATIONS:
        return ((InternalEList<?>)getFeatureSpecifications()).basicRemove(otherEnd, msgs);
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
      case ModelPackage.FEATURES_PROVIDER__FEATURE_SPECIFICATIONS:
        return getFeatureSpecifications();
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
      case ModelPackage.FEATURES_PROVIDER__FEATURE_SPECIFICATIONS:
        getFeatureSpecifications().clear();
        getFeatureSpecifications().addAll((Collection<? extends FeatureSpecification>)newValue);
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
      case ModelPackage.FEATURES_PROVIDER__FEATURE_SPECIFICATIONS:
        getFeatureSpecifications().clear();
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
      case ModelPackage.FEATURES_PROVIDER__FEATURE_SPECIFICATIONS:
        return featureSpecifications != null && !featureSpecifications.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //FeaturesProviderImpl
