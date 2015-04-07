/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
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

/**
 * This ensures that only one instance of {@link AdapterFactoryEditingDomain} will
 * be used.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class GlobalAdapterFactoryEditingDomainProvider extends DefaultAdapterFactoryEditingDomainProvider {

	private static AdapterFactoryEditingDomain singleton;
	
	@Override
	public AdapterFactoryEditingDomain get() {
		if (singleton == null) {
			singleton = super.get();
		}
		return singleton;
	}

}
