package org.eclipse.emf.parsley.dsl.ui.wizard.template;

import org.eclipse.emf.parsley.generator.common.EmfParsleyViewFilesGenerator;

/**
 * @author Francesco Guidieri
 * 
 */
public abstract class AbstractTemplateWizardHelper implements IWizardTemplate{

	protected static final String ABSTRACT_SAVEABLE="AbstractSaveable";

	protected static EmfParsleyViewFilesGenerator viewFilesGenerator= new EmfParsleyViewFilesGenerator();
	
	protected String getSimpleNameProject(String projectName) {
		String simpleNameProject = null;
		if (projectName.contains(".")) {
			simpleNameProject = projectName.substring(projectName
					.lastIndexOf(".") + 1);
		} else {
			simpleNameProject = projectName.substring(projectName
					.lastIndexOf("/") + 1);
		}
		simpleNameProject = simpleNameProject.substring(0, 1).toUpperCase()
				.concat(simpleNameProject.substring(1));
		return simpleNameProject;
	}
	
}