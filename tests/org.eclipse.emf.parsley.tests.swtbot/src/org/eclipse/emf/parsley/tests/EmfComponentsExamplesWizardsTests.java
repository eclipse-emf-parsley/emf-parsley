package org.eclipse.emf.parsley.tests;

import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Lorenzo Bettini
 * 
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfComponentsExamplesWizardsTests extends
		EmfComponentsAbstractTests {

	@Test
	public void canCreateMailExampleProjectWithWizard() throws Exception {
		createExampleProjectsInWorkspace(EMF_COMPONENTS_MAIL_RCP_EXAMPLE,
				"org.eclipse.emf.parsley.examples.mail.model");
		assertNoErrorsInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateFirstExampleProjectWithWizard() throws Exception {
		createExampleProjectsInWorkspace(EMF_COMPONENTS_FIRST_EXAMPLE,
				"org.eclipse.emf.parsley.examples.firstexample",
				"org.eclipse.emf.examples.library",
				"org.eclipse.emf.examples.library.edit");
		assertNoErrorsInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateViewsExamplesProjectWithWizard() throws Exception {
		createExampleProjectsInWorkspace(EMF_COMPONENTS_VIEWS_EXAMPLES,
				"org.eclipse.emf.parsley.examples.views");
		assertNoErrorsInProjectAfterAutoBuild();
	}

}
