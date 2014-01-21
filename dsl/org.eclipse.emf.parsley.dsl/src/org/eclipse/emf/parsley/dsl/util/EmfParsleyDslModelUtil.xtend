/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.util

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.parsley.dsl.model.EmfFeatureAccess

import static extension org.eclipse.xtext.EcoreUtil2.*

class EmfParsleyDslModelUtil {
	
	def static containingEmfFeatureAccess(EObject o) {
		o.getContainerOfType(typeof(EmfFeatureAccess))
	}
}