/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.examples.mail.unifiedfoldersview;

import org.osgi.framework.Bundle;

import org.eclipse.emf.parsley.EmfParsleyExtensionFactory;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;

import com.google.inject.Injector;

public class UnifiedfoldersviewExecutableExtensionFactory extends
		EmfParsleyExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return UnifiedfoldersviewActivator.getDefault().getBundle();
	}

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return UnifiedfoldersviewActivator.getDefault().createModule();
	}

	@Override
	protected Injector getInjector() {
		return UnifiedfoldersviewActivator.getDefault().getInjector();
	}
}
