/**
 */
package org.eclipse.emf.parsley.dsl.model.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.parsley.dsl.model.EmfFeatureAccess;
import org.eclipse.emf.parsley.dsl.model.ExtendsClause;
import org.eclipse.emf.parsley.dsl.model.FeatureSpecification;
import org.eclipse.emf.parsley.dsl.model.FeaturesProvider;
import org.eclipse.emf.parsley.dsl.model.FormControlFactory;
import org.eclipse.emf.parsley.dsl.model.FormControlSpecification;
import org.eclipse.emf.parsley.dsl.model.LabelProvider;
import org.eclipse.emf.parsley.dsl.model.LabelSpecification;
import org.eclipse.emf.parsley.dsl.model.Model;
import org.eclipse.emf.parsley.dsl.model.ModelFactory;
import org.eclipse.emf.parsley.dsl.model.ModelPackage;
import org.eclipse.emf.parsley.dsl.model.Module;
import org.eclipse.emf.parsley.dsl.model.PartSpecification;
import org.eclipse.emf.parsley.dsl.model.PropertyDescriptionProvider;
import org.eclipse.emf.parsley.dsl.model.PropertyDescriptionSpecification;
import org.eclipse.emf.parsley.dsl.model.ProposalCreator;
import org.eclipse.emf.parsley.dsl.model.ProposalSpecification;
import org.eclipse.emf.parsley.dsl.model.ViewSpecification;
import org.eclipse.emf.parsley.dsl.model.ViewerContentProvider;
import org.eclipse.emf.parsley.dsl.model.WithExtendsClause;

import org.eclipse.xtext.common.types.TypesPackage;

import org.eclipse.xtext.xbase.XbasePackage;

