package org.eclipse.emf.parsley.dsl.tests.util;

import org.eclipse.emf.parsley.dsl.tests.EmfParsleyDslInjectorProvider;
import org.eclipse.emf.parsley.dsl.EmfParsleyDslRuntimeModule;
import org.eclipse.emf.parsley.dsl.EmfParsleyDslStandaloneSetup;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * This is required to run tests based on Java compiler in Tycho.
 * 
 * @author Lorenzo Bettini
 *
 */
public class EmfParsleyDslInjectorProviderCustom extends EmfParsleyDslInjectorProvider {
	public Injector internalCreateInjector() {
		return new EmfParsleyDslStandaloneSetup() {
			@Override
			public Injector createInjector() {
				return Guice.createInjector(new EmfParsleyDslRuntimeModule() {
					@Override
					public ClassLoader bindClassLoaderToInstance() {
						return EmfParsleyDslInjectorProviderCustom.class.getClassLoader();
					}
				});
			}
		}.createInjectorAndDoEMFRegistration();
	}

}
