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
import static org.junit.Assert.assertTrue;

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
		factory.createFormDetailComposite(getShell(), SWT.NONE, eClass);
	}

	@Test
	public void canCreateFormDetailCompositeWithEditingDomain() {
		factory.createFormDetailComposite(getShell(), SWT.NONE, eClass, getEditingDomain());
	}

	@Test
	public void canCreateFormDetailCompositeWithNullEditingDomain() {
		factory.createFormDetailComposite(getShell(), SWT.NONE, eClass, null);
	}

	@Test
	public void canCreateFormDetailReadOnlyComposite() {
		factory.createFormDetailReadOnlyComposite(getShell(), SWT.NONE, eClass);
	}

	@Test
	public void canCreateDialogDetailComposite() {
		factory.createDialogDetailComposite(getShell(), SWT.NONE, eClass, getEditingDomain());
	}

	@Test
	public void canCreateDialogDetailCompositeWithNullEditingDomain() {
		factory.createDialogDetailComposite(getShell(), SWT.NONE, eClass, null);
	}

	@Test
	public void canCreateTreeFormComposite() {
		factory.createTreeFormComposite(getShell(), SWT.NONE);
	}

	@Test
	public void canCreateTreeComposite() {
		factory.createTreeComposite(getShell(), SWT.NONE);
	}

	@Test
	public void canCreateTreeWithColumnsComposite() {
		factory.createTreeWithColumnsComposite(getShell(), SWT.NONE, eClass);
	}

	@Test
	public void testDefaultSashProperties() {
		syncExecVoid(() -> {
			var treeFormComposite = getOrCreateInjector().getInstance(CompositeFactory.class)
				.createTreeFormComposite(getShell(), 0);
			var sashForm = getSashForm(treeFormComposite);
			assertTrue((SWT.VERTICAL & sashForm.getStyle()) != 0);
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
			assertTrue((SWT.HORIZONTAL & sashForm.getStyle()) != 0);
			assertEquals("333, 666",
				Arrays.stream(sashForm.getWeights()).mapToObj(Integer::toString).collect(Collectors.joining(", ")));
		});
	}

	@Test
	public void canCreateTableFormComposite() {
		factory.createTableFormComposite(getShell(), SWT.NONE, eClass);
	}

	@Test
	public void canCreateTableComposite() {
		factory.createTableComposite(getShell(), SWT.NONE, eClass);
	}

	@Test
	public void canCreateTreeTableFormComposite() {
		factory.createTreeTableFormComposite(getShell(), SWT.NONE, eClass);
	}

	@Test
	public void canCreateDialogWidgetFactory() {
		factory.createDialogWidgetFactory(getShell());
	}

	@Test
	public void canCreateFormWidgetFactory() {
		factory.createFormWidgetFactory(getShell(), getFormToolkit());
	}

	@Test
	public void canCreateFeatureLabelCaptionProvider() {
		factory.createFeatureLabelCaptionProvider();
	}

	@Test
	public void canCreateFormFeatureCaptionProvider() {
		factory.createFormFeatureCaptionProvider(getFormToolkit());
	}

	@Test
	public void canCreateDialogControlFactory() {
		factory.createDialogControlFactory(getShell(), EcorePackage.eINSTANCE.getEObject(), getEditingDomain());
	}

	@Test
	public void canCreateFormControlFactory() {
		factory.createFormControlFactory(getShell(), EcorePackage.eINSTANCE.getEObject(), getEditingDomain(), getFormToolkit());
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
