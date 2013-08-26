package org.eclipse.emf.parsley.examples.cdo.treeform;

import org.eclipse.emf.parsley.cdo.CDOEmfComponentsModule;
import org.eclipse.emf.parsley.examples.cdo.treeform.ui.provider.FeaturesProviderGen;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.examples.cdo.treeform Emf Components Dsl Module file
 */
@SuppressWarnings("all")
public class EmfComponentsGuiceModuleGen extends CDOEmfComponentsModule {
  public EmfComponentsGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends FeaturesProvider> bindFeaturesProvider() {
    return FeaturesProviderGen.class;
  }
}
