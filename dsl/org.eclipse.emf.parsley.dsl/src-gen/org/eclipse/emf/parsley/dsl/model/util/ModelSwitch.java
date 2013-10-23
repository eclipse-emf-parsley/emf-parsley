/**
 */
package org.eclipse.emf.parsley.dsl.model.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.emf.parsley.dsl.model.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.parsley.dsl.model.ModelPackage
 * @generated
 */
public class ModelSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static ModelPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = ModelPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case ModelPackage.MODEL:
      {
        Model model = (Model)theEObject;
        T result = caseModel(model);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.MODULE:
      {
        Module module = (Module)theEObject;
        T result = caseModule(module);
        if (result == null) result = caseWithExtendsClause(module);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.EXTENDS_CLAUSE:
      {
        ExtendsClause extendsClause = (ExtendsClause)theEObject;
        T result = caseExtendsClause(extendsClause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.LABEL_PROVIDER:
      {
        LabelProvider labelProvider = (LabelProvider)theEObject;
        T result = caseLabelProvider(labelProvider);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.LABEL_SPECIFICATION:
      {
        LabelSpecification labelSpecification = (LabelSpecification)theEObject;
        T result = caseLabelSpecification(labelSpecification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.FEATURE_CAPTION_PROVIDER:
      {
        FeatureCaptionProvider featureCaptionProvider = (FeatureCaptionProvider)theEObject;
        T result = caseFeatureCaptionProvider(featureCaptionProvider);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.FORM_FEATURE_CAPTION_PROVIDER:
      {
        FormFeatureCaptionProvider formFeatureCaptionProvider = (FormFeatureCaptionProvider)theEObject;
        T result = caseFormFeatureCaptionProvider(formFeatureCaptionProvider);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.DIALOG_FEATURE_CAPTION_PROVIDER:
      {
        DialogFeatureCaptionProvider dialogFeatureCaptionProvider = (DialogFeatureCaptionProvider)theEObject;
        T result = caseDialogFeatureCaptionProvider(dialogFeatureCaptionProvider);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.FEATURE_CAPTION_SPECIFICATION:
      {
        FeatureCaptionSpecification featureCaptionSpecification = (FeatureCaptionSpecification)theEObject;
        T result = caseFeatureCaptionSpecification(featureCaptionSpecification);
        if (result == null) result = caseEmfFeatureAccess(featureCaptionSpecification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.FEATURES_PROVIDER:
      {
        FeaturesProvider featuresProvider = (FeaturesProvider)theEObject;
        T result = caseFeaturesProvider(featuresProvider);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.FEATURE_SPECIFICATION:
      {
        FeatureSpecification featureSpecification = (FeatureSpecification)theEObject;
        T result = caseFeatureSpecification(featureSpecification);
        if (result == null) result = caseEmfFeatureAccess(featureSpecification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.FORM_CONTROL_FACTORY:
      {
        FormControlFactory formControlFactory = (FormControlFactory)theEObject;
        T result = caseFormControlFactory(formControlFactory);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.DIALOG_CONTROL_FACTORY:
      {
        DialogControlFactory dialogControlFactory = (DialogControlFactory)theEObject;
        T result = caseDialogControlFactory(dialogControlFactory);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.CONTROL_FACTORY_SPECIFICATION:
      {
        ControlFactorySpecification controlFactorySpecification = (ControlFactorySpecification)theEObject;
        T result = caseControlFactorySpecification(controlFactorySpecification);
        if (result == null) result = caseEmfFeatureAccess(controlFactorySpecification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.PROPOSAL_CREATOR:
      {
        ProposalCreator proposalCreator = (ProposalCreator)theEObject;
        T result = caseProposalCreator(proposalCreator);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.PROPOSAL_SPECIFICATION:
      {
        ProposalSpecification proposalSpecification = (ProposalSpecification)theEObject;
        T result = caseProposalSpecification(proposalSpecification);
        if (result == null) result = caseEmfFeatureAccess(proposalSpecification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.VIEWER_CONTENT_PROVIDER:
      {
        ViewerContentProvider viewerContentProvider = (ViewerContentProvider)theEObject;
        T result = caseViewerContentProvider(viewerContentProvider);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.EMF_FEATURE_ACCESS:
      {
        EmfFeatureAccess emfFeatureAccess = (EmfFeatureAccess)theEObject;
        T result = caseEmfFeatureAccess(emfFeatureAccess);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.WITH_EXTENDS_CLAUSE:
      {
        WithExtendsClause withExtendsClause = (WithExtendsClause)theEObject;
        T result = caseWithExtendsClause(withExtendsClause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.PARTS_SPECIFICATIONS:
      {
        PartsSpecifications partsSpecifications = (PartsSpecifications)theEObject;
        T result = casePartsSpecifications(partsSpecifications);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.PART_SPECIFICATION:
      {
        PartSpecification partSpecification = (PartSpecification)theEObject;
        T result = casePartSpecification(partSpecification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelPackage.VIEW_SPECIFICATION:
      {
        ViewSpecification viewSpecification = (ViewSpecification)theEObject;
        T result = caseViewSpecification(viewSpecification);
        if (result == null) result = casePartSpecification(viewSpecification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Model</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModel(Model object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Module</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Module</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModule(Module object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Extends Clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Extends Clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExtendsClause(ExtendsClause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Label Provider</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Label Provider</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLabelProvider(LabelProvider object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Label Specification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Label Specification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLabelSpecification(LabelSpecification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature Caption Provider</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature Caption Provider</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeatureCaptionProvider(FeatureCaptionProvider object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Form Feature Caption Provider</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Form Feature Caption Provider</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFormFeatureCaptionProvider(FormFeatureCaptionProvider object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Dialog Feature Caption Provider</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Dialog Feature Caption Provider</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDialogFeatureCaptionProvider(DialogFeatureCaptionProvider object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature Caption Specification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature Caption Specification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeatureCaptionSpecification(FeatureCaptionSpecification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Features Provider</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Features Provider</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeaturesProvider(FeaturesProvider object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature Specification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature Specification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeatureSpecification(FeatureSpecification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Form Control Factory</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Form Control Factory</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFormControlFactory(FormControlFactory object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Dialog Control Factory</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Dialog Control Factory</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDialogControlFactory(DialogControlFactory object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Control Factory Specification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Control Factory Specification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseControlFactorySpecification(ControlFactorySpecification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Proposal Creator</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Proposal Creator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseProposalCreator(ProposalCreator object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Proposal Specification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Proposal Specification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseProposalSpecification(ProposalSpecification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Viewer Content Provider</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Viewer Content Provider</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseViewerContentProvider(ViewerContentProvider object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Emf Feature Access</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Emf Feature Access</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEmfFeatureAccess(EmfFeatureAccess object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>With Extends Clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>With Extends Clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWithExtendsClause(WithExtendsClause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Parts Specifications</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Parts Specifications</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePartsSpecifications(PartsSpecifications object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Part Specification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Part Specification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePartSpecification(PartSpecification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>View Specification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>View Specification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseViewSpecification(ViewSpecification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} //ModelSwitch
