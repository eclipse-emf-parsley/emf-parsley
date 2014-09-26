/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.util;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Lists;

/**
 * @author Lorenzo Bettini - Initial Contribution and API
 * 
 */
public class EmfParsleyUtil {
	public static IStatusLineManager getStatusLineManager() {
		try {
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
		} catch (NullPointerException e) {
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
	@SuppressWarnings("unchecked")
	public static Collection<Object> ensureCollection(Object contents) {
		if (contents == null)
			return Collections.emptyList();
		if (contents instanceof Collection<?>)
			return (Collection<Object>) contents;
		else if (contents instanceof Iterable<?>)
			return Lists.newArrayList((Iterable<?>)contents);
		else if (contents instanceof Iterator<?>)
			return Lists.newArrayList((Iterator<?>)contents);
		else
			return Collections.singleton(contents);
	}
}
