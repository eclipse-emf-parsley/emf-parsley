package org.eclipse.emf.parsley.examples.mail.mailsview;

import org.eclipse.emf.parsley.EmfComponentsGuiceModule;
import org.eclipse.emf.parsley.examples.mail.mailsview.ui.provider.FeaturesProviderGen;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.examples.mail.mailsview Emf Components Dsl Module file
 */
@SuppressWarnings("all")
public class EmfComponentsGuiceModuleGen extends EmfComponentsGuiceModule {
  public EmfComponentsGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends FeaturesProvider> bindFeaturesProvider() {
    return FeaturesProviderGen.class;
  }
}
