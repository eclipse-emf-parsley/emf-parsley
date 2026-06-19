package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.parsley.composite.TreeFormFactory;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.junit.Test;

public class TreeFormFactoryTest extends AbstractEmfParsleyShellBasedTest {
	
	@Test
	public void testDefaultSashProperties() {
		syncExecVoid(() -> {
			SashForm sashForm = getOrCreateInjector()
				.getInstance(TreeFormFactory.class).createTreeFormComposite(getShell(), 0)
					.getSashForm();
			assertNotEquals(0, SWT.VERTICAL & sashForm.getStyle());
			assertEquals("200, 200", 
				Arrays.stream(sashForm.getWeights())
					.mapToObj(Integer::toString)
					.reduce((a, b) -> a + ", " + b)
					.orElse(""));
		});
	}

	@Test
	public void testCustomSashProperties() {
		syncExecVoid(() -> {
			SashForm sashForm = createInjector(
					new EmfParsleyGuiceModuleForTesting() {
						@Override
						public int valueTreeFormSashStyle() {
							return SWT.HORIZONTAL;
						}
					
						@Override
						public List<Integer> valueTreeFormSashWeights() {
							return Arrays.asList(1, 2);
						}
					}
				)
				.getInstance(TreeFormFactory.class).createTreeFormComposite(getShell(), 0)
					.getSashForm();
			assertNotEquals(0, SWT.HORIZONTAL & sashForm.getStyle());
			assertEquals("333, 666",
				Arrays.stream(sashForm.getWeights())
					.mapToObj(Integer::toString)
					.reduce((a, b) -> a + ", " + b)
					.orElse(""));
		});
	}
}
