package org.eclipse.emf.parsley.tests.views.config;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.tests.views.ViewsSaveableTreeFormView;

@SuppressWarnings("all")
public class ConfiguratorGen extends Configurator {
  public URI resourceURI(final ViewsSaveableTreeFormView it) {
    return URI.createURI("platform:/resource/MyTestProject/TestContainer.xmi");
  }
}
