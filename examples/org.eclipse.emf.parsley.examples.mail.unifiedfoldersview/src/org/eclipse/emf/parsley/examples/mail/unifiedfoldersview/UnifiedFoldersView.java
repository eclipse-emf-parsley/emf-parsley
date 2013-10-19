package org.eclipse.emf.parsley.examples.mail.unifiedfoldersview;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.parsley.examples.mail.accountsview.views.AccountsView;

public class UnifiedFoldersView extends AccountsView {

	public static final String ID = "org.eclipse.emf.parsley.examples.mail.unifiedfoldersview";
	
	@Override
	protected void mostRecentCommandAffectsResource(Command mostRecentCommand) {
		super.mostRecentCommandAffectsResource(mostRecentCommand);
		// if a child changes the parent is not refreshed by default
		getTreeViewer().refresh();
		getTreeViewer().expandAll();
	}
}
