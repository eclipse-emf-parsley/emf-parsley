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


import org.eclipse.emf.parsley.examples.mail.accountsview.views.AccountsView;
import org.eclipse.emf.parsley.examples.mail.unifiedfoldersview.UnifiedFoldersView;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	/**
	 * The ID of the perspective as specified in the extension.
	 */
	public static final String ID = "org.eclipse.emf.parsley.examples.mail.rcp.perspective";

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		
		//layout.addStandaloneView(NavigationView.ID,  false, IPageLayout.LEFT, 0.25f, editorArea);
		// don't show the view tabs
//		layout.addStandaloneView(AccountsView.ID, true, IPageLayout.LEFT, 0.25f, editorArea);
//		layout.addStandaloneView(MailsView.ID, true, IPageLayout.TOP, 0.25f, editorArea);
//		layout.addStandaloneView(MessageView.ID, true, IPageLayout.BOTTOM, 0.25f, editorArea);

		// if you want to show the view tabs
//		layout.addView(AccountsView.ID, IPageLayout.LEFT, 0.25f, editorArea);
//		layout.addView(UnifiedFoldersView.ID, IPageLayout.LEFT, 0.25f, editorArea);
		
		IFolderLayout folder = layout.createFolder("Accounts", IPageLayout.LEFT, 0.25f, editorArea);
		folder.addView(AccountsView.ID);
		folder.addView(UnifiedFoldersView.ID);
		
		//layout.getViewLayout(NavigationView.ID).setCloseable(false);
//		layout.getViewLayout(AccountsView.ID).setCloseable(false);
//		layout.getViewLayout(UnifiedFoldersView.ID).setCloseable(false);
	}
}
