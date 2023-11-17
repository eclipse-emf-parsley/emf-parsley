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

import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.parsley.EmfParsleyConstants;
import org.eclipse.emf.parsley.runtime.ui.IImageHelper;
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcher;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Default implementation for {@link ILabelProvider} that uses polymorphic dispatch to invoke methods at runtime,
 * it also implements {@link IFontProvider} and {@link IColorProvider}.
 *
 * You can define {@link #text(Object)} and {@link #image(Object)} methods specifying the input type.
 * The framework will select the correct implementation depending on the runtime type of the argument.
 *
 * The same holds for {@link #font(Object)}, {@link #foreground(Object)} and {@link #background(Object)}.
 *
 * @author Lorenzo Bettini - Initial contribution and API
 * @author Francesco Guidieri - Javadocs :-)
 */
public class ViewerLabelProvider implements ILabelProvider, IFontProvider, IColorProvider {

	@Inject
	private IImageHelper imageHelper;

	@Inject
	@Named(EmfParsleyConstants.ITERABLE_STRING_SEPARATOR)
	private String separator;

	@Inject
	@Named(EmfParsleyConstants.ITERABLE_STRING_ELLIPSES)
	private String ellipses;

	@Inject
	@Named(EmfParsleyConstants.ITERABLE_STRING_MAX_LENGTH)
	private int iterableStringMaxLength;

	private PolymorphicDispatcher<String> textDispatcher = PolymorphicDispatcher
			.createForSingleTarget("text", 1, 1, this);

	private PolymorphicDispatcher<Object> imageDispatcher = PolymorphicDispatcher
			.createForSingleTarget("image", 1, 1, this);

	private PolymorphicDispatcher<Font> fontDispatcher = PolymorphicDispatcher
			.createForSingleTarget("font", 1, 1, this);

	private PolymorphicDispatcher<Color> foregroundDispatcher = PolymorphicDispatcher
			.createForSingleTarget("foreground", 1, 1, this);

	private PolymorphicDispatcher<Color> backgroundDispatcher = PolymorphicDispatcher
			.createForSingleTarget("background", 1, 1, this);

	protected ILabelProvider delegateLabelProvider;

	@Inject
	public ViewerLabelProvider(AdapterFactoryLabelProvider delegate) {
		delegateLabelProvider = delegate;
	}

	@Override
	public String getText(Object element) {
		if (element == null) {
			return "";
		}

		String text = textDispatcher.invoke(element);
		if (text != null) {
			return text;
		}

		return getDelegateText(element);
	}

	protected String getDelegateText(Object element) {
		return getDelegateLabelProvider().getText(element);
	}

	@Override
	public Image getImage(Object element) {
		if (element == null) {
			return null;
		}

		Object imageObject = imageDispatcher.invoke(element);
		if (imageObject != null) {
			Image image = imageHelper.convertToImage(imageObject);
			if (image != null) {
				return image;
			}
		}

		return getDelegateImage(element);
	}

	protected Image getDelegateImage(Object element) {
		return getDelegateLabelProvider().getImage(element);
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		getDelegateLabelProvider().addListener(listener);
	}

	@Override
	public void dispose() {
		getDelegateLabelProvider().dispose();
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return getDelegateLabelProvider().isLabelProperty(element, property);
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		getDelegateLabelProvider().removeListener(listener);
	}

	public ILabelProvider getDelegateLabelProvider() {
		return delegateLabelProvider;
	}

	/**
	 * This method will be linked at runtime, belonging to the real input type at runtime.
	 * @return the text representation of the input parameter
	 */
	public String text(Object element) {
		return null;
	}

	/**
	 * This method will be linked at runtime, belonging to the real input type at runtime.
	 * @return the image that represents the input
	 */
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

	/**
	 * Custom implementation for {@link Iterable} using separator, max length and ellipses
	 * and calling getText on each element.
	 * @param iterable
	 * @return
	 */
	public String text(Iterable<?> iterable) {
		StringBuilder builder = new StringBuilder();
		for (Object object : iterable) {
			if (builder.length() > 0) {
				builder.append(separator);
			}
			builder.append(getText(object));
			if (builder.length() > iterableStringMaxLength) {
				return builder.substring(0, iterableStringMaxLength) + ellipses;
			}
		}
		return builder.toString();
	}

	@Override
	public Font getFont(Object element) {
		if (element == null) {
			return null;
		}

		return fontDispatcher.invoke(element);
	}

	/**
	 * This method will be linked at runtime, belonging to the real input type at runtime.
	 * @return the font that represents the input
	 */
	public Font font(Object element) {
		return null;
	}

	@Override
	public Color getForeground(Object element) {
		if (element == null) {
			return null;
		}
		return foregroundDispatcher.invoke(element);
	}

	/**
	 * This method will be linked at runtime, belonging to the real input type at runtime.
	 * @return the foreground color
	 */
	public Color foreground(Object element) {
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		if (element == null) {
			return null;
		}
		return backgroundDispatcher.invoke(element);
	}

	/**
	 * This method will be linked at runtime, belonging to the real input type at runtime.
	 * @return the background color
	 */
	public Color background(Object element) {
		return null;
	}

}
