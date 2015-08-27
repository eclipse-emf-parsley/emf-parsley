package org.eclipse.emf.parsley.viewers;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;

import com.google.inject.Inject;

public class GenericColumnLabelProvider extends ColumnLabelProvider {
	
	@Inject
	private ILabelProvider labelProvider;
	
	@Override
	public String getText(Object element) {
		return labelProvider.getText(element);
	}

	@Override
	public Image getImage(Object element) {
		return labelProvider.getImage(element);
	}
}
