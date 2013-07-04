package org.eclipse.emf.parsley.dsl.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.dsl.model.EmfFeatureAccess;
import org.eclipse.xtext.EcoreUtil2;

@SuppressWarnings("all")
public class EmfParsleyDslModelUtil {
  public static EmfFeatureAccess containingEmfFeatureAccess(final EObject o) {
    EmfFeatureAccess _containerOfType = EcoreUtil2.<EmfFeatureAccess>getContainerOfType(o, EmfFeatureAccess.class);
    return _containerOfType;
  }
}
