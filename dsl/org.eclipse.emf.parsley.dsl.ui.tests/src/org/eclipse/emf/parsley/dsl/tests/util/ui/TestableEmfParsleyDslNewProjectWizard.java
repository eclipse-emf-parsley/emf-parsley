/**
 * 
 */
package org.eclipse.emf.parsley.dsl.tests.util.ui;

import org.eclipse.emf.parsley.dsl.ui.wizard.EmfParsleyDslProjectTemplateProvider;
import org.eclipse.xtext.ui.wizard.IExtendedProjectInfo;
import org.eclipse.xtext.ui.wizard.template.TemplateNewProjectWizard;
import org.eclipse.xtext.ui.wizard.template.TemplateProjectInfo;

import com.google.inject.Inject;

/**
 * Manually set the project name (usually set in the dialog text edit)
 * 
 * @author Lorenzo Bettini
 */
public class TestableEmfParsleyDslNewProjectWizard extends
		TemplateNewProjectWizard {

	public static final String TEST_PROJECT = "testproject";

	@Inject
	private EmfParsleyDslProjectTemplateProvider templates;

	@Override
	public IExtendedProjectInfo getProjectInfo() {
		IExtendedProjectInfo projectInfo = new TemplateProjectInfo(templates.getProjectTemplates()[0]);
		projectInfo.setProjectName(TEST_PROJECT);
		return projectInfo;
	}
}
