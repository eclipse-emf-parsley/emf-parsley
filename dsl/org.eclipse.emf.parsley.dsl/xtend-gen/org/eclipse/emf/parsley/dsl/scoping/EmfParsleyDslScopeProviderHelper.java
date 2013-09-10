package org.eclipse.emf.parsley.dsl.scoping;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.beans.Introspector;
import java.util.Arrays;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.parsley.dsl.model.EmfFeatureAccess;
import org.eclipse.emf.parsley.dsl.util.EmfParsleyDslModelUtil;
import org.eclipse.xtext.common.types.JvmFeature;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.eclipse.xtext.scoping.impl.SimpleScope;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * For the moment Xbase uses two different scope providers, one for
 * the runtime part and one for the content assist, thus we must
 * factor out commong behavior.
 * 
 * see http://www.eclipse.org/forums/index.php/mv/msg/476486/1041675/#msg_1041675
 */
@SuppressWarnings("all")
public class EmfParsleyDslScopeProviderHelper {
  public IScope createCustomScope(final EObject context, final EReference reference) {
    EClassifier _eType = reference.getEType();
    EClass _jvmMember = TypesPackage.eINSTANCE.getJvmMember();
    boolean _equals = Objects.equal(_eType, _jvmMember);
    if (_equals) {
      IScope _customScope = null;
      EmfFeatureAccess _containingEmfFeatureAccess = EmfParsleyDslModelUtil.containingEmfFeatureAccess(context);
      JvmTypeReference _parameterType = null;
      if (_containingEmfFeatureAccess!=null) {
        _parameterType=_containingEmfFeatureAccess.getParameterType();
      }
      JvmType _type = null;
      if (_parameterType!=null) {
        _type=_parameterType.getType();
      }
      if (_type!=null) {
        _customScope=this.customScope(_type);
      }
      return _customScope;
    }
    return null;
  }
  
  protected IScope _customScope(final JvmType t) {
    return null;
  }
  
  protected IScope _customScope(final JvmGenericType t) {
    Iterable<JvmFeature> _allFeatures = t.getAllFeatures();
    Iterable<JvmOperation> _filter = Iterables.<JvmOperation>filter(_allFeatures, JvmOperation.class);
    final Function1<JvmOperation,Boolean> _function = new Function1<JvmOperation,Boolean>() {
      public Boolean apply(final JvmOperation it) {
        boolean _isStatic = it.isStatic();
        boolean _not = (!_isStatic);
        return Boolean.valueOf(_not);
      }
    };
    Iterable<JvmOperation> _filter_1 = IterableExtensions.<JvmOperation>filter(_filter, _function);
    final Function<JvmOperation,QualifiedName> _function_1 = new Function<JvmOperation,QualifiedName>() {
      public QualifiedName apply(final JvmOperation it) {
        QualifiedName _xifexpression = null;
        String _simpleName = it.getSimpleName();
        boolean _isGetterMethod = EmfParsleyDslScopeProviderHelper.this.isGetterMethod(_simpleName);
        if (_isGetterMethod) {
          String _simpleName_1 = it.getSimpleName();
          String _propertyNameForGetterMethod = EmfParsleyDslScopeProviderHelper.this.getPropertyNameForGetterMethod(_simpleName_1);
          QualifiedName _create = QualifiedName.create(_propertyNameForGetterMethod);
          _xifexpression = _create;
        } else {
          _xifexpression = null;
        }
        return _xifexpression;
      }
    };
    Iterable<IEObjectDescription> _scopedElementsFor = Scopes.<JvmOperation>scopedElementsFor(_filter_1, _function_1);
    SimpleScope _simpleScope = new SimpleScope(_scopedElementsFor);
    return _simpleScope;
  }
  
  public boolean isGetterMethod(final String opName) {
    boolean _and = false;
    boolean _and_1 = false;
    boolean _startsWith = opName.startsWith("get");
    if (!_startsWith) {
      _and_1 = false;
    } else {
      int _length = opName.length();
      boolean _greaterThan = (_length > 3);
      _and_1 = (_startsWith && _greaterThan);
    }
    if (!_and_1) {
      _and = false;
    } else {
      char _charAt = opName.charAt(3);
      boolean _isUpperCase = Character.isUpperCase(_charAt);
      _and = (_and_1 && _isUpperCase);
    }
    if (_and) {
      return true;
    }
    boolean _and_2 = false;
    boolean _and_3 = false;
    boolean _startsWith_1 = opName.startsWith("is");
    if (!_startsWith_1) {
      _and_3 = false;
    } else {
      int _length_1 = opName.length();
      boolean _greaterThan_1 = (_length_1 > 2);
      _and_3 = (_startsWith_1 && _greaterThan_1);
    }
    if (!_and_3) {
      _and_2 = false;
    } else {
      char _charAt_1 = opName.charAt(2);
      boolean _isUpperCase_1 = Character.isUpperCase(_charAt_1);
      _and_2 = (_and_3 && _isUpperCase_1);
    }
    if (_and_2) {
      return true;
    }
    return false;
  }
  
  public String getPropertyNameForGetterMethod(final String opName) {
    boolean _and = false;
    boolean _and_1 = false;
    boolean _startsWith = opName.startsWith("get");
    if (!_startsWith) {
      _and_1 = false;
    } else {
      int _length = opName.length();
      boolean _greaterThan = (_length > 3);
      _and_1 = (_startsWith && _greaterThan);
    }
    if (!_and_1) {
      _and = false;
    } else {
      char _charAt = opName.charAt(3);
      boolean _isUpperCase = Character.isUpperCase(_charAt);
      _and = (_and_1 && _isUpperCase);
    }
    if (_and) {
      String _substring = opName.substring(3);
      return Introspector.decapitalize(_substring);
    }
    boolean _and_2 = false;
    boolean _and_3 = false;
    boolean _startsWith_1 = opName.startsWith("is");
    if (!_startsWith_1) {
      _and_3 = false;
    } else {
      int _length_1 = opName.length();
      boolean _greaterThan_1 = (_length_1 > 2);
      _and_3 = (_startsWith_1 && _greaterThan_1);
    }
    if (!_and_3) {
      _and_2 = false;
    } else {
      char _charAt_1 = opName.charAt(2);
      boolean _isUpperCase_1 = Character.isUpperCase(_charAt_1);
      _and_2 = (_and_3 && _isUpperCase_1);
    }
    if (_and_2) {
      String _substring_1 = opName.substring(2);
      return Introspector.decapitalize(_substring_1);
    }
    return null;
  }
  
  public IScope customScope(final JvmType t) {
    if (t instanceof JvmGenericType) {
      return _customScope((JvmGenericType)t);
    } else if (t != null) {
      return _customScope(t);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(t).toString());
    }
  }
}
