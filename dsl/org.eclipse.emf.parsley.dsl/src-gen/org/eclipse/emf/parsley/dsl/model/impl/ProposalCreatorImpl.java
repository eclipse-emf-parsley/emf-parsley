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

import org.eclipse.emf.parsley.dsl.model.ModelPackage;
import org.eclipse.emf.parsley.dsl.model.ProposalCreator;
import org.eclipse.emf.parsley.dsl.model.ProposalSpecification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Proposal Creator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.ProposalCreatorImpl#getProposalsSpecifications <em>Proposals Specifications</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProposalCreatorImpl extends MinimalEObjectImpl.Container implements ProposalCreator
{
  /**
   * The cached value of the '{@link #getProposalsSpecifications() <em>Proposals Specifications</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProposalsSpecifications()
   * @generated
   * @ordered
   */
  protected EList<ProposalSpecification> proposalsSpecifications;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ProposalCreatorImpl()
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
    return ModelPackage.Literals.PROPOSAL_CREATOR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ProposalSpecification> getProposalsSpecifications()
  {
    if (proposalsSpecifications == null)
    {
      proposalsSpecifications = new EObjectContainmentEList<ProposalSpecification>(ProposalSpecification.class, this, ModelPackage.PROPOSAL_CREATOR__PROPOSALS_SPECIFICATIONS);
    }
    return proposalsSpecifications;
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
      case ModelPackage.PROPOSAL_CREATOR__PROPOSALS_SPECIFICATIONS:
        return ((InternalEList<?>)getProposalsSpecifications()).basicRemove(otherEnd, msgs);
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
      case ModelPackage.PROPOSAL_CREATOR__PROPOSALS_SPECIFICATIONS:
        return getProposalsSpecifications();
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
      case ModelPackage.PROPOSAL_CREATOR__PROPOSALS_SPECIFICATIONS:
        getProposalsSpecifications().clear();
        getProposalsSpecifications().addAll((Collection<? extends ProposalSpecification>)newValue);
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
      case ModelPackage.PROPOSAL_CREATOR__PROPOSALS_SPECIFICATIONS:
        getProposalsSpecifications().clear();
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
      case ModelPackage.PROPOSAL_CREATOR__PROPOSALS_SPECIFICATIONS:
        return proposalsSpecifications != null && !proposalsSpecifications.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ProposalCreatorImpl
