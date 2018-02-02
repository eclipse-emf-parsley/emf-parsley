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

import org.eclipse.swt.widgets.Shell;

import com.google.inject.Inject;

/**
 * A wrapper class for parameters for a Dialog; the Dialog's constructor must be
 * annotated with {@link Inject}; the corresponding dialog must be created with
 * one of our factories.
 * 
 * @author Lorenzo Bettini
 * @since 2.0
 *
 */
@FactoryParameter
public class DialogParameters implements InjectableParameter {

	private Shell parentShell;
	private String title;

	public DialogParameters() {
		// required by Guice
	}

	public DialogParameters(Shell parentShell, String title) {
		this.parentShell = parentShell;
		this.title = title;
	}

	public Shell getParentShell() {
		return parentShell;
	}

	public String getTitle() {
		return title;
	}

}
