package org.eclipse.emf.parsley.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author bettini
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	EmfComponentsColumnLabelProviderTests.class,
	EmfComponentsLabelProviderTests.class,
	EmfComponentsProvidersTests.class,
	EmfComponentsViewTests.class,
	EmfComponentsSaveableViewTests.class,
	EmfComponentsEditorTests.class,
	EmfComponentsFormTests.class
	// for the moment wizard tests does not work
	// when built with maven...
	// EmfComponentsWizardsTests.class
})
public class EmfComponentsMavenTestsSuite {
}
