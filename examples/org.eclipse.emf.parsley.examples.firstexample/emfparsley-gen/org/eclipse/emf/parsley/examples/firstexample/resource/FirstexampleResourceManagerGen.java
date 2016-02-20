package org.eclipse.emf.parsley.examples.firstexample.resource;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.examples.extlibrary.EXTLibraryFactory;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.emf.parsley.resource.ResourceManager;

@SuppressWarnings("all")
public class FirstexampleResourceManagerGen extends ResourceManager {
  @Override
  public void initialize(final Resource it) {
    EList<EObject> _contents = it.getContents();
    Library _createLibrary = EXTLibraryFactory.eINSTANCE.createLibrary();
    _contents.add(_createLibrary);
  }
}
