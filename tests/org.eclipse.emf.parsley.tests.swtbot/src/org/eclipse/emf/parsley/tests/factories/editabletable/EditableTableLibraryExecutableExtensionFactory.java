package org.eclipse.emf.parsley.tests.factories.editabletable;
import org.eclipse.emf.parsley.tests.EmfParsleyTestsActivator;
import org.eclipse.emf.parsley.tests.factories.EmfParsleyTestsExecutableExtensionFactory;

import com.google.inject.Injector;

/**
 * Uses an editable table
 * 
 * @author fguidieri
 * 
 */
public class EditableTableLibraryExecutableExtensionFactory extends
		EmfParsleyTestsExecutableExtensionFactory {

	@Override
	public Injector getInjector() {
		return EmfParsleyTestsActivator.getDefault().getInjector(
				this.getClass());
	}

}
