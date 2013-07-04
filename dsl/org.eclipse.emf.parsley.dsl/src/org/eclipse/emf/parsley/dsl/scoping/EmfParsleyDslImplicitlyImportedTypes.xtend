package org.eclipse.emf.parsley.dsl.scoping

import org.eclipse.jface.databinding.swt.SWTObservables
import org.eclipse.swt.widgets.Control
import org.eclipse.xtext.xbase.scoping.batch.ImplicitlyImportedTypes

class EmfParsleyDslImplicitlyImportedTypes extends ImplicitlyImportedTypes {
	override protected getExtensionClasses() {
		(super.getExtensionClasses() + #[typeof(Control), typeof(SWTObservables)])
			.toList
	}
}