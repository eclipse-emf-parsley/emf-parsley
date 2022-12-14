/*
 * generated by Xtext 2.11.0
 */
package org.eclipse.emf.parsley.dsl.ide

import com.google.inject.Guice
import org.eclipse.emf.parsley.dsl.EmfParsleyDslRuntimeModule
import org.eclipse.emf.parsley.dsl.EmfParsleyDslStandaloneSetup
import org.eclipse.xtext.util.Modules2

/**
 * Initialization support for running Xtext languages as language servers.
 */
class EmfParsleyDslIdeSetup extends EmfParsleyDslStandaloneSetup {

	override createInjector() {
		Guice.createInjector(Modules2.mixin(new EmfParsleyDslRuntimeModule, new EmfParsleyDslIdeModule))
	}
	
}
