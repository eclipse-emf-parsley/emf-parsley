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

import org.eclipse.emf.parsley.dsl.model.LabelProvider;
import org.eclipse.emf.parsley.dsl.model.LabelSpecification;
import org.eclipse.emf.parsley.dsl.model.ModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Label Provider</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.LabelProviderImpl#getLabelSpecifications <em>Label Specifications</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.LabelProviderImpl#getImageSpecifications <em>Image Specifications</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LabelProviderImpl extends MinimalEObjectImpl.Container implements LabelProvider
{
  /**
   * The cached value of the '{@link #getLabelSpecifications() <em>Label Specifications</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLabelSpecifications()
   * @generated
   * @ordered
   */
  protected EList<LabelSpecification> labelSpecifications;

  /**
   * The cached value of the '{@link #getImageSpecifications() <em>Image Specifications</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImageSpecifications()
   * @generated
   * @ordered
   */
  protected EList<LabelSpecification> imageSpecifications;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LabelProviderImpl()
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
    return ModelPackage.Literals.LABEL_PROVIDER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<LabelSpecification> getLabelSpecifications()
  {
    if (labelSpecifications == null)
    {
      labelSpecifications = new EObjectContainmentEList<LabelSpecification>(LabelSpecification.class, this, ModelPackage.LABEL_PROVIDER__LABEL_SPECIFICATIONS);
    }
    return labelSpecifications;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<LabelSpecification> getImageSpecifications()
  {
    if (imageSpecifications == null)
    {
      imageSpecifications = new EObjectContainmentEList<LabelSpecification>(LabelSpecification.class, this, ModelPackage.LABEL_PROVIDER__IMAGE_SPECIFICATIONS);
    }
    return imageSpecifications;
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
      case ModelPackage.LABEL_PROVIDER__LABEL_SPECIFICATIONS:
        return ((InternalEList<?>)getLabelSpecifications()).basicRemove(otherEnd, msgs);
      case ModelPackage.LABEL_PROVIDER__IMAGE_SPECIFICATIONS:
        return ((InternalEList<?>)getImageSpecifications()).basicRemove(otherEnd, msgs);
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
      case ModelPackage.LABEL_PROVIDER__LABEL_SPECIFICATIONS:
        return getLabelSpecifications();
      case ModelPackage.LABEL_PROVIDER__IMAGE_SPECIFICATIONS:
        return getImageSpecifications();
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
      case ModelPackage.LABEL_PROVIDER__LABEL_SPECIFICATIONS:
        getLabelSpecifications().clear();
        getLabelSpecifications().addAll((Collection<? extends LabelSpecification>)newValue);
        return;
      case ModelPackage.LABEL_PROVIDER__IMAGE_SPECIFICATIONS:
        getImageSpecifications().clear();
        getImageSpecifications().addAll((Collection<? extends LabelSpecification>)newValue);
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
      case ModelPackage.LABEL_PROVIDER__LABEL_SPECIFICATIONS:
        getLabelSpecifications().clear();
        return;
      case ModelPackage.LABEL_PROVIDER__IMAGE_SPECIFICATIONS:
        getImageSpecifications().clear();
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
      case ModelPackage.LABEL_PROVIDER__LABEL_SPECIFICATIONS:
        return labelSpecifications != null && !labelSpecifications.isEmpty();
      case ModelPackage.LABEL_PROVIDER__IMAGE_SPECIFICATIONS:
        return imageSpecifications != null && !imageSpecifications.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //LabelProviderImpl
