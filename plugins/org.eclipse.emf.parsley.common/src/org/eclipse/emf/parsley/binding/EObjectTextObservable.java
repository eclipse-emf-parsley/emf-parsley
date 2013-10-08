/**
 * 
 */
package org.eclipse.emf.parsley.binding;

import org.eclipse.core.databinding.observable.value.AbstractObservableValue;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Text;

/**
 * To perform data binding between a Text and an EObject (representing
 * a reference)
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class EObjectTextObservable extends AbstractObservableValue {
	
	private ILabelProvider labelProvider;
	
	private Text text;
	
	private EObject current;

	public EObjectTextObservable(ILabelProvider labelProvider, Text text) {
		super();
		this.labelProvider = labelProvider;
		this.text = text;
	}

	public Object getValueType() {
		return null;
	}

	@Override
	protected Object doGetValue() {
		return current;
	}
	
	@Override
	protected void doSetValue(Object value) {
		if (!(value instanceof EObject))
			return;
		
		current = (EObject) value;
		
		if (current != null) {
			text.setText(labelProvider.getText(current));
		} else {
			text.setText("");
		}
	}

}