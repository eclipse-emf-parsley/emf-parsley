package org.eclipse.emf.parsley.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author bettini
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	EmfParsleyColumnLabelProviderTests.class,
	EmfParsleyLabelProviderTests.class,
	EmfParsleyProvidersTests.class,
	EmfParsleyViewTests.class,
	EmfParsleySaveableViewTests.class,
	EmfParsleyEditorTests.class,
	EmfParsleyFormTests.class
	// for the moment wizard tests does not work
	// when built with maven...
	// EmfParsleyWizardsTests.class
})
public class EmfParsleyMavenTestsSuite {
}
