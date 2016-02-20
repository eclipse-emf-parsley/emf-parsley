package org.eclipse.emf.parsley.examples.cdo.treeform;

import org.eclipse.emf.parsley.cdo.CDOEmfParsleyModule;
import org.eclipse.emf.parsley.examples.cdo.treeform.customizations.TreeformCDOResourceManager;
import org.eclipse.emf.parsley.examples.cdo.treeform.ui.provider.TreeformFeaturesProviderGen;
import org.eclipse.emf.parsley.resource.ResourceManager;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.examples.cdo.treeform Emf Parsley Dsl Module file
 */
@SuppressWarnings("all")
public class TreeformEmfParsleyGuiceModuleGen extends CDOEmfParsleyModule {
  public TreeformEmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends ResourceManager> bindResourceManager() {
    return TreeformCDOResourceManager.class;
  }
  
  @Override
  public Class<? extends FeaturesProvider> bindFeaturesProvider() {
    return TreeformFeaturesProviderGen.class;
  }
}
