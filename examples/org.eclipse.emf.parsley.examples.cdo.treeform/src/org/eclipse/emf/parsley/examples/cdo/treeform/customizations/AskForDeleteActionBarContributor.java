/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.examples.cdo.treeform.customizations;


import org.eclipse.emf.edit.ui.action.DeleteAction;
import org.eclipse.emf.parsley.edit.action.EditingActionManager;
import org.eclipse.jface.dialogs.MessageDialog;

public class AskForDeleteActionBarContributor extends EditingActionManager {

	@Override
	protected DeleteAction createDeleteAction() {
		return new ConfirmDeleteAction();
	}
	
	class ConfirmDeleteAction extends DeleteAction{

		public ConfirmDeleteAction() {
			super(true);
		}
		
		@Override
		public void run() {
			if(MessageDialog.openConfirm(null, "Delete", "Do you want to delete this?")){
				super.run();
			}
		}
		
	}

}
