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
package org.eclipse.emf.parsley.dsl.util

import com.google.inject.Inject
import org.eclipse.emf.parsley.dsl.model.Module
import org.eclipse.xtext.common.types.JvmGenericType
import org.eclipse.xtext.common.types.JvmOperation
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelAssociations

class EmfParsleyDslGuiceModuleHelper {
	@Inject extension IJvmModelAssociations

	def getModuleInferredType(Module module) {
		module.jvmElements.filter(JvmGenericType).head
	}

	def Iterable<JvmOperation> getAllGuiceValueBindingsMethodsInSuperclass(Module module) {
		module.moduleInferredType?.allGuiceValueBindingsMethodsInSuperclass ?: emptyList
	}

	def getAllGuiceValueBindingsMethodsInSuperclass(JvmGenericType type) {
		// These are all the value bindings in the superclass
		(type.superTypes.head.type as JvmGenericType).allFeatures.filter(JvmOperation).filter[
			simpleName.startsWith("value")]
	}
}
