/**
 * 
 */
package org.eclipse.emf.parsley.dialogs;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.factories.FormFactory;
import org.eclipse.emf.parsley.widgets.AbstractDetailComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.google.inject.Inject;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 */
public class DetailFormBasedDialog extends AbstractDetailDialog {

	@Inject
	private FormFactory formFactory;

	public DetailFormBasedDialog(Shell parentShell, String title, EObject eObject) {
		super(parentShell, title, eObject);
	}

	protected AbstractDetailComposite createDetailComposite(Composite composite) {
		return formFactory.createFormDetailComposite(composite, SWT.NONE);
	}

}
