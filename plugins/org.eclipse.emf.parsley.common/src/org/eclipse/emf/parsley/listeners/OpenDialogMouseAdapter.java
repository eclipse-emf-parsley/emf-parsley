/**
 * 
 */
package org.eclipse.emf.parsley.listeners;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.dialogs.DetailDialog;
import org.eclipse.emf.parsley.dialogs.DetailFormBasedDialog;
import org.eclipse.emf.parsley.edit.IEditingStrategy;
import org.eclipse.emf.parsley.factories.DialogFactory;
import org.eclipse.emf.parsley.util.EmfSelectionHelper;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Display;

import com.google.inject.Inject;

/**
 * A {@link MouseAdapter} that opens a {@link DetailFormBasedDialog} on double
 * click.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 * 
 */
public class OpenDialogMouseAdapter extends MouseAdapter implements
		IEditorMouseListener {

	@Inject
	private EmfSelectionHelper helper;

	@Inject
	private DialogFactory dialogFactory;

	@Inject
	private ILabelProvider labelProvider;
	
	@Inject 
	protected IEditingStrategy editingStrategy;

	@Override
	public void mouseDoubleClick(MouseEvent event) {
		if (event.button == 1) {
			EObject original = helper.getEObjectFromMouseEvent(event);
			if (original != null) {
				EObject toBeEdited = editingStrategy.prepare(original);
				DetailDialog dialog = dialogFactory.createDetailDialog(Display
						.getCurrent().getActiveShell(), labelProvider
						.getText(original), original, toBeEdited);
				int rc = dialog.open();
				if (rc == Window.OK) {
					editingStrategy.update(original, toBeEdited);
				}
			}
		}
	}
}
