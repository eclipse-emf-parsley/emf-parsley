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
package org.eclipse.swt.dnd;

import java.awt.dnd.DragSourceEvent;

import org.eclipse.swt.internal.SWTEventListener;

public interface DragSourceListener extends SWTEventListener {

	public void dragStart(DragSourceEvent event);

	public void dragSetData(DragSourceEvent event);

	public void dragFinished(DragSourceEvent event);
}
