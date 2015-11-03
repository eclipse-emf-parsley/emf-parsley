/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
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
import org.eclipse.emf.edit.domain.EditingDomain;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * The default implementation is to delegate to a {@link Provider} for
 * {@link AdapterFactoryEditingDomain}.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class DefaultEditingDomainProvider implements Provider<EditingDomain> {

	@Inject
	private Provider<AdapterFactoryEditingDomain> delegateProvider;

	@Override
	public EditingDomain get() {
		return delegateProvider.get();
	}

}
