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
package org.eclipse.emf.parsley.dsl.typing

import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.xbase.typesystem.legacy.StandardTypeReferenceOwner
import org.eclipse.xtext.xbase.typesystem.references.OwnedConverter
import org.eclipse.xtext.xbase.typesystem.util.CommonTypeComputationServices

class EmfParsleyDslTypeSystem {
	@Inject CommonTypeComputationServices services;
	
	def isConformant(EObject context, Class<?> expected, JvmTypeReference actual) {
		val actualType = actual.toLightweightTypeReference(context)
		actualType.isSubtypeOf(expected)
	}
	
	def toLightweightTypeReference(JvmTypeReference typeRef, EObject context) {
		val converter = new OwnedConverter(new StandardTypeReferenceOwner(services, context))
		converter.toLightweightReference(typeRef)
	}
	
}