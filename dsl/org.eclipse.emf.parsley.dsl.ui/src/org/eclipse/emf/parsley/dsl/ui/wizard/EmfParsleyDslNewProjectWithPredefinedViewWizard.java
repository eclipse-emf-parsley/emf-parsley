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

import org.eclipse.xtext.ui.wizard.IExtendedProjectInfo;
import org.eclipse.xtext.ui.wizard.IProjectCreator;

import com.google.inject.Inject;

/**
 * @author Francesco Guidieri
 *
 */
public class EmfParsleyDslNewProjectWithPredefinedViewWizard extends EmfParsleyDslNewProjectWizard {

	private EmfParsleyDslProjectTemplateSelectionPage selectPredefinedViewPage;
	private EmfParsleyDslNewProjectCreationPage mainPage;

	@Inject
	public EmfParsleyDslNewProjectWithPredefinedViewWizard(IProjectCreator projectCreator) {
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
	protected IExtendedProjectInfo getProjectInfo() {
		EmfParsleyDslProjectInfo projectInfo = new EmfParsleyDslProjectInfo();
		projectInfo.setProjectName(mainPage.getProjectName());
		if (!mainPage.useDefaults()) {
			projectInfo.setLocationPath(mainPage.getLocationPath());
		}
		projectInfo.setSelectedTemplate(selectPredefinedViewPage.getSelectedTemplate());
		projectInfo.setRapOption(mainPage.isRapOption());
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
