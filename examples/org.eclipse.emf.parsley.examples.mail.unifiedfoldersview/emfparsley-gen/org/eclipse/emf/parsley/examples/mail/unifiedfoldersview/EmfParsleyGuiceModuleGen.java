/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.examples.mail.unifiedfoldersview;

import org.eclipse.emf.parsley.examples.mail.accountsview.AccountsviewGuiceModule;
import org.eclipse.emf.parsley.examples.mail.unifiedfoldersview.edit.ui.provider.ViewerContentProviderGen;
import org.eclipse.emf.parsley.examples.mail.unifiedfoldersview.ui.provider.LabelProviderGen;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * org.eclipse.emf.parsley.examples.mail.unifiedfoldersview Emf Parsley Dsl Module file
 */
@SuppressWarnings("all")
public class EmfParsleyGuiceModuleGen extends AccountsviewGuiceModule {
  public EmfParsleyGuiceModuleGen(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  @Override
  public Class<? extends ILabelProvider> bindILabelProvider() {
    return LabelProviderGen.class;
  }
  
  @Override
  public Class<? extends IContentProvider> bindIContentProvider() {
    return ViewerContentProviderGen.class;
  }
}
