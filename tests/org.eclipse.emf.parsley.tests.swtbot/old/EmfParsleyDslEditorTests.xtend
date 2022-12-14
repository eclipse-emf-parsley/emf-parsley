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
package org.eclipse.emf.parsley.tests.swtbot

import org.eclipse.emf.parsley.tests.swtbot.utils.SWTBotEclipseEditorCustom
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(SWTBotJunit4ClassRunner))
class EmfParsleyDslEditorTests extends EmfParsleyDslUiAbstractTests {

	// This is now checked in org.eclipse.emf.parsley.dsl.ui.tests.EmfParsleyDslWorkbenchTest.testPluginXmlGeneration()
//	@Test
//	def void checkPluginXmlGen() {
//		createDslProjectWithWizard
//		
//		val editor = bot.editorByTitle("module.parsley")
//		
//		editor.setEditorContentsSaveAndWaitForAutoBuild(
//'''
//module «TEST_PROJ_NAME» {
//	
//	// parts should trigger generation of «PLUGIN_XML_EMFPARSLEY_GEN»
//	
//	parts {
//		viewpart id {
//			viewname "View Name"
//			viewclass org.eclipse.emf.parsley.views.AbstractSaveableTreeView
//			// viewcategory my.category
//		}
//	}
//}
//'''			
//		)
//		
//		// now the generated plugin xml file should be available
//		val projTree = getProjectTreeItem(TEST_PROJ_NAME)
//		projTree.expand.getNode(PLUGIN_XML_EMFPARSLEY_GEN)
//		
//		editor.setEditorContentsSaveAndWaitForAutoBuild(
//'''
//module «TEST_PROJ_NAME» {
//	// removed parts
//	
//	// «PLUGIN_XML_EMFPARSLEY_GEN» should be removed
//}
//'''			
//		)
//		
//		// now the generated plugin xml file must have been removed
//		Assert::assertTrue(
//			PLUGIN_XML_EMFPARSLEY_GEN + " still present!",
//			projTree.nodes.forall [ it != PLUGIN_XML_EMFPARSLEY_GEN ]
//		)
//	}

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
	viewclass viewclassref
	// viewcategory my.category
}'''			
		)
	}

	@Test
	def void checkOrganizeImportsInsertMissingImports() {
		assertOrganizeImports(
'''
module my.test.proj {
	parts {
		viewpart id {
			viewname "View Name"
			viewclass OnSelectionFormView
		}
	}

	labelProvider {
		text {
			EClass -> ""
		}
	}

	featuresProvider {
		features {
			EStructuralFeature -> name
		}
	}
}''',
'''
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.parsley.views.OnSelectionFormView

module my.test.proj {
	parts {
		viewpart id {
			viewname "View Name"
			viewclass OnSelectionFormView
		}
	}

	labelProvider {
		text {
			EClass -> ""
		}
	}

	featuresProvider {
		features {
			EStructuralFeature -> name
		}
	}
}'''			
		)
	}

	def private void assertProposal(CharSequence input, CharSequence proposal, CharSequence expectedAfterProposal) {
		assertProposal(input, "", proposal, expectedAfterProposal)
	}

	def private void assertProposal(CharSequence input, String textToInsert, CharSequence proposal, CharSequence expectedAfterProposal) {
		createDslProjectWithWizard
		
		val editor = bot.editorByTitle("Proj.parsley")
		
		editor.toTextEditor.setText("")
		
		editor.toTextEditor.insertText(input.toString)
		
		val lines = input.toString.split("\n").length
		
		editor.toTextEditor.navigateTo(lines, 50)
		
		val swtbotEditor = new SWTBotEclipseEditorCustom(editor.reference, bot)
		swtbotEditor.
			autoCompleteProposal(textToInsert, 
				proposal.toString
			)
		
		editor.save

		editor.assertEditorText(expectedAfterProposal)

		editor.saveAndClose
	
	}

//	def private void assertNoProposals(CharSequence input, String textToInsert, CharSequence... notExpectedProposals) {
//		createDslProjectWithWizard
//		
//		val editor = bot.editorByTitle("module.parsley")
//		
//		editor.toTextEditor.setText("")
//		
//		editor.toTextEditor.insertText(input.toString)
//		
//		val lines = input.toString.split("\n").length
//		
//		editor.toTextEditor.navigateTo(lines, 50)
//		
//		val autoCompleteProposals = 
//			new SWTBotEclipseEditorCustom(editor.reference, bot).
//				getAutoCompleteProposals(textToInsert)
//		editor.save
//		
//		for (p : autoCompleteProposals) {
//			for (notExpected : notExpectedProposals) {
//				Assert.assertFalse(p + " should not be proposed",
//					p == notExpected)
//			}
//		}
//	
//	}

	def private void assertOrganizeImports(CharSequence input, CharSequence expectedAfterOrganize) {
		createDslProjectWithWizard
		
		val editor = bot.editorByTitle("Proj.parsley")
		
		editor.setEditorContentsSaveAndWaitForAutoBuild(
			"", false			
		)
		
		editor.toTextEditor.insertText(input.toString)
		
		editor.save
		
		editor.contextMenu("Organize Imports").click
		
		editor.save

		editor.assertEditorText(expectedAfterOrganize)

		editor.saveAndClose
	
	}

}
