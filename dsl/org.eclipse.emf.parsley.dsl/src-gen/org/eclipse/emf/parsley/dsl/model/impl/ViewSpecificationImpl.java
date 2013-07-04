/**
 */
package org.eclipse.emf.parsley.dsl.model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.parsley.dsl.model.ModelPackage;
import org.eclipse.emf.parsley.dsl.model.ViewSpecification;

import org.eclipse.xtext.common.types.JvmTypeReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>View Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.ViewSpecificationImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.ViewSpecificationImpl#getViewName <em>View Name</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.ViewSpecificationImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.emf.parsley.dsl.model.impl.ViewSpecificationImpl#getCategory <em>Category</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ViewSpecificationImpl extends PartSpecificationImpl implements ViewSpecification
{
  /**
   * The default value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected static final String ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected String id = ID_EDEFAULT;

  /**
   * The default value of the '{@link #getViewName() <em>View Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getViewName()
   * @generated
   * @ordered
   */
  protected static final String VIEW_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getViewName() <em>View Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getViewName()
   * @generated
   * @ordered
   */
  protected String viewName = VIEW_NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected JvmTypeReference type;

  /**
   * The default value of the '{@link #getCategory() <em>Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCategory()
   * @generated
   * @ordered
   */
  protected static final String CATEGORY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCategory() <em>Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCategory()
   * @generated
   * @ordered
   */
  protected String category = CATEGORY_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ViewSpecificationImpl()
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
    return ModelPackage.Literals.VIEW_SPECIFICATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getId()
  {
    return id;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setId(String newId)
  {
    String oldId = id;
    id = newId;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.VIEW_SPECIFICATION__ID, oldId, id));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getViewName()
  {
    return viewName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setViewName(String newViewName)
  {
    String oldViewName = viewName;
    viewName = newViewName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.VIEW_SPECIFICATION__VIEW_NAME, oldViewName, viewName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmTypeReference getType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetType(JvmTypeReference newType, NotificationChain msgs)
  {
    JvmTypeReference oldType = type;
    type = newType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.VIEW_SPECIFICATION__TYPE, oldType, newType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(JvmTypeReference newType)
  {
    if (newType != type)
    {
      NotificationChain msgs = null;
      if (type != null)
        msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.VIEW_SPECIFICATION__TYPE, null, msgs);
      if (newType != null)
        msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.VIEW_SPECIFICATION__TYPE, null, msgs);
      msgs = basicSetType(newType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.VIEW_SPECIFICATION__TYPE, newType, newType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCategory()
  {
    return category;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCategory(String newCategory)
  {
    String oldCategory = category;
    category = newCategory;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.VIEW_SPECIFICATION__CATEGORY, oldCategory, category));
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
      case ModelPackage.VIEW_SPECIFICATION__TYPE:
        return basicSetType(null, msgs);
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
      case ModelPackage.VIEW_SPECIFICATION__ID:
        return getId();
      case ModelPackage.VIEW_SPECIFICATION__VIEW_NAME:
        return getViewName();
      case ModelPackage.VIEW_SPECIFICATION__TYPE:
        return getType();
      case ModelPackage.VIEW_SPECIFICATION__CATEGORY:
        return getCategory();
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
      case ModelPackage.VIEW_SPECIFICATION__ID:
        setId((String)newValue);
        return;
      case ModelPackage.VIEW_SPECIFICATION__VIEW_NAME:
        setViewName((String)newValue);
        return;
      case ModelPackage.VIEW_SPECIFICATION__TYPE:
        setType((JvmTypeReference)newValue);
        return;
      case ModelPackage.VIEW_SPECIFICATION__CATEGORY:
        setCategory((String)newValue);
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
      case ModelPackage.VIEW_SPECIFICATION__ID:
        setId(ID_EDEFAULT);
        return;
      case ModelPackage.VIEW_SPECIFICATION__VIEW_NAME:
        setViewName(VIEW_NAME_EDEFAULT);
        return;
      case ModelPackage.VIEW_SPECIFICATION__TYPE:
        setType((JvmTypeReference)null);
        return;
      case ModelPackage.VIEW_SPECIFICATION__CATEGORY:
        setCategory(CATEGORY_EDEFAULT);
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
      case ModelPackage.VIEW_SPECIFICATION__ID:
        return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case ModelPackage.VIEW_SPECIFICATION__VIEW_NAME:
        return VIEW_NAME_EDEFAULT == null ? viewName != null : !VIEW_NAME_EDEFAULT.equals(viewName);
      case ModelPackage.VIEW_SPECIFICATION__TYPE:
        return type != null;
      case ModelPackage.VIEW_SPECIFICATION__CATEGORY:
        return CATEGORY_EDEFAULT == null ? category != null : !CATEGORY_EDEFAULT.equals(category);
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
    result.append(" (id: ");
    result.append(id);
    result.append(", viewName: ");
    result.append(viewName);
    result.append(", category: ");
    result.append(category);
    result.append(')');
    return result.toString();
  }

} //ViewSpecificationImpl
