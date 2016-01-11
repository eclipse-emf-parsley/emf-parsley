package org.eclipse.emf.parsley.tests.views.config;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage;
import org.eclipse.emf.parsley.tests.views.ViewsSaveableEditableTableView;
import org.eclipse.emf.parsley.tests.views.ViewsSaveableTreeFormView;
import org.eclipse.emf.parsley.tests.views.ViewsSaveableTreeView;

@SuppressWarnings("all")
public class ConfiguratorGen extends Configurator {
  public URI resourceURI(final ViewsSaveableTreeFormView it) {
    return URI.createURI("platform:/resource/MyTestProject/TestContainer.xmi");
  }
  
  public URI resourceURI(final ViewsSaveableTreeView it) {
    return URI.createURI("platform:/resource/MyTestProject/TestContainerForDnD.xmi");
  }
  
  public URI resourceURI(final ViewsSaveableEditableTableView it) {
    return URI.createURI("platform:/resource/MyTestProject/TestContainer.xmi");
  }
  
  public EClass eClass(final ViewsSaveableEditableTableView it) {
    return TestmodelsPackage.Literals.CLASS_FOR_CONTROLS;
  }
}
