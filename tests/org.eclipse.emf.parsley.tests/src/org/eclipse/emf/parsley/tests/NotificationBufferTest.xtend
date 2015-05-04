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
package org.eclipse.emf.parsley.tests

import org.eclipse.emf.parsley.edit.NotificationBuffer
import org.eclipse.emf.parsley.tests.util.TestAdapter
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule

class NotificationBufferTest {

	protected NotificationBuffer buffer = null
	
	protected TestAdapter adapter1

	protected TestAdapter adapter2
	
	@Rule public extension EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule()
	
	@Before
	def void setUp() {
		buffer = new NotificationBuffer(library)
		adapter1 = new TestAdapter
		adapter2 = new TestAdapter
		library.eAdapters += adapter1
		library.eAdapters += adapter2
	}
	
	@Test
	def public void checkAdaptersAreRemovedAndRestored() {
		2.assertAdaptersSize
		buffer.startBuffering
		1.assertAdaptersSize // NotificationBuffer is set as adapter
		buffer.stopBuffering
		2.assertAdaptersSize
	}

	@Test
	def public void checkNotificationsAreBuffered() {
		clearTestAdaptersNotifications
		buffer.startBuffering
		addTestBook("1") // the test adapters will receive no notification
		"[eventType: 3, eventType: 3]".assertBufferedNotifications
	}

	@Test
	def public void checkNotificationsStartAndStopBuffering() {
		clearTestAdaptersNotifications
		addTestBook("1")
		"[eventType: 3, Notifier: TEST, eventType: 3, Notifier: TEST]".assertNotificationsInTestAdapters
		buffer.startBuffering
		clearTestAdaptersNotifications()
		addTestBook("1") // the test adapters will receive no notification
		"[]".assertNotificationsInTestAdapters
		buffer.stopBuffering
		"[]".assertNotificationsInTestAdapters
		addTestBook("1") // the test adapters will notifications again
		"[eventType: 3, Notifier: TEST, eventType: 3, Notifier: TEST]".assertNotificationsInTestAdapters
	}

	@Test
	def public void checkPropagateBufferedNotifications() {
		clearTestAdaptersNotifications
		"[]".assertNotificationsInTestAdapters
		buffer.startBuffering
		// notification about adapter removed
		"[eventType: 8, Notifier: TEST]".assertNotificationsInTestAdapters
		clearTestAdaptersNotifications
		
		addTestBook("1") // the test adapters will receive no notification
		"[]".assertNotificationsInTestAdapters
		
		buffer.stopBuffering
		"[]".assertNotificationsInTestAdapters
		
		buffer.propagateBufferedNotifications
		"[eventType: 3, Notifier: TEST, eventType: 3, Notifier: TEST, eventType: 8, Notifier: TEST]".assertNotificationsInTestAdapters
		// it also contains notification about NotificationBuffer has been added
		// as adapter
	}
	
	def private assertAdaptersSize(int expected) {
		expected.assertEquals(library.eAdapters.size)
	}

	def private assertNotificationsInTestAdapters(CharSequence expected) {
		expected.toString.assertEquals(adapter1.notificationsToString)
		expected.toString.assertEquals(adapter2.notificationsToString)
	}

	def private assertBufferedNotifications(CharSequence expected) {
		expected.toString.assertEquals(buffer.notifications.map[
			"eventType: " + eventType].toString)
	}
	
	private def clearTestAdaptersNotifications() {
		adapter1.clearNotifications
		adapter2.clearNotifications
	}

}