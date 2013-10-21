package org.eclipse.emf.parsley.tests.util

import java.util.ArrayList
import org.eclipse.emf.common.notify.Notification
import org.eclipse.emf.common.notify.impl.AdapterImpl
import org.eclipse.emf.parsley.examples.library.Book
import org.eclipse.emf.parsley.examples.library.Library

class TestAdapter extends AdapterImpl {
	
	public val notifications = new ArrayList<Notification>()
	
	override notifyChanged(Notification n) {
		notifications += n
	}

	def notificationsToString() {
		notifications.map[
			"eventType: " + eventType + ", Notifier: "
			+ notifier.eObjectToString
		].toString
	}

	def dispatch eObjectToString(Object o) {
		o.toString
	}

	def dispatch eObjectToString(Book b) {
		b.title
	}

	def dispatch eObjectToString(Library lib) {
		lib.name
	}

	def clearNotifications() {
		notifications.clear
	}
}