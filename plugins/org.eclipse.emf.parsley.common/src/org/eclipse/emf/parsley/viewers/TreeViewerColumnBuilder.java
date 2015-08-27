/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - initial API and implementation
 *******************************************************************************/

package org.eclipse.emf.parsley.viewers;


import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.EmfParsleyConstants;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Sets the columns of a TreeViewer according to an EClass (adds a column for
 * each feature of the EClass, retrieved using an injected
 * {@link TableFeaturesProvider}).
 * 
 * @author Francesco Guidieri - initial API and implementation
 * 
 */
public class TreeViewerColumnBuilder {

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

	/**
	 * Setups the columns of the given treeViewer using the features of the
	 * given eClass; the features are retrieved using an injected
	 * {@link TreeFeaturesProvider}.
	 * 
	 * @param treeViewer
	 * @param eClass
	 * @param contentProvider
	 */
	public void buildTreeViewer(TreeViewer treeViewer, EClass eClass) {
		List<EStructuralFeature> typeFeatures = featuresProvider
				.getFeatures(eClass);
		buildTreeViewer(treeViewer, typeFeatures);
	}

	/**
	 * Setups the columns of the given treeViewer using the features of the
	 * given eClass; the features are retrieved using an injected
	 * {@link TreeFeaturesProvider}.
	 * 
	 * @param treeViewer
	 * @param eClass
	 * @param contentProvider
	 */
	public void buildTreeViewer(TreeViewer treeViewer, List<EStructuralFeature> typeFeatures) {

		final Tree tree = treeViewer.getTree();
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);

		Layout layout = null;

		final Layout treeParentLayout = treeViewer.getTree().getParent().getLayout();
		if (treeParentLayout instanceof TreeColumnLayout) {
			layout = treeParentLayout;
		} else {
			layout = new TableLayout();
			tree.setLayout(layout);
			tree.setHeaderVisible(true);
			tree.setLinesVisible(true);
		}

		int i = 0;
		
		createEmpty(treeViewer, layout, defaultWeight);
		
		for (EStructuralFeature eStructuralFeature : typeFeatures) {
			int weight = defaultWeight;
			if (weights.size() > i) {
				weight = weights.get(i++);
			}

			buildTreeViewerColumn(treeViewer, layout, eStructuralFeature, weight);
		}
	}

	protected TreeViewerColumn buildTreeViewerColumn(TreeViewer treeViewer, Layout layout,
			EStructuralFeature eStructuralFeature, int weight) {
		TreeViewerColumn viewerColumn = createTreeViewerColumn(treeViewer, eStructuralFeature);
		TreeColumn objectColumn = viewerColumn.getColumn();
		if (layout instanceof TreeColumnLayout) {
			((TreeColumnLayout) layout).setColumnData(viewerColumn.getColumn(), new ColumnWeightData(weight, 30, true));
		} else if (layout instanceof TableLayout) {
			((TableLayout) layout).addColumnData(new ColumnWeightData(weight, 30, true));
		}
		objectColumn.setText(featureCaptionProvider.getText(eStructuralFeature.eClass(), eStructuralFeature));
		objectColumn.setResizable(true);
		return viewerColumn;
	}
	
	
	protected TreeViewerColumn createEmpty(TreeViewer treeViewer, Layout layout, int weight){
		TreeViewerColumn viewerColumn = new TreeViewerColumn(
				treeViewer, SWT.NONE);
		viewerColumn.setLabelProvider(columnLabelProviderFactory.createColumnLabelProvider());
		TreeColumn objectColumn = viewerColumn.getColumn();
		if (layout instanceof TreeColumnLayout) {
			((TreeColumnLayout) layout).setColumnData(viewerColumn.getColumn(), new ColumnWeightData(weight, 30, true));
		} else if (layout instanceof TableLayout) {
			((TableLayout) layout).addColumnData(new ColumnWeightData(weight, 30, true));
		}
		objectColumn.setResizable(true);
		return viewerColumn;
		
	}

	protected TreeViewerColumn createTreeViewerColumn(
			TreeViewer treeViewer, EStructuralFeature eStructuralFeature) {
		TreeViewerColumn treeViewerColumn = new TreeViewerColumn(
				treeViewer, SWT.NONE);
		treeViewerColumn.setLabelProvider(columnLabelProviderFactory
					.createColumnLabelProvider(eStructuralFeature));
		return treeViewerColumn;
	}
}
