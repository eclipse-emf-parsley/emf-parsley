/**
 * 
 */
package org.eclipse.emf.parsley.tests.util;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;

/**
 * A fake class used for testing the case for non structured viewer
 * 
 * @author Lorenzo Bettini
 *
 */
public class NonStructuredViewer extends Viewer {

	private Object input;

	private IStructuredContentProvider contentProvider;

	private Control control;

	public NonStructuredViewer(Control control) {
		this.control = control;
	}

	@Override
	public Control getControl() {
		return control;
	}

	@Override
	public Object getInput() {
		return null;
	}

	@Override
	public ISelection getSelection() {
		return null;
	}

	@Override
	public void refresh() {
	}

	@Override
	public void setInput(Object input) {
		this.input = input;
		contentProvider.inputChanged(this, null, input);
		contentProvider.getElements(this.input);
	}

	@Override
	public void setSelection(ISelection selection, boolean reveal) {
	}

	public void setContentProvider(IStructuredContentProvider contentProvider) {
		this.contentProvider = contentProvider;
	}

}
