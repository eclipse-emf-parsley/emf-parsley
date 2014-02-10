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

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.common.types.JvmGenericType
import org.eclipse.xtext.common.types.JvmType
import org.eclipse.xtext.common.types.TypesPackage
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.impl.SimpleScope

import static extension org.eclipse.emf.parsley.dsl.util.EmfParsleyDslModelUtil.*
import static extension org.eclipse.xtext.scoping.Scopes.*
import org.eclipse.xtext.common.types.JvmOperation
import java.beans.Introspector

/**
 * For the moment Xbase uses two different scope providers, one for
 * the runtime part and one for the content assist, thus we must
 * factor out commong behavior.
 * 
 * see http://www.eclipse.org/forums/index.php/mv/msg/476486/1041675/#msg_1041675
 *
 */
class EmfParsleyDslScopeProviderHelper {

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
		if ((opName.startsWith("get"))
				&& opName.length() > 3
				&& Character::isUpperCase(opName.charAt(3)))
			return true;

		if (opName.startsWith("is") && opName.length() > 2
				&& Character::isUpperCase(opName.charAt(2)))
			return true;
		return false;
	}
	
	def getPropertyNameForGetterMethod(String opName) {
		if (opName.startsWith("get") && opName.length() > 3 && Character::isUpperCase(opName.charAt(3)))
			return Introspector::decapitalize(opName.substring(3));

		if (opName.startsWith("is") && opName.length() > 2 && Character::isUpperCase(opName.charAt(2)))
			return Introspector::decapitalize(opName.substring(2));
		return null;
	}
}