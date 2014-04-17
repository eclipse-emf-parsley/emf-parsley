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

import org.apache.log4j.Logger;

import com.google.common.base.Predicate;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class PolymorphicDispatcherExtensions {
	public static <T> PolymorphicDispatcher<T> createPolymorphicDispatcher(
			final Object target, Predicate<Method> predicate) {
		
		return new PolymorphicDispatcher<T>(Collections.singletonList(target), predicate) {
			private PolymorphicDispatcher.ErrorHandler<T> controlHandler = 
					new PolymorphicDispatcher.ExceptionLogHandler<T>(Logger.getLogger(target.getClass()));
			
			@Override
			protected T handleNoSuchMethod(Object... params) {
				if (PolymorphicDispatcher.ExceptionLogHandler.class
						.equals(controlHandler.getClass()))
					return null;
				return super.handleNoSuchMethod(params);
			}
		};
	}
}
