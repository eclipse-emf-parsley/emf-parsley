package org.eclipse.emf.parsley.examples.mail.accountsview.views;

import java.io.IOException;


import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.parsley.views.AbstractSaveableTreeView;


/**
 * @author Lorenzo Bettini
 */
public class AccountsView extends AbstractSaveableTreeView {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.eclipse.emf.parsley.examples.mail.accountsview.views.AccountsView";
	
	static URI mailModelURI = URI.createFileURI(System.getProperty("user.home")
			+ "/examples/mail/My.mail");

	@Override
	protected URI createResourceURI() {
		return mailModelURI;
	}

	@Override
	protected void mostRecentCommandAffectsResource(Command mostRecentCommand) {
		try {
			saveResourceAndUpdateDirtyState();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}