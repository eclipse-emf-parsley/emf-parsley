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

import org.eclipse.emf.parsley.dsl.model.DialogFeatureCaptionProvider;
import org.eclipse.emf.parsley.dsl.model.FeatureCaptionSpecification;
import org.eclipse.emf.parsley.dsl.model.ModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dialog Feature Caption Provider</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.DialogFeatureCaptionProviderImpl#getSpecifications <em>Specifications</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.DialogFeatureCaptionProviderImpl#getLabelSpecifications <em>Label Specifications</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DialogFeatureCaptionProviderImpl extends MinimalEObjectImpl.Container implements DialogFeatureCaptionProvider
{
  /**
   * The cached value of the '{@link #getSpecifications() <em>Specifications</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSpecifications()
   * @generated
   * @ordered
   */
  protected EList<FeatureCaptionSpecification> specifications;

  /**
   * The cached value of the '{@link #getLabelSpecifications() <em>Label Specifications</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLabelSpecifications()
   * @generated
   * @ordered
   */
  protected EList<FeatureCaptionSpecification> labelSpecifications;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DialogFeatureCaptionProviderImpl()
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
    return ModelPackage.Literals.DIALOG_FEATURE_CAPTION_PROVIDER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<FeatureCaptionSpecification> getSpecifications()
  {
    if (specifications == null)
    {
      specifications = new EObjectContainmentEList<FeatureCaptionSpecification>(FeatureCaptionSpecification.class, this, ModelPackage.DIALOG_FEATURE_CAPTION_PROVIDER__SPECIFICATIONS);
    }
    return specifications;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<FeatureCaptionSpecification> getLabelSpecifications()
  {
    if (labelSpecifications == null)
    {
      labelSpecifications = new EObjectContainmentEList<FeatureCaptionSpecification>(FeatureCaptionSpecification.class, this, ModelPackage.DIALOG_FEATURE_CAPTION_PROVIDER__LABEL_SPECIFICATIONS);
    }
    return labelSpecifications;
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
      case ModelPackage.DIALOG_FEATURE_CAPTION_PROVIDER__SPECIFICATIONS:
        return ((InternalEList<?>)getSpecifications()).basicRemove(otherEnd, msgs);
      case ModelPackage.DIALOG_FEATURE_CAPTION_PROVIDER__LABEL_SPECIFICATIONS:
        return ((InternalEList<?>)getLabelSpecifications()).basicRemove(otherEnd, msgs);
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
      case ModelPackage.DIALOG_FEATURE_CAPTION_PROVIDER__SPECIFICATIONS:
        return getSpecifications();
      case ModelPackage.DIALOG_FEATURE_CAPTION_PROVIDER__LABEL_SPECIFICATIONS:
        return getLabelSpecifications();
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
      case ModelPackage.DIALOG_FEATURE_CAPTION_PROVIDER__SPECIFICATIONS:
        getSpecifications().clear();
        getSpecifications().addAll((Collection<? extends FeatureCaptionSpecification>)newValue);
        return;
      case ModelPackage.DIALOG_FEATURE_CAPTION_PROVIDER__LABEL_SPECIFICATIONS:
        getLabelSpecifications().clear();
        getLabelSpecifications().addAll((Collection<? extends FeatureCaptionSpecification>)newValue);
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
      case ModelPackage.DIALOG_FEATURE_CAPTION_PROVIDER__SPECIFICATIONS:
        getSpecifications().clear();
        return;
      case ModelPackage.DIALOG_FEATURE_CAPTION_PROVIDER__LABEL_SPECIFICATIONS:
        getLabelSpecifications().clear();
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
      case ModelPackage.DIALOG_FEATURE_CAPTION_PROVIDER__SPECIFICATIONS:
        return specifications != null && !specifications.isEmpty();
      case ModelPackage.DIALOG_FEATURE_CAPTION_PROVIDER__LABEL_SPECIFICATIONS:
        return labelSpecifications != null && !labelSpecifications.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //DialogFeatureCaptionProviderImpl
