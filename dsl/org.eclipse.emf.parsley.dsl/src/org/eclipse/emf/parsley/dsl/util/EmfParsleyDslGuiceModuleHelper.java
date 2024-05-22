/**
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 */
package org.eclipse.emf.parsley.dsl.util;

import static org.eclipse.xtext.xbase.lib.CollectionLiterals.emptyList;
import static org.eclipse.xtext.xbase.lib.IterableExtensions.exists;
import static org.eclipse.xtext.xbase.lib.IterableExtensions.filter;
import static org.eclipse.xtext.xbase.lib.IterableExtensions.head;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.dsl.typing.EmfParsleyDslTypeSystem;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelAssociations;

import com.google.inject.Inject;

public class EmfParsleyDslGuiceModuleHelper {
	@Inject
	private IJvmModelAssociations jvmModelAssociations;

	@Inject
	private EmfParsleyDslTypeSystem emfParsleyDslTypeSystem;

	public JvmGenericType getModuleInferredType(final org.eclipse.emf.parsley.dsl.model.Module module) {
		return head(getInferredJavaTypes(module));
	}

	public Iterable<JvmGenericType> getInferredJavaTypes(final EObject o) {
		return filter(jvmModelAssociations.getJvmElements(o), JvmGenericType.class);
	}

	public Iterable<JvmOperation> getAllGuiceValueBindingsMethodsInSuperclass(
			final org.eclipse.emf.parsley.dsl.model.Module module) {
		return getAllGuiceValueBindingsMethodsInSuperclass(getModuleInferredType(module));
	}

	public Iterable<JvmOperation> getAllGuiceTypeBindingsMethodsInSuperclass(
			final org.eclipse.emf.parsley.dsl.model.Module module) {
		return filter(superTypeJvmOperations(module), it -> it.getSimpleName().startsWith("bind")
				&&
				// for the moment we handle only bind methods that return Class<? extends Something>
				head(((JvmParameterizedTypeReference) it.getReturnType()).getArguments()) != null);
	}

	public Iterable<JvmOperation> getAllGuiceProviderBindingsMethodsInSuperclass(
			final org.eclipse.emf.parsley.dsl.model.Module module) {
		return filter(superTypeJvmOperations(module), it -> it.getSimpleName().startsWith("provide"));
	}

	public Iterable<JvmOperation> getAllGuiceValueBindingsMethodsInSuperclass(final JvmGenericType type) {
		// These are all the value bindings in the superclass
		return filter(superTypeJvmOperations(type), it -> it.getSimpleName().startsWith("value"));
	}

	public boolean containsConstructorAcceptingPluginParameter(final EObject context, final JvmTypeReference typeRef) {
		final JvmType type = typeRef.getType();
		if (type instanceof JvmGenericType) {
			return exists(((JvmGenericType) type).getDeclaredConstructors(),
					it -> it.getParameters().size() == 1
							&& emfParsleyDslTypeSystem.isConformant(context, AbstractUIPlugin.class,
									head(it.getParameters()).getParameterType()));
		}
		return false;
	}

	private Iterable<JvmOperation> superTypeJvmOperations(final org.eclipse.emf.parsley.dsl.model.Module module) {
		return superTypeJvmOperations(getModuleInferredType(module));
	}

	private Iterable<JvmOperation> superTypeJvmOperations(final JvmGenericType type) {
		if (type == null) {
			return emptyList();
		}
		return filter(((JvmGenericType) head(type.getSuperTypes()).getType()).getAllFeatures(),
				JvmOperation.class);
	}
}
