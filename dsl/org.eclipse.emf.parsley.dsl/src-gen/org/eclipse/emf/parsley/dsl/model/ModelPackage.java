/**
 */
package org.eclipse.emf.parsley.dsl.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.parsley.dsl.model.ModelFactory
 * @model kind="package"
 * @generated
 */
public interface ModelPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "model";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/emf/parsley/dsl/EmfParsleyDsl";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "model";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ModelPackage eINSTANCE = org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.parsley.dsl.model.impl.ModelImpl <em>Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.parsley.dsl.model.impl.ModelImpl
   * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getModel()
   * @generated
   */
  int MODEL = 0;

  /**
   * The feature id for the '<em><b>Import Section</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__IMPORT_SECTION = 0;

  /**
   * The feature id for the '<em><b>Module</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__MODULE = 1;

  /**
   * The number of structural features of the '<em>Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.parsley.dsl.model.impl.WithExtendsClauseImpl <em>With Extends Clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.parsley.dsl.model.impl.WithExtendsClauseImpl
   * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getWithExtendsClause()
   * @generated
   */
  int WITH_EXTENDS_CLAUSE = 15;

  /**
   * The number of structural features of the '<em>With Extends Clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WITH_EXTENDS_CLAUSE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.parsley.dsl.model.impl.ModuleImpl <em>Module</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.parsley.dsl.model.impl.ModuleImpl
   * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getModule()
   * @generated
   */
  int MODULE = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODULE__NAME = WITH_EXTENDS_CLAUSE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Extends Clause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODULE__EXTENDS_CLAUSE = WITH_EXTENDS_CLAUSE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Label Provider</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODULE__LABEL_PROVIDER = WITH_EXTENDS_CLAUSE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Property Description Provider</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODULE__PROPERTY_DESCRIPTION_PROVIDER = WITH_EXTENDS_CLAUSE_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Features Provider</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODULE__FEATURES_PROVIDER = WITH_EXTENDS_CLAUSE_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Form Control Factory</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODULE__FORM_CONTROL_FACTORY = WITH_EXTENDS_CLAUSE_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Proposal Creator</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODULE__PROPOSAL_CREATOR = WITH_EXTENDS_CLAUSE_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Viewer Content Provider</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODULE__VIEWER_CONTENT_PROVIDER = WITH_EXTENDS_CLAUSE_FEATURE_COUNT + 7;

  /**
   * The feature id for the '<em><b>Parts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODULE__PARTS = WITH_EXTENDS_CLAUSE_FEATURE_COUNT + 8;

  /**
   * The number of structural features of the '<em>Module</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODULE_FEATURE_COUNT = WITH_EXTENDS_CLAUSE_FEATURE_COUNT + 9;

  /**
   * The meta object id for the '{@link org.eclipse.emf.parsley.dsl.model.impl.ExtendsClauseImpl <em>Extends Clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.parsley.dsl.model.impl.ExtendsClauseImpl
   * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getExtendsClause()
   * @generated
   */
  int EXTENDS_CLAUSE = 2;

  /**
   * The feature id for the '<em><b>Super Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS_CLAUSE__SUPER_TYPE = 0;

  /**
   * The number of structural features of the '<em>Extends Clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS_CLAUSE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.parsley.dsl.model.impl.LabelProviderImpl <em>Label Provider</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.parsley.dsl.model.impl.LabelProviderImpl
   * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getLabelProvider()
   * @generated
   */
  int LABEL_PROVIDER = 3;

  /**
   * The feature id for the '<em><b>Label Specifications</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LABEL_PROVIDER__LABEL_SPECIFICATIONS = 0;

  /**
   * The feature id for the '<em><b>Image Specifications</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LABEL_PROVIDER__IMAGE_SPECIFICATIONS = 1;

  /**
   * The number of structural features of the '<em>Label Provider</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LABEL_PROVIDER_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.parsley.dsl.model.impl.LabelSpecificationImpl <em>Label Specification</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.parsley.dsl.model.impl.LabelSpecificationImpl
   * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getLabelSpecification()
   * @generated
   */
  int LABEL_SPECIFICATION = 4;

  /**
   * The feature id for the '<em><b>Parameter Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LABEL_SPECIFICATION__PARAMETER_TYPE = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LABEL_SPECIFICATION__NAME = 1;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LABEL_SPECIFICATION__EXPRESSION = 2;

  /**
   * The number of structural features of the '<em>Label Specification</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LABEL_SPECIFICATION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.parsley.dsl.model.impl.PropertyDescriptionProviderImpl <em>Property Description Provider</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.parsley.dsl.model.impl.PropertyDescriptionProviderImpl
   * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getPropertyDescriptionProvider()
   * @generated
   */
  int PROPERTY_DESCRIPTION_PROVIDER = 5;

  /**
   * The feature id for the '<em><b>Label Specifications</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_DESCRIPTION_PROVIDER__LABEL_SPECIFICATIONS = 0;

  /**
   * The number of structural features of the '<em>Property Description Provider</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_DESCRIPTION_PROVIDER_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.parsley.dsl.model.impl.EmfFeatureAccessImpl <em>Emf Feature Access</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.parsley.dsl.model.impl.EmfFeatureAccessImpl
   * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getEmfFeatureAccess()
   * @generated
   */
  int EMF_FEATURE_ACCESS = 14;

  /**
   * The feature id for the '<em><b>Parameter Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMF_FEATURE_ACCESS__PARAMETER_TYPE = 0;

  /**
   * The number of structural features of the '<em>Emf Feature Access</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMF_FEATURE_ACCESS_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.parsley.dsl.model.impl.PropertyDescriptionSpecificationImpl <em>Property Description Specification</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.parsley.dsl.model.impl.PropertyDescriptionSpecificationImpl
   * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getPropertyDescriptionSpecification()
   * @generated
   */
  int PROPERTY_DESCRIPTION_SPECIFICATION = 6;

  /**
   * The feature id for the '<em><b>Parameter Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_DESCRIPTION_SPECIFICATION__PARAMETER_TYPE = EMF_FEATURE_ACCESS__PARAMETER_TYPE;

  /**
   * The feature id for the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_DESCRIPTION_SPECIFICATION__FEATURE = EMF_FEATURE_ACCESS_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_DESCRIPTION_SPECIFICATION__EXPRESSION = EMF_FEATURE_ACCESS_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Property Description Specification</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_DESCRIPTION_SPECIFICATION_FEATURE_COUNT = EMF_FEATURE_ACCESS_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.parsley.dsl.model.impl.FeaturesProviderImpl <em>Features Provider</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.parsley.dsl.model.impl.FeaturesProviderImpl
   * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getFeaturesProvider()
   * @generated
   */
  int FEATURES_PROVIDER = 7;

  /**
   * The feature id for the '<em><b>Feature Specifications</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURES_PROVIDER__FEATURE_SPECIFICATIONS = 0;

  /**
   * The number of structural features of the '<em>Features Provider</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURES_PROVIDER_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.parsley.dsl.model.impl.FeatureSpecificationImpl <em>Feature Specification</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.parsley.dsl.model.impl.FeatureSpecificationImpl
   * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getFeatureSpecification()
   * @generated
   */
  int FEATURE_SPECIFICATION = 8;

  /**
   * The feature id for the '<em><b>Parameter Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_SPECIFICATION__PARAMETER_TYPE = EMF_FEATURE_ACCESS__PARAMETER_TYPE;

  /**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_SPECIFICATION__FEATURES = EMF_FEATURE_ACCESS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Feature Specification</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_SPECIFICATION_FEATURE_COUNT = EMF_FEATURE_ACCESS_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.parsley.dsl.model.impl.FormControlFactoryImpl <em>Form Control Factory</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.parsley.dsl.model.impl.FormControlFactoryImpl
   * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getFormControlFactory()
   * @generated
   */
  int FORM_CONTROL_FACTORY = 9;

  /**
   * The feature id for the '<em><b>Control Specifications</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORM_CONTROL_FACTORY__CONTROL_SPECIFICATIONS = 0;

  /**
   * The number of structural features of the '<em>Form Control Factory</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORM_CONTROL_FACTORY_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.parsley.dsl.model.impl.FormControlSpecificationImpl <em>Form Control Specification</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.parsley.dsl.model.impl.FormControlSpecificationImpl
   * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getFormControlSpecification()
   * @generated
   */
  int FORM_CONTROL_SPECIFICATION = 10;

  /**
   * The feature id for the '<em><b>Parameter Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORM_CONTROL_SPECIFICATION__PARAMETER_TYPE = EMF_FEATURE_ACCESS__PARAMETER_TYPE;

  /**
   * The feature id for the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORM_CONTROL_SPECIFICATION__FEATURE = EMF_FEATURE_ACCESS_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORM_CONTROL_SPECIFICATION__EXPRESSION = EMF_FEATURE_ACCESS_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORM_CONTROL_SPECIFICATION__TARGET = EMF_FEATURE_ACCESS_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Form Control Specification</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORM_CONTROL_SPECIFICATION_FEATURE_COUNT = EMF_FEATURE_ACCESS_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.parsley.dsl.model.impl.ProposalCreatorImpl <em>Proposal Creator</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.parsley.dsl.model.impl.ProposalCreatorImpl
   * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getProposalCreator()
   * @generated
   */
  int PROPOSAL_CREATOR = 11;

  /**
   * The feature id for the '<em><b>Proposals Specifications</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPOSAL_CREATOR__PROPOSALS_SPECIFICATIONS = 0;

  /**
   * The number of structural features of the '<em>Proposal Creator</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPOSAL_CREATOR_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.parsley.dsl.model.impl.ProposalSpecificationImpl <em>Proposal Specification</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.parsley.dsl.model.impl.ProposalSpecificationImpl
   * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getProposalSpecification()
   * @generated
   */
  int PROPOSAL_SPECIFICATION = 12;

  /**
   * The feature id for the '<em><b>Parameter Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPOSAL_SPECIFICATION__PARAMETER_TYPE = EMF_FEATURE_ACCESS__PARAMETER_TYPE;

  /**
   * The feature id for the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPOSAL_SPECIFICATION__FEATURE = EMF_FEATURE_ACCESS_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPOSAL_SPECIFICATION__EXPRESSION = EMF_FEATURE_ACCESS_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Proposal Specification</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPOSAL_SPECIFICATION_FEATURE_COUNT = EMF_FEATURE_ACCESS_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.parsley.dsl.model.impl.ViewerContentProviderImpl <em>Viewer Content Provider</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.parsley.dsl.model.impl.ViewerContentProviderImpl
   * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getViewerContentProvider()
   * @generated
   */
  int VIEWER_CONTENT_PROVIDER = 13;

  /**
   * The feature id for the '<em><b>Children Specifications</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIEWER_CONTENT_PROVIDER__CHILDREN_SPECIFICATIONS = 0;

  /**
   * The number of structural features of the '<em>Viewer Content Provider</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIEWER_CONTENT_PROVIDER_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.parsley.dsl.model.impl.PartSpecificationImpl <em>Part Specification</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.parsley.dsl.model.impl.PartSpecificationImpl
   * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getPartSpecification()
   * @generated
   */
  int PART_SPECIFICATION = 16;

  /**
   * The number of structural features of the '<em>Part Specification</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PART_SPECIFICATION_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.parsley.dsl.model.impl.ViewSpecificationImpl <em>View Specification</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.parsley.dsl.model.impl.ViewSpecificationImpl
   * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getViewSpecification()
   * @generated
   */
  int VIEW_SPECIFICATION = 17;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIEW_SPECIFICATION__ID = PART_SPECIFICATION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>View Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIEW_SPECIFICATION__VIEW_NAME = PART_SPECIFICATION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIEW_SPECIFICATION__TYPE = PART_SPECIFICATION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Category</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIEW_SPECIFICATION__CATEGORY = PART_SPECIFICATION_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>View Specification</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIEW_SPECIFICATION_FEATURE_COUNT = PART_SPECIFICATION_FEATURE_COUNT + 4;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.parsley.dsl.model.Model <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.Model
   * @generated
   */
  EClass getModel();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.parsley.dsl.model.Model#getImportSection <em>Import Section</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Import Section</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.Model#getImportSection()
   * @see #getModel()
   * @generated
   */
  EReference getModel_ImportSection();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.parsley.dsl.model.Model#getModule <em>Module</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Module</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.Model#getModule()
   * @see #getModel()
   * @generated
   */
  EReference getModel_Module();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.parsley.dsl.model.Module <em>Module</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Module</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.Module
   * @generated
   */
  EClass getModule();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.dsl.model.Module#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.Module#getName()
   * @see #getModule()
   * @generated
   */
  EAttribute getModule_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.parsley.dsl.model.Module#getExtendsClause <em>Extends Clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Extends Clause</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.Module#getExtendsClause()
   * @see #getModule()
   * @generated
   */
  EReference getModule_ExtendsClause();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.parsley.dsl.model.Module#getLabelProvider <em>Label Provider</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Label Provider</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.Module#getLabelProvider()
   * @see #getModule()
   * @generated
   */
  EReference getModule_LabelProvider();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.parsley.dsl.model.Module#getPropertyDescriptionProvider <em>Property Description Provider</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Property Description Provider</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.Module#getPropertyDescriptionProvider()
   * @see #getModule()
   * @generated
   */
  EReference getModule_PropertyDescriptionProvider();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.parsley.dsl.model.Module#getFeaturesProvider <em>Features Provider</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Features Provider</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.Module#getFeaturesProvider()
   * @see #getModule()
   * @generated
   */
  EReference getModule_FeaturesProvider();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.parsley.dsl.model.Module#getFormControlFactory <em>Form Control Factory</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Form Control Factory</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.Module#getFormControlFactory()
   * @see #getModule()
   * @generated
   */
  EReference getModule_FormControlFactory();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.parsley.dsl.model.Module#getProposalCreator <em>Proposal Creator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Proposal Creator</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.Module#getProposalCreator()
   * @see #getModule()
   * @generated
   */
  EReference getModule_ProposalCreator();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.parsley.dsl.model.Module#getViewerContentProvider <em>Viewer Content Provider</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Viewer Content Provider</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.Module#getViewerContentProvider()
   * @see #getModule()
   * @generated
   */
  EReference getModule_ViewerContentProvider();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.parsley.dsl.model.Module#getParts <em>Parts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parts</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.Module#getParts()
   * @see #getModule()
   * @generated
   */
  EReference getModule_Parts();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.parsley.dsl.model.ExtendsClause <em>Extends Clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Extends Clause</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.ExtendsClause
   * @generated
   */
  EClass getExtendsClause();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.parsley.dsl.model.ExtendsClause#getSuperType <em>Super Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Super Type</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.ExtendsClause#getSuperType()
   * @see #getExtendsClause()
   * @generated
   */
  EReference getExtendsClause_SuperType();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.parsley.dsl.model.LabelProvider <em>Label Provider</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Label Provider</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.LabelProvider
   * @generated
   */
  EClass getLabelProvider();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.parsley.dsl.model.LabelProvider#getLabelSpecifications <em>Label Specifications</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Label Specifications</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.LabelProvider#getLabelSpecifications()
   * @see #getLabelProvider()
   * @generated
   */
  EReference getLabelProvider_LabelSpecifications();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.parsley.dsl.model.LabelProvider#getImageSpecifications <em>Image Specifications</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Image Specifications</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.LabelProvider#getImageSpecifications()
   * @see #getLabelProvider()
   * @generated
   */
  EReference getLabelProvider_ImageSpecifications();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.parsley.dsl.model.LabelSpecification <em>Label Specification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Label Specification</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.LabelSpecification
   * @generated
   */
  EClass getLabelSpecification();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.parsley.dsl.model.LabelSpecification#getParameterType <em>Parameter Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parameter Type</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.LabelSpecification#getParameterType()
   * @see #getLabelSpecification()
   * @generated
   */
  EReference getLabelSpecification_ParameterType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.dsl.model.LabelSpecification#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.LabelSpecification#getName()
   * @see #getLabelSpecification()
   * @generated
   */
  EAttribute getLabelSpecification_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.parsley.dsl.model.LabelSpecification#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.LabelSpecification#getExpression()
   * @see #getLabelSpecification()
   * @generated
   */
  EReference getLabelSpecification_Expression();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.parsley.dsl.model.PropertyDescriptionProvider <em>Property Description Provider</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property Description Provider</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.PropertyDescriptionProvider
   * @generated
   */
  EClass getPropertyDescriptionProvider();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.parsley.dsl.model.PropertyDescriptionProvider#getLabelSpecifications <em>Label Specifications</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Label Specifications</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.PropertyDescriptionProvider#getLabelSpecifications()
   * @see #getPropertyDescriptionProvider()
   * @generated
   */
  EReference getPropertyDescriptionProvider_LabelSpecifications();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.parsley.dsl.model.PropertyDescriptionSpecification <em>Property Description Specification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property Description Specification</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.PropertyDescriptionSpecification
   * @generated
   */
  EClass getPropertyDescriptionSpecification();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.parsley.dsl.model.PropertyDescriptionSpecification#getFeature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Feature</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.PropertyDescriptionSpecification#getFeature()
   * @see #getPropertyDescriptionSpecification()
   * @generated
   */
  EReference getPropertyDescriptionSpecification_Feature();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.parsley.dsl.model.PropertyDescriptionSpecification#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.PropertyDescriptionSpecification#getExpression()
   * @see #getPropertyDescriptionSpecification()
   * @generated
   */
  EReference getPropertyDescriptionSpecification_Expression();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.parsley.dsl.model.FeaturesProvider <em>Features Provider</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Features Provider</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.FeaturesProvider
   * @generated
   */
  EClass getFeaturesProvider();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.parsley.dsl.model.FeaturesProvider#getFeatureSpecifications <em>Feature Specifications</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Feature Specifications</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.FeaturesProvider#getFeatureSpecifications()
   * @see #getFeaturesProvider()
   * @generated
   */
  EReference getFeaturesProvider_FeatureSpecifications();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.parsley.dsl.model.FeatureSpecification <em>Feature Specification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature Specification</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.FeatureSpecification
   * @generated
   */
  EClass getFeatureSpecification();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.parsley.dsl.model.FeatureSpecification#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Features</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.FeatureSpecification#getFeatures()
   * @see #getFeatureSpecification()
   * @generated
   */
  EReference getFeatureSpecification_Features();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.parsley.dsl.model.FormControlFactory <em>Form Control Factory</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Form Control Factory</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.FormControlFactory
   * @generated
   */
  EClass getFormControlFactory();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.parsley.dsl.model.FormControlFactory#getControlSpecifications <em>Control Specifications</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Control Specifications</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.FormControlFactory#getControlSpecifications()
   * @see #getFormControlFactory()
   * @generated
   */
  EReference getFormControlFactory_ControlSpecifications();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.parsley.dsl.model.FormControlSpecification <em>Form Control Specification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Form Control Specification</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.FormControlSpecification
   * @generated
   */
  EClass getFormControlSpecification();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.parsley.dsl.model.FormControlSpecification#getFeature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Feature</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.FormControlSpecification#getFeature()
   * @see #getFormControlSpecification()
   * @generated
   */
  EReference getFormControlSpecification_Feature();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.parsley.dsl.model.FormControlSpecification#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.FormControlSpecification#getExpression()
   * @see #getFormControlSpecification()
   * @generated
   */
  EReference getFormControlSpecification_Expression();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.parsley.dsl.model.FormControlSpecification#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Target</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.FormControlSpecification#getTarget()
   * @see #getFormControlSpecification()
   * @generated
   */
  EReference getFormControlSpecification_Target();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.parsley.dsl.model.ProposalCreator <em>Proposal Creator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Proposal Creator</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.ProposalCreator
   * @generated
   */
  EClass getProposalCreator();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.parsley.dsl.model.ProposalCreator#getProposalsSpecifications <em>Proposals Specifications</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Proposals Specifications</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.ProposalCreator#getProposalsSpecifications()
   * @see #getProposalCreator()
   * @generated
   */
  EReference getProposalCreator_ProposalsSpecifications();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.parsley.dsl.model.ProposalSpecification <em>Proposal Specification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Proposal Specification</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.ProposalSpecification
   * @generated
   */
  EClass getProposalSpecification();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.parsley.dsl.model.ProposalSpecification#getFeature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Feature</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.ProposalSpecification#getFeature()
   * @see #getProposalSpecification()
   * @generated
   */
  EReference getProposalSpecification_Feature();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.parsley.dsl.model.ProposalSpecification#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.ProposalSpecification#getExpression()
   * @see #getProposalSpecification()
   * @generated
   */
  EReference getProposalSpecification_Expression();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.parsley.dsl.model.ViewerContentProvider <em>Viewer Content Provider</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Viewer Content Provider</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.ViewerContentProvider
   * @generated
   */
  EClass getViewerContentProvider();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.parsley.dsl.model.ViewerContentProvider#getChildrenSpecifications <em>Children Specifications</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Children Specifications</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.ViewerContentProvider#getChildrenSpecifications()
   * @see #getViewerContentProvider()
   * @generated
   */
  EReference getViewerContentProvider_ChildrenSpecifications();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.parsley.dsl.model.EmfFeatureAccess <em>Emf Feature Access</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Emf Feature Access</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.EmfFeatureAccess
   * @generated
   */
  EClass getEmfFeatureAccess();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.parsley.dsl.model.EmfFeatureAccess#getParameterType <em>Parameter Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parameter Type</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.EmfFeatureAccess#getParameterType()
   * @see #getEmfFeatureAccess()
   * @generated
   */
  EReference getEmfFeatureAccess_ParameterType();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.parsley.dsl.model.WithExtendsClause <em>With Extends Clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>With Extends Clause</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.WithExtendsClause
   * @generated
   */
  EClass getWithExtendsClause();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.parsley.dsl.model.PartSpecification <em>Part Specification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Part Specification</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.PartSpecification
   * @generated
   */
  EClass getPartSpecification();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.parsley.dsl.model.ViewSpecification <em>View Specification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>View Specification</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.ViewSpecification
   * @generated
   */
  EClass getViewSpecification();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.dsl.model.ViewSpecification#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.ViewSpecification#getId()
   * @see #getViewSpecification()
   * @generated
   */
  EAttribute getViewSpecification_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.dsl.model.ViewSpecification#getViewName <em>View Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>View Name</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.ViewSpecification#getViewName()
   * @see #getViewSpecification()
   * @generated
   */
  EAttribute getViewSpecification_ViewName();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.parsley.dsl.model.ViewSpecification#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.ViewSpecification#getType()
   * @see #getViewSpecification()
   * @generated
   */
  EReference getViewSpecification_Type();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.parsley.dsl.model.ViewSpecification#getCategory <em>Category</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Category</em>'.
   * @see org.eclipse.emf.parsley.dsl.model.ViewSpecification#getCategory()
   * @see #getViewSpecification()
   * @generated
   */
  EAttribute getViewSpecification_Category();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  ModelFactory getModelFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.eclipse.emf.parsley.dsl.model.impl.ModelImpl <em>Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.parsley.dsl.model.impl.ModelImpl
     * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getModel()
     * @generated
     */
    EClass MODEL = eINSTANCE.getModel();

    /**
     * The meta object literal for the '<em><b>Import Section</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL__IMPORT_SECTION = eINSTANCE.getModel_ImportSection();

    /**
     * The meta object literal for the '<em><b>Module</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL__MODULE = eINSTANCE.getModel_Module();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.parsley.dsl.model.impl.ModuleImpl <em>Module</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.parsley.dsl.model.impl.ModuleImpl
     * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getModule()
     * @generated
     */
    EClass MODULE = eINSTANCE.getModule();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MODULE__NAME = eINSTANCE.getModule_Name();

    /**
     * The meta object literal for the '<em><b>Extends Clause</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODULE__EXTENDS_CLAUSE = eINSTANCE.getModule_ExtendsClause();

    /**
     * The meta object literal for the '<em><b>Label Provider</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODULE__LABEL_PROVIDER = eINSTANCE.getModule_LabelProvider();

    /**
     * The meta object literal for the '<em><b>Property Description Provider</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODULE__PROPERTY_DESCRIPTION_PROVIDER = eINSTANCE.getModule_PropertyDescriptionProvider();

    /**
     * The meta object literal for the '<em><b>Features Provider</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODULE__FEATURES_PROVIDER = eINSTANCE.getModule_FeaturesProvider();

    /**
     * The meta object literal for the '<em><b>Form Control Factory</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODULE__FORM_CONTROL_FACTORY = eINSTANCE.getModule_FormControlFactory();

    /**
     * The meta object literal for the '<em><b>Proposal Creator</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODULE__PROPOSAL_CREATOR = eINSTANCE.getModule_ProposalCreator();

    /**
     * The meta object literal for the '<em><b>Viewer Content Provider</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODULE__VIEWER_CONTENT_PROVIDER = eINSTANCE.getModule_ViewerContentProvider();

    /**
     * The meta object literal for the '<em><b>Parts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODULE__PARTS = eINSTANCE.getModule_Parts();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.parsley.dsl.model.impl.ExtendsClauseImpl <em>Extends Clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.parsley.dsl.model.impl.ExtendsClauseImpl
     * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getExtendsClause()
     * @generated
     */
    EClass EXTENDS_CLAUSE = eINSTANCE.getExtendsClause();

    /**
     * The meta object literal for the '<em><b>Super Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXTENDS_CLAUSE__SUPER_TYPE = eINSTANCE.getExtendsClause_SuperType();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.parsley.dsl.model.impl.LabelProviderImpl <em>Label Provider</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.parsley.dsl.model.impl.LabelProviderImpl
     * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getLabelProvider()
     * @generated
     */
    EClass LABEL_PROVIDER = eINSTANCE.getLabelProvider();

    /**
     * The meta object literal for the '<em><b>Label Specifications</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LABEL_PROVIDER__LABEL_SPECIFICATIONS = eINSTANCE.getLabelProvider_LabelSpecifications();

    /**
     * The meta object literal for the '<em><b>Image Specifications</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LABEL_PROVIDER__IMAGE_SPECIFICATIONS = eINSTANCE.getLabelProvider_ImageSpecifications();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.parsley.dsl.model.impl.LabelSpecificationImpl <em>Label Specification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.parsley.dsl.model.impl.LabelSpecificationImpl
     * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getLabelSpecification()
     * @generated
     */
    EClass LABEL_SPECIFICATION = eINSTANCE.getLabelSpecification();

    /**
     * The meta object literal for the '<em><b>Parameter Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LABEL_SPECIFICATION__PARAMETER_TYPE = eINSTANCE.getLabelSpecification_ParameterType();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LABEL_SPECIFICATION__NAME = eINSTANCE.getLabelSpecification_Name();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LABEL_SPECIFICATION__EXPRESSION = eINSTANCE.getLabelSpecification_Expression();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.parsley.dsl.model.impl.PropertyDescriptionProviderImpl <em>Property Description Provider</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.parsley.dsl.model.impl.PropertyDescriptionProviderImpl
     * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getPropertyDescriptionProvider()
     * @generated
     */
    EClass PROPERTY_DESCRIPTION_PROVIDER = eINSTANCE.getPropertyDescriptionProvider();

    /**
     * The meta object literal for the '<em><b>Label Specifications</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROPERTY_DESCRIPTION_PROVIDER__LABEL_SPECIFICATIONS = eINSTANCE.getPropertyDescriptionProvider_LabelSpecifications();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.parsley.dsl.model.impl.PropertyDescriptionSpecificationImpl <em>Property Description Specification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.parsley.dsl.model.impl.PropertyDescriptionSpecificationImpl
     * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getPropertyDescriptionSpecification()
     * @generated
     */
    EClass PROPERTY_DESCRIPTION_SPECIFICATION = eINSTANCE.getPropertyDescriptionSpecification();

    /**
     * The meta object literal for the '<em><b>Feature</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROPERTY_DESCRIPTION_SPECIFICATION__FEATURE = eINSTANCE.getPropertyDescriptionSpecification_Feature();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROPERTY_DESCRIPTION_SPECIFICATION__EXPRESSION = eINSTANCE.getPropertyDescriptionSpecification_Expression();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.parsley.dsl.model.impl.FeaturesProviderImpl <em>Features Provider</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.parsley.dsl.model.impl.FeaturesProviderImpl
     * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getFeaturesProvider()
     * @generated
     */
    EClass FEATURES_PROVIDER = eINSTANCE.getFeaturesProvider();

    /**
     * The meta object literal for the '<em><b>Feature Specifications</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURES_PROVIDER__FEATURE_SPECIFICATIONS = eINSTANCE.getFeaturesProvider_FeatureSpecifications();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.parsley.dsl.model.impl.FeatureSpecificationImpl <em>Feature Specification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.parsley.dsl.model.impl.FeatureSpecificationImpl
     * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getFeatureSpecification()
     * @generated
     */
    EClass FEATURE_SPECIFICATION = eINSTANCE.getFeatureSpecification();

    /**
     * The meta object literal for the '<em><b>Features</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE_SPECIFICATION__FEATURES = eINSTANCE.getFeatureSpecification_Features();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.parsley.dsl.model.impl.FormControlFactoryImpl <em>Form Control Factory</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.parsley.dsl.model.impl.FormControlFactoryImpl
     * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getFormControlFactory()
     * @generated
     */
    EClass FORM_CONTROL_FACTORY = eINSTANCE.getFormControlFactory();

    /**
     * The meta object literal for the '<em><b>Control Specifications</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FORM_CONTROL_FACTORY__CONTROL_SPECIFICATIONS = eINSTANCE.getFormControlFactory_ControlSpecifications();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.parsley.dsl.model.impl.FormControlSpecificationImpl <em>Form Control Specification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.parsley.dsl.model.impl.FormControlSpecificationImpl
     * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getFormControlSpecification()
     * @generated
     */
    EClass FORM_CONTROL_SPECIFICATION = eINSTANCE.getFormControlSpecification();

    /**
     * The meta object literal for the '<em><b>Feature</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FORM_CONTROL_SPECIFICATION__FEATURE = eINSTANCE.getFormControlSpecification_Feature();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FORM_CONTROL_SPECIFICATION__EXPRESSION = eINSTANCE.getFormControlSpecification_Expression();

    /**
     * The meta object literal for the '<em><b>Target</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FORM_CONTROL_SPECIFICATION__TARGET = eINSTANCE.getFormControlSpecification_Target();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.parsley.dsl.model.impl.ProposalCreatorImpl <em>Proposal Creator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.parsley.dsl.model.impl.ProposalCreatorImpl
     * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getProposalCreator()
     * @generated
     */
    EClass PROPOSAL_CREATOR = eINSTANCE.getProposalCreator();

    /**
     * The meta object literal for the '<em><b>Proposals Specifications</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROPOSAL_CREATOR__PROPOSALS_SPECIFICATIONS = eINSTANCE.getProposalCreator_ProposalsSpecifications();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.parsley.dsl.model.impl.ProposalSpecificationImpl <em>Proposal Specification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.parsley.dsl.model.impl.ProposalSpecificationImpl
     * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getProposalSpecification()
     * @generated
     */
    EClass PROPOSAL_SPECIFICATION = eINSTANCE.getProposalSpecification();

    /**
     * The meta object literal for the '<em><b>Feature</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROPOSAL_SPECIFICATION__FEATURE = eINSTANCE.getProposalSpecification_Feature();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROPOSAL_SPECIFICATION__EXPRESSION = eINSTANCE.getProposalSpecification_Expression();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.parsley.dsl.model.impl.ViewerContentProviderImpl <em>Viewer Content Provider</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.parsley.dsl.model.impl.ViewerContentProviderImpl
     * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getViewerContentProvider()
     * @generated
     */
    EClass VIEWER_CONTENT_PROVIDER = eINSTANCE.getViewerContentProvider();

    /**
     * The meta object literal for the '<em><b>Children Specifications</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VIEWER_CONTENT_PROVIDER__CHILDREN_SPECIFICATIONS = eINSTANCE.getViewerContentProvider_ChildrenSpecifications();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.parsley.dsl.model.impl.EmfFeatureAccessImpl <em>Emf Feature Access</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.parsley.dsl.model.impl.EmfFeatureAccessImpl
     * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getEmfFeatureAccess()
     * @generated
     */
    EClass EMF_FEATURE_ACCESS = eINSTANCE.getEmfFeatureAccess();

    /**
     * The meta object literal for the '<em><b>Parameter Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMF_FEATURE_ACCESS__PARAMETER_TYPE = eINSTANCE.getEmfFeatureAccess_ParameterType();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.parsley.dsl.model.impl.WithExtendsClauseImpl <em>With Extends Clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.parsley.dsl.model.impl.WithExtendsClauseImpl
     * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getWithExtendsClause()
     * @generated
     */
    EClass WITH_EXTENDS_CLAUSE = eINSTANCE.getWithExtendsClause();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.parsley.dsl.model.impl.PartSpecificationImpl <em>Part Specification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.parsley.dsl.model.impl.PartSpecificationImpl
     * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getPartSpecification()
     * @generated
     */
    EClass PART_SPECIFICATION = eINSTANCE.getPartSpecification();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.parsley.dsl.model.impl.ViewSpecificationImpl <em>View Specification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.parsley.dsl.model.impl.ViewSpecificationImpl
     * @see org.eclipse.emf.parsley.dsl.model.impl.ModelPackageImpl#getViewSpecification()
     * @generated
     */
    EClass VIEW_SPECIFICATION = eINSTANCE.getViewSpecification();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VIEW_SPECIFICATION__ID = eINSTANCE.getViewSpecification_Id();

    /**
     * The meta object literal for the '<em><b>View Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VIEW_SPECIFICATION__VIEW_NAME = eINSTANCE.getViewSpecification_ViewName();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VIEW_SPECIFICATION__TYPE = eINSTANCE.getViewSpecification_Type();

    /**
     * The meta object literal for the '<em><b>Category</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VIEW_SPECIFICATION__CATEGORY = eINSTANCE.getViewSpecification_Category();

  }

} //ModelPackage
