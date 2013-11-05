package org.eclipse.emf.parsley.tests;

import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Lorenzo Bettini
 * 
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfParsleyExamplesWizardsTests extends
		EmfParsleyAbstractTests {

	@Test
	public void canCreateMailExampleProjectWithWizard() throws Exception {
		createExampleProjectsInWorkspace(EMF_PARSLEY_MAIL_RCP_EXAMPLE,
				"org.eclipse.emf.parsley.examples.mail.model");
		assertNoErrorsInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateFirstExampleProjectWithWizard() throws Exception {
		createExampleProjectsInWorkspace(EMF_PARSLEY_FIRST_EXAMPLE,
				"org.eclipse.emf.parsley.examples.firstexample",
				"org.eclipse.emf.examples.library",
				"org.eclipse.emf.examples.library.edit");
		assertNoErrorsInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateViewsExamplesProjectWithWizard() throws Exception {
		createExampleProjectsInWorkspace(EMF_PARSLEY_VIEWS_EXAMPLES,
				"org.eclipse.emf.parsley.examples.views");
		assertNoErrorsInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateE4ExamplesProjectWithWizard() throws Exception {
		createExampleProjectsInWorkspace(EMF_PARSLEY_E4_EXAMPLE,
				"org.eclipse.emf.parsley.examples.eclipse4",
				"org.eclipse.emf.parsley.examples.eclipse4.parsleypart");
		assertNoErrorsInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateCdoServerProjectWithWizard() throws Exception {
		createExampleProjectsInWorkspace(EMF_PARSLEY_CDO_SERVER_EXAMPLE,
				"org.eclipse.emf.parsley.examples.cdo.server");
		assertNoErrorsInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateCdoExampleProjectWithWizard() throws Exception {
		createExampleProjectsInWorkspace(EMF_PARSLEY_CDO_EXAMPLE,
				"org.eclipse.emf.parsley.examples.cdo.model",
				"org.eclipse.emf.parsley.examples.cdo.treeform",
				"org.eclipse.emf.parsley.examples.cdo.rcp",
				"org.eclipse.emf.parsley.examples.cdo.product.feature");
		assertNoErrorsInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateRapTargetPlatformExampleProjectWithWizard() throws Exception {
		createExampleProjectsInWorkspace(EMF_PARSLEY_RAP_TP_EXAMPLE,
				"org.eclipse.emf.parsley.examples.rap.targetplatform");
		assertNoErrorsInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateRapCdoExampleProjectWithWizard() throws Exception {
		createExampleProjectsInWorkspace(EMF_PARSLEY_RAP_CDO_EXAMPLE,
				"org.eclipse.emf.parsley.examples.cdo.model",
				"org.eclipse.emf.parsley.examples.cdo.treeform",
				"org.eclipse.emf.parsley.examples.cdo.rap");
		//assertNoErrorsInProjectAfterAutoBuild();
		// errors are expected since the RAP target platform is not set
	}

	@Test
	public void canCreateRapExampleProjectWithWizard() throws Exception {
		createExampleProjectsInWorkspace(EMF_PARSLEY_RAP_EXAMPLE,
				"org.eclipse.emf.parsley.examples.rap.model",
				"org.eclipse.emf.parsley.examples.rap.ui");
		//assertNoErrorsInProjectAfterAutoBuild();
		// errors are expected since the RAP target platform is not set
	}
}
