/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.runtime.ui;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * An implementation of {@link IImageHelper} that loads images using the
 * current class loader.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 */
public class ClassLoaderImageHelper extends AbstractImageHelper implements IImageHelper {

	@Override
	public Image getImage(String name) {
		return loadImageFromClassLoader(name);
	}

	/**
	 * Loads the image with the path specified in the parameter;
	 * it returns null in case the name does not represent a valid {@link URI}.
	 * 
	 * @param name
	 * @return
	 */
	protected Image loadImageFromClassLoader(String name) {
		URL url = getClass().getClassLoader().getResource(name);
		URI uri = null;
		
		try {
			uri = url.toURI();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		String path = uri.getPath();
		
		return new Image(Display.getDefault(), path);
	}

	@Override
	public Image getImage(ImageDescriptor imageDescriptor) {
		return imageDescriptor.createImage();
	}

}
