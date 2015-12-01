/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Vincenzo Caselli - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.web.tools;


import org.eclipse.emf.parsley.web.tools.ParsleyWebFacetInstallConfig.PERSISTENCE_OPTION;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.wst.common.project.facet.ui.AbstractFacetWizardPage;

/**
* Wizard page for the Parsley-JEE project custom options
* 
* @author Vincenzo Caselli
* 
*/
public class ParsleyWebFacetInstallPage extends AbstractFacetWizardPage {
//    private Image imageCd = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_CONTENT_PROPOSAL).getImage();

    private ParsleyWebFacetInstallConfig config;

    public ParsleyWebFacetInstallPage() {
        super("parsleyweb.core.facet.install.page");

        setTitle("EMF Parsley Web project options");
        setDescription("Configure the EMF Parsley Web project options.");
    }

    public void createControl(final Composite parent) {
        final Composite composite = new Composite(parent, SWT.NONE);

        setControl(composite);
        composite.setLayout(new GridLayout(1, false));

        Group grpPersistenceOptions = new Group(composite, SWT.NONE);
        RowLayout rl_grpPersistenceOptions = new RowLayout(SWT.VERTICAL);
        rl_grpPersistenceOptions.marginTop = 20;
        rl_grpPersistenceOptions.marginLeft = 20;
        rl_grpPersistenceOptions.spacing = 20;
        rl_grpPersistenceOptions.justify = true;
        grpPersistenceOptions.setLayout(rl_grpPersistenceOptions);
        grpPersistenceOptions.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
        grpPersistenceOptions.setText("Persistence options");

        Button btnPersistenceNone = new Button(grpPersistenceOptions, SWT.RADIO);
        btnPersistenceNone.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

            }
        });
        btnPersistenceNone.setSelection(true);
        config.setPersistenceOption(PERSISTENCE_OPTION.NONE);
        btnPersistenceNone.setText("None");

        Button btnPersistenceTeneo = new Button(grpPersistenceOptions, SWT.RADIO);
        btnPersistenceTeneo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                config.setPersistenceOption(PERSISTENCE_OPTION.TENEO);
            }
        });
        btnPersistenceTeneo.setText("Teneo");

        Button btnPersistenceCdo = new Button(grpPersistenceOptions, SWT.RADIO);
        btnPersistenceCdo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                config.setPersistenceOption(PERSISTENCE_OPTION.CDO);
            }
        });
        btnPersistenceCdo.setText("CDO");
    }

    public void setConfig(final Object config) {
        this.config = (ParsleyWebFacetInstallConfig) config;
    }

    public void transferStateToConfig() {
        // this.config.setModelsList(this.modelsList.getItems());
    }

}

