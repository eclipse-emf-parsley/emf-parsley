package org.eclipse.emf.parsley.dsl.ui.wizard.template;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.parsley.wizards.NewEmfParsleyProjectSupport;

/**
 * @author Francesco Guidieri
 * 
 */
public class SaveableTreeTemplateWizardHelper  extends AbstractTemplateWizardHelper{
	
	public static final SaveableTreeTemplateWizardHelper singlethon=new SaveableTreeTemplateWizardHelper();

	public String getLabel() {
		return "Saveable Tree View";
	}

	public String getPostFix() {
		return "TreeView";
	}

	@Override
	public String getOrGenerateViewClass(IProject project, String projectName,	String packagePath, IProgressMonitor monitor) throws CoreException {
		String className=getSimpleNameProject(packagePath) + getPostFix();
		String classContent =viewFilesGenerator.generateConcreteForResourceTreeView(projectName, className,ABSTRACT_SAVEABLE+getPostFix()).toString();
		
		NewEmfParsleyProjectSupport.createProjectFile(project,packagePath + "/"
				 +className.concat(".java"), classContent,
				NewEmfParsleyProjectSupport
						.createSubProgressMonitor(monitor));
		return projectName+"."+className;
	}

	@Override
	public String getDescription() {
		return "<p>This wizard creates a plug-in that contains a view with a <b>tree</b> component.</p>"
				+ "<p>This view read the content form a resource and can save changes, that can be performed in other parts (like Property view).</p>"
				+ "<p><b>The user must specify:</b></p>"
				+ "<li>the resource URI</li>";
	}

}
