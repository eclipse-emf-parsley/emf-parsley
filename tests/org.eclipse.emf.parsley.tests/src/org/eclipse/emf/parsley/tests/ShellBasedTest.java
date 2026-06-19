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
package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.eclipse.emf.parsley.junit4.util.LogAppenderTestRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * Check that failures in shell based tests are caught and reported, and other tests.
 * 
 * @author Lorenzo Bettini
 */
public class ShellBasedTest extends AbstractEmfParsleyShellBasedTest {

	@Rule
	public final LogAppenderTestRule logAppender = new LogAppenderTestRule(AbstractEmfParsleyShellBasedTest.class);

	@Test
	public void testSyncExecSuccess() {
		assertTrue(syncExec(() -> {
			return true;
		}));
	}

	@Test
	public void testSyncExecWithFailureReturnsNull() {
		assertNull(syncExec(() -> {
			fail("intentional failure");
			return true;
		}));
		logAppender.assertContainsMessage("Exception in runnable: intentional failure");
	}

	@Test(expected = AssertionError.class)
	public void testSyncExecVoidWithFailureMakesTheTestFail() {
		syncExecVoid(() -> {
			fail("intentional failure");
		});
	}

	@Test
	public void testSyncExecVoidSuccess() {
		syncExecVoid(() -> {
			assertTrue(true);
		});
	}

	@Test
	public void testShellIsClosedByDefault() {
		assertFalse("Shell should not be visible.", getShell().isVisible());
	}
}
