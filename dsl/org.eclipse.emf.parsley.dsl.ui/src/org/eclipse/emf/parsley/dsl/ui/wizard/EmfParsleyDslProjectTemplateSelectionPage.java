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

import java.util.Iterator;

import org.eclipse.emf.parsley.dsl.ui.wizard.template.TemplateWizardConfiguration;
import org.eclipse.emf.parsley.dsl.ui.wizard.template.TemplateWizardConfigurationsFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.pde.internal.ui.parts.FormBrowser;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.ide.IIDEHelpContextIds;

/**
 * @author Francesco Guidieri
 * 
 */
public class EmfParsleyDslProjectTemplateSelectionPage extends WizardPage implements ISelectionChangedListener{

	public static final String ONSELECTION_CATEGORY = "On selection";
	public static final String SAVEABLE_CATEGORY = "Saveable";

	private TableViewer templateSelectionViewer;
	private GridData gd;
	private FormBrowser descriptionBrowser;
	
	protected EmfParsleyDslProjectTemplateSelectionPage() {
		super("emfParsleySelectPredefinedViewPage");
		descriptionBrowser = new FormBrowser(SWT.BORDER | SWT.V_SCROLL);
		descriptionBrowser.setText(""); //$NON-NLS-1$
	}
	
	public void createDescriptionIn(Composite composite) {
		descriptionBrowser.createControl(composite);
		Control c = descriptionBrowser.getControl();
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.widthHint = 200;
		c.setLayoutData(gridData);
	}
	
	public void setDescriptionText(String text) {
		descriptionBrowser.setText(text == null ? "No Description available." : text);
	}

	public void setDescriptionEnabled(boolean enabled) {
		Control dcontrol = descriptionBrowser.getControl();
		if (dcontrol != null) {
			dcontrol.setEnabled(enabled);
		}
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		initializeDialogUnits(parent);

		PlatformUI.getWorkbench().getHelpSystem()
				.setHelp(container, IIDEHelpContextIds.NEW_PROJECT_WIZARD_PAGE);

		container.setLayout(new GridLayout(2,false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		new Label(container,SWT.NONE).setText("Select one of these Emf Parsley templates");
		new Label(container,SWT.NONE);
		
		SashForm sashForm = new SashForm(container, SWT.HORIZONTAL);
		gd = new GridData(GridData.FILL_BOTH);
		// limit the width of the sash form to avoid the wizard
		// opening very wide. This is just preferred size - 
		// it can be made bigger by the wizard
		// See bug #83356
		gd.widthHint = 300;
		sashForm.setLayoutData(gd);

		templateSelectionViewer = new TableViewer(sashForm, SWT.BORDER);
		templateSelectionViewer.setContentProvider(new ArrayContentProvider());
		templateSelectionViewer.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				return ((TemplateWizardConfiguration)element).getLabel();
			}
		});
		
		createDescriptionIn(sashForm);
		templateSelectionViewer.setInput(new TemplateWizardConfigurationsFactory().createTemplateWizardConfigurations());
		templateSelectionViewer.addSelectionChangedListener(this);
		Dialog.applyDialogFont(container);
		setPageComplete(false);
		setControl(container);
	}
	
	public TemplateWizardConfiguration getSelectedTemplate(){
		return (TemplateWizardConfiguration) ((IStructuredSelection)templateSelectionViewer.getSelection()).getFirstElement();
	}
	
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		setErrorMessage(null);
		IStructuredSelection selection = (IStructuredSelection) event.getSelection();
		TemplateWizardConfiguration currentWizardSelection = null;
		Iterator<?> iter = selection.iterator();
		if (iter.hasNext()) {
			currentWizardSelection = (TemplateWizardConfiguration) iter.next();
		}
		if (currentWizardSelection == null) {
			setDescriptionText(""); //$NON-NLS-1$
		} else{
			setDescriptionText(currentWizardSelection.getDescription().toString());			
		}
		setPageComplete(currentWizardSelection!=null);
		getContainer().updateButtons();
	}
	
}
