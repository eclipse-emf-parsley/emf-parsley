/*******************************************************************************
 * Copyright (c) 2018 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.composite.CompositeFactory;
import org.eclipse.emf.parsley.composite.TreeFormComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.junit.Before;
import org.junit.Test;

/**
 * Make sure we can instantiate all our composites via dependency injection.
 */
public class CompositeFactoryTest extends AbstractControlFactoryTest {

	private CompositeFactory factory;

	private EClass eClass;

	@Before
	public void setupFactory() {
		factory = getOrCreateInjector().getInstance(CompositeFactory.class);
		eClass = EcorePackage.eINSTANCE.getEObject();
	}

	@Test
	public void canCreateFormDetailComposite() {
		assertNotNull(factory.createFormDetailComposite(getShell(), SWT.NONE, eClass));
	}

	@Test
	public void canCreateFormDetailCompositeWithEditingDomain() {
		assertNotNull(factory.createFormDetailComposite(getShell(), SWT.NONE, eClass, getEditingDomain()));
	}

	@Test
	public void canCreateFormDetailCompositeWithNullEditingDomain() {
		assertNotNull(factory.createFormDetailComposite(getShell(), SWT.NONE, eClass, null));
	}

	@Test
	public void canCreateFormDetailReadOnlyComposite() {
		assertNotNull(factory.createFormDetailReadOnlyComposite(getShell(), SWT.NONE, eClass));
	}

	@Test
	public void canCreateDialogDetailComposite() {
		assertNotNull(factory.createDialogDetailComposite(getShell(), SWT.NONE, eClass, getEditingDomain()));
	}

	@Test
	public void canCreateDialogDetailCompositeWithNullEditingDomain() {
		assertNotNull(factory.createDialogDetailComposite(getShell(), SWT.NONE, eClass, null));
	}

	@Test
	public void canCreateTreeFormComposite() {
		assertNotNull(factory.createTreeFormComposite(getShell(), SWT.NONE));
	}

	@Test
	public void canCreateTreeComposite() {
		assertNotNull(factory.createTreeComposite(getShell(), SWT.NONE));
	}

	@Test
	public void canCreateTreeWithColumnsComposite() {
		assertNotNull(factory.createTreeWithColumnsComposite(getShell(), SWT.NONE, eClass));
	}

	@Test
	public void testDefaultSashProperties() {
		syncExecVoid(() -> {
			var treeFormComposite = getOrCreateInjector().getInstance(CompositeFactory.class)
				.createTreeFormComposite(getShell(), 0);
			var sashForm = getSashForm(treeFormComposite);
			assertNotEquals(0, sashForm.getStyle() & SWT.VERTICAL);
			assertEquals("200, 200",
				Arrays.stream(sashForm.getWeights()).mapToObj(Integer::toString).collect(Collectors.joining(", ")));
		});
	}

	@Test
	public void testCustomSashProperties() {
		syncExecVoid(() -> {
			var treeFormComposite = createInjector(
				new EmfParsleyGuiceModuleForTesting() {
					@Override
					public int valueTreeFormSashStyle() {
						return SWT.HORIZONTAL;
					}

					@Override
					public int[] valueTreeFormSashWeights() {
						return new int[] { 1, 2 };
					}
				}
			).getInstance(CompositeFactory.class)
			.createTreeFormComposite(getShell(), 0);
			var sashForm = getSashForm(treeFormComposite);
			assertNotEquals(0, sashForm.getStyle() & SWT.HORIZONTAL);
			assertEquals("333, 666",
				Arrays.stream(sashForm.getWeights()).mapToObj(Integer::toString).collect(Collectors.joining(", ")));
		});
	}

	@Test
	public void canCreateTableFormComposite() {
		assertNotNull(factory.createTableFormComposite(getShell(), SWT.NONE, eClass));
	}

	@Test
	public void canCreateTableComposite() {
		assertNotNull(factory.createTableComposite(getShell(), SWT.NONE, eClass));
	}

	@Test
	public void canCreateTreeTableFormComposite() {
		assertNotNull(factory.createTreeTableFormComposite(getShell(), SWT.NONE, eClass));
	}

	@Test
	public void canCreateDialogWidgetFactory() {
		assertNotNull(factory.createDialogWidgetFactory(getShell()));
	}

	@Test
	public void canCreateFormWidgetFactory() {
		assertNotNull(factory.createFormWidgetFactory(getShell(), getFormToolkit()));
	}

	@Test
	public void canCreateFeatureLabelCaptionProvider() {
		assertNotNull(factory.createFeatureLabelCaptionProvider());
	}

	@Test
	public void canCreateFormFeatureCaptionProvider() {
		assertNotNull(factory.createFormFeatureCaptionProvider(getFormToolkit()));
	}

	@Test
	public void canCreateDialogControlFactory() {
		assertNotNull(factory.createDialogControlFactory(getShell(), EcorePackage.eINSTANCE.getEObject(), getEditingDomain()));
	}

	@Test
	public void canCreateFormControlFactory() {
		assertNotNull(factory.createFormControlFactory(getShell(), EcorePackage.eINSTANCE.getEObject(), getEditingDomain(), getFormToolkit()));
	}

	private SashForm getSashForm(TreeFormComposite treeFormComposite) {
		// access the protected method for testing purposes
		try {
			Method sashForm = treeFormComposite.getClass().getSuperclass().getDeclaredMethod("getSashForm");
			sashForm.setAccessible(true);
			return (SashForm) sashForm.invoke(treeFormComposite);
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected EditingDomain getEditingDomain() {
		return getOrCreateInjector().getInstance(EditingDomain.class);
	}
}
