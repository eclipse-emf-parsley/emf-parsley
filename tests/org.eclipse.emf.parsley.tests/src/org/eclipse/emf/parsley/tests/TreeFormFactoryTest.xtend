package org.eclipse.emf.parsley.tests

import com.google.inject.Guice
import org.eclipse.emf.parsley.factories.TreeFormFactory
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.swt.SWT
import org.junit.Test

import static extension org.junit.Assert.*

class TreeFormFactoryTest extends AbstractEmfParsleyShellBasedTest {
	
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