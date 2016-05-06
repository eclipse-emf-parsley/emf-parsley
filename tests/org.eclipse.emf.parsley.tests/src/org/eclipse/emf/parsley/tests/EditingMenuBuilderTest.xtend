package org.eclipse.emf.parsley.tests

import org.eclipse.emf.parsley.tests.scenarios.EditingMenuBuilderTestWithEditingDomainRetrievedScenario
import org.eclipse.emf.parsley.tests.scenarios.EditingMenuBuilderTestWithEditingDomainSetScenario
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite)
@Suite.SuiteClasses(#[
	EditingMenuBuilderTestWithEditingDomainSetScenario,
	EditingMenuBuilderTestWithEditingDomainRetrievedScenario
])
class EditingMenuBuilderTest {


}