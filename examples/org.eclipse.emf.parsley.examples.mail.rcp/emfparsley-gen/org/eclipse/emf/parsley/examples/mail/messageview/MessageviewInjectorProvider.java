package org.eclipse.emf.parsley.examples.mail.messageview;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.emf.parsley.runtime.ui.PluginUtil;

@SuppressWarnings("all")
public class MessageviewInjectorProvider {
  private static Injector injector;

  public static synchronized Injector getInjector() {
    if (injector == null) {
      injector = Guice.createInjector(
        new MessageviewEmfParsleyGuiceModule(PluginUtil.getPlugin(
          PluginUtil.getBundle(MessageviewInjectorProvider.class))));
    }
    return injector;
  }
}
