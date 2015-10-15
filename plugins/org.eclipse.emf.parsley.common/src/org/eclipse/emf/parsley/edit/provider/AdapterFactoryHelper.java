/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.edit.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;

import com.google.inject.Inject;

/**
 * Helper methods using {@link AdapterFactory}
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class AdapterFactoryHelper {

	@Inject
	private AdapterFactory adapterFactory;

	/**
	 * Returns the {@link IItemPropertyDescriptor} of the passed object for the
	 * specified feature.
	 * 
	 * @param notifier
	 * @param feature
	 * @return the property descriptor or null
	 */
	public IItemPropertyDescriptor getItemPropertyDescriptor(Notifier notifier, EStructuralFeature feature) {
		return ((IItemPropertySource) adapterFactory.adapt(notifier, IItemPropertySource.class))
				.getPropertyDescriptor(notifier, feature);
	}
}
