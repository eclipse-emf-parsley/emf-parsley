package org.eclipse.emf.parsley.dsl.tests.inputs;

import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * To addess https://bugs.eclipse.org/bugs/show_bug.cgi?id=519645
 * 
 * @author Lorenzo Bettini
 *
 */
public class TestIntermediateEmfParsleyJavaGuiceModule extends EmfParsleyJavaGuiceModule {

	public TestIntermediateEmfParsleyJavaGuiceModule(final AbstractUIPlugin plugin) {
		// not used
	}
}
