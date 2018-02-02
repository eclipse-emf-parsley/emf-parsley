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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * A wrapper class for parameters for editing an {@link EObject} with an
 * {@link EditingDomain}.
 * 
 * @author Lorenzo Bettini
 * @since 2.0
 *
 */
@FactoryParameter
public class EObjectParameter implements InjectableParameter {

	private EObject object;
	private EditingDomain editingDomain;

	public EObjectParameter() {
		// required by Guice
	}

	public EObjectParameter(EObject object, EditingDomain editingDomain) {
		this.object = object;
		this.editingDomain = editingDomain;
	}

	public final EObject getObject() {
		return object;
	}

	public final EditingDomain getEditingDomain() {
		return editingDomain;
	}

}
