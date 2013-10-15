package org.eclipse.emf.parsley.dsl.ui.wizard.template;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.parsley.views.OnSelectionFormView;

/**
 * @author Francesco Guidieri
 * 
 */
public class OnSelectionFormTemplateWizardHelper extends AbstractTemplateWizardHelper{
	
	public static final OnSelectionFormTemplateWizardHelper singlethon=new OnSelectionFormTemplateWizardHelper();
	
	public String getLabel() {
		return "On selection Form View";
	}

	@Override
	public String getOrGenerateViewClass(IProject project, String projectName,String packagePath, IProgressMonitor monitor) throws CoreException {
		return OnSelectionFormView.class.getName();
	}

	@Override
	public String getDescription() {
		return "<p>This wizard creates an Emf-Parsley plug-in with the following component:</p>"
				+ "<li>On selection <b>form</b> view</li>"
				+ "<p><b>No user change is needed to run the project</b></p>";
	}

}
