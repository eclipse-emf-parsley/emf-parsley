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
		assertEquals("Number of templates has changed, please update these tests!", 10, configurations.size)
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
		eClass {
			NameOnSelectionTableFormView -> {
				// TODO return the EClass of objects to be shown
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
		eClass {
			NameOnSelectionTableView -> {
				// TODO return the EClass of objects to be shown
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
		eClass {
			NameSaveableTableFormView -> {
				// TODO return the EClass of objects to be shown
			}
		}
		resourceURI {
			NameSaveableTableFormView -> {
				// TODO create and return a org.eclipse.emf.common.util.URI
				return null;
			}
		}
	}
	
	resourceManager {
		initializeResource {
			// Optional: initialize an empty Resource
			// 'it' is of type Resource
			// e.g., it.getContents += myFactory.createMyClass
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
		eClass {
			NameSaveableTableView -> {
				// TODO return the EClass of objects to be shown
			}
		}
		resourceURI {
			NameSaveableTableView -> {
				// TODO create and return a org.eclipse.emf.common.util.URI
				return null;
			}
		}
	}
	
	resourceManager {
		initializeResource {
			// Optional: initialize an empty Resource
			// 'it' is of type Resource
			// e.g., it.getContents += myFactory.createMyClass
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
	
	resourceManager {
		initializeResource {
			// Optional: initialize an empty Resource
			// 'it' is of type Resource
			// e.g., it.getContents += myFactory.createMyClass
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
	
	resourceManager {
		initializeResource {
			// Optional: initialize an empty Resource
			// 'it' is of type Resource
			// e.g., it.getContents += myFactory.createMyClass
		}
	}
}
'''.assertGenerated(8)
	}

	@Test
	def void testSaveableTreeWithColumnsView() {
'''
import my.project.name.NameSaveableTreeWithColumnsView

/* my.project.name Emf Parsley Dsl Module file */
module my.project.name {
	
	parts {
		viewpart my.project.name {
			viewname "Name"
			viewclass NameSaveableTreeWithColumnsView
		}
	}
	
	configurator {
		eClass {
			NameSaveableTreeWithColumnsView -> {
				// TODO return the EClass with the features to be shown
			}
		}
		resourceURI {
			NameSaveableTreeWithColumnsView -> {
				// TODO create and return a org.eclipse.emf.common.util.URI
				return null;
			}
		}
	}
	
	resourceManager {
		initializeResource {
			// Optional: initialize an empty Resource
			// 'it' is of type Resource
			// e.g., it.getContents += myFactory.createMyClass
		}
	}
}
'''.assertGenerated(9)
	}

	private def assertGenerated(CharSequence expectedModule, int index) {
		expectedModule.toString.assertEquals(
			configurations.get(index).getParsleyModuleContents("my.project.name")
		)
	}

}