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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.junit.Test;

/**
 * Check that failures in shell based tests are caught and reported, and other tests.
 * 
 * @author Lorenzo Bettini
 */
public class ShellBasedTest extends AbstractEmfParsleyShellBasedTest {

	@Test
	public void testSyncExecSuccess() {
		assertTrue(syncExec(() -> {
			return true;
		}));
	}

	@Test
	public void testSyncExecWithException() {
		var throwable = assertThrows(AssertionError.class, () -> {
			syncExec(() -> {
				fail("intentional failure");
				return true;
			});
		});
		assertEquals("intentional failure", throwable.getMessage());
		throwable = assertThrows(AssertionError.class, () -> {
			syncExec(() -> {
				throw new NullPointerException("intentional NPE");
			});
		});
		assertTrue(
				"The cause of the AssertionError should be a NullPointerException: " + throwable.getCause(),
				throwable.getCause() instanceof NullPointerException);
	}

	@Test
	public void testSyncExecVoidWithException() {
		var throwable = assertThrows(AssertionError.class, () -> {
			syncExecVoid(() -> {
				fail("intentional failure");
			});
		});
		assertEquals("intentional failure", throwable.getMessage());
		throwable = assertThrows(AssertionError.class, () -> {
			syncExecVoid(() -> {
				throw new NullPointerException("intentional NPE");
			});
		});
		assertTrue(
				"The cause of the AssertionError should be a NullPointerException: " + throwable.getCause(),
				throwable.getCause() instanceof NullPointerException);
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
