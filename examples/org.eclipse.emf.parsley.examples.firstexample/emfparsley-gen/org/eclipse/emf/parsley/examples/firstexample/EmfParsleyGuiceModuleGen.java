package org.eclipse.emf.parsley.examples.firstexample;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.edit.IEditingStrategy;
import org.eclipse.emf.parsley.edit.UndoableEditingStrategy;
import org.eclipse.emf.parsley.edit.action.EditingMenuBuilder;
import org.eclipse.emf.parsley.examples.firstexample.config.ConfiguratorGen;
import org.eclipse.emf.parsley.examples.firstexample.edit.action.MenuBuilderGen;
import org.eclipse.emf.parsley.examples.firstexample.edit.ui.provider.ViewerContentProviderGen;
import org.eclipse.emf.parsley.examples.firstexample.resource.ResourceManagerGen;
import org.eclipse.emf.parsley.examples.firstexample.ui.provider.FeatureCaptionProviderGen;
import org.eclipse.emf.parsley.examples.firstexample.ui.provider.FeaturesProviderGen;
import org.eclipse.emf.parsley.examples.firstexample.ui.provider.LabelProviderGen;
import org.eclipse.emf.parsley.resource.ResourceManager;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.examples.firstexample Emf Parsley Dsl Module file
 */
@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends IEditingStrategy> bindIEditingStrategy() {
    return UndoableEditingStrategy.class;
  }
  
  @Override
  public int valueTreeFormSashStyle() {
    return SWT.HORIZONTAL;
  }
  
  @Override
  public Class<? extends ILabelProvider> bindILabelProvider() {
    return LabelProviderGen.class;
  }
  
  @Override
  public Class<? extends FeatureCaptionProvider> bindFeatureCaptionProvider() {
    return FeatureCaptionProviderGen.class;
  }
  
  @Override
  public Class<? extends FeaturesProvider> bindFeaturesProvider() {
    return FeaturesProviderGen.class;
  }
  
  @Override
  public Class<? extends IContentProvider> bindIContentProvider() {
    return ViewerContentProviderGen.class;
  }
  
  @Override
  public Class<? extends EditingMenuBuilder> bindEditingMenuBuilder() {
    return MenuBuilderGen.class;
  }
  
  @Override
  public Class<? extends Configurator> bindConfigurator() {
    return ConfiguratorGen.class;
  }
  
  @Override
  public Class<? extends ResourceManager> bindResourceManager() {
    return ResourceManagerGen.class;
  }
}
