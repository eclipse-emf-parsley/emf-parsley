/**
 * 
 */
package org.eclipse.emf.parsley.listeners;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.dialogs.DetailDialog;
import org.eclipse.emf.parsley.dialogs.DetailFormBasedDialog;
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

	@Override
	public void mouseDoubleClick(MouseEvent event) {
		if (event.button == 1) {
			EObject eObject = helper.getEObjectFromMouseEvent(event);
			System.out.println(eObject);
			if (eObject != null) {
				DetailDialog dialog = dialogFactory.createDetailDialog(Display
						.getCurrent().getActiveShell(), labelProvider
						.getText(eObject), eObject);
				int rc = dialog.open();
				if (rc == Window.OK) {
					System.out.println("OK pressed");
					// Save entered text (dialog.getValue()) back to table
				} else {
					System.out.println("Cancel pressed");
				}
			}
		}
	}
}
