/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.widgets;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * An abstraction for creating {@link Label}, {@link Text}, {@link Button}, etc.
 * It will be implemented creating standard controls and form controls.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public interface IWidgetFactory {

	void init(Composite parent);

	Label createLabel(String text);

	Label createLabel(Composite parent, String text);

	Button createButton(String text, int... styles);

	Button createButton(Composite parent, String text, int style);

	Text createText(String text);

	Text createText(String text, int... styles);

	Text createText(Composite parent, String text);

	Text createText(Composite parent, String text, int style);

	ComboViewer createComboViewer(int... styles);

	ComboViewer createComboViewer(Composite parent, int style);

	DateTime createDateTime();

	DateTime createDateTime(int... styles);

	DateTime createDateTime(Composite parent);

	DateTime createDateTime(Composite parent, int style);

	Composite getParent();

}