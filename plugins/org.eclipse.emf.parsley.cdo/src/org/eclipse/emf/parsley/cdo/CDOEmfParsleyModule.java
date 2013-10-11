/**
 * 
 */
package org.eclipse.emf.parsley.cdo;


import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * @author bettini
 *
 */
public class CDOEmfParsleyModule extends EmfParsleyGuiceModule {

	public CDOEmfParsleyModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@Override
	public Class<? extends ResourceLoader> bindResourceLoader() {
		return CDOResourceLoader.class;
	}
	
	public Class<? extends CDOSessionManager> bindCDOSessionManager(){
		return CDOSessionManager.class;
	}
	
}
