/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.osgi.framework.BundleContext;

public class EmfParsleyTestsActivator extends org.eclipse.emf.parsley.ui.EmfParsleyAbstractActivator {

	private static EmfParsleyTestsActivator plugin = new EmfParsleyTestsActivator();

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		super.start(bundleContext);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		plugin = null;
		super.stop(bundleContext);
	}
	
	public static EmfParsleyTestsActivator getDefault() {
		return plugin;
	}

	@Override
	protected EmfParsleyGuiceModule createModule() {
		return new EmfParsleyGuiceModule(getDefault());
	}

}
