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

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

/**
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
	@SuppressWarnings("unchecked")
	public static Collection<Object> ensureCollection(Object contents) {
		if (contents == null) {
			return Collections.emptyList();
		}
		if (contents instanceof Collection<?>) {
			return (Collection<Object>) contents;
		} else if (contents instanceof Iterable<?>) {
			return Lists.newArrayList((Iterable<?>) contents);
		} else if (contents instanceof Iterator<?>) {
			return Lists.newArrayList((Iterator<?>) contents);
		} else {
			return Collections.singleton(contents);
		}
	}

	/**
	 * Given a List of Integer it returns an array of int
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
}
