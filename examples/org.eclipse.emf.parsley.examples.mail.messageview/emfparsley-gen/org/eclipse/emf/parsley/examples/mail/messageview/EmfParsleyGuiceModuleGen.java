package org.eclipse.emf.parsley.examples.mail.messageview;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.binding.FormControlFactory;
import org.eclipse.emf.parsley.examples.mail.messageview.binding.FormControlFactoryGen;
import org.eclipse.emf.parsley.examples.mail.messageview.ui.provider.FeaturesProviderGen;
import org.eclipse.emf.parsley.examples.mail.messageview.ui.provider.LabelProviderGen;
import org.eclipse.emf.parsley.examples.mail.messageview.ui.provider.PropertyDescriptionProviderGen;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.PropertyDescriptionProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.examples.mail.messageview Emf Parsley Dsl Module file
 */
@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends ILabelProvider> bindILabelProvider() {
    return LabelProviderGen.class;
  }
  
  @Override
  public Class<? extends PropertyDescriptionProvider> bindPropertyDescriptionProvider() {
    return PropertyDescriptionProviderGen.class;
  }
  
  @Override
  public Class<? extends FeaturesProvider> bindFeaturesProvider() {
    return FeaturesProviderGen.class;
  }
  
  @Override
  public Class<? extends FormControlFactory> bindFormControlFactory() {
    return FormControlFactoryGen.class;
  }
}
