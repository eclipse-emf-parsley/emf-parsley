/*******************************************************************************
 * Copyright (c) 2018 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.inject.parameters;

import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * A wrapper class for a Composite that can be used in a constructor annotated
 * with {@link Inject}; the corresponding Composite should be created only
 * through one of our factories.
 * 
 * @author Lorenzo Bettini
 * @since 2.0
 *
 */
@FactoryParameter
public class CompositeParameter extends GenericInjectableParameter<Composite> {

	public CompositeParameter() {
		// required by Guice
	}

	public CompositeParameter(Composite composite) {
		super(composite);
	}

	public final Composite getComposite() {
		return getWrapped();
	}
}
