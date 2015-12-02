/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Vincenzo Caselli - Initial contribution and API
 * 
 *******************************************************************************/
package org.eclipse.swt.events;

import org.eclipse.swt.internal.SWTEventListener;

public interface MouseListener extends SWTEventListener {

	/**
	 * Sent when a mouse button is pressed twice within the (operating system
	 * specified) double click period.
	 *
	 * @param e
	 *            an event containing information about the mouse double click
	 *
	 * @see org.eclipse.swt.widgets.Display#getDoubleClickTime()
	 */
	public void mouseDoubleClick(MouseEvent e);

	/**
	 * Sent when a mouse button is pressed.
	 *
	 * @param e
	 *            an event containing information about the mouse button press
	 */
	public void mouseDown(MouseEvent e);

	/**
	 * Sent when a mouse button is released.
	 *
	 * @param e
	 *            an event containing information about the mouse button release
	 */
	public void mouseUp(MouseEvent e);
}
