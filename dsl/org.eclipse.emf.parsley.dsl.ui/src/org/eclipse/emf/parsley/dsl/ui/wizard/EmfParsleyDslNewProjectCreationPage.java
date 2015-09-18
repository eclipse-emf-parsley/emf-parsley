/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.ui.wizard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

/**
 * @author Francesco Guidieri
 * 
 */
public class EmfParsleyDslNewProjectCreationPage extends
		WizardNewProjectCreationPage {

	private Button fUseTemplate;
	private Button btnRapOption;

	public EmfParsleyDslNewProjectCreationPage(String pageName) {
		super(pageName);
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		container.setLayout(new GridLayout());
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		super.createControl(container);
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		fUseTemplate = new Button(composite, SWT.CHECK);
		fUseTemplate.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		fUseTemplate
				.setText("Create a Parsley plug-in using one of the templates");
		fUseTemplate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getContainer().updateButtons();
			}
		});
		fUseTemplate.setSelection(true);
		
		btnRapOption = new Button(composite, SWT.CHECK);
		btnRapOption.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		btnRapOption.setText("Prepare for Remote Application Platform (RAP)");

		
		setControl(container);
		 
	}
	
	public boolean isRapOption() {
		return btnRapOption.getSelection();
	}
	
	@Override
	public boolean canFlipToNextPage() {
		return  super.isPageComplete() && fUseTemplate.getSelection();
	}
	
	@Override
	public boolean isPageComplete() {
		return (!isCurrentPage())
				||(super.isPageComplete() && !fUseTemplate.getSelection());
	}
}
