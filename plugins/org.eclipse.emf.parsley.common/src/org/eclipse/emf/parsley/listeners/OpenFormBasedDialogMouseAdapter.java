/**
 * 
 */
package org.eclipse.emf.parsley.listeners;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.dialogs.DetailFormBasedDialog;
import org.eclipse.emf.parsley.factories.DialogFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.widgets.Shell;

import com.google.inject.Inject;

/**
 * A {@link MouseAdapter} that opens a {@link DetailFormBasedDialog} on double
 * click.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 * 
 */
public class OpenFormBasedDialogMouseAdapter extends OpenDialogMouseAdapter
		implements IEditorMouseListener {

	@Inject
	private DialogFactory dialogFactory;

	@Override
	protected Dialog createDialog(EObject o, EditingDomain editingDomain,
			Shell activeShell, String title) {
		return dialogFactory.createDetailFormBasedDialog(activeShell, title, o,
				editingDomain);
	}
}
