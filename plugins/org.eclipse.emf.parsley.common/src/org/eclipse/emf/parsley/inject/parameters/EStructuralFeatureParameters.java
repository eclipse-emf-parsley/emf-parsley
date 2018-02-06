/*******************************************************************************
 * Copyright (c) 2018 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.inject.parameters;

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.inject.Inject;

/**
 * A wrapper class for parameters for a {@link List} of
 * {@link EStructuralFeature} that can be used in a constructor annotated with
 * {@link Inject}.
 * 
 * @author Lorenzo Bettini
 * @since 2.0
 *
 */
@FactoryParameter
public class EStructuralFeatureParameters extends GenericInjectableParameter<List<EStructuralFeature>> {

	public EStructuralFeatureParameters() {
		// required by Guice
	}

	public EStructuralFeatureParameters(List<EStructuralFeature> eStructuralFeatures) {
		super(eStructuralFeatures);
	}

	public final List<EStructuralFeature> getEStructuralFeatures() {
		return getWrapped();
	}
}
