/**
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 */
package org.eclipse.emf.parsley.dsl.util;

import static org.eclipse.xtext.EcoreUtil2.getContainerOfType;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.dsl.model.EmfFeatureAccess;

public class EmfParsleyDslModelUtil {

	private EmfParsleyDslModelUtil() {
		// only static utility methods
	}

	public static EmfFeatureAccess containingEmfFeatureAccess(final EObject o) {
		return getContainerOfType(o, EmfFeatureAccess.class);
	}

	public static org.eclipse.emf.parsley.dsl.model.Module containingModule(final EObject o) {
		return getContainerOfType(o,
				org.eclipse.emf.parsley.dsl.model.Module.class);
	}
}
