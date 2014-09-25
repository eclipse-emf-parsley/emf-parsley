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
package org.eclipse.emf.parsley.tests

import com.google.inject.Binder
import org.eclipse.emf.parsley.EmfParsleyGuiceModule
import org.eclipse.emf.parsley.tests.ui.util.TestImageHelper

/**
 * A custom module just for testing, avoiding to initialize
 * things related to an OSGI environment
 */
class EmfParsleyGuiceModuleForTesting extends EmfParsleyGuiceModule {
	new () {
		super(EmfParsleyTestsActivator.getDefault)
	}
	
	override configure(Binder binder) {
		val compound = getBindings();
		compound.configure(binder);
	}
	
	override bindIImageHelper() {
		TestImageHelper
	}
}