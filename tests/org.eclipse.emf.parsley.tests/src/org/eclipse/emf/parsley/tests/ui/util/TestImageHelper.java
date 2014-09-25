/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.ui.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.emf.parsley.runtime.ui.IImageHelper;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 */
public class TestImageHelper implements IImageHelper {

	public Image getImage(String name) {
		Image image = loadImageFromLocalTest(name);
		return image;
	}

	public static Image loadImageFromLocalTest(String name) {
		URL url = TestImageHelper.class.getClassLoader().getResource(name);
		URI uri = null;
		
		try {
			uri = url.toURI();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		String path = uri.getPath();
		
		Image image = new Image(Display.getDefault(), path);
		return image;
	}

	public Image getImage(ImageDescriptor imageDescriptor) {
		return imageDescriptor.createImage();
	}

}
