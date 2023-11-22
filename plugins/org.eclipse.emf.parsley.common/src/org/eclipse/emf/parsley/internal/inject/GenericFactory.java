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
import java.util.HashMap;

import org.eclipse.emf.parsley.inject.parameters.FactoryParameter;
import org.eclipse.emf.parsley.inject.parameters.InjectableParameter;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;
import com.google.inject.Singleton;

/**
 * A generic factory for for injecting {@link InjectableParameter} parameters,
 * annotated with {@link FactoryParameter}, in the constructors annotated with
 * {@link Inject}.
 * 
 * <p>
 * FOR INTERNAL USE ONLY!
 * 
 * @param <T>
 *            The type of objects to be created by injection
 * @param <P>
 *            The type of parameter to inject
 * 
 * @author Lorenzo Bettini
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
@Singleton
public class GenericFactory<T> {

	@Inject
	private FactoryScope scope;

	@Inject
	private Injector injector;

	public <V extends T> V createInstance(Class<V> type, InjectableParameter... params) {
		try {
			scope.enter();
			for (InjectableParameter param : params) {
				scope.addParameter(param);
			}
			return injector.getInstance(type);
		} finally {
			scope.leave();
		}
	}

	/**
	 * Performs Guice bindings so that this factory can be used to inject parameters
	 * annotated with {@link FactoryScope}.
	 * 
	 * @param binder
	 */
	public static void prepareFactory(Binder binder) {
		// the following configuration is required for injecting
		// parameters into constructors using a GenericFactory
		FactoryScope scope = new FactoryScope();
		// tell Guice about the scope
		binder.bindScope(FactoryParameter.class, scope);
		// make our scope instance injectable
		binder.bind(FactoryScope.class).toInstance(scope);
	}

	/**
	 * A {@link Scope} implementation for injecting parameters into constructors
	 * using our factories.
	 * 
	 * <p>
	 * FOR INTERNAL USE ONLY!
	 * 
	 * <p>
	 * Use this scope as follows: inject an instance and add parameters that will be
	 * injected into constructors in a try/finally block of the shape:
	 * 
	 * <pre>
	 * <code>
	 *   // enter the scope
	 *   scope.enter();
	 *   try {
	 *     // add parameters...
	 *     scope.addParameter(...);
	 *     // create an instance through an injector
	 *   } finally {
	 *     // leave the scope
	 *     scope.exit();
	 *   }
	 * </code>
	 * </pre>
	 * 
	 * @author Lorenzo Bettini - initial API and implementation
	 *
	 */
	private static class FactoryScope implements Scope {

		private static class Parameters extends HashMap<Key<?>, Object> {

		}

		private static class ParametersStack extends ArrayDeque<Parameters> {

		}

		// Make this a ThreadLocal for multithreading.
		private final ThreadLocal<ParametersStack> parametersStack = ThreadLocal.withInitial(ParametersStack::new);

		@Override
		public <T> Provider<T> scope(final Key<T> key, final Provider<T> unscoped) {
			return () -> {
				Object param = parametersStack.get().peek().get(key);
				@SuppressWarnings("unchecked")
				T toReturn = (T) param;
				return toReturn;
			};
		}

		public void enter() {
			parametersStack.get().push(new Parameters());
		}

		public void leave() {
			parametersStack.get().pop();
		}

		public void addParameter(InjectableParameter o) {
			parametersStack.get().peek().put(Key.get(o.getClass()), o);
		}

	}
}
