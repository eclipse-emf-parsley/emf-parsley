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

import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest
import org.eclipse.emf.parsley.junit4.util.LogAppenderTestRule
import org.junit.Rule
import org.junit.Test

import static extension org.junit.Assert.*

/**
 * Check that failures in shell based tests are caught and reported.
 * 
 * @author Lorenzo Bettini
 */
class ShellBasedTest extends AbstractEmfParsleyShellBasedTest {
	
	@Rule public val LogAppenderTestRule logAppender = new LogAppenderTestRule(AbstractEmfParsleyShellBasedTest);

	@Test
	def void testSyncExecSuccess() {
		syncExec[
			return true
		].assertTrue
	}
	
	@Test
	def void testSyncExecWithFailureReturnsNull() {
		syncExec[
			fail("intentional failure")
			return true
		].assertNull
		logAppender.assertContainsMessage("Exception in runnable: intentional failure")
	}

	@Test(expected=AssertionError)
	def void testSyncExecVoidWithFailureMakesTheTestFail() {
		syncExecVoid[
			fail("intentional failure")
		]
	}

	@Test
	def void testSyncExecVoidSuccess() {
		syncExecVoid[
			assertTrue(true)
		]
	}

	@Test
	def void testShellIsClosedByDefault() {
		assertFalse("Shell should not be visible.", shell.isVisible)
	}
}