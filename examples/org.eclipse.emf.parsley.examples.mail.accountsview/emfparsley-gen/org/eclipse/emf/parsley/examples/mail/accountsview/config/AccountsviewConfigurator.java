package org.eclipse.emf.parsley.examples.mail.accountsview.config;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.examples.mail.accountsview.views.AccountsView;

@SuppressWarnings("all")
public class AccountsviewConfigurator extends Configurator {
  private final URI mailModelURI = URI.createFileURI(
    (System.getProperty("user.home") + "/examples/mail/My.mail"));
  
  public URI getMailModelURI() {
    return this.mailModelURI;
  }
  
  public URI resourceURI(final AccountsView it) {
    return this.mailModelURI;
  }
}
