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
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.parsley.dsl.model.Module
import org.eclipse.emf.parsley.dsl.model.WithExtendsClause
import org.eclipse.emf.parsley.dsl.typing.EmfParsleyDslTypeSystem
import org.eclipse.xtext.common.types.JvmGenericType
import org.eclipse.xtext.common.types.JvmOperation
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelAssociations
import org.eclipse.xtext.xbase.typesystem.^override.IResolvedOperation
import org.eclipse.xtext.xbase.typesystem.^override.OverrideHelper
import org.eclipse.ui.plugin.AbstractUIPlugin

class EmfParsleyDslGuiceModuleHelper {
	@Inject extension IJvmModelAssociations

	@Inject extension OverrideHelper

	@Inject extension EmfParsleyDslTypeSystem

	def getModuleInferredType(Module module) {
		module.inferredJavaTypes.head
	}

	def getInferredJavaTypes(EObject o) {
		o.jvmElements.filter(JvmGenericType)
	}

	def Iterable<JvmOperation> getAllGuiceValueBindingsMethodsInSuperclass(Module module) {
		module.moduleInferredType.getAllGuiceValueBindingsMethodsInSuperclass
	}

	def Iterable<JvmOperation> getAllGuiceTypeBindingsMethodsInSuperclass(Module module) {
		module.superTypeJvmOperations.filter[
			simpleName.startsWith("bind")
			&&
			// for the moment we handle only bind methods that return Class<? extends Something>
			(returnType as JvmParameterizedTypeReference).arguments.head !== null
		]
	}

	def Iterable<JvmOperation> getAllGuiceProviderBindingsMethodsInSuperclass(Module module) {
		module.superTypeJvmOperations.filter[
			simpleName.startsWith("provide")
		]
	}

	def Iterable<JvmOperation> getAllGuiceValueBindingsMethodsInSuperclass(JvmGenericType type) {
		// These are all the value bindings in the superclass
		type.superTypeJvmOperations.filter[
			simpleName.startsWith("value")
		]
	}

	def getAllWithExtendsClauseInferredJavaTypes(Module module) {
		module.allWithExtendsClause.map[inferredJavaTypes].flatten
	}

	def getAllWithExtendsClause(Module module) {
		module.eContents.filter(WithExtendsClause)
	}

	def getJavaResolvedFeatures(JvmGenericType type) {
		type.getResolvedFeatures()
	}

	def getJavaMethodResolvedErasedSignature(IResolvedOperation op) {
		op.resolvedErasureSignature
	}

	def containsConstructorAcceptingPluginParameter(EObject context, JvmTypeReference typeRef) {
		val type = typeRef.type
		if (type instanceof JvmGenericType) {
			return type.declaredConstructors.exists[
				parameters.size == 1
				&&
				context.isConformant(AbstractUIPlugin, parameters.head.parameterType)
			]
		}
		return false
	}

	def private superTypeJvmOperations(Module module) {
		module.moduleInferredType.superTypeJvmOperations
	}

	def private superTypeJvmOperations(JvmGenericType type) {
		if (type === null) {
			return emptyList
		}
		return (type.superTypes.head.type as JvmGenericType).allFeatures.filter(JvmOperation)
	}
}
