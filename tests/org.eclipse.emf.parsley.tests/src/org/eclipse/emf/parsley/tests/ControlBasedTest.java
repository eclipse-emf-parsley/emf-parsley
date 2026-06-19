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

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyControlBasedTest;
import org.eclipse.emf.parsley.junit4.util.LogAppenderTestRule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.junit.Rule;
import org.junit.Test;

/**
 * Check that failures in control based tests are caught and reported, and other tests.
 * 
 * @author Lorenzo Bettini
 */
public class ControlBasedTest extends AbstractEmfParsleyControlBasedTest {

	@Rule
	public final LogAppenderTestRule logAppender = new LogAppenderTestRule(AbstractEmfParsleyControlBasedTest.class);

	@Test
	public void testSyncExecInRealmSuccess() {
		assertTrue(syncExecInRealm(() -> {
			return true;
		}));
	}

	@Test
	public void testSyncExecInRealmWithFailureReturnsNull() {
		assertNull(syncExecInRealm(() -> {
			fail("intentional failure");
			return true;
		}));
		logAppender.assertContainsMessage("Exception in runnable: intentional failure");
	}

	@Test
	public void testEditingDomainIsNullByDefault() {
		assertNull(getEditingDomain());
	}

	@Test(expected = AssertionError.class)
	public void testNotACheckboxIsDetected() {
		// this should throw an AssertionError
		assertCheckbox(new Button(getShell(), SWT.None), false);
	}
}
