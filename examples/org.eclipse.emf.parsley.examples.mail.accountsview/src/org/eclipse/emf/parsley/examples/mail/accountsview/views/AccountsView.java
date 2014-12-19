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
package org.eclipse.emf.parsley.examples.mail.accountsview.views;

import java.io.IOException;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.parsley.views.SaveableTreeView;


/**
 * @author Lorenzo Bettini
 */
public class AccountsView extends SaveableTreeView {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.eclipse.emf.parsley.examples.mail.accountsview.views.AccountsView";
	
	@Override
	protected void mostRecentCommandAffectsResource(Command mostRecentCommand) {
		try {
			saveResourceAndUpdateDirtyState();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}