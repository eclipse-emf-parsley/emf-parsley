/*******************************************************************************
 * Copyright (c) 2018 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.internal.inject;

import java.util.ArrayDeque;
import java.util.Deque;

import org.eclipse.emf.parsley.inject.InjectableParameter;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.util.Types;

/**
 * This is the Guice {@link Provider} that we internally use to correctly inject
 * {@link InjectableParameter} parameters into constructors annotated with
 * {@link Inject}.
 * 
 * FOR INTERNAL USE ONLY!
 * 
 * This assumes that, before creating an instance via injection that requires T,
 * an instance of T is first pushed in the internal stack using
 * {@link #insertForLaterProvide(Object)}.
 * 
 * @param <T>
 *            The type of the parameter to inject
 * 
 * @author Lorenzo Bettini
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class InjectableParameterProvider<T extends InjectableParameter> implements Provider<T> {

	private Deque<T> stack = new ArrayDeque<T>();

	public void insertForLaterProvide(T params) {
		stack.push(params);
	}

	@Override
	public T get() {
		return stack.pop();
	}

	/**
	 * Creates the Guice binding that binds the parameterType to the corresponding
	 * provider making sure the provider is also injected as a {@link Singleton};
	 * thus, instances of parameterType will always be created with the same
	 * provider (per {@link Injector}).
	 * 
	 * @param binder
	 * @param parameterType
	 */
	public static <ParameterType extends InjectableParameter> void bindInjectableParameterProvider(Binder binder,
			Class<ParameterType> parameterType) {
		TypeLiteral<ParameterType> parameterTypeLiteral = TypeLiteral.get(parameterType);
		@SuppressWarnings("unchecked")
		TypeLiteral<InjectableParameterProvider<ParameterType>> providerTypeLiteral = 
			(TypeLiteral<InjectableParameterProvider<ParameterType>>)
			TypeLiteral.get(Types.newParameterizedType(InjectableParameterProvider.class, parameterType));
		// first bind the parameter type to the generic provider (used by the factory) to a generic instance of the provider
		binder.bind(parameterTypeLiteral).toProvider(providerTypeLiteral);
		// then make sure the same provider is used for injecting all those instances
		binder.bind(providerTypeLiteral).in(Scopes.SINGLETON);
	}
}