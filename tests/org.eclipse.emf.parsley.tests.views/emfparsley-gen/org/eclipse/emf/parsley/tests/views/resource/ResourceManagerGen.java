package org.eclipse.emf.parsley.tests.views.resource;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.resource.ResourceManager;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls;
import org.eclipse.emf.parsley.tests.models.testmodels.TestContainer;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsFactory;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class ResourceManagerGen extends ResourceManager {
  private final TestmodelsFactory factory = TestmodelsFactory.eINSTANCE;
  
  public TestmodelsFactory getFactory() {
    return this.factory;
  }
  
  @Override
  public void initialize(final Resource it) {
    EList<EObject> _contents = it.getContents();
    TestContainer _createTestContainer = this.factory.createTestContainer();
    final Procedure1<TestContainer> _function = new Procedure1<TestContainer>() {
      @Override
      public void apply(final TestContainer it) {
        EList<ClassForControls> _classesForControls = it.getClassesForControls();
        ClassForControls _createClassForControls = ResourceManagerGen.this.factory.createClassForControls();
        _classesForControls.add(_createClassForControls);
      }
    };
    TestContainer _doubleArrow = ObjectExtensions.<TestContainer>operator_doubleArrow(_createTestContainer, _function);
    _contents.add(_doubleArrow);
  }
}
