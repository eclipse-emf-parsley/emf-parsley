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
package org.eclipse.emf.parsley.wizards;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

/**
 * @author Lorenzo Bettini
 * 
 */
public class NewEmfParsleyProjectWizard extends Wizard implements INewWizard {

	private WizardNewProjectCreationPage pageOne;
	
	private EmfParsleyChoiceTemplate pageTwo;

	private static final Logger LOGGER = Logger.getLogger(NewEmfParsleyProjectWizard.class);

	protected static final String WIZARD_PAGE = "Emf Parsley Project Wizard";
	
	protected static final String WIZARD_CHOICE_PAGE = "Emf Parsley Project Wizard Template";

	protected static final String WIZARD_NAME = "New Emf Parsley Project";

	/**
	 * 
	 */
	public NewEmfParsleyProjectWizard() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 * org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setNeedsProgressMonitor(true);
		setWindowTitle(WIZARD_NAME);
	}

	public void addPages() {
		super.addPages();

		pageOne = new WizardNewProjectCreationPage(WIZARD_PAGE);
		pageOne.setTitle("Emf Component Project");
		pageOne.setDescription("Create a plugin project for Emf Parsley.");
		
		pageTwo = new EmfParsleyChoiceTemplate(WIZARD_CHOICE_PAGE);
        pageTwo.setTitle("View type choice");
        pageTwo.setDescription("Choose a View type.");

		addPage(pageOne);
		addPage(pageTwo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		final String name = pageOne.getProjectName();
		URI loc = null;
		if (!pageOne.useDefaults()) {
			loc = pageOne.getLocationURI();
		}
		final URI location = loc;

		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				NewEmfParsleyProjectSupport.createProject(name, location, pageTwo.getChoice(),
						monitor);
			}
		};

		try {
			getContainer().run(true, false, op);
		} catch (Exception exception) {
			LOGGER.error("performFinish", exception);
			return false;
		}

		return true;
	}

}
