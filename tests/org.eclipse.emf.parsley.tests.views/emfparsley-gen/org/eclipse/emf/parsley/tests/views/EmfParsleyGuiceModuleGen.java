package org.eclipse.emf.parsley.tests.views;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.resource.ResourceManager;
import org.eclipse.emf.parsley.tests.views.config.ConfiguratorGen;
import org.eclipse.emf.parsley.tests.views.resource.ResourceManagerGen;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Defines Parsley parts acting on parsley.tests.models,
 * for testing purposes.
 * 
 * The resources edited by these views are expected to be found
 * on a project called "MyTestProject" in the workbench.
 */
@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends Configurator> bindConfigurator() {
    return ConfiguratorGen.class;
  }
  
  @Override
  public Class<? extends ResourceManager> bindResourceManager() {
    return ResourceManagerGen.class;
  }
}
