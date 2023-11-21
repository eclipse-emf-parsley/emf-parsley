/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.views;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.composite.CompositeFactory;
import org.eclipse.emf.parsley.composite.FormDetailComposite;
import org.eclipse.swt.SWT;

import com.google.inject.Inject;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class OnSelectionReadOnlyFormView extends OnSelectionFormView {

	@Inject
	private CompositeFactory compositeFactory;

	@Override
	protected FormDetailComposite createFormDetailComposite(EObject eObject) {
		return compositeFactory.createFormDetailReadOnlyComposite(parent, SWT.NONE, eObject);
	}
}
