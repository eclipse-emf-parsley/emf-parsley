package org.eclipse.emf.parsley.dsl.tests.inputs;

import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;

import com.google.inject.Provider;

/**
 * This is only for testing purposes in the compiler tests
 * 
 * @author Lorenzo Bettini
 *
 */
public class TestFeaturesProviderProvider implements Provider<FeaturesProvider> {

	public FeaturesProvider get() {
		return null;
	}

}