import org.eclipse.xtext.xtype.XtypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelPackageImpl extends EPackageImpl implements ModelPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass modelEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass moduleEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass extendsClauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass labelProviderEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass labelSpecificationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass propertyDescriptionProviderEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass propertyDescriptionSpecificationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass featuresProviderEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass featureSpecificationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass formControlFactoryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass formControlSpecificationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass proposalCreatorEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass proposalSpecificationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass viewerContentProviderEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass emfFeatureAccessEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass withExtendsClauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass partSpecificationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass viewSpecificationEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.eclipse.emf.parsley.dsl.model.ModelPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private ModelPackageImpl()
  {
    super(eNS_URI, ModelFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link ModelPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static ModelPackage init()
  {
    if (isInited) return (ModelPackage)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI);

    // Obtain or create and register package
    ModelPackageImpl theModelPackage = (ModelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ModelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ModelPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    XbasePackage.eINSTANCE.eClass();
    XtypePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theModelPackage.createPackageContents();

    // Initialize created meta-data
    theModelPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theModelPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, theModelPackage);
    return theModelPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getModel()
  {
    return modelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModel_ImportSection()
  {
    return (EReference)modelEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModel_Module()
  {
    return (EReference)modelEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getModule()
  {
    return moduleEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getModule_Name()
  {
    return (EAttribute)moduleEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModule_ExtendsClause()
  {
    return (EReference)moduleEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModule_LabelProvider()
  {
    return (EReference)moduleEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModule_PropertyDescriptionProvider()
  {
    return (EReference)moduleEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModule_FeaturesProvider()
  {
    return (EReference)moduleEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModule_FormControlFactory()
  {
    return (EReference)moduleEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModule_ProposalCreator()
  {
    return (EReference)moduleEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModule_ViewerContentProvider()
  {
    return (EReference)moduleEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModule_Parts()
  {
    return (EReference)moduleEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExtendsClause()
  {
    return extendsClauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExtendsClause_SuperType()
  {
    return (EReference)extendsClauseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLabelProvider()
  {
    return labelProviderEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLabelProvider_LabelSpecifications()
  {
    return (EReference)labelProviderEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLabelProvider_ImageSpecifications()
  {
    return (EReference)labelProviderEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLabelSpecification()
  {
    return labelSpecificationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLabelSpecification_ParameterType()
  {
    return (EReference)labelSpecificationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLabelSpecification_Name()
  {
    return (EAttribute)labelSpecificationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLabelSpecification_Expression()
  {
    return (EReference)labelSpecificationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPropertyDescriptionProvider()
  {
    return propertyDescriptionProviderEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPropertyDescriptionProvider_LabelSpecifications()
  {
    return (EReference)propertyDescriptionProviderEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPropertyDescriptionSpecification()
  {
    return propertyDescriptionSpecificationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPropertyDescriptionSpecification_Feature()
  {
    return (EReference)propertyDescriptionSpecificationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPropertyDescriptionSpecification_Expression()
  {
    return (EReference)propertyDescriptionSpecificationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFeaturesProvider()
  {
    return featuresProviderEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeaturesProvider_FeatureSpecifications()
  {
    return (EReference)featuresProviderEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFeatureSpecification()
  {
    return featureSpecificationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeatureSpecification_Features()
  {
    return (EReference)featureSpecificationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFormControlFactory()
  {
    return formControlFactoryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFormControlFactory_ControlSpecifications()
  {
    return (EReference)formControlFactoryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFormControlSpecification()
  {
    return formControlSpecificationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFormControlSpecification_Feature()
  {
    return (EReference)formControlSpecificationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFormControlSpecification_Expression()
  {
    return (EReference)formControlSpecificationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFormControlSpecification_Target()
  {
    return (EReference)formControlSpecificationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getProposalCreator()
  {
    return proposalCreatorEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getProposalCreator_ProposalsSpecifications()
  {
    return (EReference)proposalCreatorEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getProposalSpecification()
  {
    return proposalSpecificationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getProposalSpecification_Feature()
  {
    return (EReference)proposalSpecificationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getProposalSpecification_Expression()
  {
    return (EReference)proposalSpecificationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getViewerContentProvider()
  {
    return viewerContentProviderEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getViewerContentProvider_ChildrenSpecifications()
  {
    return (EReference)viewerContentProviderEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEmfFeatureAccess()
  {
    return emfFeatureAccessEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEmfFeatureAccess_ParameterType()
  {
    return (EReference)emfFeatureAccessEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getWithExtendsClause()
  {
    return withExtendsClauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPartSpecification()
  {
    return partSpecificationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getViewSpecification()
  {
    return viewSpecificationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getViewSpecification_Id()
  {
    return (EAttribute)viewSpecificationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getViewSpecification_ViewName()
  {
    return (EAttribute)viewSpecificationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getViewSpecification_Type()
  {
    return (EReference)viewSpecificationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getViewSpecification_Category()
  {
    return (EAttribute)viewSpecificationEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelFactory getModelFactory()
  {
    return (ModelFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    modelEClass = createEClass(MODEL);
    createEReference(modelEClass, MODEL__IMPORT_SECTION);
    createEReference(modelEClass, MODEL__MODULE);

    moduleEClass = createEClass(MODULE);
    createEAttribute(moduleEClass, MODULE__NAME);
    createEReference(moduleEClass, MODULE__EXTENDS_CLAUSE);
    createEReference(moduleEClass, MODULE__LABEL_PROVIDER);
    createEReference(moduleEClass, MODULE__PROPERTY_DESCRIPTION_PROVIDER);
    createEReference(moduleEClass, MODULE__FEATURES_PROVIDER);
    createEReference(moduleEClass, MODULE__FORM_CONTROL_FACTORY);
    createEReference(moduleEClass, MODULE__PROPOSAL_CREATOR);
    createEReference(moduleEClass, MODULE__VIEWER_CONTENT_PROVIDER);
    createEReference(moduleEClass, MODULE__PARTS);

    extendsClauseEClass = createEClass(EXTENDS_CLAUSE);
    createEReference(extendsClauseEClass, EXTENDS_CLAUSE__SUPER_TYPE);

    labelProviderEClass = createEClass(LABEL_PROVIDER);
    createEReference(labelProviderEClass, LABEL_PROVIDER__LABEL_SPECIFICATIONS);
    createEReference(labelProviderEClass, LABEL_PROVIDER__IMAGE_SPECIFICATIONS);

    labelSpecificationEClass = createEClass(LABEL_SPECIFICATION);
    createEReference(labelSpecificationEClass, LABEL_SPECIFICATION__PARAMETER_TYPE);
    createEAttribute(labelSpecificationEClass, LABEL_SPECIFICATION__NAME);
    createEReference(labelSpecificationEClass, LABEL_SPECIFICATION__EXPRESSION);

    propertyDescriptionProviderEClass = createEClass(PROPERTY_DESCRIPTION_PROVIDER);
    createEReference(propertyDescriptionProviderEClass, PROPERTY_DESCRIPTION_PROVIDER__LABEL_SPECIFICATIONS);

    propertyDescriptionSpecificationEClass = createEClass(PROPERTY_DESCRIPTION_SPECIFICATION);
    createEReference(propertyDescriptionSpecificationEClass, PROPERTY_DESCRIPTION_SPECIFICATION__FEATURE);
    createEReference(propertyDescriptionSpecificationEClass, PROPERTY_DESCRIPTION_SPECIFICATION__EXPRESSION);

    featuresProviderEClass = createEClass(FEATURES_PROVIDER);
    createEReference(featuresProviderEClass, FEATURES_PROVIDER__FEATURE_SPECIFICATIONS);

    featureSpecificationEClass = createEClass(FEATURE_SPECIFICATION);
    createEReference(featureSpecificationEClass, FEATURE_SPECIFICATION__FEATURES);

    formControlFactoryEClass = createEClass(FORM_CONTROL_FACTORY);
    createEReference(formControlFactoryEClass, FORM_CONTROL_FACTORY__CONTROL_SPECIFICATIONS);

    formControlSpecificationEClass = createEClass(FORM_CONTROL_SPECIFICATION);
    createEReference(formControlSpecificationEClass, FORM_CONTROL_SPECIFICATION__FEATURE);
    createEReference(formControlSpecificationEClass, FORM_CONTROL_SPECIFICATION__EXPRESSION);
    createEReference(formControlSpecificationEClass, FORM_CONTROL_SPECIFICATION__TARGET);

    proposalCreatorEClass = createEClass(PROPOSAL_CREATOR);
    createEReference(proposalCreatorEClass, PROPOSAL_CREATOR__PROPOSALS_SPECIFICATIONS);

    proposalSpecificationEClass = createEClass(PROPOSAL_SPECIFICATION);
    createEReference(proposalSpecificationEClass, PROPOSAL_SPECIFICATION__FEATURE);
    createEReference(proposalSpecificationEClass, PROPOSAL_SPECIFICATION__EXPRESSION);

    viewerContentProviderEClass = createEClass(VIEWER_CONTENT_PROVIDER);
    createEReference(viewerContentProviderEClass, VIEWER_CONTENT_PROVIDER__CHILDREN_SPECIFICATIONS);

    emfFeatureAccessEClass = createEClass(EMF_FEATURE_ACCESS);
    createEReference(emfFeatureAccessEClass, EMF_FEATURE_ACCESS__PARAMETER_TYPE);

    withExtendsClauseEClass = createEClass(WITH_EXTENDS_CLAUSE);

    partSpecificationEClass = createEClass(PART_SPECIFICATION);

    viewSpecificationEClass = createEClass(VIEW_SPECIFICATION);
    createEAttribute(viewSpecificationEClass, VIEW_SPECIFICATION__ID);
    createEAttribute(viewSpecificationEClass, VIEW_SPECIFICATION__VIEW_NAME);
    createEReference(viewSpecificationEClass, VIEW_SPECIFICATION__TYPE);
    createEAttribute(viewSpecificationEClass, VIEW_SPECIFICATION__CATEGORY);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    XtypePackage theXtypePackage = (XtypePackage)EPackage.Registry.INSTANCE.getEPackage(XtypePackage.eNS_URI);
    TypesPackage theTypesPackage = (TypesPackage)EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI);
    XbasePackage theXbasePackage = (XbasePackage)EPackage.Registry.INSTANCE.getEPackage(XbasePackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    moduleEClass.getESuperTypes().add(this.getWithExtendsClause());
    propertyDescriptionSpecificationEClass.getESuperTypes().add(this.getEmfFeatureAccess());
    featureSpecificationEClass.getESuperTypes().add(this.getEmfFeatureAccess());
    formControlSpecificationEClass.getESuperTypes().add(this.getEmfFeatureAccess());
    proposalSpecificationEClass.getESuperTypes().add(this.getEmfFeatureAccess());
    viewSpecificationEClass.getESuperTypes().add(this.getPartSpecification());

    // Initialize classes and features; add operations and parameters
    initEClass(modelEClass, Model.class, "Model", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getModel_ImportSection(), theXtypePackage.getXImportSection(), null, "importSection", null, 0, 1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModel_Module(), this.getModule(), null, "module", null, 0, 1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(moduleEClass, Module.class, "Module", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getModule_Name(), ecorePackage.getEString(), "name", null, 0, 1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModule_ExtendsClause(), this.getExtendsClause(), null, "extendsClause", null, 0, 1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModule_LabelProvider(), this.getLabelProvider(), null, "labelProvider", null, 0, 1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModule_PropertyDescriptionProvider(), this.getPropertyDescriptionProvider(), null, "propertyDescriptionProvider", null, 0, 1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModule_FeaturesProvider(), this.getFeaturesProvider(), null, "featuresProvider", null, 0, 1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModule_FormControlFactory(), this.getFormControlFactory(), null, "formControlFactory", null, 0, 1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModule_ProposalCreator(), this.getProposalCreator(), null, "proposalCreator", null, 0, 1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModule_ViewerContentProvider(), this.getViewerContentProvider(), null, "viewerContentProvider", null, 0, 1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModule_Parts(), this.getPartSpecification(), null, "parts", null, 0, -1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(extendsClauseEClass, ExtendsClause.class, "ExtendsClause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getExtendsClause_SuperType(), theTypesPackage.getJvmTypeReference(), null, "superType", null, 0, 1, ExtendsClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(labelProviderEClass, LabelProvider.class, "LabelProvider", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getLabelProvider_LabelSpecifications(), this.getLabelSpecification(), null, "labelSpecifications", null, 0, -1, LabelProvider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getLabelProvider_ImageSpecifications(), this.getLabelSpecification(), null, "imageSpecifications", null, 0, -1, LabelProvider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(labelSpecificationEClass, LabelSpecification.class, "LabelSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getLabelSpecification_ParameterType(), theTypesPackage.getJvmTypeReference(), null, "parameterType", null, 0, 1, LabelSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getLabelSpecification_Name(), ecorePackage.getEString(), "name", null, 0, 1, LabelSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getLabelSpecification_Expression(), theXbasePackage.getXExpression(), null, "expression", null, 0, 1, LabelSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(propertyDescriptionProviderEClass, PropertyDescriptionProvider.class, "PropertyDescriptionProvider", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPropertyDescriptionProvider_LabelSpecifications(), this.getPropertyDescriptionSpecification(), null, "labelSpecifications", null, 0, -1, PropertyDescriptionProvider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(propertyDescriptionSpecificationEClass, PropertyDescriptionSpecification.class, "PropertyDescriptionSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPropertyDescriptionSpecification_Feature(), theTypesPackage.getJvmMember(), null, "feature", null, 0, 1, PropertyDescriptionSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPropertyDescriptionSpecification_Expression(), theXbasePackage.getXExpression(), null, "expression", null, 0, 1, PropertyDescriptionSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(featuresProviderEClass, FeaturesProvider.class, "FeaturesProvider", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFeaturesProvider_FeatureSpecifications(), this.getFeatureSpecification(), null, "featureSpecifications", null, 0, -1, FeaturesProvider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(featureSpecificationEClass, FeatureSpecification.class, "FeatureSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFeatureSpecification_Features(), theTypesPackage.getJvmMember(), null, "features", null, 0, -1, FeatureSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(formControlFactoryEClass, FormControlFactory.class, "FormControlFactory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFormControlFactory_ControlSpecifications(), this.getFormControlSpecification(), null, "controlSpecifications", null, 0, -1, FormControlFactory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(formControlSpecificationEClass, FormControlSpecification.class, "FormControlSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFormControlSpecification_Feature(), theTypesPackage.getJvmMember(), null, "feature", null, 0, 1, FormControlSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFormControlSpecification_Expression(), theXbasePackage.getXExpression(), null, "expression", null, 0, 1, FormControlSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFormControlSpecification_Target(), theXbasePackage.getXExpression(), null, "target", null, 0, 1, FormControlSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(proposalCreatorEClass, ProposalCreator.class, "ProposalCreator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getProposalCreator_ProposalsSpecifications(), this.getProposalSpecification(), null, "proposalsSpecifications", null, 0, -1, ProposalCreator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(proposalSpecificationEClass, ProposalSpecification.class, "ProposalSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getProposalSpecification_Feature(), theTypesPackage.getJvmMember(), null, "feature", null, 0, 1, ProposalSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getProposalSpecification_Expression(), theXbasePackage.getXExpression(), null, "expression", null, 0, 1, ProposalSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(viewerContentProviderEClass, ViewerContentProvider.class, "ViewerContentProvider", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getViewerContentProvider_ChildrenSpecifications(), this.getLabelSpecification(), null, "childrenSpecifications", null, 0, -1, ViewerContentProvider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(emfFeatureAccessEClass, EmfFeatureAccess.class, "EmfFeatureAccess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEmfFeatureAccess_ParameterType(), theTypesPackage.getJvmTypeReference(), null, "parameterType", null, 0, 1, EmfFeatureAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(withExtendsClauseEClass, WithExtendsClause.class, "WithExtendsClause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(partSpecificationEClass, PartSpecification.class, "PartSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(viewSpecificationEClass, ViewSpecification.class, "ViewSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getViewSpecification_Id(), ecorePackage.getEString(), "id", null, 0, 1, ViewSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getViewSpecification_ViewName(), ecorePackage.getEString(), "viewName", null, 0, 1, ViewSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getViewSpecification_Type(), theTypesPackage.getJvmTypeReference(), null, "type", null, 0, 1, ViewSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getViewSpecification_Category(), ecorePackage.getEString(), "category", null, 0, 1, ViewSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //ModelPackageImpl
