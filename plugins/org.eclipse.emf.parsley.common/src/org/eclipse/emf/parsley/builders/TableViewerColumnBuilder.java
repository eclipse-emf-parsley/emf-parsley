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

package org.eclipse.emf.parsley.builders;


import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.EmfParsleyConstants;
import org.eclipse.emf.parsley.factories.ColumnLabelProviderFactory;
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Sets the columns of a TableViewer according to an EClass (adds a column for
 * each feature of the EClass, retrieved using an njected
 * {@link TableFeaturesProvider}).
 * 
 * @author Lorenzo Bettini - initial API and implementation
 * 
 */
public class TableViewerColumnBuilder {

	@Inject
	@Named(EmfParsleyConstants.DEFAULT_TABLE_COLUMN_WEIGHT)
	private int defaultWeight;

	@Inject
	@Named(EmfParsleyConstants.TABLE_COLUMN_WEIGHTS)
	private List<Integer> weights;

	@Inject
	protected ColumnLabelProviderFactory columnLabelProviderFactory;

	@Inject
	protected FeatureCaptionProvider featureCaptionProvider;

	@Inject
	protected TableFeaturesProvider featuresProvider;

	/**
	 * Setups the columns of the given tableViewer using the features of the
	 * given eClass; the features are retrieved using an injected
	 * {@link TableFeaturesProvider}.
	 * 
	 * @param tableViewer
	 * @param eClass
	 * @param contentProvider
	 */
	public void buildTableViewer(TableViewer tableViewer, EClass eClass,
			IStructuredContentProvider contentProvider) {
		final Table table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableLayout layout = new TableLayout();
		table.setLayout(layout);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		List<EStructuralFeature> typeFeatures = featuresProvider
				.getFeatures(eClass);
		int i=0;
		for (EStructuralFeature eStructuralFeature : typeFeatures) {
			int weight=defaultWeight;
			if(weights.size()>i){
				weight=weights.get(i++);
			}
			buildTableViewerColumn(tableViewer, layout, eClass, eStructuralFeature,
					contentProvider,weight);
		}
	}
	
	protected TableViewerColumn buildTableViewerColumn(TableViewer tableViewer,
			TableLayout layout, EClass eClass, EStructuralFeature eStructuralFeature,
			IStructuredContentProvider contentProvider, int weight) {
		TableViewerColumn viewerColumn = createTableViewerColumn(tableViewer,
				eStructuralFeature, contentProvider);
		TableColumn objectColumn = viewerColumn.getColumn();
		layout.addColumnData(new ColumnWeightData(weight, 30, true));
		objectColumn.setText(featureCaptionProvider.getText(eClass, eStructuralFeature));
		objectColumn.setResizable(true);
		return viewerColumn;
	}

	protected TableViewerColumn createTableViewerColumn(
			TableViewer tableViewer, EStructuralFeature eStructuralFeature,
			IStructuredContentProvider contentProvider) {
		TableViewerColumn tableViewerColumn = new TableViewerColumn(
				tableViewer, SWT.NONE);
		if (contentProvider != null) {
			tableViewerColumn.setLabelProvider(columnLabelProviderFactory
					.createColumnLabelProvider(eStructuralFeature,
							contentProvider));
		} else {
			tableViewerColumn.setLabelProvider(columnLabelProviderFactory
					.createColumnLabelProvider(eStructuralFeature));
		}
		return tableViewerColumn;
	}
}
