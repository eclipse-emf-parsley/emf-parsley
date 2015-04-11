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

import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.xtext.ui.wizard.IProjectCreator;
import org.eclipse.xtext.ui.wizard.IProjectInfo;

import com.google.inject.Inject;

/**
 * @author Francesco Guidieri
 * 
 */
public class EmfParsleyDslNewProjectWithPredefinedViewWizard extends EmfParsleyDslNewProjectWizard{

	private EmfParsleyDslProjectTemplateSelectionPage selectPredefinedViewPage;
	private WizardNewProjectCreationPage mainPage;

	@Inject
	public EmfParsleyDslNewProjectWithPredefinedViewWizard(
			IProjectCreator projectCreator) {
		super(projectCreator);
	}
	
	@Override
	public void addPages() {
		mainPage = new EmfParsleyDslNewProjectCreationPage("basicNewProjectPage");
		mainPage.setTitle("EmfParsleyDsl Project");
		mainPage.setDescription("Create a new EmfParsleyDsl project.");
		addPage(mainPage);
		selectPredefinedViewPage = new EmfParsleyDslProjectTemplateSelectionPage();
		selectPredefinedViewPage.setTitle("EmfParsleyDsl Project");
		selectPredefinedViewPage.setDescription("Create a new EmfParsleyDsl project.");
		addPage(selectPredefinedViewPage);
	}
	
	
	@Override
	protected IProjectInfo getProjectInfo() {
		org.eclipse.emf.parsley.dsl.ui.wizard.EmfParsleyDslProjectInfo projectInfo = new org.eclipse.emf.parsley.dsl.ui.wizard.EmfParsleyDslProjectInfo();
		projectInfo.setProjectName(mainPage.getProjectName());
		projectInfo.setSelectedTemplate(selectPredefinedViewPage.getSelectedTemplate());
		return projectInfo;
	}
	
	@Override
	public boolean canFinish() {
		if (mainPage.isPageComplete()) {
			return true;
		} else {
			return selectPredefinedViewPage.isPageComplete();
		}
    }

}
