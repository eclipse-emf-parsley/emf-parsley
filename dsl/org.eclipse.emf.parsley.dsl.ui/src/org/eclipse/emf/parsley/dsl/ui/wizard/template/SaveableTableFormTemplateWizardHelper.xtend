package org.eclipse.emf.parsley.dsl.ui.wizard.template;

/**
 * @author Francesco Guidieri
 * 
 */
public class SaveableTableFormTemplateWizardHelper extends SaveableTableTemplateWizardHelper{
	
	public static final SaveableTableFormTemplateWizardHelper singlethon=new SaveableTableFormTemplateWizardHelper();
	
	override getLabel() {
		return "Saveable Table Form View";
	}

	override getPostFix() {
		return "TableFormView";
	}

	override getDescription() {
		'''
		<p>This wizard creates a plug-in that contains a view with a <b>table form</b> component.</p>
		<p>This view reads the content from a resource and can save changes.</p>
		<p><b>The user must specify:</b></p>
		<li>the resource URI</li>
		<li>how to reach the contents from the resource</li>
		<li>the EClass to be represented</li>
		'''
	}

}
