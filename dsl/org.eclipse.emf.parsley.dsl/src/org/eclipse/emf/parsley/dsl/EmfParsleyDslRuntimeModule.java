/**
 * Copyright (c) 2023 Lorenzo Bettini and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 */
package org.eclipse.emf.parsley.dsl;

import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslGenerator;
import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslGeneratorConfigProvider;
import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider;
import org.eclipse.emf.parsley.dsl.scoping.EmfParsleyDslImplicitlyImportedFeatures;
import org.eclipse.emf.parsley.dsl.scoping.EmfParsleyDslImportedNamespaceScopeProvider;
import org.eclipse.emf.parsley.dsl.scoping.EmfParsleyDslResourceDescriptionStrategy;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;
import org.eclipse.xtext.xbase.compiler.IGeneratorConfigProvider;
import org.eclipse.xtext.xbase.scoping.batch.ImplicitlyImportedFeatures;

import com.google.inject.Binder;
import com.google.inject.name.Names;

/**
 * Use this class to register components to be used at runtime / without the
 * Equinox extension registry.
 */
public class EmfParsleyDslRuntimeModule extends AbstractEmfParsleyDslRuntimeModule {
	public Class<? extends IOutputConfigurationProvider> bindIOutputConfigurationProvider() {
		return EmfParsleyDslOutputConfigurationProvider.class;
	}

	@Override
	public Class<? extends IGenerator> bindIGenerator() {
		return EmfParsleyDslGenerator.class;
	}

	public Class<? extends ImplicitlyImportedFeatures> bindImplicitlyImportedFeatures() {
		return EmfParsleyDslImplicitlyImportedFeatures.class;
	}

	@Override
	public Class<? extends IDefaultResourceDescriptionStrategy> bindIDefaultResourceDescriptionStrategy() {
		return EmfParsleyDslResourceDescriptionStrategy.class;
	}

	@Override
	public void configureIScopeProviderDelegate(final Binder binder) {
		binder.bind(IScopeProvider.class).annotatedWith(Names.named(AbstractDeclarativeScopeProvider.NAMED_DELEGATE))
				.to(EmfParsleyDslImportedNamespaceScopeProvider.class);
	}

	public Class<? extends IGeneratorConfigProvider> bindIGeneratorConfigProvider() {
		return EmfParsleyDslGeneratorConfigProvider.class;
	}
}
