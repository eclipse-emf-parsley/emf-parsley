/**
 * 
 */
package org.eclipse.emf.parsley.examples.mail.mailsview.views;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.examples.mail.MailPackage;
import org.eclipse.emf.parsley.views.AbstractOnSelectionTableView;


/**
 * @author Lorenzo Bettini
 *
 */
public class MailsView extends AbstractOnSelectionTableView {

	public static final String ID = "org.eclipse.emf.parsley.examples.mail.mailsview.views.MailsView";

	@Override
	protected EStructuralFeature getEStructuralFeature() {
		return MailPackage.Literals.FOLDER__MAILS;
	}

}
