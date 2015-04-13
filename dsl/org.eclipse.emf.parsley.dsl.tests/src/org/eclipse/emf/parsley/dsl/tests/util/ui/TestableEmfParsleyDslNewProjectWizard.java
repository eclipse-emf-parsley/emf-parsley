/**
 * 
 */
package org.eclipse.emf.parsley.dsl.tests.util.ui;

import org.eclipse.emf.parsley.dsl.ui.wizard.EmfParsleyDslNewProjectWizard;
import org.eclipse.xtext.ui.wizard.IProjectCreator;
import org.eclipse.xtext.ui.wizard.IProjectInfo;

import com.google.inject.Inject;

/**
 * Manually set the project name (usually set in the dialog text edit)
 * 
 * @author Lorenzo Bettini
 */
public class TestableEmfParsleyDslNewProjectWizard extends
		EmfParsleyDslNewProjectWizard {

	public static final String TEST_PROJECT = "TestProject";

	@Inject
	public TestableEmfParsleyDslNewProjectWizard(IProjectCreator projectCreator) {
		super(projectCreator);
	}

	@Override
	public IProjectInfo getProjectInfo() {
		IProjectInfo projectInfo = super.getProjectInfo();
		projectInfo.setProjectName(TEST_PROJECT);
		return projectInfo;
	}
}
