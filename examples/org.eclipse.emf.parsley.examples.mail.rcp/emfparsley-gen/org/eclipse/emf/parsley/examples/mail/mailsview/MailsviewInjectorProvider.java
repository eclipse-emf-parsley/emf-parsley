package org.eclipse.emf.parsley.examples.mail.mailsview;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.emf.parsley.runtime.ui.PluginUtil;

@SuppressWarnings("all")
public class MailsviewInjectorProvider {
  private static Injector injector;

  public static synchronized Injector getInjector() {
    if (injector == null) {
      injector = Guice.createInjector(
        new MailsviewEmfParsleyGuiceModule(PluginUtil.getPlugin(
          PluginUtil.getBundle(MailsviewInjectorProvider.class))));
    }
    return injector;
  }
}
