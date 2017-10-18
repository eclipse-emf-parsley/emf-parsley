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
package org.eclipse.emf.parsley.composite;

import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * A version of the form where all controls are by default readonly
 * controls.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class FormDetailReadOnlyComposite extends FormDetailComposite {

	/**
	 * @deprecated Use {@link #FormDetailReadOnlyComposite(Composite, int, int)}
	 */
	@Deprecated
	public FormDetailReadOnlyComposite(Composite parent, int style) {
		this(parent, style, 2);
	}

	/**
	 * @since 1.3
	 */
	public FormDetailReadOnlyComposite(Composite parent, int style, int gridColumns) {
		super(parent, style, gridColumns);
	}

	@Override
	@Inject
	public void setFormControlFactory(FormControlFactory formControlFactory) {
		formControlFactory.setReadonly(true);
		super.setFormControlFactory(formControlFactory);
	}

}
