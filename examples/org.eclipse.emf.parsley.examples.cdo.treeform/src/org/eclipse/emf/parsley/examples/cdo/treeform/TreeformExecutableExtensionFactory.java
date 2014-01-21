/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.examples.cdo.treeform;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.cdo.CDOEmfParsleyExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

public class TreeformExecutableExtensionFactory extends
		CDOEmfParsleyExtensionFactory{

	@Override
	protected Bundle getBundle() {
		return TreeformCDOActivator.getDefault().getBundle();
	}

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return TreeformCDOActivator.getDefault().createModule();
	}

	@Override
	protected Injector getInjector() {
		return TreeformCDOActivator.getDefault().getInjector();
	}
}
