package org.eclipse.emf.parsley.tests

import org.junit.runner.RunWith
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner
import org.junit.Test
import org.junit.Assert

import static org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider.*

@RunWith(typeof(SWTBotJunit4ClassRunner))
public class EmfParsleyDslEditorTests extends EmfParsleyDslUiAbstractTests {

	@Test
	def void checkPluginXmlGen() {
		createDslProjectWithWizard
		
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
		assertProposal(
'''
module «TEST_PROJ_NAME» {
	parts { 
''',
		"ViewSpecification - Template for ViewSpecification",
'''
module my.emfparsley.proj {
	parts { 
viewpart id {
	viewname "View Name"
	viewclass type
	// viewcategory my.category
}'''			
		)
	}

	@Test
	def void checkProposalForType() {
		assertProposal(
'''
module «TEST_PROJ_NAME» {
	labelProvider {
		text {
''',
		"EClass",
		"EClass - org.eclipse.emf.ecore",
'''
import org.eclipse.emf.ecore.EClass

module my.emfparsley.proj {
	labelProvider {
		text {
EClass'''			
		)
	}

	@Test
	def void checkProposalForTypeInViewSpecification() {
		assertProposal(
'''
module my.test.proj {
	
	parts {
		viewpart id {
			viewname "View Name"
			viewclass 
''',
		"OnSelection",
		"OnSelectionFormView - org.eclipse.emf.parsley.views",
'''
import org.eclipse.emf.parsley.views.OnSelectionFormView

module my.test.proj {
	
	parts {
		viewpart id {
			viewname "View Name"
			viewclass 
OnSelectionFormView'''			
		)
	}

	def private void assertProposal(CharSequence input, CharSequence proposal, CharSequence expectedAfterProposal) {
		assertProposal(input, "", proposal, expectedAfterProposal)
	}

	def private void assertProposal(CharSequence input, String textToInsert, CharSequence proposal, CharSequence expectedAfterProposal) {
		createDslProjectWithWizard
		
		val editor = bot.editorByTitle("module.parsley")
		
		editor.setEditorContentsSaveAndWaitForAutoBuild(
			"", false			
		)
		
		editor.toTextEditor.insertText(input.toString)
		
		val lines = input.toString.split("\n").length
		
		editor.toTextEditor.navigateTo(lines, 50)
		
		editor.toTextEditor.autoCompleteProposal(textToInsert, 
			proposal.toString
		)
		
		println("proposals")
		
		editor.save

		editor.assertEditorText(expectedAfterProposal)

		editor.saveAndClose
	
	}

}
