package org.eclipse.emf.parsley.dsl.ui.wizard.template;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Francesco Guidieri
 * 
 */
public interface IWizardTemplate {

	public String getLabel();

	public String getDescription();

	public String getOrGenerateViewClass(IProject project, String projectName,
			String packagePath, IProgressMonitor monitor) throws CoreException;

}
