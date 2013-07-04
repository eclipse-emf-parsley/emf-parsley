
package org.eclipse.emf.parsley.dsl;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class EmfParsleyDslStandaloneSetup extends EmfParsleyDslStandaloneSetupGenerated{

	public static void doSetup() {
		new EmfParsleyDslStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

