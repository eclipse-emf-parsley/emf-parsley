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
import org.eclipse.emf.parsley.edit.IEditingStrategy;
import org.eclipse.emf.parsley.util.EmfEventHelper;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.google.inject.Inject;

/**
 * A {@link MouseAdapter} that opens a {@link DetailFormBasedDialog} on double
 * click.
 *
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class OpenDialogMouseAdapter extends MouseAdapter implements
		IEditorMouseListener {

	@Inject
	private EmfEventHelper helper;

	@Inject
	private DialogFactory dialogFactory;

	@Inject
	private ILabelProvider labelProvider;

	@Inject
	protected IEditingStrategy editingStrategy;

	@Override
	public void mouseDoubleClick(MouseEvent event) {
		if (event.button == 1) {
			EObject o = helper.getEObjectFromMouseEvent(event);
			if (o != null) {
				editingStrategy.prepare(o);
				Dialog dialog = createDialog(Display
						.getCurrent().getActiveShell(),
						labelProvider.getText(o), o,
						editingStrategy.getEditingDomain(o));
				int rc = dialog.open();
				if (rc == Window.OK) {
					editingStrategy.update(o);
				} else {
					editingStrategy.rollback(o);
				}
			}
		}
	}

	/**
	 * @since 2.0
	 */
	protected Dialog createDialog(Shell parentShell, String title,
			EObject object, EditingDomain editingDomain) {
		return dialogFactory.createDetailDialog(parentShell, title, object,
				editingDomain);
	}
}
