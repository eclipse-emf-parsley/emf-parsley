package org.eclipse.emf.parsley.listeners;

import org.eclipse.emf.parsley.EmfParsleyActivator;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * Opens the Properties view upon doubleclick.
 * 
 * @author Lorenzo Bettini - Initial Contribution and API
 * 
 */
public class OpenPropertyViewMouseAdapter extends MouseAdapter
		implements IEditorMouseListener {

	@Override
	public void mouseDoubleClick(MouseEvent event) {
		if (event.button == 1) {
			try {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow()
						.getActivePage()
						.showView("org.eclipse.ui.views.PropertySheet");
			} catch (PartInitException exception) {
				EmfParsleyActivator.log(exception);
			}
		}
	}
}