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
package org.eclipse.swt.widgets;

import java.awt.Image;

public abstract class Item extends Widget {
	String text;
	Image image;

	public Item(Widget parent, int style) {
		super(parent, style);
		text = "";
	}

	public Item(Widget parent, int style, int index) {
		this(parent, style);
	}

}
