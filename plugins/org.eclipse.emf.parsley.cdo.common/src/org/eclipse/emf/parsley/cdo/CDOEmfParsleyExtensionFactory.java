/**
 * 
 */
package org.eclipse.emf.parsley.cdo;

import org.eclipse.emf.parsley.EmfParsleyExtensionFactory;
import org.eclipse.emf.parsley.EmfParsleyGuiceModule;

/**
 * @author bettini
 *
 */
public class CDOEmfParsleyExtensionFactory extends
		EmfParsleyExtensionFactory {

	@Override
	protected EmfParsleyGuiceModule getModule() {
		return new CDOEmfParsleyModule(EmfParsleyCDOActivator.getDefault());
	}
	
	
	

}
