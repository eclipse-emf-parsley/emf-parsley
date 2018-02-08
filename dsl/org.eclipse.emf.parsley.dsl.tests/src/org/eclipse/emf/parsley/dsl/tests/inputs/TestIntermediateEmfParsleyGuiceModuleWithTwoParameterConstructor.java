package org.eclipse.emf.parsley.dsl.tests.inputs;

import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * To addess https://bugs.eclipse.org/bugs/show_bug.cgi?id=519645
 * 
 * @author Lorenzo Bettini
 *
 */
public class TestIntermediateEmfParsleyGuiceModuleWithTwoParameterConstructor extends EmfParsleyJavaGuiceModule {

	public TestIntermediateEmfParsleyGuiceModuleWithTwoParameterConstructor(final AbstractUIPlugin plugin, String s) {
		// not used
	}
}
