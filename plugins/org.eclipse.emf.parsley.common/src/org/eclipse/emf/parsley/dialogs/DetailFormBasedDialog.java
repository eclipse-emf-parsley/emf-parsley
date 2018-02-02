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

package org.eclipse.emf.parsley.dialogs;

import org.eclipse.emf.parsley.composite.AbstractDetailComposite;
import org.eclipse.emf.parsley.composite.CompositeFactory;
import org.eclipse.emf.parsley.inject.parameters.DialogParameters;
import org.eclipse.emf.parsley.inject.parameters.EObjectParameter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 */
public class DetailFormBasedDialog extends AbstractDetailDialog {

	@Inject
	private CompositeFactory compositeFactory;

	/**
	 * @since 2.0
	 */
	@Inject
	public DetailFormBasedDialog(DialogParameters params, EObjectParameter eObjectParameter) {
		super(params, eObjectParameter);
	}

	@Override
	protected AbstractDetailComposite createDetailComposite(Composite composite) {
		return compositeFactory.createFormDetailComposite(composite, SWT.NONE);
	}

}
