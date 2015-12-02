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

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.DeviceData;

public class Display extends Device {
	static Display Default;
	boolean disposed;
	static final int GROW_SIZE = 1024;

	/* Skinning support */
	Widget[] skinList = new Widget[GROW_SIZE];
	int skinCount;

	public Display() {
		this(null);
		Display.Default = this;
	}

	public Display(DeviceData data) {
		super(data);
	}

	public static Display getCurrent() {
		return getDefault(); /// +
	}

	public static Display getDefault() {
		synchronized (Device.class) {
			if (Default == null)
				Default = new Display();
			return Default;
		}
	}

	public boolean isDisposed() {
		synchronized (Device.class) {
			return disposed;
		}
	}

	void addSkinnableWidget(Widget widget) {
		if (skinCount >= skinList.length) {
			Widget[] newSkinWidgets = new Widget[skinList.length + GROW_SIZE];
			System.arraycopy(skinList, 0, newSkinWidgets, 0, skinList.length);
			skinList = newSkinWidgets;
		}
		skinList[skinCount++] = widget;
	}

}
