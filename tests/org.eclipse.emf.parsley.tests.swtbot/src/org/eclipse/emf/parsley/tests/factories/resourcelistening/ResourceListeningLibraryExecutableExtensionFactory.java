/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.factories.resourcelistening;

import org.eclipse.emf.parsley.tests.EmfParsleyTestsActivator;
import org.eclipse.emf.parsley.tests.factories.EmfParsleyTestsExecutableExtensionFactory;

import com.google.inject.Injector;

/**
 * Uses an editable table
 * 
 * @author Francesco Guidieri
 * 
 */
public class ResourceListeningLibraryExecutableExtensionFactory extends
		EmfParsleyTestsExecutableExtensionFactory {

	@Override
	public Injector getInjector() {
		return EmfParsleyTestsActivator.getDefault().getInjector(
				this.getClass());
	}

}
