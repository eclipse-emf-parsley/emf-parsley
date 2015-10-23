package org.eclipse.emf.parsley.examples.maven;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.examples.maven.config.ConfiguratorGen;
import org.eclipse.emf.parsley.examples.maven.resource.ResourceManagerGen;
import org.eclipse.emf.parsley.resource.ResourceManager;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.examples.maven Emf Parsley Dsl Module file
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
