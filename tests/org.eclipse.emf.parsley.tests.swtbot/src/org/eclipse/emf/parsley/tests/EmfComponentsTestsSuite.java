package org.eclipse.emf.parsley.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Lorenzo Bettini - Initial Contribution
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	EmfComponentsUtilTests.class,
	EmfComponentsColumnLabelProviderTests.class,
	EmfComponentsLabelProviderTests.class,
	EmfComponentsProvidersTests.class,
	EmfComponentsProvidersBasedOnViewTests.class,
	EmfComponentsViewTests.class,
	EmfComponentsSaveableViewTests.class,
	EmfComponentsEditorTests.class,
	EmfComponentsFormTests.class,
	EmfComponentsWizardsTests.class,
	EmfComponentsExamplesWizardsTests.class,
	EmfParsleyDslWizardsTests.class,
	EmfParsleyDslEditorTests.class
})
public class EmfComponentsTestsSuite {
}
