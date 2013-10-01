package org.eclipse.emf.parsley.dsl.ui.wizard.template;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.parsley.views.OnSelectionTableFormView;

/**
 * @author Francesco Guidieri
 * 
 */
public class OnSelectionTableFormTemplateWizardHelper extends AbstractTemplateWizardHelper{
	
	public static final OnSelectionTableFormTemplateWizardHelper singlethon=new OnSelectionTableFormTemplateWizardHelper();
	
	public String getLabel() {
		return "On selection Table Form View";
	}


	@Override
	public String getOrGenerateViewClass(IProject project, String projectName,String packagePath, IProgressMonitor monitor) throws CoreException {
		return OnSelectionTableFormView.class.getName();
	}

	@Override
	public String getDescription() {
		return "<p>This wizard creates an Emf-Parsley plug-in with the following component:</p>"
				+ "<li><b>On selection table form view</b></li>"
				+ "<p><b>No user change is needed to run the project</b></p>";
	}
	
}
