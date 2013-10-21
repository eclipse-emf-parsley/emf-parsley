package org.eclipse.emf.parsley.dsl.ui.wizard.template;

/**
 * @author Francesco Guidieri
 * 
 */
public class SaveableTreeFormTemplateWizardHelper extends SaveableTreeTemplateWizardHelper{
	
	public static final SaveableTreeFormTemplateWizardHelper singlethon=new SaveableTreeFormTemplateWizardHelper();
	
	override getLabel() {
		return "Saveable Tree Form View";
	}

	override getPostFix() {
		return "TreeFormView";
	}

	override getDescription() {
		'''
		<p>This wizard creates a plug-in that contains a view with a <b>tree form</b> component.</p>
		<p>This view reads the content from a resource and can save changes.</p>
		<p><b>The user must specify:</b></p>
		<li>the resource URI</li>
		'''
	}
	
}
