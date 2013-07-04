/**
 * 
 */
package org.eclipse.emf.parsley.factories;


import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.ui.provider.AdapterMapCellLabelProvider;
import org.eclipse.emf.parsley.ui.provider.TableColumnLabelProvider;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Lorenzo Bettini
 * 
 * factory for ColumnLabelProvider
 * 
 */
public class ColumnLabelProviderFactory {

	@Inject
	protected Provider<ILabelProvider> labelProviderProvider;

	@Inject
	protected Provider<TableColumnLabelProvider> tableColumnProviderProvider;

	public ColumnLabelProvider createColumnLabelProvider(
			EStructuralFeature eStructuralFeature) {
		TableColumnLabelProvider columnProvider = tableColumnProviderProvider
				.get();
		columnProvider.seteStructuralFeature(eStructuralFeature);
		return columnProvider;
	}

	public CellLabelProvider createColumnLabelProvider(
			EStructuralFeature eStructuralFeature,
			IStructuredContentProvider contentProvider) {
		if (contentProvider instanceof ObservableListContentProvider) {
			return createColumnLabelProvider(eStructuralFeature,
					(ObservableListContentProvider) contentProvider);
		} else {
			return createColumnLabelProvider(eStructuralFeature);
		}
	}

	public CellLabelProvider createColumnLabelProvider(
			EStructuralFeature eStructuralFeature,
			ObservableListContentProvider cp) {
		TableColumnLabelProvider columnProvider = tableColumnProviderProvider
				.get();
		columnProvider.seteStructuralFeature(eStructuralFeature);

		IObservableMap observableMap = EMFProperties.value(eStructuralFeature)
				.observeDetail(cp.getKnownElements());
		// IObservableMap[] observableMaps=new IObservableMap[]{observableMap};
		return new AdapterMapCellLabelProvider(observableMap,
				labelProviderProvider.get());
	}

}
