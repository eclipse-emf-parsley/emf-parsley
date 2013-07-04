package org.eclipse.emf.parsley.tests

import org.junit.runner.RunWith
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner
import org.junit.Test
import org.junit.Assert

import static org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider.*

@RunWith(typeof(SWTBotJunit4ClassRunner))
public class EmfComponentsDslWizardsTests extends EmfComponentsAbstractTests {

	val TEST_PROJ_NAME = "my.emfparsley.proj"

	@Test
	def canCreateDslProjectWithWizard() {
		createProjectInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_COMPONENTS_DSL_PROJECT, TEST_PROJ_NAME);
		//bot.sleep(300000);
		assertNoErrorsInProjectAfterAutoBuild();
	}
	
	@Test
	def void checkPluginXmlGen() {
		createProjectInWorkspace(EMF_PARSLEY_CATEGORY,
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

}