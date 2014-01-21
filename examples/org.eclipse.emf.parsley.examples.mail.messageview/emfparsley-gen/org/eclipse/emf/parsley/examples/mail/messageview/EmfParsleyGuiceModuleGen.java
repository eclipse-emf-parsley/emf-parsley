/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.examples.mail.messageview;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.binding.FormControlFactory;
import org.eclipse.emf.parsley.examples.mail.messageview.binding.FormControlFactoryGen;
import org.eclipse.emf.parsley.examples.mail.messageview.ui.provider.FeatureCaptionProviderGen;
import org.eclipse.emf.parsley.examples.mail.messageview.ui.provider.FeaturesProviderGen;
import org.eclipse.emf.parsley.examples.mail.messageview.ui.provider.LabelProviderGen;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
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
  public Class<? extends FeatureCaptionProvider> bindFeatureCaptionProvider() {
    return FeatureCaptionProviderGen.class;
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
