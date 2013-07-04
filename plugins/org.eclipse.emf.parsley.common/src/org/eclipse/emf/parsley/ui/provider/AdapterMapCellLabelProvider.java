package org.eclipse.emf.parsley.ui.provider;

import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.jface.databinding.viewers.ObservableMapCellLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

public class AdapterMapCellLabelProvider extends ObservableMapCellLabelProvider {

	ILabelProvider wrappedLabelProvider;
	
	public AdapterMapCellLabelProvider(IObservableMap attributeMap, ILabelProvider wrappedLabelProvider) {
		super(attributeMap);
		this.wrappedLabelProvider=wrappedLabelProvider;
	}
	
	
	@Override
	public void update(ViewerCell cell) {
		Object element = cell.getElement();
		Object value = attributeMaps[0].get(element);
		//TODO Da finire
		cell.setText(wrappedLabelProvider.getText(value)); 
//		cell.setImage(wrappedLabelProvider.getImage(element)); 
	}
	

}
