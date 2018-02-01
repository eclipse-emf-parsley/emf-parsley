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

import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.inject.Inject;

/**
 * A wrapper class for parameters for an {@link EStructuralFeature} that can be
 * used in a constructor annotated with {@link Inject}.
 * 
 * @author Lorenzo Bettini
 * @since 2.0
 *
 */
@FactoryParameter
public class EStructuralFeatureParameter implements InjectableParameter {

	private EStructuralFeature eStructuralFeature;

	public EStructuralFeatureParameter() {
		// required by Guice
	}

	public EStructuralFeatureParameter(EStructuralFeature eStructuralFeature) {
		this.eStructuralFeature = eStructuralFeature;
	}

	public EStructuralFeature getEStructuralFeature() {
		return eStructuralFeature;
	}
}
