/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.labeling;

import org.eclipse.emf.parsley.examples.library.Writer;
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider;


public class CustomLibraryTableColumnLabelProvider extends
		TableColumnLabelProvider {

	public String text_Writer_name(Object element) {
		if (element instanceof Writer) {
			Writer writer = (Writer) element;
			return "Writer " + writer.getName();
		}
		return null;
	}

}
