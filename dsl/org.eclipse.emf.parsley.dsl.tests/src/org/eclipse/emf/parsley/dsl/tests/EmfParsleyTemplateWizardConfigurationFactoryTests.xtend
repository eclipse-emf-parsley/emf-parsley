package org.eclipse.emf.parsley.dsl.tests

import java.util.List
import org.eclipse.emf.parsley.dsl.ui.wizard.template.TemplateWizardConfiguration
import org.eclipse.emf.parsley.dsl.ui.wizard.template.TemplateWizardConfigurationsFactory
import org.junit.Test

import static extension org.junit.Assert.*

/**
 * @author Lorenzo Bettini - Initial contribution and API
 */
class EmfParsleyTemplateWizardConfigurationFactoryTests {

	var List<TemplateWizardConfiguration> configurations;
	
	new() {
		configurations = new TemplateWizardConfigurationsFactory().createTemplateWizardConfigurations
	}

	@Test
	def void testNumberOfConfigurations() {
		assertEquals("Number of templates has changed, please update these tests!", 9, configurations.size)
	}

	@Test
	def void testOnSelectionTreeView() {
'''
import my.project.name.NameOnSelectionTreeView

/* my.project.name Emf Parsley Dsl Module file */
module my.project.name {
	
	parts {
		viewpart my.project.name {
			viewname "Name"
			viewclass NameOnSelectionTreeView
		}
	}
	
}
'''.assertGenerated(0)
	}

	@Test
	def void testOnSelectionFormView() {
'''
import my.project.name.NameOnSelectionFormView

/* my.project.name Emf Parsley Dsl Module file */
module my.project.name {
	
	parts {
		viewpart my.project.name {
			viewname "Name"
			viewclass NameOnSelectionFormView
		}
	}
	
}
'''.assertGenerated(1)
	}

	@Test
	def void testOnSelectionTableFormView() {
'''
import my.project.name.NameOnSelectionTableFormView

/* my.project.name Emf Parsley Dsl Module file */
module my.project.name {
	
	parts {
		viewpart my.project.name {
			viewname "Name"
			viewclass NameOnSelectionTableFormView
		}
	}
	
	configurator {
		eStructuralFeature {
			NameOnSelectionTableFormView -> {
				// TODO return the EStructuralFeature to get the elements to represent
			}
		}
	}
}
'''.assertGenerated(2)
	}

	@Test
	def void testOnSelectionTableView() {
'''
import my.project.name.NameOnSelectionTableView

/* my.project.name Emf Parsley Dsl Module file */
module my.project.name {
	
	parts {
		viewpart my.project.name {
			viewname "Name"
			viewclass NameOnSelectionTableView
		}
	}
	
	configurator {
		eStructuralFeature {
			NameOnSelectionTableView -> {
				// TODO return the EStructuralFeature to get the elements to represent
			}
		}
	}
}
'''.assertGenerated(3)
	}

	@Test
	def void testOnSelectionTreeFormView() {
'''
import my.project.name.NameOnSelectionTreeFormView

/* my.project.name Emf Parsley Dsl Module file */
module my.project.name {
	
	parts {
		viewpart my.project.name {
			viewname "Name"
			viewclass NameOnSelectionTreeFormView
		}
	}
	
}
'''.assertGenerated(4)
	}

	@Test
	def void testSaveableTableFormView() {
'''
import my.project.name.NameSaveableTableFormView

/* my.project.name Emf Parsley Dsl Module file */
module my.project.name {
	
	parts {
		viewpart my.project.name {
			viewname "Name"
			viewclass NameSaveableTableFormView
		}
	}
	
	configurator {
		contents {
			NameSaveableTableFormView -> {
				// TODO return the contents from the resource
				// e.g., resource.^contents.get(0)
			}
		}
		eClass {
			NameSaveableTableFormView -> {
				// TODO return the EClass to represent
			}
		}
		resourceURI {
			NameSaveableTableFormView -> {
				// TODO create and return a org.eclipse.emf.common.util.URI
				return null;
			}
		}
	}
}
'''.assertGenerated(5)
	}

	@Test
	def void testSaveableTableView() {
'''
import my.project.name.NameSaveableTableView

/* my.project.name Emf Parsley Dsl Module file */
module my.project.name {
	
	parts {
		viewpart my.project.name {
			viewname "Name"
			viewclass NameSaveableTableView
		}
	}
	
	configurator {
		contents {
			NameSaveableTableView -> {
				// TODO return the contents from the resource
				// e.g., resource.^contents.get(0)
			}
		}
		eClass {
			NameSaveableTableView -> {
				// TODO return the EClass to represent
			}
		}
		resourceURI {
			NameSaveableTableView -> {
				// TODO create and return a org.eclipse.emf.common.util.URI
				return null;
			}
		}
	}
}
'''.assertGenerated(6)
	}

	@Test
	def void testSaveableTreeFormView() {
'''
import my.project.name.NameSaveableTreeFormView

/* my.project.name Emf Parsley Dsl Module file */
module my.project.name {
	
	parts {
		viewpart my.project.name {
			viewname "Name"
			viewclass NameSaveableTreeFormView
		}
	}
	
	configurator {
		resourceURI {
			NameSaveableTreeFormView -> {
				// TODO create and return a org.eclipse.emf.common.util.URI
				return null;
			}
		}
	}
}
'''.assertGenerated(7)
	}

	@Test
	def void testSaveableTreeView() {
'''
import my.project.name.NameSaveableTreeView

/* my.project.name Emf Parsley Dsl Module file */
module my.project.name {
	
	parts {
		viewpart my.project.name {
			viewname "Name"
			viewclass NameSaveableTreeView
		}
	}
	
	configurator {
		resourceURI {
			NameSaveableTreeView -> {
				// TODO create and return a org.eclipse.emf.common.util.URI
				return null;
			}
		}
	}
}
'''.assertGenerated(8)
	}
	
	private def assertGenerated(CharSequence expectedModule, int index) {
		expectedModule.toString.assertEquals(
			configurations.get(index).getParsleyModuleContents("my.project.name")
		)
	}

}