package org.eclipse.emf.parsley.dsl.ui.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.eclipse.emf.parsley.dsl.ui.wizard.template.TemplateWizardConfiguration;
import org.eclipse.emf.parsley.dsl.ui.wizard.template.TemplateWizardConfigurationsFactory;
import org.junit.Test;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 */
public class EmfParsleyTemplateWizardConfigurationFactoryTest {

	private final List<TemplateWizardConfiguration> configurations;

	public EmfParsleyTemplateWizardConfigurationFactoryTest() {
		configurations = new TemplateWizardConfigurationsFactory().createTemplateWizardConfigurations();
	}

	@Test
	public void testNumberOfConfigurations() {
		assertEquals("Number of templates has changed, please update these tests!", 10, configurations.size());
	}

	@Test
	public void testOnSelectionTreeView() {
		assertGenerated(
			"""
			import my.project.name.NameOnSelectionTreeView

			/* my.project.name EMF Parsley Dsl Module file */
			module my.project.name {
			\t
				parts {
					viewpart my.project.name {
						viewname "Name"
						viewclass NameOnSelectionTreeView
					}
				}
			\t
			}
			""",
			0
		);
	}

	@Test
	public void testOnSelectionFormView() {
		assertGenerated(
			"""
			import my.project.name.NameOnSelectionFormView

			/* my.project.name EMF Parsley Dsl Module file */
			module my.project.name {
			\t
				parts {
					viewpart my.project.name {
						viewname "Name"
						viewclass NameOnSelectionFormView
					}
				}
			\t
			}
			""",
			1
		);
	}

	@Test
	public void testOnSelectionTableFormView() {
		assertGenerated(
			"""
			import my.project.name.NameOnSelectionTableFormView

			/* my.project.name EMF Parsley Dsl Module file */
			module my.project.name {
			\t
				parts {
					viewpart my.project.name {
						viewname "Name"
						viewclass NameOnSelectionTableFormView
					}
				}
			\t
				configurator {
					eClass {
						NameOnSelectionTableFormView -> {
							// TODO return the EClass of objects to be shown
						}
					}
				}
			}
			""",
			2
		);
	}

	@Test
	public void testOnSelectionTableView() {
		assertGenerated(
			"""
			import my.project.name.NameOnSelectionTableView

			/* my.project.name EMF Parsley Dsl Module file */
			module my.project.name {
			\t
				parts {
					viewpart my.project.name {
						viewname "Name"
						viewclass NameOnSelectionTableView
					}
				}
			\t
				configurator {
					eClass {
						NameOnSelectionTableView -> {
							// TODO return the EClass of objects to be shown
						}
					}
				}
			}
			""",
			3
		);
	}

	@Test
	public void testOnSelectionTreeFormView() {
		assertGenerated(
			"""
			import my.project.name.NameOnSelectionTreeFormView

			/* my.project.name EMF Parsley Dsl Module file */
			module my.project.name {
			\t
				parts {
					viewpart my.project.name {
						viewname "Name"
						viewclass NameOnSelectionTreeFormView
					}
				}
			\t
			}
			""",
			4
		);
	}

	@Test
	public void testSaveableTableFormView() {
		assertGenerated(
			"""
			import my.project.name.NameSaveableTableFormView

			/* my.project.name EMF Parsley Dsl Module file */
			module my.project.name {
			\t
				parts {
					viewpart my.project.name {
						viewname "Name"
						viewclass NameSaveableTableFormView
					}
				}
			\t
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
			\t
				resourceManager {
					initializeResource {
						// Optional: initialize an empty Resource
						// 'it' is of type Resource
						// e.g., it.getContents += myFactory.createMyClass
					}
				}
			}
			""",
			5
		);
	}

	@Test
	public void testSaveableTableView() {
		assertGenerated(
			"""
			import my.project.name.NameSaveableTableView

			/* my.project.name EMF Parsley Dsl Module file */
			module my.project.name {
			\t
				parts {
					viewpart my.project.name {
						viewname "Name"
						viewclass NameSaveableTableView
					}
				}
			\t
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
			\t
				resourceManager {
					initializeResource {
						// Optional: initialize an empty Resource
						// 'it' is of type Resource
						// e.g., it.getContents += myFactory.createMyClass
					}
				}
			}
			""",
			6
		);
	}

	@Test
	public void testSaveableTreeFormView() {
		assertGenerated(
			"""
			import my.project.name.NameSaveableTreeFormView

			/* my.project.name EMF Parsley Dsl Module file */
			module my.project.name {
			\t
				parts {
					viewpart my.project.name {
						viewname "Name"
						viewclass NameSaveableTreeFormView
					}
				}
			\t
				configurator {
					resourceURI {
						NameSaveableTreeFormView -> {
							// TODO create and return a org.eclipse.emf.common.util.URI
							return null;
						}
					}
				}
			\t
				resourceManager {
					initializeResource {
						// Optional: initialize an empty Resource
						// 'it' is of type Resource
						// e.g., it.getContents += myFactory.createMyClass
					}
				}
			}
			""",
			7
		);
	}

	@Test
	public void testSaveableTreeView() {
		assertGenerated(
			"""
			import my.project.name.NameSaveableTreeView

			/* my.project.name EMF Parsley Dsl Module file */
			module my.project.name {
			\t
				parts {
					viewpart my.project.name {
						viewname "Name"
						viewclass NameSaveableTreeView
					}
				}
			\t
				configurator {
					resourceURI {
						NameSaveableTreeView -> {
							// TODO create and return a org.eclipse.emf.common.util.URI
							return null;
						}
					}
				}
			\t
				resourceManager {
					initializeResource {
						// Optional: initialize an empty Resource
						// 'it' is of type Resource
						// e.g., it.getContents += myFactory.createMyClass
					}
				}
			}
			""",
			8
		);
	}

	@Test
	public void testSaveableTreeWithColumnsView() {
		assertGenerated(
			"""
			import my.project.name.NameSaveableTreeWithColumnsView

			/* my.project.name EMF Parsley Dsl Module file */
			module my.project.name {
			\t
				parts {
					viewpart my.project.name {
						viewname "Name"
						viewclass NameSaveableTreeWithColumnsView
					}
				}
			\t
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
			\t
				resourceManager {
					initializeResource {
						// Optional: initialize an empty Resource
						// 'it' is of type Resource
						// e.g., it.getContents += myFactory.createMyClass
					}
				}
			}
			""",
			9
		);
	}

	private void assertGenerated(CharSequence expectedModule, int index) {
		assertEquals(expectedModule.toString(),
			configurations.get(index).getParsleyModuleContents("my.project.name").replace("\r\n", "\n")
		);
	}
}
