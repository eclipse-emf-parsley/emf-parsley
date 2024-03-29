/*******************************************************************************
 * Copyright (c) 2008, 2013, 2017 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * itemis AG - Initial contribution and API
 * Francesco Guidieri - additional error handlers
 * Lorenzo Bettini - https://bugs.eclipse.org/bugs/show_bug.cgi?id=512441
 *******************************************************************************/
package org.eclipse.emf.parsley.runtime.util;

import static org.eclipse.emf.parsley.runtime.util.ReflectionUtil.getObjectType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.base.Predicate;

/**
 * @author Sven Efftinge - Initial contribution and API
 * @author Francesco Guidieri - additional error handlers
 * @author Lorenzo Bettini - https://bugs.eclipse.org/bugs/show_bug.cgi?id=512441
 */
public class PolymorphicDispatcher<RT> {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(PolymorphicDispatcher.class);
	private final List<? extends Object> targets;
	private final Predicate<Method> methodFilter;

	private Collection<MethodDesc> methods;

	public static class DefaultErrorHandler<RT> implements ErrorHandler<RT> {
		@Override
		public RT handle(Object[] params, Throwable e) {
			return Exceptions.throwUncheckedException(e);
		}
	}

	public static class NullErrorHandler<RT> implements ErrorHandler<RT> {

		public static <RT> ErrorHandler<RT> get() {
			return new NullErrorHandler<>();
		}

		@Override
		public RT handle(Object[] params, Throwable throwable) {
			// ignore
			return null;
		}
	}

	public static class WarningErrorHandler<RT> implements ErrorHandler<RT> {

		private Logger logger;

		public WarningErrorHandler(Logger logger) {
			this.logger = logger;
		}

		public static <RT> ErrorHandler<RT> get(Logger logger) {
			return new WarningErrorHandler<>(logger);
		}

		@Override
		public RT handle(Object[] params, Throwable throwable) {
			logger.warn("Error in polymorphic dispatcher : "+throwable.getMessage(), throwable);
			return null;
		}
	}

	public static class MethodNameFilter implements Predicate<Method> {

		protected final int maxParams;

		protected final String methodName;

		protected final int minParams;

		public MethodNameFilter(String methodName, int minParams, int maxParams) {
			this.maxParams = maxParams;
			this.methodName = methodName;
			this.minParams = minParams;
		}

		@Override
		public boolean apply(Method param) {
			return param.getName().equals(methodName) && param.getParameterTypes().length >= minParams
					&& param.getParameterTypes().length <= maxParams;
		}

		@Override
		public String toString() {
			return "'" + methodName + "'";
		}

		public int getMaxParams() {
			return maxParams;
		}

		public int getMinParams() {
			return minParams;
		}
	}

	public static class Predicates {

		private Predicates() {

		}

		public static Predicate<Method> forName(String name) {
			return new MethodNameFilter(name, 1, 1);
		}

		public static Predicate<Method> forName(String name, int params) {
			return new MethodNameFilter(name, params, params);
		}

	}

	public static interface ErrorHandler<P> {
		P handle(Object[] params, Throwable throwable);
	}

	private final ErrorHandler<RT> handler;

	public static <T> PolymorphicDispatcher<T> createForSingleTarget(final String methodName, final Object singleTarget) {
		return new PolymorphicDispatcher<>(methodName, Collections.singletonList(singleTarget));
	}

	public static <T> PolymorphicDispatcher<T> createForSingleTarget(final String methodName, int min, int max, final Object singleTarget) {
		return new PolymorphicDispatcher<>(methodName, min, max, Collections.singletonList(singleTarget));
	}

	public static <T> PolymorphicDispatcher<T> createForSingleTarget(Predicate<Method> methodFilter, Object singleTarget) {
		return new PolymorphicDispatcher<>(Collections.singletonList(singleTarget), methodFilter);
	}

	public static <T> PolymorphicDispatcher<T> createForVarTarget(final String methodName, final Object... targets) {
		return new PolymorphicDispatcher<>(methodName, Arrays.asList(targets));
	}

