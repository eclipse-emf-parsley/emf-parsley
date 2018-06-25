/** 
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 */
package
/*
 * generated by Xtext
 */
org.eclipse.emf.parsley.dsl

import com.google.inject.Binder
import com.google.inject.name.Names
import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslGenerator
import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider
import org.eclipse.emf.parsley.dsl.scoping.EmfParsleyDslImplicitlyImportedFeatures
import org.eclipse.emf.parsley.dsl.scoping.EmfParsleyDslImportedNamespaceScopeProvider
import org.eclipse.emf.parsley.dsl.scoping.EmfParsleyDslResourceDescriptionStrategy
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.generator.IOutputConfigurationProvider
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy
import org.eclipse.xtext.scoping.IScopeProvider
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider
import org.eclipse.xtext.xbase.scoping.batch.ImplicitlyImportedFeatures

/** 
 * Use this class to register components to be used at runtime / without the
 * Equinox extension registry.
 */
class EmfParsleyDslRuntimeModule extends AbstractEmfParsleyDslRuntimeModule {

	def Class<? extends IOutputConfigurationProvider> bindIOutputConfigurationProvider() {
		return EmfParsleyDslOutputConfigurationProvider
	}

	override Class<? extends IGenerator> bindIGenerator() {
		return EmfParsleyDslGenerator
	}

	def Class<? extends ImplicitlyImportedFeatures> bindImplicitlyImportedFeatures() {
		return EmfParsleyDslImplicitlyImportedFeatures
	}

	override Class<? extends IDefaultResourceDescriptionStrategy> bindIDefaultResourceDescriptionStrategy() {
		return EmfParsleyDslResourceDescriptionStrategy
	}

	override void configureIScopeProviderDelegate(Binder binder) {
		binder.bind(IScopeProvider).annotatedWith(
			Names.named(AbstractDeclarativeScopeProvider.NAMED_DELEGATE)).to(
			EmfParsleyDslImportedNamespaceScopeProvider)
	}

}
