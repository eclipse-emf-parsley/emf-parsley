/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
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

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.parsley.internal.databinding.DatabindingSubstitutionLabelProvider;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest;
import org.eclipse.emf.parsley.tests.models.testmodels.ClassForDefaultValidation;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.ui.provider.FeatureLabelCaptionProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.inject.Inject;

public class DatabindingSubstitutionLabelProviderTest extends AbstractEmfParsleyTest {

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	@Inject
	private ILabelProvider labelProvider;

	@Inject
	private FeatureLabelCaptionProvider featureLabelCaptionProvider;

	private final ClassForDefaultValidation objectForValidation = fixtures.getTestFactory()
			.createClassForDefaultValidation();

	@Before
	public void setupUtil() {
		getOrCreateInjector().injectMembers(this);
	}

	@Test
	public void testObjectLabel() {
		DatabindingSubstitutionLabelProvider prov = new DatabindingSubstitutionLabelProvider(objectForValidation,
				labelProvider, featureLabelCaptionProvider);
		assertEquals("Class For Default Validation", prov.getObjectLabel(objectForValidation));
	}

	@Test
	public void testFeatureLabel() {
		DatabindingSubstitutionLabelProvider prov = new DatabindingSubstitutionLabelProvider(objectForValidation,
				labelProvider, featureLabelCaptionProvider);
		assertEquals("Not Empty",
				prov.getFeatureLabel(fixtures.getTestPackage().getClassForDefaultValidation_NotEmpty()));
	}

	@Test
	public void testValueLabel() {
		DatabindingSubstitutionLabelProvider prov = new DatabindingSubstitutionLabelProvider(objectForValidation,
				labelProvider, featureLabelCaptionProvider);
		assertEquals("0", prov.getValueLabel(EcorePackage.eINSTANCE.getEInt(), "0"));
	}

}
