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
package org.eclipse.swt.graphics;

public final class Image extends Resource implements Drawable {

    Image(Device device) {
        super(device);
    }

    public Image(Device device, int width, int height) {
        super(device);
    }
    /// +

    public long /* int */ internal_new_GC(GCData data) {
        return 0; /// +
    }

    @Override
    public void internal_dispose_GC(long handle, GCData data) {}

}
