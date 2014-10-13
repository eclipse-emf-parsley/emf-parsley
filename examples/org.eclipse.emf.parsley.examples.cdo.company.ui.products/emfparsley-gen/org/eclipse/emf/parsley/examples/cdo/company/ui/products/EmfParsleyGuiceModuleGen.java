package org.eclipse.emf.parsley.examples.cdo.company.ui.products;

import java.util.Collections;
import java.util.List;
import org.eclipse.emf.parsley.examples.cdo.company.CompanyGuiceModule;
import org.eclipse.emf.parsley.examples.cdo.company.ui.products.edit.ui.provider.ViewerContentProviderGen;
import org.eclipse.emf.parsley.examples.cdo.company.ui.products.ui.provider.FeatureCaptionProviderGen;
import org.eclipse.emf.parsley.examples.cdo.company.ui.products.ui.provider.FeaturesProviderGen;
import org.eclipse.emf.parsley.examples.cdo.company.ui.products.ui.provider.LabelProviderGen;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

/**
 * org.eclipse.emf.parsley.examples.cdo.company.ui.products Emf Parsley Dsl Module file
 */
@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends CompanyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public int valueTreeFormSashStyle() {
    return SWT.HORIZONTAL;
  }
  
  @Override
  public List<Integer> valueTreeFormSashWeights() {
    return Collections.<Integer>unmodifiableList(CollectionLiterals.<Integer>newArrayList(Integer.valueOf(1), Integer.valueOf(3)));
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
}
