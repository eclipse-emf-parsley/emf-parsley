/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests

import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyControlBasedTest
import org.eclipse.emf.parsley.junit4.util.LogAppenderTestRule
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.SWT

/**
 * Check that failures in control based tests are caught and reported, and other tests.
 * 
 * @author Lorenzo Bettini
 */
class ControlBasedTest extends AbstractEmfParsleyControlBasedTest {
	
	@Rule public val LogAppenderTestRule logAppender = new LogAppenderTestRule(AbstractEmfParsleyControlBasedTest);

	@Test
	def void testSyncExecInRealmSuccess() {
		syncExecInRealm[
			return true
		].assertTrue
	}
	
	@Test
	def void testSyncExecInRealmWithFailureReturnsNull() {
		syncExecInRealm[
			fail("intentional failure")
			return true
		].assertNull
		logAppender.assertContainsMessage("Exception in runnable: intentional failure")
	}

	@Test
	def void testEditingDomainIsNullByDefault() {
		editingDomain.assertNull
	}

	@Test(expected=AssertionError)
	def void testNotACheckboxIsDetected() {
		// this should throw an AssertionError
		assertCheckbox(new Button(shell, SWT.None), false)
	}
}