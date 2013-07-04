package org.eclipse.emf.parsley.examples.mail.rcp;


import org.eclipse.emf.parsley.examples.mail.accountsview.views.AccountsView;
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
		layout.addView(AccountsView.ID, IPageLayout.LEFT, 0.25f, editorArea);
		
//		IFolderLayout folder = layout.createFolder("messages", IPageLayout.TOP, 0.5f, editorArea);
//		folder.addPlaceholder(View.ID + ":*");
//		folder.addView(View.ID);
		
		//layout.getViewLayout(NavigationView.ID).setCloseable(false);
		layout.getViewLayout(AccountsView.ID).setCloseable(false);
	}
}
