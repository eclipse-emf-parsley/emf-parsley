/*******************************************************************************
 * Copyright (c) 2018 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.internal.listeners;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Widget;

/**
 * An EMF adapter that can be disposed and takes into consideration the
 * disposing state of a given {@link Widget}; executes the specified
 * {@link Runnable} when it receives an EMF {@link Notification} in the UI
 * thread, synchronously if the widget is not disposed.
 * 
 * @author Lorenzo Bettini - initial API and implementation
 *
 */
public class DisposingAwareAdapter extends AdapterImpl {

	private boolean disposing = false;
	private Widget widget;
	private Runnable runnable;

	public DisposingAwareAdapter(EObject model, Widget widget, Runnable runnable) {
		this.widget = widget;
		this.runnable = runnable;
		model.eAdapters().add(this);
	}

	public void dispose() {
		disposing = true;
		getTarget().eAdapters().remove(this);
	}

	@Override
	public void notifyChanged(Notification msg) {
		if (!widget.isDisposed() && !disposing) {
			widget.getDisplay().syncExec(runnable);
		}
	}

}
