package org.eclipse.emf.parsley.tests.views.resource;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.resource.ResourceManager;
import org.eclipse.emf.parsley.tests.models.testmodels.TestContainer;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsFactory;

@SuppressWarnings("all")
public class ResourceManagerGen extends ResourceManager {
  @Override
  public void initialize(final Resource it) {
    EList<EObject> _contents = it.getContents();
    TestContainer _createTestContainer = TestmodelsFactory.eINSTANCE.createTestContainer();
    _contents.add(_createTestContainer);
  }
}
