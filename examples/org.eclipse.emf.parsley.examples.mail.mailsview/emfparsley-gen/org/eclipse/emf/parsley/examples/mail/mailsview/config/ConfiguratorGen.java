package org.eclipse.emf.parsley.examples.mail.mailsview.config;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.examples.mail.MailPackage;
import org.eclipse.emf.parsley.examples.mail.mailsview.views.MailsView;

@SuppressWarnings("all")
public class ConfiguratorGen extends Configurator {
  public EClass eClass(final MailsView it) {
    return MailPackage.Literals.MAIL;
  }
}
