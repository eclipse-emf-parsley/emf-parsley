/**
 * 
 */
package org.eclipse.emf.parsley.views;

import org.eclipse.emf.parsley.widgets.FormDetailComposite;
import org.eclipse.swt.SWT;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class OnSelectionReadOnlyFormView extends OnSelectionFormView {

	@Override
	protected FormDetailComposite createFormDetailComposite() {
		return formFactory.createFormDetailReadOnlyComposite(
				parent, SWT.NONE);
	}
}
