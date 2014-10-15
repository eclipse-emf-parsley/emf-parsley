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
public class SaveableTableTemplateWizardHelper  extends TemplateWizardConfiguration{
	
	public static final SaveableTableTemplateWizardHelper singlethon=new SaveableTableTemplateWizardHelper(
		"Saveable Table View",
		"",
		"TableView",
		[projectName, className | 
			viewFilesGenerator.
				generateConcreteForResourceTableView(projectName, className, ABSTRACT_SAVEABLE+"TableView").toString()
		],
		'''
		<p>This wizard creates a plug-in that contains a view with a <b>table</b> component.</p>
		<p>This view reads the content from a resource and can save changes, that can be performed in other parts (like Property view).</p>
		<p><b>The user must specify:</b></p>
		<li>the resource URI</li>
		<li>how to reach the contents from the resource</li>
		<li>the EClass to be represented</li>
		'''
	)

	new(String label, String className, String postfix, (String,String)=>String classContentProvider, CharSequence description) {
		super(label, className, postfix, classContentProvider, description)
	}
}
