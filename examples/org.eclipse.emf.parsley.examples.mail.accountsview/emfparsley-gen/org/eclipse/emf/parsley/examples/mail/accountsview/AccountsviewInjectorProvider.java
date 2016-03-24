package org.eclipse.emf.parsley.examples.mail.accountsview;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.emf.parsley.examples.mail.accountsview.AccountsviewEmfParsleyGuiceModule;
import org.eclipse.emf.parsley.runtime.ui.PluginUtil;

@SuppressWarnings("all")
public class AccountsviewInjectorProvider {
  private static Injector injector;
  
  public static synchronized Injector getInjector() {
    if (injector == null) {
      injector = Guice.createInjector(
        new AccountsviewEmfParsleyGuiceModule(PluginUtil.getPlugin(
          PluginUtil.getBundle(AccountsviewInjectorProvider.class))));
    }
    return injector;
  }
}
