package org.eclipse.emf.parsley.tests.util

import com.google.inject.Inject
import java.util.List
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
import org.eclipse.emf.parsley.tests.models.testmodels.ClassWithName
import org.eclipse.emf.parsley.ui.provider.ViewerLabelProvider

class ViewerLabelProviderForList extends ViewerLabelProvider {

	@Inject
	new(AdapterFactoryLabelProvider delegate) {
		super(delegate)
	}

	def text(List<ClassWithName> l) {
		l.map[name].join(", ")
	}
}
