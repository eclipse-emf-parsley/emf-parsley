/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.parsley.examples.library.Book;
import org.eclipse.emf.parsley.examples.library.Library;

public class TestAdapter extends AdapterImpl {
	
	public final List<Notification> notifications = new ArrayList<>();
	
	@Override
	public void notifyChanged(Notification n) {
		notifications.add(n);
	}

	public String notificationsToString() {
		return notifications.stream()
			.map(n -> "eventType: " + n.getEventType() + ", Notifier: " + eObjectToString(n.getNotifier()))
			.collect(Collectors.toList())
			.toString();
	}

	// Dispatch methods converted to instanceof checks
	public String eObjectToString(Object o) {
		if (o instanceof Book) {
			return eObjectToString((Book) o);
		} else if (o instanceof Library) {
			return eObjectToString((Library) o);
		} else {
			return o.toString();
		}
	}

	public String eObjectToString(Book b) {
		return b.getTitle();
	}

	public String eObjectToString(Library lib) {
		return lib.getName();
	}

	public void clearNotifications() {
		notifications.clear();
	}
}
