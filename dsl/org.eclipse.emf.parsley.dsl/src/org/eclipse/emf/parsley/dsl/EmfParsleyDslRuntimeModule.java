/*
 * generated by Xtext
 */
package org.eclipse.emf.parsley.dsl;

import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslGenerator;
import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider;
import org.eclipse.emf.parsley.dsl.scoping.EmfParsleyDslImplicitlyImportedTypes;
import org.eclipse.emf.parsley.dsl.scoping.EmfParsleyDslImportedNamespaceScopeProvider;
import org.eclipse.emf.parsley.dsl.scoping.EmfParsleyDslScopeProvider;
import org.eclipse.emf.parsley.dsl.scoping.EmfParsleyDslXbaseBatchScopeProvider;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.generator.OutputConfigurationProvider;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.xbase.scoping.batch.ImplicitlyImportedTypes;
import org.eclipse.xtext.xbase.scoping.batch.XbaseBatchScopeProvider;

import com.google.inject.Binder;
import com.google.inject.name.Names;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class EmfParsleyDslRuntimeModule extends org.eclipse.emf.parsley.dsl.AbstractEmfParsleyDslRuntimeModule {

	@Override
	public Class<? extends XbaseBatchScopeProvider> bindXbaseBatchScopeProvider() {
		return EmfParsleyDslXbaseBatchScopeProvider.class;
	}

	public Class<? extends IOutputConfigurationProvider> bindIOutputConfigurationProvider() {
		return EmfParsleyDslOutputConfigurationProvider.class;
	}

	public Class<? extends OutputConfigurationProvider> bindOutputConfigurationProvider() {
		return EmfParsleyDslOutputConfigurationProvider.class;
	}
	
	@Override
	public Class<? extends IGenerator> bindIGenerator() {
		return EmfParsleyDslGenerator.class;
	}

	@Override
	public Class<? extends IScopeProvider> bindIScopeProvider() {
		return EmfParsleyDslScopeProvider.class;
	}

	public Class<? extends ImplicitlyImportedTypes> bindImplicitlyImportedTypes() {
		return EmfParsleyDslImplicitlyImportedTypes.class;
	}
	
	@Override
	public void configureIScopeProviderDelegate(Binder binder) {
		binder.bind(org.eclipse.xtext.scoping.IScopeProvider.class)
				.annotatedWith(
						Names.named(org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider.NAMED_DELEGATE))
				.to(EmfParsleyDslImportedNamespaceScopeProvider.class);
	}
}
