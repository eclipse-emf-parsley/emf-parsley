/*******************************************************************************
 * Copyright (c) 20134 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.runtime.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

/**
 * @author Lorenzo Bettini - initial API and implementation
 *
 */
public abstract class AbstractImageHelper implements IImageHelper {

	public Image convertToImage(Object imageDescription) {
		if (imageDescription instanceof Image) {
			return (Image) imageDescription;
		} else if (imageDescription instanceof ImageDescriptor) {
			return getImage((ImageDescriptor) imageDescription);
		} else if (imageDescription instanceof String) {
			return getImage((String) imageDescription);
		}
		return null;
	}

}
