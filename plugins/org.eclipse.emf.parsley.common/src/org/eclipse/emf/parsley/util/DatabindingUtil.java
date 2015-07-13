/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.util;

import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

/**
 * A utility class with methods for databinding that have the same signatures of
 * the deprecated ones and use the new {@link WidgetProperties} methods.
 * 
 * This is useful in the DSL since it will be easier to implement target
 * observable specifications.
 * 
 * @author Lorenzo Bettini - initial API and implementation
 *
 */
public class DatabindingUtil {

	protected DatabindingUtil() {

	}

	/**
	 * Returns an observable observing the text attribute of the provided
	 * <code>control</code>. The supported types are:
	 * <ul>
	 * <li>org.eclipse.swt.widgets.Text</li>
	 * <li>org.eclipse.swt.custom.StyledText (as of 1.3)</li>
	 * </ul>
	 *
	 * @param control
	 * @param event
	 *            event type to register for change events
	 * @return observable value
	 * @throws IllegalArgumentException
	 *             if <code>control</code> type is unsupported
	 */
	public static ISWTObservableValue observeText(Control control, int event) {
		return WidgetProperties.text(event).observe(control);
	}

	/**
	 * Returns an observable observing the text attribute of the provided
	 * <code>widget</code>. The supported types are:
	 * <ul>
	 * <li>org.eclipse.swt.widgets.Button (as of 1.3)</li>
	 * <li>org.eclipse.swt.custom.CCombo</li>
	 * <li>org.eclipse.swt.custom.CLabel</li>
	 * <li>org.eclipse.swt.widgets.Combo</li>
	 * <li>org.eclipse.swt.widgets.Group (as of 1.7)</li>
	 * <li>org.eclipse.swt.widgets.Item</li>
	 * <li>org.eclipse.swt.widgets.Label</li>
	 * <li>org.eclipse.swt.widgets.Link (as of 1.2)</li>
	 * <li>org.eclipse.swt.widgets.Shell</li>
	 * <li>org.eclipse.swt.widgets.StyledText (as of 1.3)</li>
	 * <li>org.eclipse.swt.widgets.Text (as of 1.3)</li>
	 * </ul>
	 *
	 * @param widget
	 * @return observable value
	 * @throws IllegalArgumentException
	 *             if the type of <code>widget</code> is unsupported
	 */
	public static ISWTObservableValue observeText(Widget widget) {
		return WidgetProperties.text().observe(widget);
	}

	/**
	 * Returns an observable observing the selection attribute of the provided
	 * <code>control</code>. The supported types are:
	 * <ul>
	 * <li>org.eclipse.swt.widgets.Spinner</li>
	 * <li>org.eclipse.swt.widgets.Button</li>
	 * <li>org.eclipse.swt.widgets.Combo</li>
	 * <li>org.eclipse.swt.custom.CCombo</li>
	 * <li>org.eclipse.swt.widgets.List</li>
	 * <li>org.eclipse.swt.widgets.MenuItem (since 1.5)</li>
	 * <li>org.eclipse.swt.widgets.Scale</li>
	 * </ul>
	 *
	 * @param widget
	 * @return observable value
	 * @throws IllegalArgumentException
	 *             if <code>control</code> type is unsupported
	 */
	public static ISWTObservableValue observeSelection(Widget widget) {
		return WidgetProperties.selection().observe(widget);
	}

}
