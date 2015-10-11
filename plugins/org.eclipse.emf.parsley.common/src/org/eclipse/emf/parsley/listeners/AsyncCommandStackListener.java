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

import java.util.EventObject;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.util.EmfCommandsUtil;
import org.eclipse.swt.widgets.Widget;

/**
 * A custom {@link CommandStackListener} that executes asynchronously in the UI
 * thread, delegating an {@link AsyncCommandStackListenerClient} appropriately.
 * 
 * Instances of this class should be created using the corresponding helper class.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class AsyncCommandStackListener implements CommandStackListener {

	private Widget widget;

	private AsyncCommandStackListenerClient client;

	private Resource resourceToObserve;

	/**
	 * The {@link Widget} is used to run in the UI thread.
	 * 
	 * @param widget
	 */
	public void setWidget(Widget widget) {
		this.widget = widget;
	}

	/**
	 * The client to notify.
	 * 
	 * @param client
	 */
	public void setClient(AsyncCommandStackListenerClient client) {
		this.client = client;
	}

	/**
	 * If set, the
	 * {@link AsyncCommandStackListenerClient#mostRecentCommandAffectsResource(Command)}
	 * is called only if the last command affects the resource.
	 * 
	 * @param resourceToObserve
	 */
	public void setResourceToObserve(Resource resourceToObserve) {
		this.resourceToObserve = resourceToObserve;
	}

	@Override
	public void commandStackChanged(final EventObject event) {
		widget.getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				Command mostRecentCommand = EmfCommandsUtil.mostRecentCommand(event);

				if (mostRecentCommand != null) {
					if (resourceToObserve == null
							|| EmfCommandsUtil.affectsResource(mostRecentCommand, resourceToObserve)) {
						client.mostRecentCommandAffectsResource(mostRecentCommand);
					}

					client.postCommandStackChanged(mostRecentCommand);
				}
			}
		});
	}

}
