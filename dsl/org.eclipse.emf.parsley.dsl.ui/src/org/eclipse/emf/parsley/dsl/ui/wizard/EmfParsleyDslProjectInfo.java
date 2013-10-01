package org.eclipse.emf.parsley.dsl.ui.wizard;

import org.eclipse.emf.parsley.dsl.ui.wizard.template.IWizardTemplate;
import org.eclipse.xtext.ui.wizard.DefaultProjectInfo;

/**
 * @author Francesco Guidieri
 * 
 */
public class EmfParsleyDslProjectInfo extends DefaultProjectInfo {
	
	private IWizardTemplate selectedTemplate;
	
	public IWizardTemplate getSelectedTemplate() {
		return selectedTemplate;
	}
	
	public void setSelectedTemplate(IWizardTemplate selectedTemplate) {
		this.selectedTemplate = selectedTemplate;
	}
	
}
