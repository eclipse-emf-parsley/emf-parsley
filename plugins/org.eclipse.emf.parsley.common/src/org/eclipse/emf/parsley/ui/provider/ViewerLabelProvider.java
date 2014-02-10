/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.ui.provider;

import org.eclipse.emf.parsley.runtime.ui.IImageHelper;
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcher;

import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import com.google.inject.Inject;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 * 
 */
public class ViewerLabelProvider implements ILabelProvider {

	@Inject
	private IImageHelper imageHelper;

	private PolymorphicDispatcher<String> textDispatcher = PolymorphicDispatcher
			.createForSingleTarget("text", 1, 1, this);

	private PolymorphicDispatcher<Object> imageDispatcher = PolymorphicDispatcher
			.createForSingleTarget("image", 1, 1, this);

	protected ILabelProvider delegateLabelProvider;
	
	@Inject
	public ViewerLabelProvider(AdapterFactoryLabelProvider delegate) {
		delegateLabelProvider = delegate;
	}

	public String getText(Object element) {
		if (element == null)
			return "";
		
		String text = textDispatcher.invoke(element);
		if (text != null) {
			return text;
		}
		return getDelegateText(element);
	}

	protected String getDelegateText(Object element) {
		return getDelegateLabelProvider().getText(element);
	}

	public Image getImage(Object element) {
		if (element == null)
			return null;
		
		Object imageObject = imageDispatcher.invoke(element);
		if (imageObject != null) {
			Image image = convertToImage(imageObject);
			if (image != null)
				return image;
		}
		return getDelegateImage(element);
	}

	/**
	 * @param imageDescription
	 *            a {@link String}, an {@link ImageDescriptor} or an
	 *            {@link Image}
	 * @return the {@link Image} associated with the description or
	 *         <code>null</code>
	 */
	protected Image convertToImage(Object imageDescription) {
		if (imageDescription instanceof Image) {
			return (Image) imageDescription;
		} else if (imageDescription instanceof ImageDescriptor) {
			return imageHelper.getImage((ImageDescriptor) imageDescription);
		} else if (imageDescription instanceof String) {
			return imageHelper.getImage((String) imageDescription);
		}
		return null;
	}

	protected Image getDelegateImage(Object element) {
		return getDelegateLabelProvider().getImage(element);
	}

	public void addListener(ILabelProviderListener listener) {
		getDelegateLabelProvider().addListener(listener);
	}

	public void dispose() {
		getDelegateLabelProvider().dispose();
	}

	public boolean isLabelProperty(Object element, String property) {
		return getDelegateLabelProvider().isLabelProperty(element, property);
	}

	public void removeListener(ILabelProviderListener listener) {
		getDelegateLabelProvider().removeListener(listener);
	}

	public ILabelProvider getDelegateLabelProvider() {
		return delegateLabelProvider;
	}

	public String text(Object element) {
		return null;
	}

	public Object image(Object element) {
		return null;
	}

	/**
	 * Custom implementation for {@link Entry} that uses the entry.getValue()
	 * @param entry
	 * @return
	 */
	public String text(Entry entry) {
		return getText(entry.getValue());
	}

	/**
	 * Custom implementation for {@link Entry} that uses the entry.getValue()
	 * @param entry
	 * @return
	 */
	public Object image(Entry entry) {
		return getImage(entry.getValue());
	}
}
