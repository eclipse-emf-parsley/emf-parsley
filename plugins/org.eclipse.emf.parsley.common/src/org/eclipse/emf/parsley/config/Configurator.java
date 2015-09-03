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
package org.eclipse.emf.parsley.config;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcher;

/**
 * This configures several aspects of many components in Emf Parsley, such as,
 * for instance, the Resource of a view, the EClass for the objects to
 * be presented in a view, etc; the configuration will take place polymorphically
 * according to the requestor, passed as a parameter of the methods.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class Configurator {

	private PolymorphicDispatcher<URI> createResourceUriDispatcher = PolymorphicDispatcher
			.createForSingleTarget("resourceURI", 1, 1, this);

	private PolymorphicDispatcher<EClass> getEClassDispatcher = PolymorphicDispatcher
			.createForSingleTarget("eClass", 1, 1, this);

	/**
	 * Returns the {@link URI} of the resource for the requestor
	 * @param requestor
	 * @return
	 */
	public URI createResourceURI(Object requestor) {
		return createResourceUriDispatcher.invoke(requestor);
	}

	/**
	 * Returns the {@link URI} of the resource for the requestor for any use the requestor may need it
	 * @param requestor
	 * @return
	 */
	public URI resourceURI(Object requestor) {
		return null;
	}

	/**
	 * Returns the {@link EClass} for the requestor for any use the requestor may need it
	 * @param requestor
	 * @return
	 */
	public EClass getEClass(Object requestor) {
		return getEClassDispatcher.invoke(requestor);
	}

	/**
	 * Returns the {@link EClass} for the requestor
	 * @param requestor
	 * @return
	 */
	public EClass eClass(Object requestor) {
		return null;
	}

}
