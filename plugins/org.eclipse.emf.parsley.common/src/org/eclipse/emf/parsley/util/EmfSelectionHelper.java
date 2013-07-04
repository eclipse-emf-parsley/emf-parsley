/**
 * 
 */
package org.eclipse.emf.parsley.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Utility methods for retrieving EMF stuff from an {@link ISelection}
 * 
 * @author Lorenzo Bettini
 * 
 */
public class EmfSelectionHelper {

	public Object getFirstSelectedElement(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ss = (IStructuredSelection) selection;
			return ss.getFirstElement();
		}
		return null;
	}

	public EObject getFirstSelectedEObject(ISelection selection) {
		Object selected = getFirstSelectedElement(selection);
		if (selected instanceof EObject) {
			return (EObject) selected;
		}
		return null;
	}
}
