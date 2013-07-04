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