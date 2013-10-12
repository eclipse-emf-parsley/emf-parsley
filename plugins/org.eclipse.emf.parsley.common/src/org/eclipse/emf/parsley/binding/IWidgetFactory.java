/**
 * 
 */
package org.eclipse.emf.parsley.binding;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * An abstraction for creating {@link Label}, {@link Text}, {@link Button}, etc.
 * It will be implemented creating standard controls and form controls.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public interface IWidgetFactory {

	Label createLabel(String text);
	
	Label createLabel(Composite parent, String text);

	Button createButton(String text, int style);

	Button createButton(Composite parent, String text, int style);
	
	Text createText(String text);

	Text createText(String text, int style);

	Text createText(Composite parent, String text);
	
	Text createText(Composite parent, String text, int style);

	ComboViewer createComboViewer(int style);

	ComboViewer createComboViewer(Composite parent, int style);
}
