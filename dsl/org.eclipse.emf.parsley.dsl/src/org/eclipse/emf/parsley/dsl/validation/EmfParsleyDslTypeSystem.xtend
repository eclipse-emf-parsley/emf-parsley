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
package org.eclipse.emf.parsley.dsl.validation

import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.parsley.EmfParsleyGuiceModule
import org.eclipse.ui.IViewPart
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.common.types.util.TypeReferences
import org.eclipse.xtext.xbase.typing.XbaseTypeConformanceComputer

class EmfParsleyDslTypeSystem {
	@Inject
	private XbaseTypeConformanceComputer conformanceComputer
	
	@Inject
	private TypeReferences typeReferences

	def isConformant(JvmTypeReference expected,
			JvmTypeReference actual) {
		conformanceComputer.isConformant(expected, actual);
	}
	
	def isEObject(JvmTypeReference type, EObject context) {
		isConformant(
				typeReferences.getTypeForName(typeof(EObject), context), type);
	}

	def isEStructuralFeature(JvmTypeReference type, EObject context) {
		isConformant(typeReferences.getTypeForName(
				typeof(EStructuralFeature), context), type);
	}

	def isViewPart(JvmTypeReference type, EObject context) {
		isConformant(typeReferences.getTypeForName(
				typeof(IViewPart), context), type);
	}

	def isEmfParsleyGuiceModule(JvmTypeReference type, EObject context) {
		isConformant(typeReferences.getTypeForName(
				typeof(EmfParsleyGuiceModule), context), type);
	}
}