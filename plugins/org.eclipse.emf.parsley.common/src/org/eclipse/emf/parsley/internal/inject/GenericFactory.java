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

import org.eclipse.emf.parsley.inject.InjectableParameter;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

/**
 * A generic factory for for injecting {@link InjectableParameter} parameters in the constructors.
 * 
 * <p>
 * FOR INTERNAL USE ONLY!
 * 
 * <p>
 * Given a class representing a parameter wrapper, implementing {@link InjectableParameter}, say MyParam, the Guice module
 * must bind P to InjectableParameterProvider&lt;MyParam&gt; and make
 * sure it's a {@link Singleton}.
 * 
 * <p>
 * This must be done with the following steps in the Guice module (pseudo code)
 * 
 * <pre>
 * providerTypeLiteral = 
 * 	new TypeLiteral&lt;InjectableParameterProvider&lt;MyParam&gt;&gt;() {};
 * // first bind the parameter type to the generic provider used by the factory to a generic instance of the provider
 * binder.bind(MyParam).toProvider(providerTypeLiteral);
 * // then make sure the same provider is used for injecting all those instances
 * binder.bind(providerTypeLiteral).in(Scopes.SINGLETON);
 * </pre>
 * 
 * This is done in the EMF Parsley base modules for the parameter types that
 * are used by the EMF Parsley components, using {@link InjectableParameterProvider#bindInjectableParameterProvider(Binder, Class)}.
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
public class GenericFactory<T, P extends InjectableParameter> {

	@Inject
	private InjectableParameterProvider<P> provider;

	@Inject
	private Injector injector;

	synchronized public <V extends T> V createInstance(Class<V> type, final P param) {
		provider.insertForLaterProvide(param);
		return injector.getInstance(type);
	}

}
