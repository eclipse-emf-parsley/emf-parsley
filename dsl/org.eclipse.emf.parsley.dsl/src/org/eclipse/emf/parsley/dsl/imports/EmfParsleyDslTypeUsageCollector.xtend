package org.eclipse.emf.parsley.dsl.imports

import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.parsley.dsl.model.ModelPackage
import org.eclipse.emf.parsley.dsl.model.ViewSpecification
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.resource.DefaultLocationInFileProvider
import org.eclipse.xtext.xbase.imports.TypeUsageCollector

/**
 * We need to collect types used in ViewSpecification
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 */
class EmfParsleyDslTypeUsageCollector extends TypeUsageCollector {
	
	@Inject
	private DefaultLocationInFileProvider locationInFileProvider
	
	protected override void collectAllReferences(EObject rootElement) {
		super.collectAllReferences(rootElement)
		val contents = EcoreUtil2.getAllContentsOfType(rootElement, ViewSpecification)
		for (v : contents) {
			if (v.type != null) {
				val refRegion = locationInFileProvider.getFullTextRegion
					(v, ModelPackage.eINSTANCE.viewSpecification_Type, 0)
					
				if (v.type.eIsProxy) {
					acceptUnresolvedType(v.type.simpleName, "", refRegion)
				} else {
					acceptType(v.type, refRegion)
				}
			}
		}
	}
}