	public PolymorphicDispatcher(final String methodName, final List<? extends Object> targets) {
		this(methodName, 1, 1, targets);
	}

	public PolymorphicDispatcher(final String methodName, final int minParams, final int maxParams, final List<? extends Object> targets) {
		this(methodName, minParams, maxParams, targets, new DefaultErrorHandler<>());
	}

	public PolymorphicDispatcher(final String methodName, final int minParams, final int maxParams, final List<? extends Object> targets,
			ErrorHandler<RT> handler) {
		this(targets, new MethodNameFilter(methodName, minParams, maxParams), handler);
	}

	public PolymorphicDispatcher(final List<? extends Object> targets, Predicate<Method> methodFilter) {
		this(targets, methodFilter, new DefaultErrorHandler<>());
	}

	public PolymorphicDispatcher(final List<? extends Object> targets, Predicate<Method> methodFilter, ErrorHandler<RT> handler) {
		this.targets = targets;
		this.methodFilter = methodFilter;
		this.handler = handler;
		methods = getCandidateMethods();
	}

	protected class MethodDesc {
		private final Object target;
		private final Method method;

		protected MethodDesc(Object target, Method method) {
			super();
			this.target = target;
			this.method = method;
		}

		public Class<?> getDeclaringClass() {
			return method.getDeclaringClass();
		}

		public Class<?>[] getParameterTypes() {
			return method.getParameterTypes();
		}

		public Method getMethod() {
			return method;
		}

		public Object getTarget() {
			return target;
		}

		public boolean isInvokeable(final List<Class<?>> paramTypes) {
			if (getParameterTypes().length != paramTypes.size()) {
				return false;
			}
			for (int i = 0; i < paramTypes.size(); i++) {
				Class<?> paramClass = paramTypes.get(i);
				if (paramClass!=null && !Void.class.equals(paramClass) && !(getObjectType(getParameterTypes()[i]).isAssignableFrom(getObjectType(paramClass)))) {
					return false;
				}
			}
			return true;
		}

		@Override
		public String toString() {
			return this.method.toString();
		}
	}

	protected int compare(MethodDesc o1, MethodDesc o2) {
		final Class<?>[] paramTypes1 = o1.getParameterTypes();
		final Class<?>[] paramTypes2 = o2.getParameterTypes();

		// sort by number of parameters
		if (paramTypes1.length > paramTypes2.length) {
			return 1;
		}
		if (paramTypes2.length > paramTypes1.length) {
			return -1;
		}

		// sort by parameter types from left to right
		for (int i = 0; i < paramTypes1.length; i++) {
			final Class<?> class1 = paramTypes1[i];
			final Class<?> class2 = paramTypes2[i];

			if (class1.equals(class2)) {
				continue;
			}
			if (class1.isAssignableFrom(class2) || Void.class.equals(class2)) {
				return -1;
			}
			if (class2.isAssignableFrom(class1) || Void.class.equals(class1)) {
				return 1;
			}
		}

		// sort by declaring class (more specific comes first).
		if (!o1.getDeclaringClass().equals(o2.getDeclaringClass())) {
			if (o1.getDeclaringClass().isAssignableFrom(o2.getDeclaringClass())) {
				return 1;
			}
			if (o2.getDeclaringClass().isAssignableFrom(o1.getDeclaringClass())) {
				return -1;
			}
		}

		// sort by target
		final int compareTo = ((Integer) targets.indexOf(o2.target)).compareTo(targets.indexOf(o1.target));
		return compareTo;
	}

	private final SimpleCache<List<Class<?>>, List<MethodDesc>> cache =
		new SimpleCache<>(
			paramTypes -> {
				// 'result' contains all best-matched MethodDesc for which
				// pairwise compare(m1, m2) == 0, meaning they're equal or unrelated.
				List<MethodDesc> result = new ArrayList<>();
				NEXT: for (PolymorphicDispatcher<RT>.MethodDesc methodDesc : methods) {
					if (methodDesc.isInvokeable(paramTypes)) {
						if (result.isEmpty()) {
							result.add(methodDesc);
						} else {
							Iterator<MethodDesc> it = result.iterator();
							while(it.hasNext()) {
								MethodDesc next = it.next();
								int compare = compare(next, methodDesc);
								if (compare < 0) {
									it.remove();
								} else if (compare > 0) {
									continue NEXT;
								}
							}
							result.add(methodDesc);
						}
					}
				}
				return result;
			}
		);

