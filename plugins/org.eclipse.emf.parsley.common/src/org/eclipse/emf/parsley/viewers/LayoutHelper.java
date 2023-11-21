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
package org.eclipse.emf.parsley.viewers;

import org.eclipse.jface.layout.AbstractColumnLayout;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Widget;

/**
 * Some methods to adjust layout.
 *
 * @author Lorenzo Bettini - initial API and implementation
 *
 */
public class LayoutHelper {

	public static final int MINIMUM_WEIGHT = 30;

	public Layout adjustForTableLayout(TableViewer tableViewer) {
		final Table table = tableViewer.getTable();

		Layout layout = null;

		final Layout parentLayout = table.getParent().getLayout();
		if (parentLayout instanceof TableColumnLayout) {
			layout = parentLayout;
		} else {
			layout = new TableLayout();
			table.setLayout(layout);
		}
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		return layout;
	}

	public Layout adjustForTableLayout(TreeViewer treeViewer) {
		final Tree tree = treeViewer.getTree();

		Layout layout = null;

		final Layout parentLayout = tree.getParent().getLayout();
		if (parentLayout instanceof TreeColumnLayout) {
			layout = parentLayout;
		} else {
			layout = new TableLayout();
			tree.setLayout(layout);
		}
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);

		return layout;
	}

	public void adjustLayoutColumnData(Layout layout, Widget column, int weight) {
		if (layout instanceof AbstractColumnLayout) {
			((AbstractColumnLayout) layout).setColumnData(column, createColumnWeightData(weight));
		} else if (layout instanceof TableLayout) {
			((TableLayout) layout).addColumnData(createColumnWeightData(weight));
		}
	}

	protected ColumnWeightData createColumnWeightData(int weight) {
		return new ColumnWeightData(weight, MINIMUM_WEIGHT, true);
	}
}
