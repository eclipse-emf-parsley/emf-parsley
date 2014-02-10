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

import org.eclipse.emf.parsley.widgets.FormDetailComposite;
import org.eclipse.swt.SWT;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class OnSelectionReadOnlyFormView extends OnSelectionFormView {

	@Override
	protected FormDetailComposite createFormDetailComposite() {
		return formFactory.createFormDetailReadOnlyComposite(
				parent, SWT.NONE);
	}
}
