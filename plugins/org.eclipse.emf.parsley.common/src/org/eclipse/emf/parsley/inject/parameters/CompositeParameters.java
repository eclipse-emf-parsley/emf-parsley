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

import org.eclipse.emf.parsley.inject.GenericCompositeFactory;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * A wrapper class for parameters for a Composite, that is, parent and style, that can be used
 * in a constructor annotated with {@link Inject}; the corresponding Composite should be
 * created only through an injected {@link GenericCompositeFactory}.
 * 
 * @author Lorenzo Bettini
 * @since 2.0
 *
 */
@FactoryParameter
public class CompositeParameters implements InjectableParameter {
	private Composite parent;

	private int style;

	public CompositeParameters() {
		// required by Guice
	}

	public CompositeParameters(Composite parent, int style) {
		this.parent = parent;
		this.style = style;
	}

	public Composite getParent() {
		return parent;
	}

	public int getStyle() {
		return style;
	}
}
