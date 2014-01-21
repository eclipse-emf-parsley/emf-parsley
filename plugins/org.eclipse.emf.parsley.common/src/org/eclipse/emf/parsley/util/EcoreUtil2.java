/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.emf.parsley.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class EcoreUtil2 {

	/**
	 * Clones the given EObject without resolving any proxies.
	 */
	public static <T extends EObject> T clone(T original) {
		return EcoreUtil.copy(original);
	}

	public static EObjectState copyState(EObject o) {
		return new EObjectState(o);
	}
}
