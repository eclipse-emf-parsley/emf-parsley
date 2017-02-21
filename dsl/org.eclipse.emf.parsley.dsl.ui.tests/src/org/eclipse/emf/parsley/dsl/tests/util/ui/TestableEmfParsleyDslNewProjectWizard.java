/**
 * 
 */
package org.eclipse.emf.parsley.dsl.tests.util.ui;

import org.eclipse.emf.parsley.dsl.ui.wizard.EmfParsleyDslNewProjectWizard;
import org.eclipse.xtext.ui.wizard.IExtendedProjectInfo;
import org.eclipse.xtext.ui.wizard.IProjectCreator;

import com.google.inject.Inject;

/**
 * Manually set the project name (usually set in the dialog text edit)
 * 
 * @author Lorenzo Bettini
 */
public class TestableEmfParsleyDslNewProjectWizard extends
		EmfParsleyDslNewProjectWizard {

	public static final String TEST_PROJECT = "testproject";

	@Inject
	public TestableEmfParsleyDslNewProjectWizard(IProjectCreator projectCreator) {
		super(projectCreator);
	}

	@Override
	public IExtendedProjectInfo getProjectInfo() {
		IExtendedProjectInfo projectInfo = super.getProjectInfo();
		projectInfo.setProjectName(TEST_PROJECT);
		return projectInfo;
	}
}
