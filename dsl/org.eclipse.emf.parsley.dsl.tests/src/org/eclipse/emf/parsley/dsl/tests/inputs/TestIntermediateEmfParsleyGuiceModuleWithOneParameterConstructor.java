package org.eclipse.emf.parsley.dsl.tests.inputs;

import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule;

/**
 * To addess https://bugs.eclipse.org/bugs/show_bug.cgi?id=519645
 * 
 * @author Lorenzo Bettini
 *
 */
public class TestIntermediateEmfParsleyGuiceModuleWithOneParameterConstructor extends EmfParsleyJavaGuiceModule {

	public TestIntermediateEmfParsleyGuiceModuleWithOneParameterConstructor(String s) {
		// not used
	}
}
