/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.edit.domain;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;

import com.google.inject.Singleton;

/**
 * This ensures that only one instance of {@link AdapterFactoryEditingDomain}
 * will be used by the same injector (according to the semantics of
 * {@link Singleton}).
 *
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
@Singleton
public class SingletonAdapterFactoryEditingDomainProvider extends DefaultAdapterFactoryEditingDomainProvider {

	private AdapterFactoryEditingDomain singleton;

	@Override
	public AdapterFactoryEditingDomain get() {
		if (singleton == null) {
			singleton = super.get();
		}
		return singleton;
	}
}
