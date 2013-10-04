package org.eclipse.emf.parsley.tests

import org.junit.runner.RunWith
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner
import org.junit.Test
import org.junit.Assert

import static org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider.*

@RunWith(typeof(SWTBotJunit4ClassRunner))
public class EmfComponentsDslWizardsTests extends EmfComponentsAbstractTests {

	val TEST_PROJ_NAME = "my.emfparsley.proj"
	val TREE_FORM_TEMPLATE = "Saveable Tree Form View"
	val TREE_TEMPLATE = "Saveable Tree View"
	val TABLE_FORM_TEMPLATE = "Saveable Table Form View"
	val TABLE_TEMPLATE = "Saveable Table View"
	val ONSELECTION_TREE_TEMPLATE = "On selection Tree Form View"
	val ONSELECTION_TABLE_TEMPLATE = "On selection Table Form View"

	@Test
	def canCreateDslProjectWithWizard() {
		createProjectWithoutTemplateInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_COMPONENTS_DSL_PROJECT, TEST_PROJ_NAME);
		//bot.sleep(300000);
		assertNoErrorsInProjectAfterAutoBuild();
	}
	
	@Test
	def canCreateDslProjectWithWizardAndTreeFormTemplate() {
		createProjectWithTemplateInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_COMPONENTS_DSL_PROJECT, TEST_PROJ_NAME,TREE_FORM_TEMPLATE);
		//bot.sleep(300000);
		assertNoErrorsInProjectAfterAutoBuild();
	}
	
	@Test
	def canCreateDslProjectWithWizardAndTreeTemplate() {
		createProjectWithTemplateInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_COMPONENTS_DSL_PROJECT, TEST_PROJ_NAME,TREE_TEMPLATE);
		//bot.sleep(300000);
		assertNoErrorsInProjectAfterAutoBuild();
	}
	
	@Test
	def canCreateDslProjectWithWizardAndTableFormTemplate() {
		createProjectWithTemplateInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_COMPONENTS_DSL_PROJECT, TEST_PROJ_NAME,TABLE_FORM_TEMPLATE);
		//bot.sleep(300000);
		assertNoErrorsInProjectAfterAutoBuild();
	}
	
	@Test
	def canCreateDslProjectWithWizardAndTableTemplate() {
		createProjectWithTemplateInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_COMPONENTS_DSL_PROJECT, TEST_PROJ_NAME,TABLE_TEMPLATE);
		//bot.sleep(300000);
		assertNoErrorsInProjectAfterAutoBuild();
	}
	
	@Test
	def canCreateDslProjectWithWizardAndOnselectionTreeTemplate() {
		createProjectWithTemplateInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_COMPONENTS_DSL_PROJECT, TEST_PROJ_NAME,ONSELECTION_TREE_TEMPLATE);
		//bot.sleep(300000);
		assertNoErrorsInProjectAfterAutoBuild();
	}
	
	@Test
	def canCreateDslProjectWithWizardAndOnselectionTableTemplate() {
		createProjectWithTemplateInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_COMPONENTS_DSL_PROJECT, TEST_PROJ_NAME,ONSELECTION_TABLE_TEMPLATE);
		//bot.sleep(300000);
		assertNoErrorsInProjectAfterAutoBuild();
	}
	
	@Test
	def void checkPluginXmlGen() {
		createProjectWithoutTemplateInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_COMPONENTS_DSL_PROJECT, TEST_PROJ_NAME);
		assertNoErrorsInProjectAfterAutoBuild();
		
		val editor = bot.editorByTitle("module.parsley")
		
		editor.setEditorContentsSaveAndWaitForAutoBuild(
'''
module «TEST_PROJ_NAME» {
	
	// parts should trigger generation of «PLUGIN_XML_EMFPARSLEY_GEN»
	
	parts {
		viewpart id {
			viewname "View Name"
			viewclass org.eclipse.emf.parsley.views.AbstractSaveableTreeView
			// viewcategory my.category
		}
	}
}
'''			
		)
		
		// now the generated plugin xml file should be available
		val projTree = getProjectTreeItem(TEST_PROJ_NAME)
		projTree.expand.getNode(PLUGIN_XML_EMFPARSLEY_GEN)
		
		editor.setEditorContentsSaveAndWaitForAutoBuild(
'''
module «TEST_PROJ_NAME» {
	// removed parts
	
	// «PLUGIN_XML_EMFPARSLEY_GEN» should be removed
}
'''			
		)
		
		// now the generated plugin xml file must have been removed
		Assert::assertTrue(
			PLUGIN_XML_EMFPARSLEY_GEN + " still present!",
			projTree.nodes.forall [ it != PLUGIN_XML_EMFPARSLEY_GEN ]
		)
	}

	@Test
	def void checkTemplateProposalForViewSpecification() {
		createProjectWithoutTemplateInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_COMPONENTS_DSL_PROJECT, TEST_PROJ_NAME);
		assertNoErrorsInProjectAfterAutoBuild();
		
		val editor = bot.editorByTitle("module.parsley")
		
		editor.setEditorContentsSaveAndWaitForAutoBuild(
			"", false			
		)
		
		editor.toTextEditor.insertText(
'''
module «TEST_PROJ_NAME» {
	parts { 
'''
		)
		
		editor.toTextEditor.navigateTo(2, 10)
		
		editor.toTextEditor.autoCompleteProposal(" ", 
			"ViewSpecification - Template for ViewSpecification"
		)
		
		editor.save

		editor.assertEditorText(
'''
module my.emfparsley.proj {
	parts { 
 viewpart id {
 	viewname "View Name"
 	viewclass type
 	// viewcategory my.category
 }'''			
		)

		editor.saveAndClose
	}

}
