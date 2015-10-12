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

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;

/**
 * @author Lorenzo Bettini - Initial Contribution and API
 * 
 */
public class EmfParsleyUiUtil {

	protected EmfParsleyUiUtil() {

	}

	public static IStatusLineManager getStatusLineManager() {
		final IWorkbenchPart activePart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getActivePart();
		if (activePart == null) {
			return null;
		}

		IWorkbenchPartSite site = activePart.getSite();

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
}
