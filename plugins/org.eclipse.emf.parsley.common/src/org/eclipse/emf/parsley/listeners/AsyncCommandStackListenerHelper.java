/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.listeners;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Widget;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Helper class for instantiating via injection a
 * {@link AsyncCommandStackListener}, setting it as a listener in the command
 * stack and adding a {@link AsyncCommandStackListenerClient}.
 *
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class AsyncCommandStackListenerHelper {

	@Inject
	private Provider<AsyncCommandStackListener> provider;

	/**
	 * Sets up an {@link AsyncCommandStackListener} observing any
	 * {@link Resource}'s involved in commands executed in the stack of the
	 * given {@link EditingDomain}.
	 *
	 * @param editingDomain
	 * @param widget
	 * @param client
	 */
	public void addCommandStackListener(EditingDomain editingDomain, Widget widget,
			AsyncCommandStackListenerClient client) {
		addCommandStackListener(editingDomain, widget, client, null);
	}

	/**
	 * Sets up an {@link AsyncCommandStackListener} observing a specific
	 * {@link Resource}'s involved in commands executed in the stack of the
	 * given {@link EditingDomain}.
	 *
	 * @param editingDomain
	 * @param widget
	 * @param client
	 * @param resourceToObserve
	 */
	public void addCommandStackListener(EditingDomain editingDomain, Widget widget,
			AsyncCommandStackListenerClient client, Resource resourceToObserve) {
		AsyncCommandStackListener listener = provider.get();
		listener.setWidget(widget);
		listener.setClient(client);
		listener.setResourceToObserve(resourceToObserve);
		editingDomain.getCommandStack().addCommandStackListener(listener);
	}
}
