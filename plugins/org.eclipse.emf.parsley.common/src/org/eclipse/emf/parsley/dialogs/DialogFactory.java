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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.inject.DetailDialogParameters;
import org.eclipse.emf.parsley.internal.inject.GenericFactory;
import org.eclipse.swt.widgets.Shell;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 * 
 */
@Singleton
public class DialogFactory {

	@Inject
	private GenericFactory<AbstractDetailDialog, DetailDialogParameters> detailDialogFactory;

	public DetailFormBasedDialog createDetailFormBasedDialog(Shell parentShell, String title, EObject object,
			EditingDomain editingDomain) {
		return detailDialogFactory.createInstance(DetailFormBasedDialog.class,
				new DetailDialogParameters(parentShell, title, object, editingDomain));
	}

	public DetailDialog createDetailDialog(Shell parentShell, String title, EObject object,
			EditingDomain editingDomain) {
		return detailDialogFactory.createInstance(DetailDialog.class,
				new DetailDialogParameters(parentShell, title, object, editingDomain));
	}

}
