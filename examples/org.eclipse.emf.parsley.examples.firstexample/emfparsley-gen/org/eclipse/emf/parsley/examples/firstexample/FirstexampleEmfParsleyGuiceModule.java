package org.eclipse.emf.parsley.examples.firstexample;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.edit.IEditingStrategy;
import org.eclipse.emf.parsley.edit.UndoableEditingStrategy;
import org.eclipse.emf.parsley.edit.action.EditingMenuBuilder;
import org.eclipse.emf.parsley.examples.firstexample.config.FirstexampleConfigurator;
import org.eclipse.emf.parsley.examples.firstexample.edit.action.FirstexampleMenuBuilder;
import org.eclipse.emf.parsley.examples.firstexample.edit.ui.provider.FirstexampleViewerContentProvider;
import org.eclipse.emf.parsley.examples.firstexample.resource.FirstexampleResourceManager;
import org.eclipse.emf.parsley.examples.firstexample.ui.provider.FirstexampleFeatureCaptionProvider;
import org.eclipse.emf.parsley.examples.firstexample.ui.provider.FirstexampleFeaturesProvider;
import org.eclipse.emf.parsley.examples.firstexample.ui.provider.FirstexampleLabelProvider;
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
public class FirstexampleEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
  public FirstexampleEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
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
    return FirstexampleLabelProvider.class;
  }
  
  @Override
  public Class<? extends FeatureCaptionProvider> bindFeatureCaptionProvider() {
    return FirstexampleFeatureCaptionProvider.class;
  }
  
  @Override
  public Class<? extends FeaturesProvider> bindFeaturesProvider() {
    return FirstexampleFeaturesProvider.class;
  }
  
  @Override
  public Class<? extends IContentProvider> bindIContentProvider() {
    return FirstexampleViewerContentProvider.class;
  }
  
  @Override
  public Class<? extends EditingMenuBuilder> bindEditingMenuBuilder() {
    return FirstexampleMenuBuilder.class;
  }
  
  @Override
  public Class<? extends Configurator> bindConfigurator() {
    return FirstexampleConfigurator.class;
  }
  
  @Override
  public Class<? extends ResourceManager> bindResourceManager() {
    return FirstexampleResourceManager.class;
  }
}
