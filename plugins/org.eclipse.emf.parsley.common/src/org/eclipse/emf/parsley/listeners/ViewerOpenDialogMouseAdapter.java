/**
 * 
 */
package org.eclipse.emf.parsley.listeners;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.dialogs.DetailComponentDialog;
import org.eclipse.emf.parsley.factories.DialogFactory;
import org.eclipse.emf.parsley.util.EmfSelectionHelper;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Display;

import com.google.inject.Inject;

/**
 * A {@link MouseAdapter} that opens a {@link DetailComponentDialog} on double click.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 * 
 */
public class ViewerOpenDialogMouseAdapter extends ViewerMouseAdapter {

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
				DetailComponentDialog dialog = dialogFactory
						.createDetailComponentDialog(Display.getCurrent()
								.getActiveShell(), labelProvider.getText(eObject), 
									eObject);
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
