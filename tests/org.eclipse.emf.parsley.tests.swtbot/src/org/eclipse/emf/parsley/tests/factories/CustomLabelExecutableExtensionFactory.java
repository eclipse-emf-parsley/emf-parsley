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


import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.tests.EmfParsleySwtBotTestsActivator;
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;

import com.google.inject.Inject;

/**
 * Uses a custom label provider
 * 
 * @author Lorenzo Bettini
 * 
 */
public class CustomLabelExecutableExtensionFactory extends
		EmfParsleyTestsExecutableExtensionFactory {

	public static class CustomLabelProvider extends ViewerLabelProvider {
		@Inject
		public CustomLabelProvider(AdapterFactoryLabelProvider delegate) {
			super(delegate);
		}

		@Override
		public String getText(Object element) {
			if (element instanceof XMLResource) {
				return super.getText(element);
			}
			return "TEST " + super.getText(element) + " ENDTEST";
		}
	}

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return new EmfParsleyGuiceModule(EmfParsleySwtBotTestsActivator.getDefault()) {

			@Override
			public Class<? extends ILabelProvider> bindILabelProvider() {
				return CustomLabelProvider.class;
			}

		};
	}

}
