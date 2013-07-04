/**
 * 
 */
package org.eclipse.emf.parsley.examples.mail.messageview.views;


import org.eclipse.emf.parsley.examples.mail.MailPackage;
import org.eclipse.emf.parsley.views.OnSelectionFormView;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Lorenzo Bettini
 * 
 */
public class MessageView extends OnSelectionFormView {
	
	public static final String ID = "org.eclipse.emf.parsley.examples.mail.messageview.views.MessageView";

	@Override
	protected void updateOnSelection(IWorkbenchPart sourcepart,
			ISelection selection) {
		// we want to display only Mail elements
		if (!MailPackage.Literals.MAIL
				.isInstance(getFirstSelectedEObject(selection))) {
			resetFormComposite();
			return;
		}

		super.updateOnSelection(sourcepart, selection);
	}
}
