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
import org.eclipse.emf.parsley.runtime.util.IAcceptor;
import org.eclipse.jface.action.Action;

/**
 * Wraps an EMF Command
 * 
 * @author Lorenzo Bettini - initial API and implementation
 *
 */
public class EmfCommandAction<T> extends Action {

	/**
	 * This records the editing domain and it has to be passed during the
	 * constructor.
	 */
	private EditingDomain editingDomain;

	/**
	 * This records the command.
	 */
	private Command command;

	/**
	 * This will be used to initialize the object that has been added by this
	 * action.
	 */
	private IAcceptor<T> addedObjectInitializer;

	public EmfCommandAction(String text, EditingDomain editingDomain, Command command) {
		this(text, editingDomain, command, null);
	}

	public EmfCommandAction(String text, EditingDomain editingDomain, Command command,
			IAcceptor<T> addedObjectInitializer) {
		super(text);
		this.editingDomain = editingDomain;
		this.command = command;
		this.addedObjectInitializer = addedObjectInitializer;
	}

	/**
	 * Executes the wrapped command in the stack of the editing domain.
	 */
	@Override
	public void run() {
		editingDomain.getCommandStack().execute(command);

		if (addedObjectInitializer != null) {
			@SuppressWarnings("unchecked")
			T addedObject = (T) command.getAffectedObjects().iterator().next();
			addedObjectInitializer.accept(addedObject);
		}

	}
}
