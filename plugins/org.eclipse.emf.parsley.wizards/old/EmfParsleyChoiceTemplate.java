/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.wizards;

import org.eclipse.jdt.ui.wizards.NewElementWizardPage;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.ide.IIDEHelpContextIds;

@SuppressWarnings("restriction")
public class EmfParsleyChoiceTemplate extends NewElementWizardPage {
    
	public enum Choice {
		NONE, TREEFORM, TABLEFORM
	}
	
    private Choice choice = Choice.NONE;

    public EmfParsleyChoiceTemplate(String name) {
        super(name);
    }
    
    @Override
	public void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.NULL);
        
        initializeDialogUnits(parent);
        
        PlatformUI.getWorkbench().getHelpSystem().setHelp(composite,
                IIDEHelpContextIds.NEW_PROJECT_WIZARD_PAGE);
        
        composite.setLayout(new GridLayout());
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        Button choiceTree = new Button(composite, SWT.RADIO);
        choiceTree.setText("Create a TreeFormView");
        choiceTree.setSelection(false);
        choiceTree.addSelectionListener(new SelectionAdapter() {
            
            @Override
            public void widgetSelected(SelectionEvent e) {
                choice = Choice.TREEFORM;
            }
        });
        
        Button choiceTable = new Button(composite, SWT.RADIO);
        choiceTable.setText("Create a TableView");
        choiceTable.setSelection(false);
        choiceTable.addSelectionListener(new SelectionAdapter() {
            
            @Override
            public void widgetSelected(SelectionEvent e) {
                choice = Choice.TABLEFORM;
            }
        });

        // Show description on opening
        setErrorMessage(null);
        setMessage(null);
        setControl(composite);
        Dialog.applyDialogFont(composite);
    }
    
    public Choice getChoice() {
        return choice;
    }
    
}
