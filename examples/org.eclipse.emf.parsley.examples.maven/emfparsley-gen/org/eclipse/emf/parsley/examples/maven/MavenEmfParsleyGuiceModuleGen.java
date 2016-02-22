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
import org.eclipse.emf.parsley.examples.maven.binding.MavenDialogControlFactoryGen;
import org.eclipse.emf.parsley.examples.maven.binding.MavenFormControlFactoryGen;
import org.eclipse.emf.parsley.examples.maven.binding.MavenProposalCreatorGen;
import org.eclipse.emf.parsley.examples.maven.config.MavenConfiguratorGen;
import org.eclipse.emf.parsley.examples.maven.edit.action.MavenMenuBuilderGen;
import org.eclipse.emf.parsley.examples.maven.edit.ui.provider.MavenTableViewerContentProviderGen;
import org.eclipse.emf.parsley.examples.maven.edit.ui.provider.MavenViewerContentProviderGen;
import org.eclipse.emf.parsley.examples.maven.resource.MavenResourceManagerGen;
import org.eclipse.emf.parsley.examples.maven.ui.provider.MavenDialogFeatureCaptionProviderGen;
import org.eclipse.emf.parsley.examples.maven.ui.provider.MavenFeatureCaptionProviderGen;
import org.eclipse.emf.parsley.examples.maven.ui.provider.MavenFeaturesProviderGen;
import org.eclipse.emf.parsley.examples.maven.ui.provider.MavenFormFeatureCaptionProviderGen;
import org.eclipse.emf.parsley.examples.maven.ui.provider.MavenLabelProviderGen;
import org.eclipse.emf.parsley.examples.maven.ui.provider.MavenTableFeaturesProviderGen;
import org.eclipse.emf.parsley.examples.maven.ui.provider.MavenTableLabelProviderGen;
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
public class MavenEmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public MavenEmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends IEditingStrategy> bindIEditingStrategy() {
    return UndoableEditingStrategy.class;
  }
  
  @Override
  public Class<? extends ILabelProvider> bindILabelProvider() {
    return MavenLabelProviderGen.class;
  }
  
  @Override
  public Class<? extends TableColumnLabelProvider> bindTableColumnLabelProvider() {
    return MavenTableLabelProviderGen.class;
  }
  
  @Override
  public Class<? extends FeatureCaptionProvider> bindFeatureCaptionProvider() {
    return MavenFeatureCaptionProviderGen.class;
  }
  
  @Override
  public Class<? extends FormFeatureCaptionProvider> bindFormFeatureCaptionProvider() {
    return MavenFormFeatureCaptionProviderGen.class;
  }
  
  @Override
  public Class<? extends DialogFeatureCaptionProvider> bindDialogFeatureCaptionProvider() {
    return MavenDialogFeatureCaptionProviderGen.class;
  }
  
  @Override
  public Class<? extends FeaturesProvider> bindFeaturesProvider() {
    return MavenFeaturesProviderGen.class;
  }
  
  @Override
  public Class<? extends TableFeaturesProvider> bindTableFeaturesProvider() {
    return MavenTableFeaturesProviderGen.class;
  }
  
  @Override
  public Class<? extends FormControlFactory> bindFormControlFactory() {
    return MavenFormControlFactoryGen.class;
  }
  
  @Override
  public Class<? extends DialogControlFactory> bindDialogControlFactory() {
    return MavenDialogControlFactoryGen.class;
  }
  
  @Override
  public Class<? extends IContentProvider> bindIContentProvider() {
    return MavenViewerContentProviderGen.class;
  }
  
  @Override
  public Class<? extends TableViewerContentProvider> bindTableViewerContentProvider() {
    return MavenTableViewerContentProviderGen.class;
  }
  
  @Override
  public Class<? extends ProposalCreator> bindProposalCreator() {
    return MavenProposalCreatorGen.class;
  }
  
  @Override
  public Class<? extends EditingMenuBuilder> bindEditingMenuBuilder() {
    return MavenMenuBuilderGen.class;
  }
  
  @Override
  public Class<? extends Configurator> bindConfigurator() {
    return MavenConfiguratorGen.class;
  }
  
  @Override
  public Class<? extends ResourceManager> bindResourceManager() {
    return MavenResourceManagerGen.class;
  }
}
