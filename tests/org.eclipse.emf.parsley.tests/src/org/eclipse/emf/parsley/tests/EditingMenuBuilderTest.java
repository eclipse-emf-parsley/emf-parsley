package org.eclipse.emf.parsley.tests;

import org.eclipse.emf.parsley.tests.scenarios.EditingMenuBuilderTestWithEditingDomainRetrievedScenario;
import org.eclipse.emf.parsley.tests.scenarios.EditingMenuBuilderTestWithEditingDomainSetScenario;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	EditingMenuBuilderTestWithEditingDomainSetScenario.class,
	EditingMenuBuilderTestWithEditingDomainRetrievedScenario.class
})
public class EditingMenuBuilderTest {

}
