/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.Lists;

/**
 * Some utility static methods.
 *
 * @author Lorenzo Bettini - Initial Contribution and API
 *
 */
public class EmfParsleyUtil {

	protected EmfParsleyUtil() {

	}

	/**
	 * Given the passed object it ensures that it is a {@link Collection}; if it
	 * is not, it returns a singleton {@link Collection}; if it is null it
	 * returns an empty {@link Collection}.
	 *
	 * @param contents
	 * @return
	 */
	public static Collection<Object> ensureCollection(Object contents) {
		if (contents == null) {
			return Collections.emptyList();
		}
		return ensureCollectionInternal(contents);
	}

	@SuppressWarnings("unchecked")
	private static Collection<Object> ensureCollectionInternal(Object contents) {
		if (contents instanceof Collection<?>) {
			return (Collection<Object>) contents;
		} else if (contents instanceof Iterable<?>) {
			return Lists.newArrayList((Iterable<?>) contents);
		} else if (contents instanceof Iterator<?>) {
			return Lists.newArrayList((Iterator<?>) contents);
		} else if (contents.getClass().isArray()) {
			return Arrays.asList((Object[]) contents);
		}
		return Collections.singleton(contents);
	}

	/**
	 * Given a List of Integer it returns an array of int
	 *
	 * @param intList
	 * @return
	 */
	public static int[] toIntArray(List<Integer> intList) {
		int[] result = new int[intList.size()];
		int i = 0;
		for (Integer e : intList) {
			result[i++] = e;
		}
		return result;
	}

	/**
	 * If the passed object is not an EObject simply returns null.
	 *
	 * @param o
	 * @return
	 */
	public static EObject getEObjectOrNull(Object o) {
		if (o instanceof EObject) {
			return (EObject) o;
		}
		return null;
	}

	/**
	 * @since 1.1
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int compareValues(Object value1, Object value2) {
		if (comparables(value1, value2)) {
			return ((Comparable) value1).compareTo(value2);
		} else if (value1 != null && value2 != null) {
			return value1.toString().compareTo(value2.toString());
		} else if (value1 != null) {
			return 1;
		} else if (value2 != null) {
			return -1;
		}
		return 0;
	}

	private static boolean comparables(Object value1, Object value2) {
		return value1 instanceof Comparable && value2 instanceof Comparable;
	}
}
