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

import java.util.HashMap;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;

import com.google.common.collect.Lists;

/**
 * Keeps the state of an {@link EObject}
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class EObjectState extends HashMap<EStructuralFeature, Object> {

	public EObjectState(EObject o) {
		if (o == null) {
			return;
		}
		
		for (EStructuralFeature f : o.eClass().getEAllStructuralFeatures()) {
			if (!f.isChangeable() || f.isDerived()) {
				continue;
			}
			
			Object eGet = o.eGet(f);
			if (eGet instanceof FeatureMap) {
				continue;
			}
			
			if (eGet instanceof EList<?>) {
				EList<?> list = (EList<?>) eGet;
				// make sure to store a copy of the list
				// so that it will not be automatically cleared in
				// case of bidirectional relations
				put(f, Lists.newArrayList(list));
			} else {
				put(f, eGet);
			}	
		}
	}

	public void copyStateTo(EObject o) {
		for (java.util.Map.Entry<EStructuralFeature, Object> e : entrySet()) {
			o.eSet(e.getKey(), e.getValue());
		}
	}
}
