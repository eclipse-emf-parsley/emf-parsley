package org.eclipse.emf.parsley.tests

import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner
import org.junit.runner.RunWith

@RunWith(typeof(SWTBotJunit4ClassRunner))
public class EmfParsleyDslUiAbstractTests extends EmfParsleyAbstractTests {

	protected val TEST_PROJ_NAME = "my.emfparsley.proj"

	def createDslProjectWithWizard() {
		createProjectWithoutTemplateInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_COMPONENTS_DSL_PROJECT, TEST_PROJ_NAME);
		assertNoErrorsInProjectAfterAutoBuild();
	}
}
