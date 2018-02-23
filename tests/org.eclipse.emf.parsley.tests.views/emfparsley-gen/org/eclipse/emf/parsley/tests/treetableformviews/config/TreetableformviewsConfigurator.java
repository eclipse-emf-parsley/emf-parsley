package org.eclipse.emf.parsley.tests.treetableformviews.config;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.tests.models.testmodels.TestmodelsPackage;
import org.eclipse.emf.parsley.tests.views.ViewsSaveableEditableTreeTableFormView;

@SuppressWarnings("all")
public class TreetableformviewsConfigurator extends Configurator {
  public URI resourceURI(final ViewsSaveableEditableTreeTableFormView it) {
    return URI.createURI("platform:/resource/MyTestProject/TestContainer.xmi");
  }
  
  public EClass eClass(final ViewsSaveableEditableTreeTableFormView it) {
    return TestmodelsPackage.Literals.CLASS_FOR_CONTROLS;
  }
}
