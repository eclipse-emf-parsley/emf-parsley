/**
 * 
 */
package org.eclipse.emf.parsley.views;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.util.EmfSelectionHelper;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import com.google.inject.Inject;

/**
 * An abstract View that reacts on selection (it should show something related
 * to an emf resource or object).
 * 
 * @author Lorenzo Bettini
 * 
 */
public abstract class AbstractOnSelectionView extends ViewPart {

	@Inject
	protected EmfSelectionHelper selectionHelper;

	// the listener we register with the selection service
	private ISelectionListener listener = new ISelectionListener() {
		public void selectionChanged(IWorkbenchPart sourcepart,
				ISelection selection) {
			// we ignore our own selections
			if (sourcepart != AbstractOnSelectionView.this) {
				updateOnSelection(sourcepart, selection);
			}
		}
	};

	protected abstract void updateOnSelection(IWorkbenchPart sourcepart,
			ISelection selection);

	@Override
	public void createPartControl(Composite parent) {
		getSite().getWorkbenchWindow().getSelectionService()
				.addSelectionListener(listener);
	}

	protected Object getFirstSelectedElement(ISelection selection) {
		return selectionHelper.getFirstSelectedElement(selection);
	}

	protected EObject getFirstSelectedEObject(ISelection selection) {
		return selectionHelper.getFirstSelectedEObject(selection);
	}

	public void dispose() {
		// important: We need do unregister our listener when the view is
		// disposed
		getSite().getWorkbenchWindow().getSelectionService()
				.removeSelectionListener(listener);
		super.dispose();
	}
}
