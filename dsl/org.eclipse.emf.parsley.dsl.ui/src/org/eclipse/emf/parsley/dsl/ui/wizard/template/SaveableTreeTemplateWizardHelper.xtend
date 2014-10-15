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

/**
 * @author Francesco Guidieri
 * 
 */
public class SaveableTreeTemplateWizardHelper  extends TemplateWizardConfiguration{
	
	public static final SaveableTreeTemplateWizardHelper singlethon=new SaveableTreeTemplateWizardHelper(
		"Saveable Tree View",
		"",
		"TreeView",
		[projectName, className | 
			viewFilesGenerator.
				generateConcreteForResourceTreeView(projectName, className, ABSTRACT_SAVEABLE+"TreeView").toString()
		],
		'''
		<p>This wizard creates a plug-in that contains a view with a <b>tree</b> component.</p>
		<p>This view reads the content from a resource and can save changes, that can be performed in other parts (like Property view).</p>
		<p><b>The user must specify:</b></p>
		<li>the resource URI</li>
		'''
	)

	new(String label, String className, String postfix, (String,String)=>String classContentProvider, CharSequence description) {
		super(label, className, postfix, classContentProvider, description)
	}
}
