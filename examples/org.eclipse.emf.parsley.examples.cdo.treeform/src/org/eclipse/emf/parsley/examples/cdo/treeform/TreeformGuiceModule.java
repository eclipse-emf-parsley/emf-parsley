/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Vincenzo Caselli - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.examples.cdo.treeform;

import org.eclipse.emf.parsley.examples.cdo.treeform.customizations.TreeformCDOEmptyResourceInitializer;
import org.eclipse.emf.parsley.resource.EmptyResourceInitializer;
import org.eclipse.ui.plugin.AbstractUIPlugin;


public class TreeformGuiceModule extends EmfParsleyGuiceModuleGen {

	public TreeformGuiceModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@Override
	public Class<? extends EmptyResourceInitializer> bindEmptyResourceInitializer() {
		return TreeformCDOEmptyResourceInitializer.class;
	}

}
