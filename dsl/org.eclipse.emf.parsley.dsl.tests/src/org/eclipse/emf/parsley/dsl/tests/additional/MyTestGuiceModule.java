/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.emf.parsley.dsl.tests.additional;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.ui.plugin.AbstractUIPlugin;


/**
 * @author Lorenzo Bettini
 *
 */
public class MyTestGuiceModule extends EmfParsleyGuiceModule {

	public MyTestGuiceModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

}
