/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.edit.action;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.Action;

/**
 * Wraps an EMF Command
 * 
 * @author Lorenzo Bettini - initial API and implementation
 *
 */
public class EmfCommandAction extends Action {

	/**
	 * This records the editing domain and it has to be passed during the
	 * constructor.
	 */
	private EditingDomain editingDomain;

	/**
	 * This records the command.
	 */
	private Command command;

	public EmfCommandAction(String text, EditingDomain editingDomain, Command command) {
		super(text);
		this.editingDomain = editingDomain;
		this.command = command;
	}

	/**
	 * Executes the wrapped command in the stack of the editing domain.
	 */
	@Override
	public void run() {
		editingDomain.getCommandStack().execute(command);
	}
}
