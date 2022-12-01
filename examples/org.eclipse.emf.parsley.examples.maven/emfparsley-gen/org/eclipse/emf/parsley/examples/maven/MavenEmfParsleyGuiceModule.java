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
import org.eclipse.emf.parsley.examples.maven.binding.MavenDialogControlFactory;
import org.eclipse.emf.parsley.examples.maven.binding.MavenFormControlFactory;
import org.eclipse.emf.parsley.examples.maven.binding.MavenProposalCreator;
import org.eclipse.emf.parsley.examples.maven.config.MavenConfigurator;
import org.eclipse.emf.parsley.examples.maven.edit.action.MavenMenuBuilder;
import org.eclipse.emf.parsley.examples.maven.edit.ui.provider.MavenTableViewerContentProvider;
import org.eclipse.emf.parsley.examples.maven.edit.ui.provider.MavenViewerContentProvider;
import org.eclipse.emf.parsley.examples.maven.resource.MavenResourceManager;
import org.eclipse.emf.parsley.examples.maven.ui.provider.MavenDialogFeatureCaptionProvider;
import org.eclipse.emf.parsley.examples.maven.ui.provider.MavenFeatureCaptionProvider;
import org.eclipse.emf.parsley.examples.maven.ui.provider.MavenFeaturesProvider;
import org.eclipse.emf.parsley.examples.maven.ui.provider.MavenFormFeatureCaptionProvider;
import org.eclipse.emf.parsley.examples.maven.ui.provider.MavenLabelProvider;
import org.eclipse.emf.parsley.examples.maven.ui.provider.MavenTableFeaturesProvider;
import org.eclipse.emf.parsley.examples.maven.ui.provider.MavenTableLabelProvider;
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
public class MavenEmfParsleyGuiceModule extends EmfParsleyGuiceModule {
  public MavenEmfParsleyGuiceModule(final AbstractUIPlugin plugin) {
    super(plugin);
  }

  @Override
  public Class<? extends IEditingStrategy> bindIEditingStrategy() {
    return UndoableEditingStrategy.class;
  }

  @Override
  public Class<? extends ILabelProvider> bindILabelProvider() {
    return MavenLabelProvider.class;
  }

  @Override
  public Class<? extends TableColumnLabelProvider> bindTableColumnLabelProvider() {
    return MavenTableLabelProvider.class;
  }

  @Override
  public Class<? extends FeatureCaptionProvider> bindFeatureCaptionProvider() {
    return MavenFeatureCaptionProvider.class;
  }

  @Override
  public Class<? extends FormFeatureCaptionProvider> bindFormFeatureCaptionProvider() {
    return MavenFormFeatureCaptionProvider.class;
  }

  @Override
  public Class<? extends DialogFeatureCaptionProvider> bindDialogFeatureCaptionProvider() {
    return MavenDialogFeatureCaptionProvider.class;
  }

  @Override
  public Class<? extends FeaturesProvider> bindFeaturesProvider() {
    return MavenFeaturesProvider.class;
  }

  @Override
  public Class<? extends TableFeaturesProvider> bindTableFeaturesProvider() {
    return MavenTableFeaturesProvider.class;
  }

  @Override
  public Class<? extends FormControlFactory> bindFormControlFactory() {
    return MavenFormControlFactory.class;
  }

  @Override
  public Class<? extends DialogControlFactory> bindDialogControlFactory() {
    return MavenDialogControlFactory.class;
  }

  @Override
  public Class<? extends IContentProvider> bindIContentProvider() {
    return MavenViewerContentProvider.class;
  }

  @Override
  public Class<? extends TableViewerContentProvider> bindTableViewerContentProvider() {
    return MavenTableViewerContentProvider.class;
  }

  @Override
  public Class<? extends ProposalCreator> bindProposalCreator() {
    return MavenProposalCreator.class;
  }

  @Override
  public Class<? extends EditingMenuBuilder> bindEditingMenuBuilder() {
    return MavenMenuBuilder.class;
  }

  @Override
  public Class<? extends Configurator> bindConfigurator() {
    return MavenConfigurator.class;
  }

  @Override
  public Class<? extends ResourceManager> bindResourceManager() {
    return MavenResourceManager.class;
  }
}
