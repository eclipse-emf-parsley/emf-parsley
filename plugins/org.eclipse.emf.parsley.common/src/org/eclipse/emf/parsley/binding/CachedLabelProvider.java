/*******************************************************************************
 * Copyright (c) 2009, 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   itemis AG - Initial API and implementation
 *   Lorenzo Bettini - refactoring for EmfParsley
 *******************************************************************************/
package org.eclipse.emf.parsley.binding;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

/**
 * Cached Label Provider.
 * 
 * @author Dennis Huebner - Initial contribution and API
 */
public class CachedLabelProvider implements ILabelProvider {

	private final Map<Object, Image> imageCache = new HashMap<Object, Image>();
	private final Map<Object, String> textCache = new HashMap<Object, String>();

	private final ILabelProvider delegate;

	/**
	 * Creates a new CachedLabelProvider which stores Images and Text in an internal cache.<br>
	 * Values are fetched from given delegate {@link ILabelProvider}.
	 */
	public CachedLabelProvider(final ILabelProvider delegate) {
		this.delegate = delegate;
	}

	@Override
	public Image getImage(Object element) {
		if (!imageCache.containsKey(element)) {
			imageCache.put(element, delegate.getImage(element));
		}
		return imageCache.get(element);
	}

	@Override
	public String getText(Object element) {
		if (!textCache.containsKey(element)) {
			textCache.put(element, delegate.getText(element));
		}
		return textCache.get(element);
	}

	@Override
	public void dispose() {
		imageCache.clear();
		textCache.clear();
		delegate.dispose();
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return delegate.isLabelProperty(element, property);
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		delegate.addListener(listener);
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		delegate.removeListener(listener);
	}

}
