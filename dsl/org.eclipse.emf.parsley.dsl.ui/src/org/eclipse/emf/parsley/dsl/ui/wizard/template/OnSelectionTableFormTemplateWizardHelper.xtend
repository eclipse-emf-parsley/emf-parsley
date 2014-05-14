/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.ui.wizard.template;

import org.eclipse.core.resources.IProject
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.emf.parsley.wizards.NewEmfParsleyProjectSupport

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
		val className=getSimpleNameProject(packagePath) + "OnSelectionTableFormView";
		val classContent =viewFilesGenerator.generateConcreteForOnSelectionTableFormView(projectName, className).toString();
		
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
		<li>the EStructuralFeature for getting the contents from the resource</li>
		'''
	}
	
}
