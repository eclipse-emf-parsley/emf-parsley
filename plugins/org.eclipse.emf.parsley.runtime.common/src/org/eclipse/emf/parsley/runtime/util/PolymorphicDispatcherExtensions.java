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
package org.eclipse.emf.parsley.runtime.util;

import java.lang.reflect.Method;
import java.util.Collections;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.base.Predicate;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class PolymorphicDispatcherExtensions {
	
	protected PolymorphicDispatcherExtensions() {
		
	}

	public static <T> T polymorphicInvokeBasedOnFeature(
			Object target, EClass eClass, EStructuralFeature feature, String prefix, Object...args) {
		return PolymorphicDispatcherExtensions.
				<T>createPolymorphicDispatcherBasedOnFeature(target,
						eClass, feature, prefix, args.length).invoke(args);
	}

	/**
	 * Creates a {@link PolymorphicDispatcher} for methods based on {@link EClass} and
	 * {@link EStructuralFeature}; the method signature is meant to be of the shape
	 * 
	 * <pre>
	 * &lt;prefix&gt;_&lt;eClass's name&gt;_&lt;feature's name&gt;(&lt;numOfParams&gt;)
	 * </pre>
	 * @param target
	 * @param eClass
	 * @param feature
	 * @param prefix
	 * @param numOfParams
	 * @return
	 */
	public static <T> PolymorphicDispatcher<T> createPolymorphicDispatcherBasedOnFeature(
			Object target, EClass eClass, EStructuralFeature feature, String prefix, int numOfParams) {
		return PolymorphicDispatcherExtensions
				.createPolymorphicDispatcher(target,
						featureBasedMethodPredicate(eClass, feature, prefix, numOfParams));
	}

	/**
	 * Creates a {@link Predicate} for methods based on {@link EClass} and
	 * {@link EStructuralFeature}; the method signature is meant to be of the shape
	 * 
	 * <pre>
	 * &lt;prefix&gt;_&lt;eClass's name&gt;_&lt;feature's name&gt;(&lt;numOfParams&gt;)
	 * </pre>
	 * @param eClass
	 * @param feature
	 * @param prefix
	 * @param numOfParams
	 * @return
	 */
	public static Predicate<Method> featureBasedMethodPredicate(
			EClass eClass, EStructuralFeature feature, String prefix, int numOfParams) {
		return PolymorphicDispatcher.Predicates.forName(prefix + eClass.getName()
				+ "_" + feature.getName(), numOfParams);
	}

	/**
	 * Creates a {@link PolymorphicDispatcher} based on the method {@link Predicate} that
	 * is meant to be invoked on the given target; if no method is found polymorphically,
	 * then it returns null.
	 * 
	 * @param target
	 * @param predicate
	 * @return
	 */
	public static <T> PolymorphicDispatcher<T> createPolymorphicDispatcher(
			final Object target, Predicate<Method> predicate) {
		
		return new PolymorphicDispatcher<T>(Collections.singletonList(target), predicate) {
			@Override
			protected T handleNoSuchMethod(Object... params) {
				return null;
			}
		};
	}
}
