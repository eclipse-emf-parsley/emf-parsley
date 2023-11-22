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

import static java.util.Arrays.asList;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.parsley.inject.AfterInject;
import org.eclipse.emf.parsley.inject.EmfParsleyLifecycle;

import com.google.common.base.Objects;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.inject.Binder;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

/**
 * {@link TypeListener} for {@link AfterInject} annotations.
 * 
 * @author Lorenzo Bettini
 *
 */
public class AfterInjectTypeListener implements TypeListener {

	/**
	 * This is notified once-per-instance, so it should run as quickly as possible;
	 * in our implementation we have previously collected the list of methods
	 * {@link AfterInject} to call, and this listener is notified only if there are
	 * such methods in the type of the instance.
	 * 
	 * @author Lorenzo Bettini
	 *
	 * @param <I>
	 */
	private static class AfterInjectInjectionListener<I> implements InjectionListener<I> {
		private Collection<Method> afterInjectMethods;

		public AfterInjectInjectionListener(Collection<Method> afterInjectMethods) {
			this.afterInjectMethods = afterInjectMethods;
		}

		@Override
		public void afterInjection(final I injectee) {
			for (final Method method : afterInjectMethods) {
				try {
					method.setAccessible(true);
					method.invoke(injectee);
				} catch (final Exception e) {
					throw new RuntimeException("@AfterInject " + method, e);
				}
			}
		}
	}

	/**
	 * TypeListeners get notified of the types that Guice injects. Since type
	 * listeners are only notified once-per-type, so we can run potentially slow
	 * operations like, in this case, collect all methods annotated with
	 * {@link AfterInject}; if there no such methods, we don't even register our
	 * {@link AfterInjectInjectionListener}.
	 * 
	 * @see com.google.inject.spi.TypeListener#hear(com.google.inject.TypeLiteral,
	 *      com.google.inject.spi.TypeEncounter)
	 */
	@Override
	public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
		Class<? super I> cl = type.getRawType();
		Collection<Method> afterInjectMethods = collectAfterInjectMethods(cl);
		if (!afterInjectMethods.isEmpty()) {
			encounter.register(new AfterInjectInjectionListener<>(afterInjectMethods));
		}
	}

	private <I> Collection<Method> collectAfterInjectMethods(Class<? super I> cl) {
		return Collections2.filter(collectLifecycleMethods(cl),
				method -> method.isAnnotationPresent(AfterInject.class));
	}

	private <I> List<Method> collectLifecycleMethods(Class<? super I> cl) {
		Class<? super I> current = cl;
		List<Method> methods = new ArrayList<>();
		while (!Objects.equal(current, Object.class)) {
			if (current.isAnnotationPresent(EmfParsleyLifecycle.class)) {
				methods.addAll(asList(current.getDeclaredMethods()));
			}
			current = current.getSuperclass();
		}
		return Lists.reverse(methods);
	}

	public static void bindAfterInjectTypeListener(Binder binder) {
		binder.bindListener(Matchers.any(), new AfterInjectTypeListener());
	}

}