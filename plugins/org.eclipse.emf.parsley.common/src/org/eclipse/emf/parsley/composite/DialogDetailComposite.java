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


import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameters;
import org.eclipse.swt.layout.GridLayout;

import com.google.inject.Inject;

public class DialogDetailComposite extends AbstractDetailComposite {

	@Inject
	private CompositeFactory compositeFactory;

	/**
	 * @since 2.0
	 */
	@Inject
	public DialogDetailComposite(CompositeParameters params) {
		super(params);

		setLayout(new GridLayout(2, false));
	}

	/**
	 * @since 2.0
	 */
	@Override
	protected DialogControlFactory createControlFactory(EObject object, EditingDomain editingDomain) {
		return compositeFactory.createDialogControlFactory(this, object, editingDomain);
	}

}
