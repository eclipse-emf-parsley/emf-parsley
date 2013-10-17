/**
 * 
 */
package org.eclipse.emf.parsley.factories;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.dialogs.DetailDialog;
import org.eclipse.emf.parsley.dialogs.DetailFormBasedDialog;
import org.eclipse.emf.parsley.widgets.AbstractDetailComposite;
import org.eclipse.emf.parsley.widgets.DialogDetailComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.google.inject.Inject;
import com.google.inject.MembersInjector;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 * 
 */
public class DialogFactory {

	@Inject
	protected MembersInjector<DetailFormBasedDialog> detailFormBasedDialogMembersInjection;

	@Inject
	protected MembersInjector<DetailDialog> detailDialogMembersInjection;

	@Inject
	protected MembersInjector<DialogDetailComposite> detailComponentDialogMembersInjection;

	@Inject
	public DialogFactory() {

	}

	public DetailFormBasedDialog createDetailFormBasedDialog(Shell parentShell,
			String title, EObject eObject) {
		DetailFormBasedDialog dialog = new DetailFormBasedDialog(parentShell,
				title, eObject);
		detailFormBasedDialogMembersInjection.injectMembers(dialog);
		return dialog;
	}

	public DetailFormBasedDialog createDetailFormBasedDialog(Shell parentShell,
			String title, EObject original, EObject toBeEdited) {
		DetailFormBasedDialog dialog = new DetailFormBasedDialog(parentShell,
				title, original, toBeEdited);
		detailFormBasedDialogMembersInjection.injectMembers(dialog);
		return dialog;
	}

	public DetailDialog createDetailDialog(Shell parentShell, String title,
			EObject eObject) {
		DetailDialog dialog = new DetailDialog(parentShell, title, eObject);
		detailDialogMembersInjection.injectMembers(dialog);
		return dialog;
	}

	public DetailDialog createDetailDialog(Shell parentShell, String title,
			EObject original, EObject toBeEdited) {
		DetailDialog dialog = new DetailDialog(parentShell, title, original,
				toBeEdited);
		detailDialogMembersInjection.injectMembers(dialog);
		return dialog;
	}

	public AbstractDetailComposite createDialogDetailComposite(
			Composite parent, int style) {
		DialogDetailComposite detailComposite = new DialogDetailComposite(
				parent, style);
		detailComponentDialogMembersInjection.injectMembers(detailComposite);
		return detailComposite;
	}

}
