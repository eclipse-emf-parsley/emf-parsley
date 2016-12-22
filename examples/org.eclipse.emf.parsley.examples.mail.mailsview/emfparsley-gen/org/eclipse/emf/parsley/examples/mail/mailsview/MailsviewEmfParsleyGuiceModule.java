package org.eclipse.emf.parsley.examples.mail.mailsview;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.examples.mail.mailsview.config.MailsviewConfigurator;
import org.eclipse.emf.parsley.examples.mail.mailsview.ui.provider.MailsviewFeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.examples.mail.mailsview EMF Parsley Dsl Module file
 */
@SuppressWarnings("all")
public class MailsviewEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
  public MailsviewEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends FeaturesProvider> bindFeaturesProvider() {
    return MailsviewFeaturesProvider.class;
  }
  
  @Override
  public Class<? extends Configurator> bindConfigurator() {
    return MailsviewConfigurator.class;
  }
}
