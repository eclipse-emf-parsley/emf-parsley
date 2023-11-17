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
import org.eclipse.emf.parsley.composite.AbstractDetailComposite;
import org.eclipse.emf.parsley.composite.DialogDetailComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.google.inject.Inject;
import com.google.inject.MembersInjector;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class DialogFactory {

	@Inject
	protected MembersInjector<DetailFormBasedDialog> detailFormBasedDialogMembersInjection;

	@Inject
	protected MembersInjector<DetailDialog> detailDialogMembersInjection;

	@Inject
	protected MembersInjector<DialogDetailComposite> detailComponentDialogMembersInjection;

	@Inject
	public DialogFactory() {

	}

	public DetailFormBasedDialog createDetailFormBasedDialog(Shell parentShell,
			String title, EObject original, EditingDomain domain) {
		DetailFormBasedDialog dialog = new DetailFormBasedDialog(parentShell,
				title, original, domain);
		detailFormBasedDialogMembersInjection.injectMembers(dialog);
		return dialog;
	}

	public DetailDialog createDetailDialog(Shell parentShell, String title,
			EObject original, EditingDomain domain) {
		DetailDialog dialog = new DetailDialog(parentShell, title, original,
				domain);
		detailDialogMembersInjection.injectMembers(dialog);
		return dialog;
	}

	public AbstractDetailComposite createDialogDetailComposite(
			Composite parent, int style) {
		DialogDetailComposite detailComposite = new DialogDetailComposite(
				parent, style);
		detailComponentDialogMembersInjection.injectMembers(detailComposite);
		return detailComposite;
	}

}
