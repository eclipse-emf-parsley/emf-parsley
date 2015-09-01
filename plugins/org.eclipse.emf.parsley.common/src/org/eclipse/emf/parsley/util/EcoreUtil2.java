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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class EcoreUtil2 {

	protected EcoreUtil2() {
	}

	/**
	 * Clones the given EObject without resolving any proxies.
	 */
	public static <T extends EObject> T clone(T original) {
		return EcoreUtil.copy(original);
	}

	public static EObjectState copyState(EObject o) {
		return new EObjectState(o);
	}

	@SuppressWarnings({ "unchecked", "cast" })
	public static <T extends EObject> List<T> getAllContentsOfType(EObject ele, EClass type) {
		return (List<T>) getAllContentsOfType(ele, (Class<T>) type.getInstanceClass());
	}

	@SuppressWarnings("unchecked")
	public static <T extends EObject> List<T> getAllContentsOfType(EObject ele, Class<T> type) {
		List<T> result = new ArrayList<T>();
		TreeIterator<EObject> allContents = ele.eAllContents();
		while (allContents.hasNext()) {
			EObject object = allContents.next();
			if (type.isAssignableFrom(object.getClass())) {
				result.add((T) object);
			}
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "cast" })
	public static <T extends EObject> List<T> getAllContentsOfType(Resource resource, EClass type) {
		return (List<T>) getAllContentsOfType(resource, (Class<T>) type.getInstanceClass());
	}

	@SuppressWarnings("unchecked")
	public static <T extends EObject> List<T> getAllContentsOfType(Resource resource, Class<T> type) {
		List<T> result = new ArrayList<T>();
		TreeIterator<EObject> iterator = resource.getAllContents();
		while (iterator.hasNext()) {
			EObject object = iterator.next();
			if (type.isAssignableFrom(object.getClass())) {
				result.add((T) object);
			}
		}
		return result;
	}
}
