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
package org.eclipse.emf.parsley.listeners;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.dialogs.DetailFormBasedDialog;
import org.eclipse.emf.parsley.dialogs.DialogFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.widgets.Shell;

import com.google.inject.Inject;

/**
 * A {@link MouseAdapter} that opens a {@link DetailFormBasedDialog} on double
 * click.
 *
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class OpenFormBasedDialogMouseAdapter extends OpenDialogMouseAdapter
		implements IEditorMouseListener {

	@Inject
	private DialogFactory dialogFactory;

	@Override
	protected Dialog createDialog(EObject o, EditingDomain editingDomain,
			Shell activeShell, String title) {
		return dialogFactory.createDetailFormBasedDialog(activeShell, title, o,
				editingDomain);
	}
}
