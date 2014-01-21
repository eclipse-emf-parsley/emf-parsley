/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.examples.eclipse4.parsleypart;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.examples.eclipse4.parsleypart.ui.provider.FeatureCaptionProviderGen;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.examples.eclipse4.parsleypart Emf Components Dsl Module file
 */
@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends EmfParsleyGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends FeatureCaptionProvider> bindFeatureCaptionProvider() {
    return FeatureCaptionProviderGen.class;
  }
}
