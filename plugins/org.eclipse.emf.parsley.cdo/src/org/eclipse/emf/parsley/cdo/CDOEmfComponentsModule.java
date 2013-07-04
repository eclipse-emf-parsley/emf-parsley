/**
 * 
 */
package org.eclipse.emf.parsley.cdo;


import org.eclipse.emf.parsley.EmfComponentsGuiceModule;
import org.eclipse.emf.parsley.resource.ResourceLoader;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * @author bettini
 *
 */
public class CDOEmfComponentsModule extends EmfComponentsGuiceModule {

	public CDOEmfComponentsModule(AbstractUIPlugin plugin) {
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
