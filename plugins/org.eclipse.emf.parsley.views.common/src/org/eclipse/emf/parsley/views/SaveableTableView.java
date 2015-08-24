/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.views;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.config.Configurator;

import com.google.inject.Inject;

/**
 * This implements the abstract methods by delegating to an injected
 * {@link Configurator}.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class SaveableTableView extends AbstractSaveableTableView {

	@Inject
	private Configurator configurator;

	@Override
	protected URI createResourceURI() {
		return configurator.createResourceURI(this);
	}

	@Override
	public Object getContents(Resource resource) {
		return configurator.getContents(this, resource);
	}

	@Override
	protected EClass getEClass() {
		return configurator.getEClass(this);
	}

}
