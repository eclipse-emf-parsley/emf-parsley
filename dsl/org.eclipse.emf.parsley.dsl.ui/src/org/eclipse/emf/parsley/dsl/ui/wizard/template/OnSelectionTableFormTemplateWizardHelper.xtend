package org.eclipse.emf.parsley.dsl.ui.wizard.template;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.parsley.wizards.NewEmfParsleyProjectSupport;

/**
 * @author Francesco Guidieri
 * 
 */
public class OnSelectionTableFormTemplateWizardHelper extends AbstractTemplateWizardHelper{
	
	public static final OnSelectionTableFormTemplateWizardHelper singlethon=new OnSelectionTableFormTemplateWizardHelper();
	
	override getLabel() {
		"On selection Table Form View";
	}


	override getOrGenerateViewClass(IProject project, String projectName,String packagePath, IProgressMonitor monitor) throws CoreException {
		val className=getSimpleNameProject(packagePath) + "OnSelectionTableForm";
		val classContent =viewFilesGenerator.generateConcreteForOnSelectionTableView(projectName, className).toString();
		
		NewEmfParsleyProjectSupport.createProjectFile(project,packagePath + "/"
				 +className.concat(".java"), classContent,
				NewEmfParsleyProjectSupport
						.createSubProgressMonitor(monitor));
		return projectName+"."+className;
	}
	
	override getDescription() {
		'''
		<p>This wizard creates an Emf-Parsley plug-in with the following component:</p>
		<li><b>On selection table form view</b></li>
		<p><b>The user must specify:</b></p>
		<li>how to reach the contents from the selected object</li>
		<li>the EClass to be represented</li>
		'''
	}
	
}