	@SuppressWarnings("unchecked")
	public RT invoke(Object... params) {
		if (methodFilter instanceof MethodNameFilter) {
			MethodNameFilter filter = (MethodNameFilter) methodFilter;
			if (params.length<filter.getMinParams() || params.length > filter.getMaxParams()) {
				throw new IllegalArgumentException("Wrong number of arguments. Expected "+filter.getMinParams()+" to "+filter.getMaxParams()+".");
			}
		}
		List<MethodDesc> result = cache.get(getTypes(params));
		// check if ambiguous
		if (result.size()>1) {
			return handleAmbigousMethods(result, params);
		}

		if (result.isEmpty()) {
			return handleNoSuchMethod(params);
		}

		try {
			MethodDesc current = result.get(0);
			current.method.setAccessible(true);
			return (RT) current.method.invoke(current.target, params);
		} catch (InvocationTargetException e) {
			if (e.getTargetException() instanceof Error) {
				throw (Error) e.getTargetException();
			}
			return handler.handle(params, e.getTargetException());
		} catch (IllegalArgumentException e) {
			return handler.handle(params, e);
		} catch (IllegalAccessException e) {
			return handler.handle(params, e);
		}
	}

	protected RT handleNoSuchMethod(Object... params) {
		return handler.handle(params, new NoSuchMethodException(methodFilter, params));
	}

	protected RT handleAmbigousMethods(List<MethodDesc> result, Object... params) {
		throw new IllegalStateException("Ambiguous methods " + result + " for params " + Arrays.toString(params));
	}

	/**
	 * @param params
	 * @return
	 */
	private List<Class<?>> getTypes(Object[] params) {
		List<Class<?>> result = new ArrayList<>(params.length);
		for (int i = 0; i < params.length; i++) {
			if (params[i]!=null) {
				result.add(params[i].getClass());
			} else {
				result.add(getDefaultClass(i));
			}
		}
		return result;
	}

	/**
	 * @return
	 */
	protected Class<?> getDefaultClass(int paramIndex) {
		return Void.class;
	}

	private Collection<MethodDesc> getCandidateMethods() {
		Collection<MethodDesc> cachedDescriptors = new ArrayList<>();
		for (Object target : targets) {
			Class<?> current = target.getClass();
			while (current != Object.class) {
				Method[] methods = current.getDeclaredMethods();
				for (Method method : methods) {
					if (methodFilter.apply(method)) {
						cachedDescriptors.add(createMethodDesc(target, method));
					}
				}
				current = current.getSuperclass();
			}
		}
		return cachedDescriptors;
	}

	protected MethodDesc createMethodDesc(Object target, Method method) {
		return new MethodDesc(target, method);
	}

	private static class NoSuchMethodException extends java.lang.NoSuchMethodException {

		private static final long serialVersionUID = 1L;
		private final Predicate<Method> methodFilter;
		private final Object[] params;

		public NoSuchMethodException(Predicate<Method> methodFilter, Object[] params) {
			this.methodFilter = methodFilter;
			this.params = params;
		}

		@Override
		public String getMessage() {
			return "Couldn't find method '" + methodFilter.toString() + "' for objects " + Arrays.toString(params);
		}
	}

	public static class ExceptionLogHandler<RT> implements ErrorHandler<RT> {

		private Logger logger;

		public ExceptionLogHandler(Logger logger) {
			this.logger = logger;
		}

		public static <RT> ErrorHandler<RT> get(Logger logger) {
			return new ExceptionLogHandler<>(logger);
		}

		@Override
		public RT handle(Object[] params, Throwable throwable) {
			if(!(throwable instanceof NoSuchMethodException)){
				logger.warn("Error in polymorphic dispatcher : "+throwable.getMessage(), throwable);
			}
			return null;
		}
	}

}
