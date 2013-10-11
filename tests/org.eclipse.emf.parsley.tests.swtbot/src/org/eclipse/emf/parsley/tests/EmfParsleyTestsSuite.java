package org.eclipse.emf.parsley.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Lorenzo Bettini - Initial Contribution
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	EmfParsleyUtilTests.class,
	EmfParsleyColumnLabelProviderTests.class,
	EmfParsleyLabelProviderTests.class,
	EmfParsleyProvidersTests.class,
	EmfParsleyProvidersBasedOnViewTests.class,
	EmfParsleyViewTests.class,
	EmfParsleySaveableViewTests.class,
	EmfParsleyEditorTests.class,
	EmfParsleyFormTests.class,
	EmfParsleyWizardsTests.class,
	EmfParsleyExamplesWizardsTests.class,
	EmfParsleyDslWizardsTests.class,
	EmfParsleyDslEditorTests.class
})
public class EmfParsleyTestsSuite {
}
