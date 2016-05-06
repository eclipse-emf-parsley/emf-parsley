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
import org.eclipse.emf.parsley.ui.provider.DelegatingColumnLabelProvider;
import org.eclipse.emf.parsley.ui.provider.FeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.TableFeaturesProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Layout;
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
	private DelegatingColumnLabelProvider delegatingColumnLabelProvider;

	@Inject
	private FeatureCaptionProvider featureCaptionProvider;

	@Inject
	private TableFeaturesProvider featuresProvider;

	@Inject
	private LayoutHelper layoutHelper;

	/**
	 * Setups the columns of the given treeViewer using the features of the
	 * given eClass; the features are retrieved using an injected
	 * {@link TableFeaturesProvider}.
	 * 
	 * @param treeViewer
	 * @param eClass
	 */
	public void buildTreeViewer(TreeViewer treeViewer, EClass eClass) {
		buildTreeViewer(treeViewer, eClass, featuresProvider.getFeatures(eClass));
	}

	/**
	 * Setups the columns of the given treeViewer using the specified features.
	 * 
	 * @param treeViewer
	 * @param typeFeatures
	 */
	public void buildTreeViewer(TreeViewer treeViewer, List<EStructuralFeature> typeFeatures) {
		buildTreeViewer(treeViewer, null, typeFeatures);
	}

	/**
	 * Setups the columns of the given treeViewer using the specified features,
	 * the eClass, if not null, is used for the feature caption provider, otherwise
	 * the containing eClass of each feature is used for the feature caption provider.
	 * 
	 * @param treeViewer
	 * @param eClass
	 * @param typeFeatures
	 */
	protected void buildTreeViewer(TreeViewer treeViewer, EClass eClass, List<EStructuralFeature> typeFeatures) {
		Layout layout = layoutHelper.adjustForTableLayout(treeViewer);

		int i = 0;

		createMainTreeViewerColumn(treeViewer, layout, defaultWeight);

		for (EStructuralFeature eStructuralFeature : typeFeatures) {
			int weight = defaultWeight;
			if (weights.size() > i) {
				weight = weights.get(i++);
			}

			buildTreeViewerColumn(treeViewer, layout,
				(eClass != null ? eClass : eStructuralFeature.getEContainingClass()),
				eStructuralFeature,
				weight);
		}
	}

	protected TreeViewerColumn buildTreeViewerColumn(TreeViewer treeViewer, Layout layout, EClass eClass,
			EStructuralFeature eStructuralFeature, int weight) {
		TreeViewerColumn viewerColumn = createTreeViewerColumn(treeViewer, eStructuralFeature);
		TreeColumn objectColumn = viewerColumn.getColumn();
		layoutHelper.adjustLayoutColumnData(layout, objectColumn, weight);
		objectColumn.setText(featureCaptionProvider.getText(eClass, eStructuralFeature));
		objectColumn.setResizable(true);
		return viewerColumn;
	}

	protected TreeViewerColumn createMainTreeViewerColumn(TreeViewer treeViewer, Layout layout, int weight) {
		TreeViewerColumn viewerColumn = new TreeViewerColumn(treeViewer, SWT.NONE);
		viewerColumn.setLabelProvider(delegatingColumnLabelProvider);
		TreeColumn objectColumn = viewerColumn.getColumn();
		layoutHelper.adjustLayoutColumnData(layout, objectColumn, weight);
		objectColumn.setResizable(true);
		return viewerColumn;
	}

	protected TreeViewerColumn createTreeViewerColumn(TreeViewer treeViewer, EStructuralFeature eStructuralFeature) {
		TreeViewerColumn treeViewerColumn = new TreeViewerColumn(treeViewer, SWT.NONE);
		treeViewerColumn.setLabelProvider(columnLabelProviderFactory.createColumnLabelProvider(eStructuralFeature));
		return treeViewerColumn;
	}
}
