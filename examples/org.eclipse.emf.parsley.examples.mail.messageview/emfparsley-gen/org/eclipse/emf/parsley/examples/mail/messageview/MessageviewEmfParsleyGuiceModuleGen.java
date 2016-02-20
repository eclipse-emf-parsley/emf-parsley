package org.eclipse.emf.parsley.examples.mail.messageview;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.composite.FormControlFactory;
import org.eclipse.emf.parsley.examples.mail.messageview.binding.MessageviewFormControlFactoryGen;
import org.eclipse.emf.parsley.examples.mail.messageview.ui.provider.MessageviewFeatureCaptionProviderGen;
import org.eclipse.emf.parsley.examples.mail.messageview.ui.provider.MessageviewFeaturesProviderGen;
import org.eclipse.emf.parsley.examples.mail.messageview.ui.provider.MessageviewLabelProviderGen;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.examples.mail.messageview Emf Parsley Dsl Module file
 */
@SuppressWarnings("all")
public class MessageviewEmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public MessageviewEmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends ILabelProvider> bindILabelProvider() {
    return MessageviewLabelProviderGen.class;
  }
  
  @Override
  public Class<? extends FeatureCaptionProvider> bindFeatureCaptionProvider() {
    return MessageviewFeatureCaptionProviderGen.class;
  }
  
  @Override
  public Class<? extends FeaturesProvider> bindFeaturesProvider() {
    return MessageviewFeaturesProviderGen.class;
  }
  
  @Override
  public Class<? extends FormControlFactory> bindFormControlFactory() {
    return MessageviewFormControlFactoryGen.class;
  }
}
