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

public interface ShellListener extends SWTEventListener {

    /**
     * Sent when a shell becomes the active window.
     *
     * @param e an event containing information about the activation
     */
    public void shellActivated(ShellEvent e);

    /**
     * Sent when a shell is closed.
     *
     * @param e an event containing information about the close
     */
    public void shellClosed(ShellEvent e);

    /**
     * Sent when a shell stops being the active window.
     *
     * @param e an event containing information about the deactivation
     */
    public void shellDeactivated(ShellEvent e);

    /**
     * Sent when a shell is un-minimized.
     *
     * @param e an event containing information about the un-minimization
     */
    public void shellDeiconified(ShellEvent e);

    /**
     * Sent when a shell is minimized.
     *
     * @param e an event containing information about the minimization
     */
    public void shellIconified(ShellEvent e);
}
