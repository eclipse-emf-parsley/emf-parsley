package org.eclipse.emf.parsley.listeners;


import org.eclipse.emf.parsley.EmfParsleyCommonActivator;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * This default implementation opens the Properties view upon doubleclick.
 * 
 * @author bettini
 *
 */
public class ViewerMouseAdapter extends MouseAdapter {

	@Override
	public void mouseDoubleClick(MouseEvent event) {
		if (event.button == 1) {
			try {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
						.showView("org.eclipse.ui.views.PropertySheet");
			} catch (PartInitException exception) {
				EmfParsleyCommonActivator.log(exception);
			}
		}
	}
}