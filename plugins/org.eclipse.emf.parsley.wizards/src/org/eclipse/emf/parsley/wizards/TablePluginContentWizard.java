/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.wizards;


import org.eclipse.pde.ui.IFieldData;
import org.eclipse.pde.ui.templates.ITemplateSection;
import org.eclipse.pde.ui.templates.NewPluginTemplateWizard;

public class TablePluginContentWizard extends NewPluginTemplateWizard {

	 protected IFieldData fData;
	 
	public void init(IFieldData data) {
	   super.init(data);
	   fData = data;
	   // not available in Indigo
	   // setWindowTitle("Simple View Wizard");      
	}

	public ITemplateSection[] createTemplateSections() {
	   return new ITemplateSection[] {new TableViewTemplateSection()};
	}

}