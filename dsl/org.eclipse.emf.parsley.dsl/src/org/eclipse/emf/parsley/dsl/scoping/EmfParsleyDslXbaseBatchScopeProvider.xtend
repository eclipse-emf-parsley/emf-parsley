package org.eclipse.emf.parsley.dsl.scoping

import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.xbase.scoping.batch.XbaseBatchScopeProvider

class EmfParsleyDslXbaseBatchScopeProvider extends XbaseBatchScopeProvider {
	
	@Inject extension EmfParsleyDslScopeProviderHelper
	
	override getScope(EObject context, EReference reference) {
		val scope = context.createCustomScope(reference)
		
		if (scope != null)
			return scope;
		super.getScope(context, reference);
	}
}