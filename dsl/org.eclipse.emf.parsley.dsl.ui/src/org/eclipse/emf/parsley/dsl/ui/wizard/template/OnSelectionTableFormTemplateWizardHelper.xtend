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
public class OnSelectionTableFormTemplateWizardHelper extends TemplateWizardConfiguration{
	
	public static final OnSelectionTableFormTemplateWizardHelper singlethon=new OnSelectionTableFormTemplateWizardHelper(
		"On selection Table Form View",
		"OnSelectionTableFormView",
		"",
		[projectName, className | 
			viewFilesGenerator.
				generateConcreteForOnSelectionTableFormView(projectName, className).toString()
		],
		'''
		<p>This wizard creates an Emf-Parsley plug-in with the following component:</p>
		<li><b>On selection table form view</b></li>
		<p><b>The user must specify:</b></p>
		<li>the EStructuralFeature for getting the contents from the resource</li>
		'''
	);

	new(String label, String className, String postfix, (String,String)=>String classContentProvider, CharSequence description) {
		super(label, className, postfix, classContentProvider, description)
	}
}
