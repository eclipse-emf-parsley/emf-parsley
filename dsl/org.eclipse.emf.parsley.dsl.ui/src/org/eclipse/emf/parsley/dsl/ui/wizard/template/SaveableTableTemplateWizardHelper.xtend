package org.eclipse.emf.parsley.dsl.ui.wizard.template;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.parsley.wizards.NewEmfParsleyProjectSupport;

/**
 * @author Francesco Guidieri
 * 
 */
public class SaveableTableTemplateWizardHelper  extends AbstractTemplateWizardHelper{
	
	public static final SaveableTableTemplateWizardHelper singlethon=new SaveableTableTemplateWizardHelper();

	override getLabel() {
		return "Saveable Table View";
	}

	def getPostFix() {
		return "TableView";
	}

	override getOrGenerateViewClass(IProject project, String projectName,String packagePath, IProgressMonitor monitor) throws CoreException {
		val className=getSimpleNameProject(packagePath) + getPostFix();
		val classContent =viewFilesGenerator.generateConcreteForResourceTableView(projectName, className,ABSTRACT_SAVEABLE+getPostFix()).toString();
		
		NewEmfParsleyProjectSupport.createProjectFile(project,packagePath + "/"
				 +className.concat(".java"), classContent,
				NewEmfParsleyProjectSupport
						.createSubProgressMonitor(monitor));
		return projectName+"."+className;
	}

	override getDescription() {
		'''
		<p>This wizard creates a plug-in that contains a view with a <b>table</b> component.</p>
		<p>This view reads the content from a resource and can save changes, that can be performed in other parts (like Property view).</p>
		<p><b>The user must specify:</b></p>
		<li>the resource URI</li>
		<li>how to reach the contents from the resource</li>
		<li>the EClass to be represented</li>
		'''
	}

}
