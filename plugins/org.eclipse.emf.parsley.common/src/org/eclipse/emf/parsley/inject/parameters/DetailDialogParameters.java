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
import org.eclipse.swt.widgets.Shell;

import com.google.inject.Inject;

/**
 * A wrapper class for parameters for a Dialog that is meant to edit an
 * {@link EObject}; the Dialog's constructor must be annotated with
 * {@link Inject}; the corresponding dialog must be created with one of our
 * factories.
 * 
 * @author Lorenzo Bettini
 * @since 2.0
 *
 */
@FactoryParameter
public class DetailDialogParameters implements InjectableParameter {

	private Shell parentShell;
	private String title;
	private EObject object;
	private EditingDomain editingDomain;

	public DetailDialogParameters() {
		// required by Guice
	}

	public DetailDialogParameters(Shell parentShell, String title, EObject object, EditingDomain editingDomain) {
		this.parentShell = parentShell;
		this.title = title;
		this.object = object;
		this.editingDomain = editingDomain;
	}

	public Shell getParentShell() {
		return parentShell;
	}

	public String getTitle() {
		return title;
	}

	public EObject getObject() {
		return object;
	}

	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

}
