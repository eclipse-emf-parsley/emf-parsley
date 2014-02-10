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
package org.eclipse.emf.parsley.tests.factories;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.listeners.IEditorMouseListener;
import org.eclipse.emf.parsley.listeners.NoOpMouseAdapter;
import org.eclipse.emf.parsley.tests.EmfParsleyTestsActivator;

/**
 * Uses a mouse adapter that does not react to mouse events.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class NoMouseEventExecutableExtensionFactory extends
		EmfParsleyTestsExecutableExtensionFactory {

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return new EmfParsleyGuiceModule(EmfParsleyTestsActivator.getDefault()) {

			@Override
			public Class<? extends IEditorMouseListener> bindIEditorMouseListener() {
				return NoOpMouseAdapter.class;
			}

		};
	}

}
