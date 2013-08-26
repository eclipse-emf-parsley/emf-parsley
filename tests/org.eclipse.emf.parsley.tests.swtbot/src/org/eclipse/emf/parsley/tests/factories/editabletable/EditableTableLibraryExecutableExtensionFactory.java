package org.eclipse.emf.parsley.tests.factories.editabletable;
import org.eclipse.emf.parsley.tests.EmfComponentsTestsActivator;
import org.eclipse.emf.parsley.tests.factories.EmfComponentsTestsExecutableExtensionFactory;

import com.google.inject.Injector;

/**
 * Uses an editable table
 * 
 * @author fguidieri
 * 
 */
public class EditableTableLibraryExecutableExtensionFactory extends
		EmfComponentsTestsExecutableExtensionFactory {

	@Override
	public Injector getInjector() {
		return EmfComponentsTestsActivator.getDefault().getInjector(
				this.getClass());
	}

}
