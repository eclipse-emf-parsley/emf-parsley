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
package org.eclipse.emf.parsley.dsl.scoping

import java.beans.Introspector
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.common.types.JvmGenericType
import org.eclipse.xtext.common.types.JvmOperation
import org.eclipse.xtext.common.types.JvmType
import org.eclipse.xtext.common.types.TypesPackage
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.impl.SimpleScope
import org.eclipse.xtext.xbase.annotations.typesystem.XbaseWithAnnotationsBatchScopeProvider

import static extension org.eclipse.emf.parsley.dsl.util.EmfParsleyDslModelUtil.*
import static extension org.eclipse.xtext.scoping.Scopes.*

class EmfParsleyDslXbaseBatchScopeProvider extends XbaseWithAnnotationsBatchScopeProvider {
	
	override getScope(EObject context, EReference reference) {
		val scope = context.createCustomScope(reference)
		
		if (scope != null)
			return scope;
		super.getScope(context, reference);
	}

	def IScope createCustomScope(EObject context, EReference reference) {
		if (reference.EType == TypesPackage::eINSTANCE.jvmMember) {
			return context.containingEmfFeatureAccess?.
				parameterType?.type?.customScope
		}

		return null;
	}
	
	def dispatch IScope customScope(JvmType t) {
		null
	}

	def dispatch IScope customScope(JvmGenericType t) {
		// a JvmMember does not have 'name', but 'simpleName'
		// thus we must also provide a function for computing the
		// QualifiedName (the default one relies on 'name')
		return new SimpleScope(
			t.allFeatures.filter(typeof(JvmOperation)).filter[!static].
				scopedElementsFor [
					if (simpleName.getterMethod)
						QualifiedName::create
							(simpleName.propertyNameForGetterMethod)
					else
						null
			]
		)
	}
	
	def isGetterMethod(String opName) {
		return getterMethodForNonBoolean(opName) || getterMethodForBoolean(opName);
	}
	
	def getPropertyNameForGetterMethod(String opName) {
		if (getterMethodForNonBoolean(opName))
			return Introspector::decapitalize(opName.substring(3));

		if (getterMethodForBoolean(opName))
			return Introspector::decapitalize(opName.substring(2));
		return null;
	}
	
	private def getterMethodForBoolean(String opName) {
		opName.getterMethodInternal("is")
	}
	
	private def getterMethodForNonBoolean(String opName) {
		opName.getterMethodInternal("get")
	}

	private def getterMethodInternal(String opName, String prefix) {
		opName.startsWith(prefix) && 
			opName.length() > prefix.length && 
			Character::isUpperCase(opName.charAt(prefix.length))
	}

}