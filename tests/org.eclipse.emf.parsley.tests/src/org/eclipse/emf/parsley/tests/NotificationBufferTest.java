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
package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.parsley.edit.NotificationBuffer;
import org.eclipse.emf.parsley.tests.util.EmfParsleyFixturesAndUtilitiesTestRule;
import org.eclipse.emf.parsley.tests.util.TestAdapter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class NotificationBufferTest {

	protected NotificationBuffer buffer = null;

	protected TestAdapter adapter1;

	protected TestAdapter adapter2;

	@Rule
	public EmfParsleyFixturesAndUtilitiesTestRule fixtures = new EmfParsleyFixturesAndUtilitiesTestRule();

	@Before
	public void setUp() {
		buffer = new NotificationBuffer(fixtures.getLibrary());
		adapter1 = new TestAdapter();
		adapter2 = new TestAdapter();
		fixtures.getLibrary().eAdapters().add(adapter1);
		fixtures.getLibrary().eAdapters().add(adapter2);
	}

	@Test
	public void checkAdaptersAreRemovedAndRestored() {
		assertAdaptersSize(2);
		buffer.startBuffering();
		assertAdaptersSize(1); // NotificationBuffer is set as adapter
		buffer.stopBuffering();
		assertAdaptersSize(2);
	}

	@Test
	public void checkNotificationsAreBuffered() {
		clearTestAdaptersNotifications();
		buffer.startBuffering();
		fixtures.addTestBook("1"); // the test adapters will receive no notification
		assertBufferedNotifications("[eventType: 3, eventType: 3]");
	}

	@Test
	public void checkNotificationsStartAndStopBuffering() {
		clearTestAdaptersNotifications();
		fixtures.addTestBook("1");
		assertNotificationsInTestAdapters("[eventType: 3, Notifier: TEST, eventType: 3, Notifier: TEST]");
		buffer.startBuffering();
		clearTestAdaptersNotifications();
		fixtures.addTestBook("1"); // the test adapters will receive no notification
		assertNotificationsInTestAdapters("[]");
		buffer.stopBuffering();
		assertNotificationsInTestAdapters("[]");
		fixtures.addTestBook("1"); // the test adapters will notifications again
		assertNotificationsInTestAdapters("[eventType: 3, Notifier: TEST, eventType: 3, Notifier: TEST]");
	}

	@Test
	public void checkPropagateBufferedNotifications() {
		clearTestAdaptersNotifications();
		assertNotificationsInTestAdapters("[]");
		buffer.startBuffering();
		// notification about adapter removed
		assertNotificationsInTestAdapters("[eventType: 8, Notifier: TEST]");
		clearTestAdaptersNotifications();

		fixtures.addTestBook("1"); // the test adapters will receive no notification
		assertNotificationsInTestAdapters("[]");

		buffer.stopBuffering();
		assertNotificationsInTestAdapters("[]");

		buffer.propagateBufferedNotifications();
		assertNotificationsInTestAdapters("[eventType: 3, Notifier: TEST, eventType: 3, Notifier: TEST, eventType: 8, Notifier: TEST]");
		// it also contains notification about NotificationBuffer has been added
		// as adapter
	}

	private void assertAdaptersSize(int expected) {
		assertEquals(expected, fixtures.getLibrary().eAdapters().size());
	}

	private void assertNotificationsInTestAdapters(String expected) {
		assertEquals(expected, adapter1.notificationsToString());
		assertEquals(expected, adapter2.notificationsToString());
	}

	private void assertBufferedNotifications(String expected) {
		String actual = buffer.getNotifications().stream()
				.map(n -> "eventType: " + n.getEventType())
				.toList()
				.toString();
		assertEquals(expected, actual);
	}

	private void clearTestAdaptersNotifications() {
		adapter1.clearNotifications();
		adapter2.clearNotifications();
	}

}
