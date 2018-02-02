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

import org.eclipse.emf.ecore.EClass;

import com.google.inject.Inject;

/**
 * A wrapper class for parameters for an {@link EClass} that can be used in a
 * constructor annotated with {@link Inject}.
 * 
 * @author Lorenzo Bettini
 * @since 2.0
 *
 */
@FactoryParameter
public class EClassParameter extends GenericInjectableParameter<EClass> {

	public EClassParameter() {
		// required by Guice
	}

	public EClassParameter(EClass eClass) {
		super(eClass);
	}

	public final EClass getEClass() {
		return getWrapped();
	}
}