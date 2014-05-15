package org.eclipse.emf.parsley.examples.cdo.company.ui.products;

import org.eclipse.emf.parsley.examples.cdo.company.CompanyGuiceModule;
import org.eclipse.emf.parsley.examples.cdo.company.ui.products.edit.ui.provider.ViewerContentProviderGen;
import org.eclipse.emf.parsley.examples.cdo.company.ui.products.factory.TreeFormFactoryGen;
import org.eclipse.emf.parsley.examples.cdo.company.ui.products.ui.provider.FeatureCaptionProviderGen;
import org.eclipse.emf.parsley.examples.cdo.company.ui.products.ui.provider.FeaturesProviderGen;
import org.eclipse.emf.parsley.examples.cdo.company.ui.products.ui.provider.LabelProviderGen;
import org.eclipse.emf.parsley.factories.TreeFormFactory;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.examples.cdo.company.ui.products Emf Parsley Dsl Module file
 */
@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends CompanyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
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
  public Class<? extends TreeFormFactory> bindTreeFormFactory() {
    return TreeFormFactoryGen.class;
  }
}
