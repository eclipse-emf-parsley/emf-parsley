/**
 * 
 */
package org.eclipse.emf.parsley.cdo;

import org.eclipse.emf.parsley.EmfComponentsExtensionFactory;
import org.eclipse.emf.parsley.EmfComponentsGuiceModule;

/**
 * @author bettini
 *
 */
public class CDOEmfComponentsExtensionFactory extends
		EmfComponentsExtensionFactory {

	@Override
	protected EmfComponentsGuiceModule getModule() {
		return new CDOEmfComponentsModule(Activator.getDefault());
	}
	
	
	

}
