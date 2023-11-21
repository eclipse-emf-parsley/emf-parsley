/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.internal.editors.listeners;

import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Base class implementing all the methods as empty but from the one we'll
 * implement in the editor.
 *
 * @author Lorenzo Bettini - initial API and implementation
 *
 */
public abstract class PartListenerAdapter implements IPartListener {

	@Override
	public void partBroughtToTop(IWorkbenchPart p) {
		// Ignore.
	}

	@Override
	public void partClosed(IWorkbenchPart p) {
		// Ignore.
	}

	@Override
	public void partDeactivated(IWorkbenchPart p) {
		// Ignore.
	}

	@Override
	public void partOpened(IWorkbenchPart p) {
		// Ignore.
	}
}
