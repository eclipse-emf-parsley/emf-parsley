/*******************************************************************************
 * Copyright (c) 2008, 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * itemis AG - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.runtime.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import com.google.inject.ImplementedBy;

/**
 * IImageHelper defines the signatures of getImage methods for retrieving {@link Image} given their
 * name or {@link ImageDescriptor}.
 * @author Sebastian Zarnekow
 * @author Michael Clay
 */
@ImplementedBy(IImageHelper.NullImageHelper.class)
public interface IImageHelper {

	Image getImage(String name);
	
	Image getImage(ImageDescriptor imageDescriptor);
	
	class NullImageHelper implements IImageHelper {

		public Image getImage(String name) {
			return null;
		}

		public Image getImage(ImageDescriptor imageDescriptor) {
			return null;
		}
		
	}

}
