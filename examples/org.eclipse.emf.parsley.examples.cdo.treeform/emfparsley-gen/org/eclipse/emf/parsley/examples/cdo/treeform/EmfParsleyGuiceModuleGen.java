/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.examples.cdo.treeform;

import org.eclipse.emf.parsley.cdo.CDOEmfParsleyModule;
import org.eclipse.emf.parsley.examples.cdo.treeform.ui.provider.FeaturesProviderGen;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.examples.cdo.treeform Emf Parsley Dsl Module file
 */
@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends CDOEmfParsleyModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends FeaturesProvider> bindFeaturesProvider() {
    return FeaturesProviderGen.class;
  }
}
