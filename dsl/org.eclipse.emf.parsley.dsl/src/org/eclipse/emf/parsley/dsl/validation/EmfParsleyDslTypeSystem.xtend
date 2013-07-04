package org.eclipse.emf.parsley.dsl.validation

import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.common.types.util.TypeReferences
import org.eclipse.xtext.xbase.typing.XbaseTypeConformanceComputer
import org.eclipse.ui.IViewPart
import org.eclipse.emf.parsley.EmfComponentsGuiceModule

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

	def isEmfComponentsGuiceModule(JvmTypeReference type, EObject context) {
		isConformant(typeReferences.getTypeForName(
				typeof(EmfComponentsGuiceModule), context), type);
	}
}