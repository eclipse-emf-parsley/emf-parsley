package org.eclipse.emf.parsley.dsl.ui.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.parsley.dsl.ui.wizard.template.TemplateWizardConfiguration;
import org.eclipse.emf.parsley.views.SaveableTreeView;
import org.junit.Test;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 */
public class EmfParsleyTemplateWizardConfigurationTest {

	@Test
	public void testPartClassName() {
		assertEquals("MynameSaveableTreeView",
			createEmptyConfiguration().getPartClassName("my.project.myname")
		);
	}

	@Test
	public void testPartClassFQN() {
		assertEquals("my.project.myname.MynameSaveableTreeView",
			createEmptyConfiguration().getPartClassFQN("my.project.myname")
		);
	}

	@Test
	public void testDefaultConfiguratorElements() {
		assertEquals("",
			createEmptyConfiguration().getConfiguratorElements("my.project.myname")
		);
	}

	@Test
	public void testConfiguratorIsNotGeneratedWhenElementsAreEmpty() {
		assertEquals("",
			createEmptyConfiguration().getConfiguratorContents("my.project.myname")
		);
	}

	@Test
	public void testConfiguratorIsGeneratedElementsAreNotEmpty() {
		assertEquals(
			"""
			configurator {
				// some contents
			}
			""",
			new TemplateWizardConfiguration(null, null, SaveableTreeView.class) {
				@Override
				public String getConfiguratorElements(String projectName) {
					return "// some contents";
				}
			}.getConfiguratorContents("my.project.myname")
		);
	}

	@Test
	public void testPartClassContents() {
		assertEquals(
			"""
			package my.project.myname;

			public class MynameSaveableTreeView extends org.eclipse.emf.parsley.views.SaveableTreeView {
			\t
			}
			""",
			createEmptyConfiguration().getContentsForPart("my.project.myname")
		);
	}

	@Test
	public void testParsleyModuleContentsWithConfigurator() {
		assertEquals(
			"""
			import my.project.myname.MynameSaveableTreeView

			/* my.project.myname EMF Parsley Dsl Module file */
			module my.project.myname {
			\t
				parts {
					viewpart my.project.myname {
						viewname "Myname"
						viewclass MynameSaveableTreeView
					}
				}
			\t
				configurator {
					// some contents
				}
			}
			""",
			new TemplateWizardConfiguration(null, null, SaveableTreeView.class) {
				@Override
				public String getConfiguratorElements(String projectName) {
					return "// some contents";
				}
			}.getParsleyModuleContents("my.project.myname")
		);
	}

	@Test
	public void testParsleyModuleContentsWithoutConfigurator() {
		assertEquals(
			"""
			import my.project.myname.MynameSaveableTreeView

			/* my.project.myname EMF Parsley Dsl Module file */
			module my.project.myname {
			\t
				parts {
					viewpart my.project.myname {
						viewname "Myname"
						viewclass MynameSaveableTreeView
					}
				}
			\t
			}
			""",
			createEmptyConfiguration().getParsleyModuleContents("my.project.myname")
		);
	}

	@Test
	public void testDefaultGetResourceManager() {
		assertEquals("",
			createEmptyConfiguration().getResourceManager()
		);
	}

	@Test
	public void testParsleyModuleContentsWithResourceManager() {
		assertEquals(
			"""
			import my.project.myname.MynameSaveableTreeView

			/* my.project.myname EMF Parsley Dsl Module file */
			module my.project.myname {
			\t
				parts {
					viewpart my.project.myname {
						viewname "Myname"
						viewclass MynameSaveableTreeView
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
			new TemplateWizardConfiguration(null, null, SaveableTreeView.class) {
				@Override
				public String getResourceManager() {
					return getProjectFilesGenerator().genResourceManager(
						getProjectFilesGenerator().genInitializeResource()
					).toString();
				}
			}.getParsleyModuleContents("my.project.myname")
		);
	}

	private TemplateWizardConfiguration createEmptyConfiguration() {
		return new TemplateWizardConfiguration(null, null, SaveableTreeView.class);
	}
}
