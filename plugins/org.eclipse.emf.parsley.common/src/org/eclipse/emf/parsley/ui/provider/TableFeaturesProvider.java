/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.ui.provider;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.inject.Inject;

/**
 * Provides the list of EStructuralFeature of an EClass only for tables.
 *
 * If there is no customization for an EClass, it defaults to the injected
 * {@link FeaturesProvider}.
 *
 * @author Lorenzo Bettini, Francesco Guidieri
 * @see FeaturesProvider
 */
public class TableFeaturesProvider extends FeaturesProvider {

	@Inject
	protected FeaturesProvider featuresProvider;

	@Override
	protected List<EStructuralFeature> getFromMap(EClass eClass) {
		List<EStructuralFeature> columnDefinition = super.getFromMap(eClass);
		if (columnDefinition != null) {
			return columnDefinition;
		}
		return featuresProvider.getFromMap(eClass);
	}

	@Override
	protected List<EStructuralFeature> getFromStringMap(EClass eClass) {
		List<EStructuralFeature> columnDefinition = super.getFromStringMap(eClass);
		if (columnDefinition != null) {
			return columnDefinition;
		}
		return featuresProvider.getFromStringMap(eClass);
	}
}
