/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
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