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
package org.eclipse.emf.parsley.composite;

import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * A facility base class for {@link Composite} instances that are meant to be
 * created via injection.
 * 
 * @author Lorenzo Bettini
 * @since 2.0
 *
 */
public class InjectableComposite extends Composite {

	@Inject
	public InjectableComposite(CompositeParameters params) {
		super(params.getParent(), params.getStyle());
	}

}
