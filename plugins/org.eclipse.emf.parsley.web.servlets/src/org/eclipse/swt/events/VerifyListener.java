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

public interface VerifyListener extends SWTEventListener {

    /**
     * Sent when the text is about to be modified.
     * <p>
     * A verify event occurs after the user has done something to modify the text (typically typed a
     * key), but before the text is modified. The doit field in the verify event indicates whether
     * or not to modify the text.
     * </p>
     *
     * @param e an event containing information about the verify
     */
    public void verifyText(VerifyEvent e);
}
