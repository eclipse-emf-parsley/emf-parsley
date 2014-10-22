/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - initial API and implementation
 * Lorenzo Bettini - refactored to avoid code duplication
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.ui.wizard.template;

import org.eclipse.core.resources.IProject
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.emf.parsley.generator.common.EmfParsleyViewFilesGenerator
import org.eclipse.emf.parsley.wizards.NewEmfParsleyProjectSupport

/**
 * @author Francesco Guidieri
 * @author Lorenzo Bettini - refactored to avoid code duplication
 */
public class TemplateWizardConfiguration {

	protected static final String ABSTRACT_SAVEABLE="AbstractSaveable";

	protected static final EmfParsleyViewFilesGenerator viewFilesGenerator= new EmfParsleyViewFilesGenerator();
	
	private String label;
	
	private String className;
	
	private String postfix;

	private (String,String)=>String classContentProvider;
	
	private CharSequence description;
	
	new(String label, String className,
			String postfix, (String,String)=>String classContentProvider,
			CharSequence description) {
		super();
		this.label = label;
		this.className = className;
		this.postfix = postfix;
		this.classContentProvider = classContentProvider;
		this.description = description;
	}

	def getLabel() {
		return label;
	}

	def getClassName() {
		return className;
	}

	def getPostfix() {
		return postfix;
	}

	def (String,String)=>String getClassContentProvider() {
		return classContentProvider;
	}

	def CharSequence getDescription() {
		return description;
	}

	def protected String getSimpleNameProject(String projectName) {
		var String simpleNameProject = null;
		if (projectName.contains(".")) {
			simpleNameProject = projectName.substring(projectName
					.lastIndexOf(".") + 1);
		} else {
			simpleNameProject = projectName.substring(projectName
					.lastIndexOf("/") + 1);
		}
		simpleNameProject = simpleNameProject.substring(0, 1).toUpperCase()
				.concat(simpleNameProject.substring(1));
		return simpleNameProject;
	}

	def getOrGenerateViewClass(IProject project, String projectName, String packagePath, IProgressMonitor monitor) throws CoreException {
		val projectRelativeClassName=getSimpleNameProject(packagePath) + className + postfix;
		val classContent = classContentProvider.apply(projectName, projectRelativeClassName)
		
		if (!classContent.empty) {
			NewEmfParsleyProjectSupport.createProjectFile(project,packagePath + "/"
				 +projectRelativeClassName.concat(".java"), classContent,
				NewEmfParsleyProjectSupport
						.createSubProgressMonitor(monitor));
			return projectName+"."+projectRelativeClassName;
		} else {
			return className;
		}
	}
}
