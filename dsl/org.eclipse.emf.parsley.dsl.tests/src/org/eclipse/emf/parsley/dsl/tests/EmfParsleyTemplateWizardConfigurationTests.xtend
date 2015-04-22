package org.eclipse.emf.parsley.dsl.tests

import org.eclipse.emf.parsley.dsl.ui.wizard.template.TemplateWizardConfiguration
import org.junit.Test

import static extension org.junit.Assert.*
import org.eclipse.emf.parsley.views.SaveableTreeView

/**
 * @author Lorenzo Bettini - Initial contribution and API
 */
class EmfParsleyTemplateWizardConfigurationTests {

	@Test
	def void testPartClassName() {
		"MynameSaveableTreeView".assertEquals(
			createEmptyConfiguration().getPartClassName("my.project.myname")
		)
	}

	@Test
	def void testPartClassFQN() {
		"my.project.myname.MynameSaveableTreeView".assertEquals(
			createEmptyConfiguration().getPartClassFQN("my.project.myname")
		)
	}

	@Test
	def void testDefaultConfiguratorElements() {
		"".assertEquals(
			createEmptyConfiguration().getConfiguratorElements("my.project.myname")
		)
	}

	@Test
	def void testConfiguratorIsNotGeneratedWhenElementsAreEmpty() {
		"".assertEquals(
			createEmptyConfiguration().getConfiguratorContents("my.project.myname")
		)
	}

	@Test
	def void testConfiguratorIsGeneratedElementsAreNotEmpty() {
'''
configurator {
	// some contents
}
'''
		.toString.assertEquals(
			new TemplateWizardConfiguration(null, null, SaveableTreeView) {
				
				override getConfiguratorElements(String projectName) {
					"// some contents"
				}
				
			}.getConfiguratorContents("my.project.myname")
		)
	}

	@Test
	def void testPartClassContents() {
'''
package my.project.myname;

public class MynameSaveableTreeView extends org.eclipse.emf.parsley.views.SaveableTreeView {
	
}
'''.toString
		.assertEquals(
			createEmptyConfiguration.getContentsForPart("my.project.myname")
		)
	}

	@Test
	def void testParsleyModuleContentsWithConfigurator() {
'''
import my.project.myname.MynameSaveableTreeView

/* my.project.myname Emf Parsley Dsl Module file */
module my.project.myname {
	
	parts {
		viewpart my.project.myname {
			viewname "Myname"
			viewclass MynameSaveableTreeView
		}
	}
	
	configurator {
		// some contents
	}
}
'''
		.toString.assertEquals(
			new TemplateWizardConfiguration(null, null, SaveableTreeView) {
				
				override getConfiguratorElements(String projectName) {
					"// some contents"
				}
				
			}.getParsleyModuleContents("my.project.myname")
		)
	}

	@Test
	def void testParsleyModuleContentsWithoutConfigurator() {
'''
import my.project.myname.MynameSaveableTreeView

/* my.project.myname Emf Parsley Dsl Module file */
module my.project.myname {
	
	parts {
		viewpart my.project.myname {
			viewname "Myname"
			viewclass MynameSaveableTreeView
		}
	}
	
}
'''
		.toString.assertEquals(
			createEmptyConfiguration.getParsleyModuleContents("my.project.myname")
		)
	}
	
	private def createEmptyConfiguration() {
		new TemplateWizardConfiguration(null, null, SaveableTreeView)
	}	

}