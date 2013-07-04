package org.eclipse.emf.parsley.tests;

import org.eclipse.emf.parsley.tests.EmfComponentsDslWizardsTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author bettini
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
	EmfComponentsDslWizardsTests.class
})
public class EmfComponentsTestsSuite {
}
