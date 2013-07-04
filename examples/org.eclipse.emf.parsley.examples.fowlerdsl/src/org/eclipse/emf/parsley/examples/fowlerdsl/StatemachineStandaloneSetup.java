
package org.eclipse.emf.parsley.examples.fowlerdsl;

import org.eclipse.emf.parsley.examples.fowlerdsl.StatemachineStandaloneSetupGenerated;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class StatemachineStandaloneSetup extends StatemachineStandaloneSetupGenerated{

	public static void doSetup() {
		new StatemachineStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

