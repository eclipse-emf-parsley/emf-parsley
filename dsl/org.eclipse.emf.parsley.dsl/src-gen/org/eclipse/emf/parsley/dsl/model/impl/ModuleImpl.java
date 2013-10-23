/**
 */
package org.eclipse.emf.parsley.dsl.model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.parsley.dsl.model.DialogControlFactory;
import org.eclipse.emf.parsley.dsl.model.DialogFeatureCaptionProvider;
import org.eclipse.emf.parsley.dsl.model.ExtendsClause;
import org.eclipse.emf.parsley.dsl.model.FeatureCaptionProvider;
import org.eclipse.emf.parsley.dsl.model.FeaturesProvider;
import org.eclipse.emf.parsley.dsl.model.FormControlFactory;
import org.eclipse.emf.parsley.dsl.model.FormFeatureCaptionProvider;
import org.eclipse.emf.parsley.dsl.model.LabelProvider;
import org.eclipse.emf.parsley.dsl.model.ModelPackage;
import org.eclipse.emf.parsley.dsl.model.Module;
import org.eclipse.emf.parsley.dsl.model.PartsSpecifications;
import org.eclipse.emf.parsley.dsl.model.ProposalCreator;
import org.eclipse.emf.parsley.dsl.model.ViewerContentProvider;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Module</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.ModuleImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.ModuleImpl#getExtendsClause <em>Extends Clause</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.ModuleImpl#getLabelProvider <em>Label Provider</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.ModuleImpl#getFeatureCaptionProvider <em>Feature Caption Provider</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.ModuleImpl#getFormFeatureCaptionProvider <em>Form Feature Caption Provider</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.ModuleImpl#getDialogFeatureCaptionProvider <em>Dialog Feature Caption Provider</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.ModuleImpl#getFeaturesProvider <em>Features Provider</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.ModuleImpl#getFormControlFactory <em>Form Control Factory</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.ModuleImpl#getDialogControlFactory <em>Dialog Control Factory</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.ModuleImpl#getProposalCreator <em>Proposal Creator</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.ModuleImpl#getViewerContentProvider <em>Viewer Content Provider</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.ModuleImpl#getPartsSpecifications <em>Parts Specifications</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModuleImpl extends WithExtendsClauseImpl implements Module
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getExtendsClause() <em>Extends Clause</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExtendsClause()
   * @generated
   * @ordered
   */
  protected ExtendsClause extendsClause;

  /**
   * The cached value of the '{@link #getLabelProvider() <em>Label Provider</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLabelProvider()
   * @generated
   * @ordered
   */
  protected LabelProvider labelProvider;

  /**
   * The cached value of the '{@link #getFeatureCaptionProvider() <em>Feature Caption Provider</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeatureCaptionProvider()
   * @generated
   * @ordered
   */
  protected FeatureCaptionProvider featureCaptionProvider;

  /**
   * The cached value of the '{@link #getFormFeatureCaptionProvider() <em>Form Feature Caption Provider</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFormFeatureCaptionProvider()
   * @generated
   * @ordered
   */
  protected FormFeatureCaptionProvider formFeatureCaptionProvider;

  /**
   * The cached value of the '{@link #getDialogFeatureCaptionProvider() <em>Dialog Feature Caption Provider</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDialogFeatureCaptionProvider()
   * @generated
   * @ordered
   */
  protected DialogFeatureCaptionProvider dialogFeatureCaptionProvider;

  /**
   * The cached value of the '{@link #getFeaturesProvider() <em>Features Provider</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeaturesProvider()
   * @generated
   * @ordered
   */
  protected FeaturesProvider featuresProvider;

  /**
   * The cached value of the '{@link #getFormControlFactory() <em>Form Control Factory</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFormControlFactory()
   * @generated
   * @ordered
   */
  protected FormControlFactory formControlFactory;

  /**
   * The cached value of the '{@link #getDialogControlFactory() <em>Dialog Control Factory</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDialogControlFactory()
   * @generated
   * @ordered
   */
  protected DialogControlFactory dialogControlFactory;

  /**
   * The cached value of the '{@link #getProposalCreator() <em>Proposal Creator</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProposalCreator()
   * @generated
   * @ordered
   */
  protected ProposalCreator proposalCreator;

  /**
   * The cached value of the '{@link #getViewerContentProvider() <em>Viewer Content Provider</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getViewerContentProvider()
   * @generated
   * @ordered
   */
  protected ViewerContentProvider viewerContentProvider;

  /**
   * The cached value of the '{@link #getPartsSpecifications() <em>Parts Specifications</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPartsSpecifications()
   * @generated
   * @ordered
   */
  protected PartsSpecifications partsSpecifications;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ModuleImpl()
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
    return ModelPackage.Literals.MODULE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExtendsClause getExtendsClause()
  {
    return extendsClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExtendsClause(ExtendsClause newExtendsClause, NotificationChain msgs)
  {
    ExtendsClause oldExtendsClause = extendsClause;
    extendsClause = newExtendsClause;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__EXTENDS_CLAUSE, oldExtendsClause, newExtendsClause);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExtendsClause(ExtendsClause newExtendsClause)
  {
    if (newExtendsClause != extendsClause)
    {
      NotificationChain msgs = null;
      if (extendsClause != null)
        msgs = ((InternalEObject)extendsClause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.MODULE__EXTENDS_CLAUSE, null, msgs);
      if (newExtendsClause != null)
        msgs = ((InternalEObject)newExtendsClause).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.MODULE__EXTENDS_CLAUSE, null, msgs);
      msgs = basicSetExtendsClause(newExtendsClause, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__EXTENDS_CLAUSE, newExtendsClause, newExtendsClause));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LabelProvider getLabelProvider()
  {
    return labelProvider;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLabelProvider(LabelProvider newLabelProvider, NotificationChain msgs)
  {
    LabelProvider oldLabelProvider = labelProvider;
    labelProvider = newLabelProvider;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__LABEL_PROVIDER, oldLabelProvider, newLabelProvider);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLabelProvider(LabelProvider newLabelProvider)
  {
    if (newLabelProvider != labelProvider)
    {
      NotificationChain msgs = null;
      if (labelProvider != null)
        msgs = ((InternalEObject)labelProvider).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.MODULE__LABEL_PROVIDER, null, msgs);
      if (newLabelProvider != null)
        msgs = ((InternalEObject)newLabelProvider).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.MODULE__LABEL_PROVIDER, null, msgs);
      msgs = basicSetLabelProvider(newLabelProvider, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__LABEL_PROVIDER, newLabelProvider, newLabelProvider));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureCaptionProvider getFeatureCaptionProvider()
  {
    return featureCaptionProvider;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFeatureCaptionProvider(FeatureCaptionProvider newFeatureCaptionProvider, NotificationChain msgs)
  {
    FeatureCaptionProvider oldFeatureCaptionProvider = featureCaptionProvider;
    featureCaptionProvider = newFeatureCaptionProvider;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__FEATURE_CAPTION_PROVIDER, oldFeatureCaptionProvider, newFeatureCaptionProvider);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFeatureCaptionProvider(FeatureCaptionProvider newFeatureCaptionProvider)
  {
    if (newFeatureCaptionProvider != featureCaptionProvider)
    {
      NotificationChain msgs = null;
      if (featureCaptionProvider != null)
        msgs = ((InternalEObject)featureCaptionProvider).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.MODULE__FEATURE_CAPTION_PROVIDER, null, msgs);
      if (newFeatureCaptionProvider != null)
        msgs = ((InternalEObject)newFeatureCaptionProvider).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.MODULE__FEATURE_CAPTION_PROVIDER, null, msgs);
      msgs = basicSetFeatureCaptionProvider(newFeatureCaptionProvider, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__FEATURE_CAPTION_PROVIDER, newFeatureCaptionProvider, newFeatureCaptionProvider));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FormFeatureCaptionProvider getFormFeatureCaptionProvider()
  {
    return formFeatureCaptionProvider;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFormFeatureCaptionProvider(FormFeatureCaptionProvider newFormFeatureCaptionProvider, NotificationChain msgs)
  {
    FormFeatureCaptionProvider oldFormFeatureCaptionProvider = formFeatureCaptionProvider;
    formFeatureCaptionProvider = newFormFeatureCaptionProvider;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__FORM_FEATURE_CAPTION_PROVIDER, oldFormFeatureCaptionProvider, newFormFeatureCaptionProvider);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFormFeatureCaptionProvider(FormFeatureCaptionProvider newFormFeatureCaptionProvider)
  {
    if (newFormFeatureCaptionProvider != formFeatureCaptionProvider)
    {
      NotificationChain msgs = null;
      if (formFeatureCaptionProvider != null)
        msgs = ((InternalEObject)formFeatureCaptionProvider).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.MODULE__FORM_FEATURE_CAPTION_PROVIDER, null, msgs);
      if (newFormFeatureCaptionProvider != null)
        msgs = ((InternalEObject)newFormFeatureCaptionProvider).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.MODULE__FORM_FEATURE_CAPTION_PROVIDER, null, msgs);
      msgs = basicSetFormFeatureCaptionProvider(newFormFeatureCaptionProvider, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__FORM_FEATURE_CAPTION_PROVIDER, newFormFeatureCaptionProvider, newFormFeatureCaptionProvider));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DialogFeatureCaptionProvider getDialogFeatureCaptionProvider()
  {
    return dialogFeatureCaptionProvider;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDialogFeatureCaptionProvider(DialogFeatureCaptionProvider newDialogFeatureCaptionProvider, NotificationChain msgs)
  {
    DialogFeatureCaptionProvider oldDialogFeatureCaptionProvider = dialogFeatureCaptionProvider;
    dialogFeatureCaptionProvider = newDialogFeatureCaptionProvider;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__DIALOG_FEATURE_CAPTION_PROVIDER, oldDialogFeatureCaptionProvider, newDialogFeatureCaptionProvider);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDialogFeatureCaptionProvider(DialogFeatureCaptionProvider newDialogFeatureCaptionProvider)
  {
    if (newDialogFeatureCaptionProvider != dialogFeatureCaptionProvider)
    {
      NotificationChain msgs = null;
      if (dialogFeatureCaptionProvider != null)
        msgs = ((InternalEObject)dialogFeatureCaptionProvider).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.MODULE__DIALOG_FEATURE_CAPTION_PROVIDER, null, msgs);
      if (newDialogFeatureCaptionProvider != null)
        msgs = ((InternalEObject)newDialogFeatureCaptionProvider).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.MODULE__DIALOG_FEATURE_CAPTION_PROVIDER, null, msgs);
      msgs = basicSetDialogFeatureCaptionProvider(newDialogFeatureCaptionProvider, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__DIALOG_FEATURE_CAPTION_PROVIDER, newDialogFeatureCaptionProvider, newDialogFeatureCaptionProvider));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeaturesProvider getFeaturesProvider()
  {
    return featuresProvider;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFeaturesProvider(FeaturesProvider newFeaturesProvider, NotificationChain msgs)
  {
    FeaturesProvider oldFeaturesProvider = featuresProvider;
    featuresProvider = newFeaturesProvider;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__FEATURES_PROVIDER, oldFeaturesProvider, newFeaturesProvider);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFeaturesProvider(FeaturesProvider newFeaturesProvider)
  {
    if (newFeaturesProvider != featuresProvider)
    {
      NotificationChain msgs = null;
      if (featuresProvider != null)
        msgs = ((InternalEObject)featuresProvider).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.MODULE__FEATURES_PROVIDER, null, msgs);
      if (newFeaturesProvider != null)
        msgs = ((InternalEObject)newFeaturesProvider).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.MODULE__FEATURES_PROVIDER, null, msgs);
      msgs = basicSetFeaturesProvider(newFeaturesProvider, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__FEATURES_PROVIDER, newFeaturesProvider, newFeaturesProvider));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FormControlFactory getFormControlFactory()
  {
    return formControlFactory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFormControlFactory(FormControlFactory newFormControlFactory, NotificationChain msgs)
  {
    FormControlFactory oldFormControlFactory = formControlFactory;
    formControlFactory = newFormControlFactory;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__FORM_CONTROL_FACTORY, oldFormControlFactory, newFormControlFactory);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFormControlFactory(FormControlFactory newFormControlFactory)
  {
    if (newFormControlFactory != formControlFactory)
    {
      NotificationChain msgs = null;
      if (formControlFactory != null)
        msgs = ((InternalEObject)formControlFactory).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.MODULE__FORM_CONTROL_FACTORY, null, msgs);
      if (newFormControlFactory != null)
        msgs = ((InternalEObject)newFormControlFactory).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.MODULE__FORM_CONTROL_FACTORY, null, msgs);
      msgs = basicSetFormControlFactory(newFormControlFactory, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__FORM_CONTROL_FACTORY, newFormControlFactory, newFormControlFactory));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DialogControlFactory getDialogControlFactory()
  {
    return dialogControlFactory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDialogControlFactory(DialogControlFactory newDialogControlFactory, NotificationChain msgs)
  {
    DialogControlFactory oldDialogControlFactory = dialogControlFactory;
    dialogControlFactory = newDialogControlFactory;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__DIALOG_CONTROL_FACTORY, oldDialogControlFactory, newDialogControlFactory);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDialogControlFactory(DialogControlFactory newDialogControlFactory)
  {
    if (newDialogControlFactory != dialogControlFactory)
    {
      NotificationChain msgs = null;
      if (dialogControlFactory != null)
        msgs = ((InternalEObject)dialogControlFactory).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.MODULE__DIALOG_CONTROL_FACTORY, null, msgs);
      if (newDialogControlFactory != null)
        msgs = ((InternalEObject)newDialogControlFactory).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.MODULE__DIALOG_CONTROL_FACTORY, null, msgs);
      msgs = basicSetDialogControlFactory(newDialogControlFactory, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__DIALOG_CONTROL_FACTORY, newDialogControlFactory, newDialogControlFactory));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ProposalCreator getProposalCreator()
  {
    return proposalCreator;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetProposalCreator(ProposalCreator newProposalCreator, NotificationChain msgs)
  {
    ProposalCreator oldProposalCreator = proposalCreator;
    proposalCreator = newProposalCreator;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__PROPOSAL_CREATOR, oldProposalCreator, newProposalCreator);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setProposalCreator(ProposalCreator newProposalCreator)
  {
    if (newProposalCreator != proposalCreator)
    {
      NotificationChain msgs = null;
      if (proposalCreator != null)
        msgs = ((InternalEObject)proposalCreator).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.MODULE__PROPOSAL_CREATOR, null, msgs);
      if (newProposalCreator != null)
        msgs = ((InternalEObject)newProposalCreator).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.MODULE__PROPOSAL_CREATOR, null, msgs);
      msgs = basicSetProposalCreator(newProposalCreator, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__PROPOSAL_CREATOR, newProposalCreator, newProposalCreator));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ViewerContentProvider getViewerContentProvider()
  {
    return viewerContentProvider;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetViewerContentProvider(ViewerContentProvider newViewerContentProvider, NotificationChain msgs)
  {
    ViewerContentProvider oldViewerContentProvider = viewerContentProvider;
    viewerContentProvider = newViewerContentProvider;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__VIEWER_CONTENT_PROVIDER, oldViewerContentProvider, newViewerContentProvider);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setViewerContentProvider(ViewerContentProvider newViewerContentProvider)
  {
    if (newViewerContentProvider != viewerContentProvider)
    {
      NotificationChain msgs = null;
      if (viewerContentProvider != null)
        msgs = ((InternalEObject)viewerContentProvider).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.MODULE__VIEWER_CONTENT_PROVIDER, null, msgs);
      if (newViewerContentProvider != null)
        msgs = ((InternalEObject)newViewerContentProvider).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.MODULE__VIEWER_CONTENT_PROVIDER, null, msgs);
      msgs = basicSetViewerContentProvider(newViewerContentProvider, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__VIEWER_CONTENT_PROVIDER, newViewerContentProvider, newViewerContentProvider));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PartsSpecifications getPartsSpecifications()
  {
    return partsSpecifications;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPartsSpecifications(PartsSpecifications newPartsSpecifications, NotificationChain msgs)
  {
    PartsSpecifications oldPartsSpecifications = partsSpecifications;
    partsSpecifications = newPartsSpecifications;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__PARTS_SPECIFICATIONS, oldPartsSpecifications, newPartsSpecifications);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPartsSpecifications(PartsSpecifications newPartsSpecifications)
  {
    if (newPartsSpecifications != partsSpecifications)
    {
      NotificationChain msgs = null;
      if (partsSpecifications != null)
        msgs = ((InternalEObject)partsSpecifications).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.MODULE__PARTS_SPECIFICATIONS, null, msgs);
      if (newPartsSpecifications != null)
        msgs = ((InternalEObject)newPartsSpecifications).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.MODULE__PARTS_SPECIFICATIONS, null, msgs);
      msgs = basicSetPartsSpecifications(newPartsSpecifications, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MODULE__PARTS_SPECIFICATIONS, newPartsSpecifications, newPartsSpecifications));
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
      case ModelPackage.MODULE__EXTENDS_CLAUSE:
        return basicSetExtendsClause(null, msgs);
      case ModelPackage.MODULE__LABEL_PROVIDER:
        return basicSetLabelProvider(null, msgs);
      case ModelPackage.MODULE__FEATURE_CAPTION_PROVIDER:
        return basicSetFeatureCaptionProvider(null, msgs);
      case ModelPackage.MODULE__FORM_FEATURE_CAPTION_PROVIDER:
        return basicSetFormFeatureCaptionProvider(null, msgs);
      case ModelPackage.MODULE__DIALOG_FEATURE_CAPTION_PROVIDER:
        return basicSetDialogFeatureCaptionProvider(null, msgs);
      case ModelPackage.MODULE__FEATURES_PROVIDER:
        return basicSetFeaturesProvider(null, msgs);
      case ModelPackage.MODULE__FORM_CONTROL_FACTORY:
        return basicSetFormControlFactory(null, msgs);
      case ModelPackage.MODULE__DIALOG_CONTROL_FACTORY:
        return basicSetDialogControlFactory(null, msgs);
      case ModelPackage.MODULE__PROPOSAL_CREATOR:
        return basicSetProposalCreator(null, msgs);
      case ModelPackage.MODULE__VIEWER_CONTENT_PROVIDER:
        return basicSetViewerContentProvider(null, msgs);
      case ModelPackage.MODULE__PARTS_SPECIFICATIONS:
        return basicSetPartsSpecifications(null, msgs);
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
      case ModelPackage.MODULE__NAME:
        return getName();
      case ModelPackage.MODULE__EXTENDS_CLAUSE:
        return getExtendsClause();
      case ModelPackage.MODULE__LABEL_PROVIDER:
        return getLabelProvider();
      case ModelPackage.MODULE__FEATURE_CAPTION_PROVIDER:
        return getFeatureCaptionProvider();
      case ModelPackage.MODULE__FORM_FEATURE_CAPTION_PROVIDER:
        return getFormFeatureCaptionProvider();
      case ModelPackage.MODULE__DIALOG_FEATURE_CAPTION_PROVIDER:
        return getDialogFeatureCaptionProvider();
      case ModelPackage.MODULE__FEATURES_PROVIDER:
        return getFeaturesProvider();
      case ModelPackage.MODULE__FORM_CONTROL_FACTORY:
        return getFormControlFactory();
      case ModelPackage.MODULE__DIALOG_CONTROL_FACTORY:
        return getDialogControlFactory();
      case ModelPackage.MODULE__PROPOSAL_CREATOR:
        return getProposalCreator();
      case ModelPackage.MODULE__VIEWER_CONTENT_PROVIDER:
        return getViewerContentProvider();
      case ModelPackage.MODULE__PARTS_SPECIFICATIONS:
        return getPartsSpecifications();
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
      case ModelPackage.MODULE__NAME:
        setName((String)newValue);
        return;
      case ModelPackage.MODULE__EXTENDS_CLAUSE:
        setExtendsClause((ExtendsClause)newValue);
        return;
      case ModelPackage.MODULE__LABEL_PROVIDER:
        setLabelProvider((LabelProvider)newValue);
        return;
      case ModelPackage.MODULE__FEATURE_CAPTION_PROVIDER:
        setFeatureCaptionProvider((FeatureCaptionProvider)newValue);
        return;
      case ModelPackage.MODULE__FORM_FEATURE_CAPTION_PROVIDER:
        setFormFeatureCaptionProvider((FormFeatureCaptionProvider)newValue);
        return;
      case ModelPackage.MODULE__DIALOG_FEATURE_CAPTION_PROVIDER:
        setDialogFeatureCaptionProvider((DialogFeatureCaptionProvider)newValue);
        return;
      case ModelPackage.MODULE__FEATURES_PROVIDER:
        setFeaturesProvider((FeaturesProvider)newValue);
        return;
      case ModelPackage.MODULE__FORM_CONTROL_FACTORY:
        setFormControlFactory((FormControlFactory)newValue);
        return;
      case ModelPackage.MODULE__DIALOG_CONTROL_FACTORY:
        setDialogControlFactory((DialogControlFactory)newValue);
        return;
      case ModelPackage.MODULE__PROPOSAL_CREATOR:
        setProposalCreator((ProposalCreator)newValue);
        return;
      case ModelPackage.MODULE__VIEWER_CONTENT_PROVIDER:
        setViewerContentProvider((ViewerContentProvider)newValue);
        return;
      case ModelPackage.MODULE__PARTS_SPECIFICATIONS:
        setPartsSpecifications((PartsSpecifications)newValue);
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
      case ModelPackage.MODULE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case ModelPackage.MODULE__EXTENDS_CLAUSE:
        setExtendsClause((ExtendsClause)null);
        return;
      case ModelPackage.MODULE__LABEL_PROVIDER:
        setLabelProvider((LabelProvider)null);
        return;
      case ModelPackage.MODULE__FEATURE_CAPTION_PROVIDER:
        setFeatureCaptionProvider((FeatureCaptionProvider)null);
        return;
      case ModelPackage.MODULE__FORM_FEATURE_CAPTION_PROVIDER:
        setFormFeatureCaptionProvider((FormFeatureCaptionProvider)null);
        return;
      case ModelPackage.MODULE__DIALOG_FEATURE_CAPTION_PROVIDER:
        setDialogFeatureCaptionProvider((DialogFeatureCaptionProvider)null);
        return;
      case ModelPackage.MODULE__FEATURES_PROVIDER:
        setFeaturesProvider((FeaturesProvider)null);
        return;
      case ModelPackage.MODULE__FORM_CONTROL_FACTORY:
        setFormControlFactory((FormControlFactory)null);
        return;
      case ModelPackage.MODULE__DIALOG_CONTROL_FACTORY:
        setDialogControlFactory((DialogControlFactory)null);
        return;
      case ModelPackage.MODULE__PROPOSAL_CREATOR:
        setProposalCreator((ProposalCreator)null);
        return;
      case ModelPackage.MODULE__VIEWER_CONTENT_PROVIDER:
        setViewerContentProvider((ViewerContentProvider)null);
        return;
      case ModelPackage.MODULE__PARTS_SPECIFICATIONS:
        setPartsSpecifications((PartsSpecifications)null);
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
      case ModelPackage.MODULE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case ModelPackage.MODULE__EXTENDS_CLAUSE:
        return extendsClause != null;
      case ModelPackage.MODULE__LABEL_PROVIDER:
        return labelProvider != null;
      case ModelPackage.MODULE__FEATURE_CAPTION_PROVIDER:
        return featureCaptionProvider != null;
      case ModelPackage.MODULE__FORM_FEATURE_CAPTION_PROVIDER:
        return formFeatureCaptionProvider != null;
      case ModelPackage.MODULE__DIALOG_FEATURE_CAPTION_PROVIDER:
        return dialogFeatureCaptionProvider != null;
      case ModelPackage.MODULE__FEATURES_PROVIDER:
        return featuresProvider != null;
      case ModelPackage.MODULE__FORM_CONTROL_FACTORY:
        return formControlFactory != null;
      case ModelPackage.MODULE__DIALOG_CONTROL_FACTORY:
        return dialogControlFactory != null;
      case ModelPackage.MODULE__PROPOSAL_CREATOR:
        return proposalCreator != null;
      case ModelPackage.MODULE__VIEWER_CONTENT_PROVIDER:
        return viewerContentProvider != null;
      case ModelPackage.MODULE__PARTS_SPECIFICATIONS:
        return partsSpecifications != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //ModuleImpl
