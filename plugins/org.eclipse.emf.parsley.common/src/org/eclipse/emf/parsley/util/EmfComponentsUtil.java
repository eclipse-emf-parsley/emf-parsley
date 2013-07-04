package org.eclipse.emf.parsley.util;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Lists;

/**
 * @author bettini
 * 
 */
public class EmfComponentsUtil {
	public static IStatusLineManager getStatusLineManager() {
		IWorkbenchPartSite site = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActivePart()
				.getSite();

		if (site instanceof IViewSite) {
			return getStatusLineManager(((IViewSite) site).getActionBars());
		} else if (site instanceof IEditorSite) {
			return getStatusLineManager(((IEditorSite) site).getActionBars());
		} else {
			return null;
		}
	}

	private static IStatusLineManager getStatusLineManager(IActionBars actionBars) {
		return actionBars.getStatusLineManager();
	}
	
	/**
	 * Given the passed object it ensures that it is a {@link Collection};
	 * if it is not, it returns a singleton {@link Collection}; if it is null
	 * it returns an empty {@link Collection}.
	 * @param contents
	 * @return
	 */
	public static Collection<?> ensureCollection(Object contents) {
		if (contents == null)
			return Collections.emptyList();
		if (contents instanceof Collection<?>)
			return (Collection<?>) contents;
		else if (contents instanceof Iterable<?>)
			return Lists.newArrayList((Iterable<?>)contents);
		else
			return Collections.singleton(contents);
	}
}
