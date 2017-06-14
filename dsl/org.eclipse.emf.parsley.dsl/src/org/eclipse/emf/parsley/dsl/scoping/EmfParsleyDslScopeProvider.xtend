/*
 * generated by Xtext 2.10.0-SNAPSHOT
 */
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

import static extension org.eclipse.emf.parsley.dsl.util.EmfParsleyDslModelUtil.*
import static extension org.eclipse.xtext.scoping.Scopes.*

/**
 * This class contains custom scoping description.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#scoping
 * on how and when to use it.
 */
class EmfParsleyDslScopeProvider extends AbstractEmfParsleyDslScopeProvider {

	override getScope(EObject context, EReference reference) {
		val scope = context.createCustomScope(reference)

		if (scope !== null)
			return scope;
		super.getScope(context, reference);
	}

	def IScope createCustomScope(EObject context, EReference reference) {
		if (reference.EType == TypesPackage::eINSTANCE.jvmMember) {
			return context.containingEmfFeatureAccess?.parameterType?.type?.customScope
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
			t.allFeatures.filter(typeof(JvmOperation)).filter[!static].scopedElementsFor [
				if (simpleName.getterMethod)
					QualifiedName::create(simpleName.propertyNameForGetterMethod)
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
		opName.startsWith(prefix) && opName.length() > prefix.length &&
			Character::isUpperCase(opName.charAt(prefix.length))
	}
}
