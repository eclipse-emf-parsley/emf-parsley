package org.eclipse.emf.parsley.dsl.validation;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.EmfComponentsGuiceModule;
import org.eclipse.ui.IViewPart;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.util.TypeReferences;
import org.eclipse.xtext.xbase.typing.XbaseTypeConformanceComputer;

@SuppressWarnings("all")
public class EmfParsleyDslTypeSystem {
  @Inject
  private XbaseTypeConformanceComputer conformanceComputer;
  
  @Inject
  private TypeReferences typeReferences;
  
  public boolean isConformant(final JvmTypeReference expected, final JvmTypeReference actual) {
    boolean _isConformant = this.conformanceComputer.isConformant(expected, actual);
    return _isConformant;
  }
  
  public boolean isEObject(final JvmTypeReference type, final EObject context) {
    JvmTypeReference _typeForName = this.typeReferences.getTypeForName(EObject.class, context);
    boolean _isConformant = this.isConformant(_typeForName, type);
    return _isConformant;
  }
  
  public boolean isEStructuralFeature(final JvmTypeReference type, final EObject context) {
    JvmTypeReference _typeForName = this.typeReferences.getTypeForName(
      EStructuralFeature.class, context);
    boolean _isConformant = this.isConformant(_typeForName, type);
    return _isConformant;
  }
  
  public boolean isViewPart(final JvmTypeReference type, final EObject context) {
    JvmTypeReference _typeForName = this.typeReferences.getTypeForName(
      IViewPart.class, context);
    boolean _isConformant = this.isConformant(_typeForName, type);
    return _isConformant;
  }
  
  public boolean isEmfComponentsGuiceModule(final JvmTypeReference type, final EObject context) {
    JvmTypeReference _typeForName = this.typeReferences.getTypeForName(
      EmfComponentsGuiceModule.class, context);
    boolean _isConformant = this.isConformant(_typeForName, type);
    return _isConformant;
  }
}
