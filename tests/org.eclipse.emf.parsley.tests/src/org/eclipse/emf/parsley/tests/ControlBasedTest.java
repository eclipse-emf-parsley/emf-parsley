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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.databinding.observable.Realm;
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
		assertNotNull(syncExecInRealm(Realm::getDefault));
	}

	@Test
	public void testSyncExecInRealmWithException() {
		var throwable = assertThrows(AssertionError.class, () ->
			syncExecInRealm(() -> {
				throw new NullPointerException("intentional NPE");
			})
		);

		assertTrue(
				"The cause of the AssertionError should be a NullPointerException: "
						+ throwable.getCause(),
				throwable.getCause() instanceof NullPointerException);
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
