/**
 * 
 */
package org.eclipse.emf.parsley.dialogs;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.factories.DialogFactory;
import org.eclipse.emf.parsley.widgets.AbstractDetailComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.google.inject.Inject;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 */
public class DetailDialog extends AbstractDetailDialog {

	@Inject
	private DialogFactory dialogFactory;

	public DetailDialog(Shell parentShell, String title, EObject eObject) {
		super(parentShell, title, eObject);
	}

	public DetailDialog(Shell parentShell, String title, EObject original,
			EObject toBeEdited) {
		super(parentShell, title, original, toBeEdited);
	}

	protected AbstractDetailComposite createDetailComposite(Composite composite) {
		return dialogFactory.createDialogDetailComposite(composite, SWT.NONE);
	}

}
