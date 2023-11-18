/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.ui.provider;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

import com.google.inject.Inject;

/**
 * Implements a {@link ColumnLabelProvider} by delegating to an injected
 * {@link ILabelProvider}.
 *
 * This is useful for implementing the main column of a tree with columns. The
 * main column is the one that shows the tree.
 *
 * @author Lorenzo Bettini - initial API and implementation
 *
 */
public class DelegatingColumnLabelProvider extends ColumnLabelProvider {

	@Inject
	private ILabelProvider labelProvider;

	@Override
	public String getText(Object element) {
		return labelProvider.getText(element);
	}

	@Override
	public Image getImage(Object element) {
		return labelProvider.getImage(element);
	}

	@Override
	public Font getFont(Object element) {
		if (labelProvider instanceof IFontProvider) {
			return ((IFontProvider) labelProvider).getFont(element);
		}
		return super.getFont(element);
	}

	@Override
	public Color getForeground(Object element) {
		if (labelProvider instanceof IColorProvider) {
			return ((IColorProvider) labelProvider).getForeground(element);
		}
		return super.getForeground(element);
	}

	@Override
	public Color getBackground(Object element) {
		if (labelProvider instanceof IColorProvider) {
			return ((IColorProvider) labelProvider).getBackground(element);
		}
		return super.getBackground(element);
	}
}
