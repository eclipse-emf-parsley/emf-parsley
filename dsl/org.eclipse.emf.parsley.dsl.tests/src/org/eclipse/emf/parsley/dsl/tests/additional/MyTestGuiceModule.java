/**
 * 
 */
package org.eclipse.emf.parsley.dsl.tests.additional;

import org.eclipse.emf.parsley.EmfComponentsGuiceModule;
import org.eclipse.ui.plugin.AbstractUIPlugin;


/**
 * @author Lorenzo Bettini
 *
 */
public class MyTestGuiceModule extends EmfComponentsGuiceModule {

	public MyTestGuiceModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

}
