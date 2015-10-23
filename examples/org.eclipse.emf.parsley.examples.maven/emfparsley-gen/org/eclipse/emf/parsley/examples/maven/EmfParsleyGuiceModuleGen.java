package org.eclipse.emf.parsley.examples.maven;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.composite.DialogControlFactory;
import org.eclipse.emf.parsley.composite.FormControlFactory;
import org.eclipse.emf.parsley.composite.ProposalCreator;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.edit.IEditingStrategy;
import org.eclipse.emf.parsley.edit.UndoableEditingStrategy;
import org.eclipse.emf.parsley.edit.action.EditingMenuBuilder;
import org.eclipse.emf.parsley.edit.ui.provider.TableViewerContentProvider;
import org.eclipse.emf.parsley.examples.maven.binding.DialogControlFactoryGen;
import org.eclipse.emf.parsley.examples.maven.binding.FormControlFactoryGen;
import org.eclipse.emf.parsley.examples.maven.binding.ProposalCreatorGen;
import org.eclipse.emf.parsley.examples.maven.config.ConfiguratorGen;
import org.eclipse.emf.parsley.examples.maven.edit.action.MenuBuilderGen;
import org.eclipse.emf.parsley.examples.maven.edit.ui.provider.TableViewerContentProviderGen;
import org.eclipse.emf.parsley.examples.maven.edit.ui.provider.ViewerContentProviderGen;
import org.eclipse.emf.parsley.examples.maven.resource.ResourceManagerGen;
import org.eclipse.emf.parsley.examples.maven.ui.provider.DialogFeatureCaptionProviderGen;
import org.eclipse.emf.parsley.examples.maven.ui.provider.FeatureCaptionProviderGen;
import org.eclipse.emf.parsley.examples.maven.ui.provider.FeaturesProviderGen;
import org.eclipse.emf.parsley.examples.maven.ui.provider.FormFeatureCaptionProviderGen;
import org.eclipse.emf.parsley.examples.maven.ui.provider.LabelProviderGen;
import org.eclipse.emf.parsley.examples.maven.ui.provider.TableFeaturesProviderGen;
import org.eclipse.emf.parsley.examples.maven.ui.provider.TableLabelProviderGen;
import org.eclipse.emf.parsley.resource.ResourceManager;
import org.eclipse.emf.parsley.ui.provider.DialogFeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider;
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.examples.maven Emf Parsley Dsl Module file
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
  public Class<? extends ILabelProvider> bindILabelProvider() {
    return LabelProviderGen.class;
  }
  
  @Override
  public Class<? extends TableColumnLabelProvider> bindTableColumnLabelProvider() {
    return TableLabelProviderGen.class;
  }
  
  @Override
  public Class<? extends FeatureCaptionProvider> bindFeatureCaptionProvider() {
    return FeatureCaptionProviderGen.class;
  }
  
  @Override
  public Class<? extends FormFeatureCaptionProvider> bindFormFeatureCaptionProvider() {
    return FormFeatureCaptionProviderGen.class;
  }
  
  @Override
  public Class<? extends DialogFeatureCaptionProvider> bindDialogFeatureCaptionProvider() {
    return DialogFeatureCaptionProviderGen.class;
  }
  
  @Override
  public Class<? extends FeaturesProvider> bindFeaturesProvider() {
    return FeaturesProviderGen.class;
  }
  
  @Override
  public Class<? extends TableFeaturesProvider> bindTableFeaturesProvider() {
    return TableFeaturesProviderGen.class;
  }
  
  @Override
  public Class<? extends FormControlFactory> bindFormControlFactory() {
    return FormControlFactoryGen.class;
  }
  
  @Override
  public Class<? extends DialogControlFactory> bindDialogControlFactory() {
    return DialogControlFactoryGen.class;
  }
  
  @Override
  public Class<? extends IContentProvider> bindIContentProvider() {
    return ViewerContentProviderGen.class;
  }
  
  @Override
  public Class<? extends TableViewerContentProvider> bindTableViewerContentProvider() {
    return TableViewerContentProviderGen.class;
  }
  
  @Override
  public Class<? extends ProposalCreator> bindProposalCreator() {
    return ProposalCreatorGen.class;
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
