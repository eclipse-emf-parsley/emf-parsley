package org.eclipse.emf.parsley.examples.mail.mailsview;

import com.google.inject.Injector;
import org.eclipse.emf.parsley.examples.mail.mailsview.MailsviewInjectorProvider;
import org.eclipse.emf.parsley.runtime.ui.AbstractGuiceAwareExecutableExtensionFactory;

@SuppressWarnings("all")
public class MailsviewExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {
  @Override
  public Injector getInjector() throws Exception {
    return MailsviewInjectorProvider.getInjector();
  }
}
