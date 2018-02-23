package org.eclipse.emf.parsley.tests.treetableformviews;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.resource.ResourceManager;
import org.eclipse.emf.parsley.resource.ResourceSaveStrategy;
import org.eclipse.emf.parsley.resource.ValidateBeforeSaveStrategy;
import org.eclipse.emf.parsley.tests.treetableformviews.config.TreetableformviewsConfigurator;
import org.eclipse.emf.parsley.tests.treetableformviews.resource.TreetableformviewsResourceManager;
import org.eclipse.emf.parsley.viewers.TableViewerColumnBuilder;
import org.eclipse.emf.parsley.viewers.TableViewerEditableColumnBuilder;
import org.eclipse.swt.SWT;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Defines Parsley parts acting on parsley.tests.models,
 * for testing purposes.
 * 
 * The resources edited by these views are expected to be found
 * on a project called "MyTestProject" in the workbench.
 */
@SuppressWarnings("all")
public class TreetableformviewsEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
  public TreetableformviewsEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends ResourceSaveStrategy> bindResourceSaveStrategy() {
    return ValidateBeforeSaveStrategy.class;
  }
  
  @Override
  public Class<? extends TableViewerColumnBuilder> bindTableViewerColumnBuilder() {
    return TableViewerEditableColumnBuilder.class;
  }
  
  @Override
  public int valueTreeFormSashStyle() {
    return SWT.HORIZONTAL;
  }
  
  @Override
  public Class<? extends Configurator> bindConfigurator() {
    return TreetableformviewsConfigurator.class;
  }
  
  @Override
  public Class<? extends ResourceManager> bindResourceManager() {
    return TreetableformviewsResourceManager.class;
  }
}
