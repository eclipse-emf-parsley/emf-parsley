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

package org.eclipse.emf.parsley.viewers;


import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.EmfParsleyConstants;
import org.eclipse.emf.parsley.internal.viewers.GenericFeatureViewerComparator;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.TableColumn;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Sets the columns of a TableViewer according to an EClass (adds a column for
 * each feature of the EClass, retrieved using an injected
 * {@link TableFeaturesProvider}).
 *
 * @author Lorenzo Bettini - initial API and implementation
 * @author Francesco Guidieri - Sorting
 */
public class TableViewerColumnBuilder {

	@Inject
	@Named(EmfParsleyConstants.DEFAULT_TABLE_COLUMN_WEIGHT)
	private int defaultWeight;

	@Inject
	@Named(EmfParsleyConstants.TABLE_COLUMN_WEIGHTS)
	private List<Integer> weights;

	@Inject
	private ColumnLabelProviderFactory columnLabelProviderFactory;

	@Inject
	private FeatureCaptionProvider featureCaptionProvider;

	@Inject
	private TableFeaturesProvider featuresProvider;

	@Inject
	private LayoutHelper layoutHelper;

	@Inject
	private GenericFeatureViewerComparator viewerComparator;

	/**
	 * Setups the columns of the given tableViewer using the features of the
	 * given eClass; the features are retrieved using an injected
	 * {@link TableFeaturesProvider}.
	 *
	 * @param tableViewer
	 * @param eClass
	 * @param contentProvider
	 */
	public void buildTableViewer(TableViewer tableViewer, EClass eClass) {
		Layout layout = layoutHelper.adjustForTableLayout(tableViewer);
		List<EStructuralFeature> typeFeatures = featuresProvider.getFeatures(eClass);
		int i = 0;
		int columnIndex = 0;
		tableViewer.setComparator(viewerComparator);
		viewerComparator.init(typeFeatures);
		for (EStructuralFeature eStructuralFeature : typeFeatures) {
			int weight = defaultWeight;
			if (weights.size() > i) {
				weight = weights.get(i++);
			}
			buildTableViewerColumn(tableViewer, layout, eClass,
					eStructuralFeature, weight, columnIndex++);
		}
	}

	protected TableViewerColumn buildTableViewerColumn(TableViewer tableViewer, Layout layout, EClass eClass,
			EStructuralFeature eStructuralFeature, int weight) {
		TableViewerColumn viewerColumn = createTableViewerColumn(tableViewer, eStructuralFeature);
		TableColumn objectColumn = viewerColumn.getColumn();
		layoutHelper.adjustLayoutColumnData(layout, objectColumn, weight);

		objectColumn.setText(featureCaptionProvider.getText(eClass, eStructuralFeature));
		objectColumn.setResizable(true);
		return viewerColumn;
	}

	/**
	 * @since 1.1
	 */
	protected TableViewerColumn buildTableViewerColumn(TableViewer tableViewer, Layout layout, EClass eClass,
			EStructuralFeature eStructuralFeature, int weight, int colNumber) {
		TableViewerColumn viewerColumn = buildTableViewerColumn(tableViewer, layout, eClass, eStructuralFeature, weight);
		TableColumn objectColumn = viewerColumn.getColumn();
		objectColumn.addSelectionListener(getSelectionAdapter(tableViewer, objectColumn, colNumber));
		return viewerColumn;
	}

	protected TableViewerColumn createTableViewerColumn(
			TableViewer tableViewer, EStructuralFeature eStructuralFeature) {
		TableViewerColumn tableViewerColumn = new TableViewerColumn(
				tableViewer, SWT.NONE);
		tableViewerColumn.setLabelProvider(columnLabelProviderFactory
					.createColumnLabelProvider(eStructuralFeature));
		return tableViewerColumn;
	}

	private SelectionAdapter getSelectionAdapter(final TableViewer viewer, final TableColumn column,
			final int index) {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				GenericFeatureViewerComparator comparator = (GenericFeatureViewerComparator) viewer.getComparator();
				comparator.setPropertyIndex(index);
				int dir = comparator.getDirection();
				viewer.getTable().setSortDirection(dir);
				viewer.getTable().setSortColumn(column);
				viewer.refresh();
			}
		};
	}
}
