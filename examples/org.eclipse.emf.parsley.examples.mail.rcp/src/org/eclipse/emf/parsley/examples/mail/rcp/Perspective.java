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
package org.eclipse.emf.parsley.examples.mail.rcp;


import org.eclipse.emf.parsley.examples.mail.accountsview.unifiedfolders.UnifiedFoldersView;
import org.eclipse.emf.parsley.examples.mail.accountsview.views.AccountsView;
import org.eclipse.emf.parsley.examples.mail.mailsview.views.MailsView;
import org.eclipse.emf.parsley.examples.mail.messageview.views.MessageView;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	/**
	 * The ID of the perspective as specified in the extension.
	 */
	public static final String ID = "org.eclipse.emf.parsley.examples.mail.rcp.perspective";

	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		
		IFolderLayout folder = layout.createFolder("Accounts", IPageLayout.LEFT, 0.25f, editorArea);
		folder.addView(AccountsView.ID);
		folder.addView(UnifiedFoldersView.ID);
		layout.addStandaloneView(MailsView.ID, true, IPageLayout.TOP, 0.25f, editorArea);
		layout.addStandaloneView(MessageView.ID, true, IPageLayout.BOTTOM, 0.25f, editorArea);
	}
}
