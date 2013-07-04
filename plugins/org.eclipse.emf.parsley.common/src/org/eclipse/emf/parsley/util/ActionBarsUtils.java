/**
 * 
 */
package org.eclipse.emf.parsley.util;

import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Utility methods to retrieve action bars from a part and site
 * 
 * @author Lorenzo Bettini
 * 
 */
public class ActionBarsUtils {

	public static IActionBars getActionBars(IWorkbenchPart part) {
		if (part instanceof IViewPart) {
			return ((IViewPart) part).getViewSite().getActionBars();
		} else if (part instanceof IEditorPart) {
			return ((IEditorPart) part).getEditorSite().getActionBars();
		}
		return null;
	}
}
