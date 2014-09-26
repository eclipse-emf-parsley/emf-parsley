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

	private WizardNewProjectCreationPage _pageOne;
	
	private EmfParsleyChoiceTemplate _pageTwo;

	private final Logger log = Logger.getLogger(getClass());

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

		_pageOne = new WizardNewProjectCreationPage(WIZARD_PAGE);
		_pageOne.setTitle("Emf Component Project");
		_pageOne.setDescription("Create a plugin project for Emf Parsley.");
		
		_pageTwo = new EmfParsleyChoiceTemplate(WIZARD_CHOICE_PAGE);
        _pageTwo.setTitle("View type choice");
        _pageTwo.setDescription("Choose a View type.");

		addPage(_pageOne);
		addPage(_pageTwo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		final String name = _pageOne.getProjectName();
		URI _location = null;
		if (!_pageOne.useDefaults()) {
			_location = _pageOne.getLocationURI();
		} // else location == null
		final URI location = _location;

		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				NewEmfParsleyProjectSupport.createProject(name, location, _pageTwo.getChoice(),
						monitor);
			}
		};

		try {
			getContainer().run(true, false, op);
		} catch (Exception exception) {
			log.error("performFinish", exception);
			return false;
		}

		return true;
	}

}
