/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 * Francesco Guidieri - Initial contribution and API
 *******************************************************************************/

package org.eclipse.emf.parsley.dialogs;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.composite.AbstractDetailComposite;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 * @author Francesco Guidieri - Initial contribution and API
 */
public abstract class AbstractDetailDialog extends Dialog {

	private String title;
	private EObject original;
	private EditingDomain domain;

	/**
	 * Initializes this dialog for editing the {@link EObject} toBeEdited;
	 * the {@link EObject} original is needed to retrieve additional information
	 * such as the {@link EditingDomain} and the containing {@link Resource}.
	 *
	 * @param parentShell
	 * @param title
	 * @param original
	 * @param toBeEdited
	 */
	public AbstractDetailDialog(Shell parentShell, String title,
			EObject original, EditingDomain domain) {
		super(parentShell);
		setShellStyle(getShellStyle() | SWT.RESIZE | SWT.TITLE | SWT.MAX);
		this.title = title;
		this.original = original;
		this.domain = domain;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		if (title != null) {
			newShell.setText(title);
		}
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogArea = (Composite) super.createDialogArea(parent);
		Composite composite = new Composite(dialogArea, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		final AbstractDetailComposite detailEmfComponent = createDetailComposite(composite);
		detailEmfComponent.init(original, domain);
		detailEmfComponent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));
		GridDataFactory.fillDefaults().grab(true, true).applyTo(composite);
		createCustomArea(dialogArea);
		return dialogArea;
	}

	protected abstract AbstractDetailComposite createDetailComposite(
			Composite composite);

	protected Composite createCustomArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		applyDialogFont(composite);
		return composite;
	}

}
