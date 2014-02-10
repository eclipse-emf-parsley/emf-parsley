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

	override getLabel() {
		return "Saveable Tree View";
	}

	def getPostFix() {
		return "TreeView";
	}

	override getOrGenerateViewClass(IProject project, String projectName,	String packagePath, IProgressMonitor monitor) throws CoreException {
		val className=getSimpleNameProject(packagePath) + getPostFix();
		val classContent =viewFilesGenerator.generateConcreteForResourceTreeView(projectName, className,ABSTRACT_SAVEABLE+getPostFix()).toString();
		
		NewEmfParsleyProjectSupport.createProjectFile(project,packagePath + "/"
				 +className.concat(".java"), classContent,
				NewEmfParsleyProjectSupport
						.createSubProgressMonitor(monitor));
		return projectName+"."+className;
	}

	override getDescription() {
		'''
		<p>This wizard creates a plug-in that contains a view with a <b>tree</b> component.</p>
		<p>This view reads the content from a resource and can save changes, that can be performed in other parts (like Property view).</p>
		<p><b>The user must specify:</b></p>
		<li>the resource URI</li>
		'''
	}

}
