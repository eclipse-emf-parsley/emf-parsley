/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.runtime.service;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import org.apache.log4j.Logger;

import com.google.inject.Binder;
import com.google.inject.Key;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.name.Names;

/**
 * This will be used for methods in the Guice module of the shape (where
 * Type is a Java type)
 *
 * <pre>
 * public Type valueXYZ() {
 *   return &lt;an object assignable to Type&gt;;
 * }
 * </pre>
 *
 * that corresponds to manually implement a configure method of the shape
 * (note the XYZ identifier)
 *
 * <pre>
 * public void configureIterableStringSeparator(Binder binder) {
 * 		binder.bind(Type.class).annotatedWith
 * 			(Names.named("XYZ")).
 * 				toInstance(&lt;an object assignable to Type&gt;);
 * }
 * </pre>
 *
 * @author Lorenzo Bettini - Initial contribution and API
 */
public class FieldSetterModule extends MethodBasedModule {

	public static final String METHOD_PREFIX = "value";

	private static final Logger LOGGER = Logger.getLogger(BindModule.class);

	public FieldSetterModule(Method method, Object owner) {
		super(method, owner);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void configure(Binder binder) {
		Type key = getKeyType();
		Object instance = invokeMethod();
		String annotation = getMethod().getName().
				substring(METHOD_PREFIX.length());
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace(
					"Adding binding from " + getMethod().getReturnType().getName() +
					" to instance " + instance.toString()
					+ ". Declaring Method was '" + getMethod().toGenericString() + "' in Module "
					+ this.getClass().getName());
		}
		LinkedBindingBuilder<Object> bind =
				binder.bind((Key<Object>)Key.get(key, Names.named(annotation)));
		bindToInstance(bind, instance);
	}

}