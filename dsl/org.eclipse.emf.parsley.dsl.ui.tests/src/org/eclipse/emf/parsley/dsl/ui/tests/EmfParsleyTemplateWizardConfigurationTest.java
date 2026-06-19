package org.eclipse.emf.parsley.dsl.ui.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.parsley.dsl.ui.wizard.template.TemplateWizardConfiguration;
import org.eclipse.emf.parsley.views.SaveableTreeView;
import org.junit.Test;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 */
public class EmfParsleyTemplateWizardConfigurationTest {

	private static final String MY_PROJECT_MYNAME = "my.project.myname";

	@Test
	public void testPartClassName() {
		assertEquals("MynameSaveableTreeView",
			createEmptyConfiguration().getPartClassName(MY_PROJECT_MYNAME)
		);
	}

	@Test
	public void testPartClassFQN() {
		assertEquals("my.project.myname.MynameSaveableTreeView",
			createEmptyConfiguration().getPartClassFQN(MY_PROJECT_MYNAME)
		);
	}

	@Test
	public void testDefaultConfiguratorElements() {
		assertEquals("",
			createEmptyConfiguration().getConfiguratorElements(MY_PROJECT_MYNAME)
		);
	}

	@Test
	public void testConfiguratorIsNotGeneratedWhenElementsAreEmpty() {
		assertEquals("",
			createEmptyConfiguration().getConfiguratorContents(MY_PROJECT_MYNAME)
		);
	}

	@Test
	public void testConfiguratorIsGeneratedElementsAreNotEmpty() {
		assertEqualsIgnoringCRLF(
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
			}.getConfiguratorContents(MY_PROJECT_MYNAME)
		);
	}

	@Test
	public void testPartClassContents() {
		assertEqualsIgnoringCRLF(
			"""
			package my.project.myname;

			public class MynameSaveableTreeView extends org.eclipse.emf.parsley.views.SaveableTreeView {
			\t
			}
			""",
			createEmptyConfiguration().getContentsForPart(MY_PROJECT_MYNAME)
		);
	}

	@Test
	public void testParsleyModuleContentsWithConfigurator() {
		assertEqualsIgnoringCRLF(
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
			}.getParsleyModuleContents(MY_PROJECT_MYNAME)
		);
	}

	@Test
	public void testParsleyModuleContentsWithoutConfigurator() {
		assertEqualsIgnoringCRLF(
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
			createEmptyConfiguration().getParsleyModuleContents(MY_PROJECT_MYNAME)
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
		assertEqualsIgnoringCRLF(
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
			}.getParsleyModuleContents(MY_PROJECT_MYNAME)
		);
	}

	private TemplateWizardConfiguration createEmptyConfiguration() {
		return new TemplateWizardConfiguration(null, null, SaveableTreeView.class);
	}

	private void assertEqualsIgnoringCRLF(String expected, String actual) {
		assertEquals(expected, actual.replace("\r\n", "\n"));
	}
}
