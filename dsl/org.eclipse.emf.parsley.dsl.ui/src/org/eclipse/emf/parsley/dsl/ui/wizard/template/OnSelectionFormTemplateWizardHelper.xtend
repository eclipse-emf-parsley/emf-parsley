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
import org.eclipse.emf.parsley.views.OnSelectionFormView;

/**
 * @author Francesco Guidieri
 * 
 */
public class OnSelectionFormTemplateWizardHelper extends AbstractTemplateWizardHelper{
	
	public static final OnSelectionFormTemplateWizardHelper singlethon=new OnSelectionFormTemplateWizardHelper();
	
	override getLabel() {
		"On selection Form View";
	}

	override getOrGenerateViewClass(IProject project, String projectName,String packagePath, IProgressMonitor monitor) throws CoreException {
		return OnSelectionFormView.getName();
	}

	override getDescription() {
		'''
		<p>This wizard creates an Emf-Parsley plug-in with the following component:</p>
		<li>On selection <b>form</b> view</li>
		<p><b>No user change is needed to run the project</b></p>
		'''
	}

}
