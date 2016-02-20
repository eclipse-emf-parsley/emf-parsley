package org.eclipse.emf.parsley.examples.mail.accountsview;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.emf.parsley.examples.mail.accountsview.AccountsviewEmfParsleyGuiceModuleGen;
import org.eclipse.emf.parsley.runtime.ui.PluginUtil;

@SuppressWarnings("all")
public class AccountsviewInjectorProvider {
  private static Injector injector;
  
  public static synchronized Injector getInjector() throws Exception {
    if (injector == null) {
      injector = Guice.createInjector(
        new AccountsviewEmfParsleyGuiceModuleGen(PluginUtil.getPlugin(
          PluginUtil.getBundle(AccountsviewInjectorProvider.class))));
    }
    return injector;
  }
}
