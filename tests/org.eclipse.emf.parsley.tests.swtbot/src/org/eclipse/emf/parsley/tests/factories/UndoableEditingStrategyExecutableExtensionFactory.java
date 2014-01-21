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
package org.eclipse.emf.parsley.tests.factories;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.edit.UndoableEditingStrategy;
import org.eclipse.emf.parsley.edit.IEditingStrategy;
import org.eclipse.emf.parsley.listeners.IEditorMouseListener;
import org.eclipse.emf.parsley.listeners.OpenDialogMouseAdapter;
import org.eclipse.emf.parsley.tests.EmfParsleyTestsActivator;



/**
 * The editing takes place on a copy of the object.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class UndoableEditingStrategyExecutableExtensionFactory extends
		EmfParsleyTestsExecutableExtensionFactory {

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return new EmfParsleyGuiceModule(
				EmfParsleyTestsActivator.getDefault()) {
			
			@Override
			public Class<? extends IEditorMouseListener> bindIEditorMouseListener() {
				return OpenDialogMouseAdapter.class;
			}

			@Override
			public Class<? extends IEditingStrategy> bindIEditingStrategy() {
				return UndoableEditingStrategy.class;
			}

		};
	}

}
