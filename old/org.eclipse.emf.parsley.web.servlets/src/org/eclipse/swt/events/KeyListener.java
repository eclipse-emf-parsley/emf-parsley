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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import org.eclipse.swt.internal.SWTEventListener;

/**
 * Classes which implement this interface provide methods that deal with the
 * events that are generated as keys are pressed on the system keyboard.
 * <p>
 * After creating an instance of a class that implements this interface it can
 * be added to a control using the <code>addKeyListener</code> method and
 * removed using the <code>removeKeyListener</code> method. When a key is
 * pressed or released, the appropriate method will be invoked.
 * </p>
 *
 * @see KeyAdapter
 * @see KeyEvent
 */
public interface KeyListener extends SWTEventListener {

	/**
	 * Sent when a key is pressed on the system keyboard.
	 *
	 * @param e
	 *            an event containing information about the key press
	 */
	public void keyPressed(KeyEvent e);

	/**
	 * Sent when a key is released on the system keyboard.
	 *
	 * @param e
	 *            an event containing information about the key release
	 */
	public void keyReleased(KeyEvent e);
}
