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
package org.eclipse.emf.parsley.inject;

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
public class EClassParameter implements InjectableParameter {

	private EClass eClass;

	public EClassParameter(EClass eClass) {
		this.eClass = eClass;
	}

	public EClass getEClass() {
		return eClass;
	}
}
