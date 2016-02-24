package org.eclipse.emf.parsley.examples.maven.resource;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.resource.ResourceManager;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class MavenResourceManager extends ResourceManager {
  @Override
  public void initialize(final Resource it) {
    EList<EObject> _contents = it.getContents();
    EClass _createEClass = EcoreFactory.eINSTANCE.createEClass();
    final Procedure1<EClass> _function = new Procedure1<EClass>() {
      @Override
      public void apply(final EClass it) {
        it.setName("TestClass");
      }
    };
    EClass _doubleArrow = ObjectExtensions.<EClass>operator_doubleArrow(_createEClass, _function);
    _contents.add(_doubleArrow);
  }
}
