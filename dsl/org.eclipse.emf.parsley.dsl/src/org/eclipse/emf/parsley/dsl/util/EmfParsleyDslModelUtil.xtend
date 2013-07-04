package org.eclipse.emf.parsley.dsl.util

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.parsley.dsl.model.EmfFeatureAccess

import static extension org.eclipse.xtext.EcoreUtil2.*

class EmfParsleyDslModelUtil {
	
	def static containingEmfFeatureAccess(EObject o) {
		o.getContainerOfType(typeof(EmfFeatureAccess))
	}
}