package org.eclipse.emf.parsley.tests.views.resource;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.resource.ResourceManager;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForControls;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName;
import org.eclipse.emf.parsley.tests.models.testmodels.TestContainer;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsFactory;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class ViewsResourceManager extends ResourceManager {
  private final TestmodelsFactory factory = TestmodelsFactory.eINSTANCE;

  public TestmodelsFactory getFactory() {
    return this.factory;
  }

  @Override
  public void initialize(final Resource it) {
    EList<EObject> _contents = it.getContents();
    TestContainer _createTestContainer = this.factory.createTestContainer();
    final Procedure1<TestContainer> _function = (TestContainer it_1) -> {
      EList<ClassForControls> _classesForControls = it_1.getClassesForControls();
      ClassForControls _createClassForControls = this.factory.createClassForControls();
      _classesForControls.add(_createClassForControls);
      EList<ClassWithName> _classesWithName = it_1.getClassesWithName();
      ClassWithName _createClassWithName = this.factory.createClassWithName();
      final Procedure1<ClassWithName> _function_1 = (ClassWithName it_2) -> {
        it_2.setName("First Class With Name");
      };
      ClassWithName _doubleArrow = ObjectExtensions.<ClassWithName>operator_doubleArrow(_createClassWithName, _function_1);
      _classesWithName.add(_doubleArrow);
      EList<ClassWithName> _classesWithName_1 = it_1.getClassesWithName();
      ClassWithName _createClassWithName_1 = this.factory.createClassWithName();
      final Procedure1<ClassWithName> _function_2 = (ClassWithName it_2) -> {
        it_2.setName("Second Class With Name");
      };
      ClassWithName _doubleArrow_1 = ObjectExtensions.<ClassWithName>operator_doubleArrow(_createClassWithName_1, _function_2);
      _classesWithName_1.add(_doubleArrow_1);
    };
    TestContainer _doubleArrow = ObjectExtensions.<TestContainer>operator_doubleArrow(_createTestContainer, _function);
    _contents.add(_doubleArrow);
  }
}
