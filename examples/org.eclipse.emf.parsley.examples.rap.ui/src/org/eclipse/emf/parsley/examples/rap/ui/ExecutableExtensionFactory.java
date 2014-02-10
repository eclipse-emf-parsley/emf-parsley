/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.examples.rap.ui;


import org.eclipse.emf.parsley.EmfParsleyExtensionFactory;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.osgi.framework.Bundle;

public class ExecutableExtensionFactory extends
		EmfParsleyExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return Activator.getDefault().getBundle();
	}

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return new GuiceModule(Activator.getDefault());
	}

}
