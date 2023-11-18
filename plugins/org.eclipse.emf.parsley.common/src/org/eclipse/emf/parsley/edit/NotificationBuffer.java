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

package org.eclipse.emf.parsley.edit;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * Buffers notifications.
 *
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class NotificationBuffer extends AdapterImpl {

	protected List<Notification> notifications = new ArrayList<>();

	protected List<Adapter> savedAdapters = new ArrayList<>();

	protected EObject eObject;

	public NotificationBuffer(EObject eObject) {
		this.eObject = eObject;
	}

	public void startBuffering() {
		EList<Adapter> eAdapters = eObject.eAdapters();
		for (Adapter a : eAdapters) {
			savedAdapters.add(a);
		}
		for (Adapter a : savedAdapters) {
			eAdapters.remove(a);
		}
		// now we listen to notifications
		eObject.eAdapters().add(this);
	}

	public void stopBuffering() {
		eObject.eAdapters().remove(this);
		eObject.eAdapters().addAll(savedAdapters);
	}

	@Override
	public void notifyChanged(Notification msg) {
		notifications.add(msg);
	}

	public void propagateBufferedNotifications() {
		for (Notification n : notifications) {
			eObject.eNotify(n);
		}
		notifications.clear();
	}

	public List<Notification> getNotifications() {
		return notifications;
	}
}
