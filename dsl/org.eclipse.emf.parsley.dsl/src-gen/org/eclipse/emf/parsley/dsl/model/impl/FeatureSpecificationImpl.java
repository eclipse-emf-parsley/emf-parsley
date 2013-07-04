/**
 */
package org.eclipse.emf.parsley.dsl.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.emf.parsley.dsl.model.FeatureSpecification;
import org.eclipse.emf.parsley.dsl.model.ModelPackage;

import org.eclipse.xtext.common.types.JvmMember;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.FeatureSpecificationImpl#getFeatures <em>Features</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureSpecificationImpl extends EmfFeatureAccessImpl implements FeatureSpecification
{
  /**
   * The cached value of the '{@link #getFeatures() <em>Features</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeatures()
   * @generated
   * @ordered
   */
  protected EList<JvmMember> features;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FeatureSpecificationImpl()
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
    return ModelPackage.Literals.FEATURE_SPECIFICATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<JvmMember> getFeatures()
  {
    if (features == null)
    {
      features = new EObjectResolvingEList<JvmMember>(JvmMember.class, this, ModelPackage.FEATURE_SPECIFICATION__FEATURES);
    }
    return features;
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
      case ModelPackage.FEATURE_SPECIFICATION__FEATURES:
        return getFeatures();
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
      case ModelPackage.FEATURE_SPECIFICATION__FEATURES:
        getFeatures().clear();
        getFeatures().addAll((Collection<? extends JvmMember>)newValue);
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
      case ModelPackage.FEATURE_SPECIFICATION__FEATURES:
        getFeatures().clear();
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
      case ModelPackage.FEATURE_SPECIFICATION__FEATURES:
        return features != null && !features.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //FeatureSpecificationImpl
