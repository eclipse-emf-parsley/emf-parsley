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

import java.util.Comparator;

import org.eclipse.emf.ecore.EStructuralFeature;

public class EStructuralFeatureNameComparator implements
		Comparator<EStructuralFeature> {
	public int compare(EStructuralFeature o1, EStructuralFeature o2) {
		return nullSafe(o1).compareTo(nullSafe(o2));
	}

	private String nullSafe(EStructuralFeature o) {
		String name = o.getName();
		return name != null ? name : "";
	}
}