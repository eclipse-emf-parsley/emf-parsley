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
import org.eclipse.emf.parsley.listeners.OpenFormBasedDialogMouseAdapter;
import org.eclipse.emf.parsley.tests.EmfParsleyTestsActivator;

/**
 * Opens a dialog on double click.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class OpenFormDialogMouseEventExecutableExtensionFactory extends
		EmfParsleyTestsExecutableExtensionFactory {

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return new EmfParsleyGuiceModule(EmfParsleyTestsActivator.getDefault()) {

			@Override
			public Class<? extends IEditorMouseListener> bindIEditorMouseListener() {
				return OpenFormBasedDialogMouseAdapter.class;
			}

		};
	}

}
