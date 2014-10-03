/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.providers;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.emf.parsley.util.EStructuralFeatureNameComparator;

import com.google.inject.Inject;


/**
 * @author Lorenzo Bettini
 *
 */
public class OrderedEStructuralFeaturesProvider extends FeaturesProvider {

	@Inject
	EStructuralFeatureNameComparator comparator;
	
	@Override
	public List<EStructuralFeature> getFeatures(EClass eClass) {
		List<EStructuralFeature> features = super.getFeatures(eClass);
		Collections.sort(features, new EStructuralFeatureNameComparator());
		return features;
	}

}
