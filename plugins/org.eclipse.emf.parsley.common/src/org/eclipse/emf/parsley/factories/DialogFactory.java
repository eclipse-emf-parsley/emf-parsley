/**
 * 
 */
package org.eclipse.emf.parsley.factories;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.dialogs.DetailComponentDialog;
import org.eclipse.swt.widgets.Shell;

import com.google.inject.Inject;
import com.google.inject.MembersInjector;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 * 
 */
public class DialogFactory {

	@Inject
	protected MembersInjector<DetailComponentDialog> detailComponentDialogMembersInjection;

	@Inject
	public DialogFactory() {

	}

	public DetailComponentDialog createDetailComponentDialog(Shell parentShell, String title, EObject eObject) {
		DetailComponentDialog dialog = new DetailComponentDialog(parentShell, title, eObject);
		detailComponentDialogMembersInjection.injectMembers(dialog);
		return dialog;
	}

}
