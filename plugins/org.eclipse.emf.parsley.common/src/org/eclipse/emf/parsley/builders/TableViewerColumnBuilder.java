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
import org.eclipse.emf.parsley.factories.ColumnLabelProviderFactory;
import org.eclipse.emf.parsley.ui.provider.FeaturesColumnProvider;
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

/**
 * Sets the columns of a TableViewer according to an EClass (adds a column for
 * each feature of the EClass).
 * 
 * @author Lorenzo Bettini
 * 
 */
public class TableViewerColumnBuilder {

	private static final int DEFAULT_WEIGHT = 3;

	@Inject
	protected ColumnLabelProviderFactory columnLabelProviderFactory;

	@Inject
	protected FeatureCaptionProvider featureCaptionProvider;

	@Inject
	protected FeaturesColumnProvider featuresProvider;

	public void buildTableViewer(TableViewer tableViewer, EClass eClass) {
		buildTableViewer(tableViewer, eClass, null);
	}

	/**
	 * @param tableViewer
	 * @param eClass
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
			int weight=DEFAULT_WEIGHT;
			if(featuresProvider.getWeights()!=null && featuresProvider.getWeights().size()>i){
				weight=featuresProvider.getWeights().get(i++);
			}
			buildTableViewerColumn(tableViewer, layout, eStructuralFeature,
					contentProvider,weight);
		}
	}
	
	protected TableViewerColumn buildTableViewerColumn(TableViewer tableViewer,
			TableLayout layout, EStructuralFeature eStructuralFeature,
			IStructuredContentProvider contentProvider) {
		return buildTableViewerColumn(tableViewer, layout, eStructuralFeature, contentProvider,DEFAULT_WEIGHT);
	}

	protected TableViewerColumn buildTableViewerColumn(TableViewer tableViewer,
			TableLayout layout, EStructuralFeature eStructuralFeature,
			IStructuredContentProvider contentProvider, int weight) {
		TableViewerColumn viewerColumn = createTableViewerColumn(tableViewer,
				eStructuralFeature, contentProvider);
		TableColumn objectColumn = viewerColumn.getColumn();
		layout.addColumnData(new ColumnWeightData(weight, 30, true));
		objectColumn.setText(featureCaptionProvider.getText(eStructuralFeature));
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
