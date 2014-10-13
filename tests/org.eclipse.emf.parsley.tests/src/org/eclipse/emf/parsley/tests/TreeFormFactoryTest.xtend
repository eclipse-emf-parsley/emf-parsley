package org.eclipse.emf.parsley.tests

import org.junit.Test
import org.eclipse.emf.parsley.factories.TreeFormFactory
import static extension org.junit.Assert.*
import org.eclipse.swt.SWT
import com.google.inject.Guice

class TreeFormFactoryTest extends AbstractShellBasedTest {
	
	@Test
	def void testDefaultSashProperties() {
		syncExecVoid[
			val sashForm = getOrCreateInjector.
				getInstance(TreeFormFactory).createTreeFormComposite(shell, 0).
					sashForm
			assertTrue(SWT.VERTICAL.bitwiseAnd(sashForm.style) != 0)
			"200, 200".assertEquals(sashForm.weights.map[toString].join(", "))
		]
	}

	@Test
	def void testCustomSashProperties() {
		syncExecVoid[
			val sashForm = Guice.createInjector(
					new EmfParsleyGuiceModuleForTesting {
						override valueTreeFormSashStyle() {
							return SWT.HORIZONTAL;
						}
					
						override valueTreeFormSashWeights() {
							#[1, 2]
						}
					}
				).
				getInstance(TreeFormFactory).createTreeFormComposite(shell, 0).
					sashForm
			assertTrue(SWT.HORIZONTAL.bitwiseAnd(sashForm.style) != 0)
			"333, 666".assertEquals(sashForm.weights.map[toString].join(", "))
		]
	}
}