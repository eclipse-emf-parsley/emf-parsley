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

import java.util.ArrayList;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.emf.parsley.junit4.ui.util.DisplayHelperTestRule;
import org.eclipse.emf.parsley.junit4.ui.util.RunnableWithResult;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Rule;

/**
 * A base class for tests that require a {@link Display} and a {@link Shell}.
 * 
 * The tests that derive from this class are meant to be run as Junit tests
 * (NOT as Plug-in Junit tests).
 * 
 * @author Lorenzo Bettini
 *
 */
public class AbstractShellBasedTest extends EmfParsleyAbstractTest {
	@Rule public DisplayHelperTestRule displayHelperTestRule = new DisplayHelperTestRule();
	
	protected Shell getShell() {
		return displayHelperTestRule.getShell();
	}

	protected <T> T syncExec(final RunnableWithResult<T> toExecute) {
		final ArrayList<T> arrayList = new ArrayList<T>();
		getDisplay().syncExec(new Runnable() {
			public void run() {
				try {
					arrayList.add(toExecute.run());
				} catch (Throwable e) {
					e.printStackTrace();
					arrayList.add(null);
				}
			}
		});
		return arrayList.get(0);
	}

	protected void syncExecVoid(final Runnable toExecute) throws Throwable {
		final ArrayList<Throwable> arrayList = new ArrayList<Throwable>();
		getDisplay().syncExec(new Runnable() {
			public void run() {
				try {
					toExecute.run();
				} catch (Throwable e) {
					arrayList.add(e);
				}
			}
		});
		if (!arrayList.isEmpty()) {
			throw arrayList.get(0);
		}
	}

	protected <T> T syncExecInRealm(final RunnableWithResult<T> toExecute) {
		final ArrayList<T> arrayList = new ArrayList<T>();
		getDisplay().syncExec(new Runnable() {
			public void run() {
				Realm.runWithDefault(SWTObservables.getRealm(Display.getDefault()), new Runnable() {
					public void run() {
						try {
							arrayList.add(toExecute.run());
						} catch (Throwable e) {
							e.printStackTrace();
							arrayList.add(null);
						}
					}
				});
			}});
		return arrayList.get(0);
	}
	
	protected Display getDisplay() {
		return displayHelperTestRule.getDisplay();
	}
}
