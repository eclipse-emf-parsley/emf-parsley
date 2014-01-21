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
package org.eclipse.emf.parsley.edit.ui.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

import com.google.inject.Inject;

/**
 * @author Lorenzo Bettini
 * 
 *         based on the homonymous class in xtext.ui
 * 
 */
public class InjectableAdapterFactoryLabelProvider extends
		AdapterFactoryLabelProvider {

	@Inject
	public InjectableAdapterFactoryLabelProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public String getText(Object object) {
		IItemLabelProvider itemLabelProvider = (IItemLabelProvider) adapterFactory
				.adapt(object, IItemLabelProvider.class);
		return itemLabelProvider != null ? itemLabelProvider.getText(object)
				: object == null ? "" : object.toString();
	}
}
