package org.eclipse.emf.parsley.dsl.ui.wizard;

import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.xtext.ui.wizard.IProjectInfo;
import org.eclipse.xtext.ui.wizard.IProjectCreator;
import com.google.inject.Inject;

public class EmfParsleyDslNewProjectWizard extends org.eclipse.xtext.ui.wizard.XtextNewProjectWizard {

	private WizardNewProjectCreationPage mainPage;

	@Inject
	public EmfParsleyDslNewProjectWizard(IProjectCreator projectCreator) {
		super(projectCreator);
		setWindowTitle("New EmfParsleyDsl Project");
	}

	/**
	 * Use this method to add pages to the wizard.
	 * The one-time generated version of this class will add a default new project page to the wizard.
	 */
	public void addPages() {
		mainPage = new WizardNewProjectCreationPage("basicNewProjectPage");
		mainPage.setTitle("EmfParsleyDsl Project");
		mainPage.setDescription("Create a new EmfParsleyDsl project.");
		addPage(mainPage);
	}

	/**
	 * Use this method to read the project settings from the wizard pages and feed them into the project info class.
	 */
	@Override
	protected IProjectInfo getProjectInfo() {
		org.eclipse.emf.parsley.dsl.ui.wizard.EmfParsleyDslProjectInfo projectInfo = new org.eclipse.emf.parsley.dsl.ui.wizard.EmfParsleyDslProjectInfo();
		projectInfo.setProjectName(mainPage.getProjectName());
		return projectInfo;
	}

}
