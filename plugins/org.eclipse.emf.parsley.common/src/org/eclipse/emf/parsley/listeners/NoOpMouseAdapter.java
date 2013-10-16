package org.eclipse.emf.parsley.listeners;

import org.eclipse.swt.events.MouseEvent;

/**
 * This implementation does nothing upon mouse events.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 * 
 */
public class NoOpMouseAdapter implements IEditorMouseListener {
	public void mouseDoubleClick(MouseEvent event) {
	}

	public void mouseDown(MouseEvent e) {
	}

	public void mouseUp(MouseEvent e) {
	}
}