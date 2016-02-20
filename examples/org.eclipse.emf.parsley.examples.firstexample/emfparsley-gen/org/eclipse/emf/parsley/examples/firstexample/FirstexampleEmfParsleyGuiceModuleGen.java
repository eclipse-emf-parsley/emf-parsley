package org.eclipse.emf.parsley.examples.firstexample;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.edit.IEditingStrategy;
import org.eclipse.emf.parsley.edit.UndoableEditingStrategy;
import org.eclipse.emf.parsley.edit.action.EditingMenuBuilder;
import org.eclipse.emf.parsley.examples.firstexample.config.FirstexampleConfiguratorGen;
import org.eclipse.emf.parsley.examples.firstexample.edit.action.FirstexampleMenuBuilderGen;
import org.eclipse.emf.parsley.examples.firstexample.edit.ui.provider.FirstexampleViewerContentProviderGen;
import org.eclipse.emf.parsley.examples.firstexample.resource.FirstexampleResourceManagerGen;
import org.eclipse.emf.parsley.examples.firstexample.ui.provider.FirstexampleFeatureCaptionProviderGen;
import org.eclipse.emf.parsley.examples.firstexample.ui.provider.FirstexampleFeaturesProviderGen;
import org.eclipse.emf.parsley.examples.firstexample.ui.provider.FirstexampleLabelProviderGen;
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
public class FirstexampleEmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public FirstexampleEmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
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
    return FirstexampleLabelProviderGen.class;
  }
  
  @Override
  public Class<? extends FeatureCaptionProvider> bindFeatureCaptionProvider() {
    return FirstexampleFeatureCaptionProviderGen.class;
  }
  
  @Override
  public Class<? extends FeaturesProvider> bindFeaturesProvider() {
    return FirstexampleFeaturesProviderGen.class;
  }
  
  @Override
  public Class<? extends IContentProvider> bindIContentProvider() {
    return FirstexampleViewerContentProviderGen.class;
  }
  
  @Override
  public Class<? extends EditingMenuBuilder> bindEditingMenuBuilder() {
    return FirstexampleMenuBuilderGen.class;
  }
  
  @Override
  public Class<? extends Configurator> bindConfigurator() {
    return FirstexampleConfiguratorGen.class;
  }
  
  @Override
  public Class<? extends ResourceManager> bindResourceManager() {
    return FirstexampleResourceManagerGen.class;
  }
}
