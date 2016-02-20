package org.eclipse.emf.parsley.examples.mail.mailsview;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.examples.mail.mailsview.config.MailsviewConfiguratorGen;
import org.eclipse.emf.parsley.examples.mail.mailsview.ui.provider.MailsviewFeaturesProviderGen;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.examples.mail.mailsview Emf Parsley Dsl Module file
 */
@SuppressWarnings("all")
public class MailsviewEmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public MailsviewEmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends FeaturesProvider> bindFeaturesProvider() {
    return MailsviewFeaturesProviderGen.class;
  }
  
  @Override
  public Class<? extends Configurator> bindConfigurator() {
    return MailsviewConfiguratorGen.class;
  }
}
