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
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.emf.parsley.tests.ui.util.RunnableWithResult;
import org.eclipse.emf.parsley.tests.ui.util.TestShell;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * A base class for tests that require a {@link Display} and a {@link Shell}.
 * 
 * The tests that derived from this class are meant to be run as Junit tests
 * (NOT as Plug-in Junit tests).
 * 
 * @author Lorenzo Bettini
 *
 */
public class AbstractShellBasedTest extends EmfParsleyAbstractTest {
	private static Thread uiThread;
	
	private static Shell shell;
	
	private final static CyclicBarrier swtBarrier = new CyclicBarrier(2);
	
	@BeforeClass
	public static void setupApp() {
		if (uiThread == null) {
			uiThread = new Thread(new Runnable() {

				public void run() {
					try {	
						while (true) {
							// open and layout the shell
							TestShell window = new TestShell();
							window.open();
							shell = window.getShell();

							// wait for the test setup
							swtBarrier.await();
	
							// run the event loop
							window.eventLoop(Display.getDefault());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			uiThread.setDaemon(true);
			uiThread.start();
		}
	}

	@Before
	public final void setupShell() throws InterruptedException, BrokenBarrierException {
		// synchronize with the thread opening the shell
		swtBarrier.await();
	}

	@After
	public void closeShell() throws InterruptedException {
		// close the shell
		getDisplay().syncExec(new Runnable() {
			public void run() {
				shell.close();
			}
		});
	}

	protected Shell getShell() {
		return shell;
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
		return Display.getDefault();
	}
}
