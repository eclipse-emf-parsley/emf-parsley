/**
 * 
 */
package org.eclipse.emf.parsley.dsl.tests.additional;

import org.eclipse.emf.parsley.EmfParsleyGuiceModule;
import org.eclipse.ui.plugin.AbstractUIPlugin;


/**
 * @author Lorenzo Bettini
 *
 */
public class MyTestGuiceModule extends EmfParsleyGuiceModule {

	public MyTestGuiceModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

}
