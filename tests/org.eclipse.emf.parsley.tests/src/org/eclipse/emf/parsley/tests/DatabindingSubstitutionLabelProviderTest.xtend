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
package org.eclipse.emf.parsley.tests

import com.google.inject.Inject
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.parsley.internal.databinding.DatabindingSubstitutionLabelProvider
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyTest
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule
import org.eclipse.emf.parsley.ui.provider.FeatureLabelCaptionProvider
import org.eclipse.jface.viewers.ILabelProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*

class DatabindingSubstitutionLabelProviderTest extends AbstractEmfParsleyTest {

	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()

	@Inject
	var ILabelProvider labelProvider

	@Inject
	var FeatureLabelCaptionProvider featureLabelCaptionProvider;

	val objectForValidation = testFactory.createClassForDefaultValidation

	@Before
	def void setupUtil() {
		getOrCreateInjector.injectMembers(this)
	}

	@Test def void testObjectLabel() {
		val prov = new DatabindingSubstitutionLabelProvider(objectForValidation, labelProvider,
			featureLabelCaptionProvider)
		"Class For Default Validation".assertEquals(prov.getObjectLabel(objectForValidation))
	}

	@Test def void testFeatureLabel() {
		val prov = new DatabindingSubstitutionLabelProvider(objectForValidation, labelProvider,
			featureLabelCaptionProvider)
		"Not Empty".assertEquals(prov.getFeatureLabel(testPackage.classForDefaultValidation_NotEmpty))
	}

	@Test def void testValueLabel() {
		val prov = new DatabindingSubstitutionLabelProvider(objectForValidation, labelProvider,
			featureLabelCaptionProvider)
		"0".assertEquals(prov.getValueLabel(EcorePackage.eINSTANCE.EInt, "0"))
	}

}
