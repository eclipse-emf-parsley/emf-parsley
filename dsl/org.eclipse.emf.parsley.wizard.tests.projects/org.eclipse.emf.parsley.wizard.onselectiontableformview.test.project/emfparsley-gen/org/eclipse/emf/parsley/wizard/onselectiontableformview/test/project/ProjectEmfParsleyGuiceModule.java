package org.eclipse.emf.parsley.wizard.onselectiontableformview.test.project;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.wizard.onselectiontableformview.test.project.config.ProjectConfigurator;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.wizard.onselectiontableformview.test.project EMF Parsley Dsl Module file
 */
@SuppressWarnings("all")
public class ProjectEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
  public ProjectEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
    super(plugin);
  }

  @Override
  public Class<? extends Configurator> bindConfigurator() {
    return ProjectConfigurator.class;
  }
}
