package org.eclipse.emf.parsley.listeners;

import org.eclipse.swt.events.MouseEvent;

/**
 * This implementation does nothing upon events.
 * 
 * @author bettini
 * 
 */
public class ViewerNoOpMouseAdapter extends ViewerMouseAdapter {
	@Override
	public void mouseDoubleClick(MouseEvent event) {
	}

	@Override
	public void mouseDown(MouseEvent e) {
	}

	@Override
	public void mouseUp(MouseEvent e) {
	}
}