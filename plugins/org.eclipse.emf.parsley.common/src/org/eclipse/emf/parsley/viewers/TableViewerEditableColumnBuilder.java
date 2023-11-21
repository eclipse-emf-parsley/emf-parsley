/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Francesco Guidieri - initial API and implementation
 * Lorenzo Bettini - https://bugs.eclipse.org/bugs/show_bug.cgi?id=479683
 *******************************************************************************/
package org.eclipse.emf.parsley.viewers;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.Layout;

import com.google.inject.Inject;


/**
 * This assumes that the objects of the table are {@link EObject}s.
 *
 * @author Francesco Guidieri - initial API and implementation
 *
 */
public class TableViewerEditableColumnBuilder extends TableViewerColumnBuilder {
	@Inject
	private TableViewerEditingSupportFactory editingSupportFactory;

	@Override
	protected TableViewerColumn buildTableViewerColumn(TableViewer tableViewer,
			Layout layout, EClass eClass, EStructuralFeature eStructuralFeature,
			int weight) {
		TableViewerColumn viewerColumn= super.buildTableViewerColumn(tableViewer, layout,
				eClass, eStructuralFeature, weight);

		TableViewerEditingSupport editingSupport =
				editingSupportFactory.createTableViewerEditingSupport(tableViewer, eStructuralFeature);
		viewerColumn.setEditingSupport(editingSupport);

		return viewerColumn;
	}

}
