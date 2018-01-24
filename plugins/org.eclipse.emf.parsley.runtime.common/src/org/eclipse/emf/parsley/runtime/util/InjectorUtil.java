/*******************************************************************************
 * Copyright (c) 2018 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.runtime.util;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import com.google.common.collect.Lists;
import com.google.inject.Binding;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.spi.DefaultElementVisitor;
import com.google.inject.spi.Element;
import com.google.inject.spi.Elements;
import com.google.inject.util.Modules;

/**
 * Utility methods dealing with injections.
 * 
 * @author Lorenzo Bettini - initial API and implementation
 * @since 2.0
 *
 */
public class InjectorUtil {

	protected InjectorUtil() {

	}

	/**
	 * Creates a new {@link Module} copyng all the bindings from the passed {@link Injector} and
	 * overriding with the bindings specified in the passed {@link Module}
	 * 
	 * @param injector the injector to copy the current bindings from
	 * @param overrides the module with the overriding bindings
	 * @return
	 */
	public static Module overrideModuleFromInjector(Injector injector, Module overrides) {
		Collection<Binding<?>> bindings = injector.getBindings().values();
		final List<Element> elements = Lists.newArrayList();
		for (Binding<?> binding : bindings) {
			binding.acceptVisitor(new DefaultElementVisitor<Void>() {
				@Override
				public <V> Void visit(Binding<V> binding) {
					Class<? super V> keyType = binding.getKey().getTypeLiteral().getRawType();
					if (keyType.equals(Injector.class) || // Binding to core guice framework type is not allowed: Injector
						keyType.equals(Logger.class)) // built-in binding
						return null;
					return super.visit(binding);
				}
				@Override
				protected Void visitOther(Element element) {
					elements.add(element);
					return null;
				}
			});
		}
		Module copy = Elements.getModule(elements);
		return Modules.override(copy).with(overrides);
	}
}
