package org.eclipse.emf.parsley.dsl.ui.wizard.template;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.parsley.views.OnSelectionTreeFormView;

/**
 * @author Francesco Guidieri
 * 
 */
public class OnSelectionTreeFormTemplateWizardHelper extends AbstractTemplateWizardHelper{
	
	public static final OnSelectionTreeFormTemplateWizardHelper singlethon=new OnSelectionTreeFormTemplateWizardHelper();
	
	public String getLabel() {
		return "On selection Tree Form View";
	}

	@Override
	public String getOrGenerateViewClass(IProject project, String projectName,String packagePath, IProgressMonitor monitor) throws CoreException {
		return OnSelectionTreeFormView.class.getName();
	}

	@Override
	public String getDescription() {
		return "<p>This wizard creates an Emf-Parsley plug-in with the following component:</p>"
				+ "<li>On selection <b>table form</b> view</li>"
				+ "<p><b>No user change is needed to run the project</b></p>";
	}

}
