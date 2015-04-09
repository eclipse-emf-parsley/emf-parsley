/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.ui.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * A Junit {@link TestRule} for executing tests that need a {@link Display}
 * and a {@link Shell} as plain Junit tests (that is, not as Plug-in tests).
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class DisplayHelperTestRule implements TestRule {

	private boolean displayOwner;
	private Display display;
	
	private Shell shell;

	public DisplayHelperTestRule() {
	}

	public Display getDisplay() {
		if (display == null) {
			displayOwner = Display.getCurrent() == null;
			display = Display.getDefault();
		}
		return display;
	}

	public Shell getShell() {
		if (shell == null) {
			shell = createShell();
		}

		return shell;
	}
	
	public Shell createShell() {
		return createShell(SWT.NONE);
	}

	public Shell createShell(int style) {
		return new Shell(getDisplay(), style);
	}

	public void flushPendingEvents() {
		while (Display.getCurrent() != null
				&& !Display.getCurrent().isDisposed()
				&& Display.getCurrent().readAndDispatch()) {
		}
	}

	public void dispose() {
		flushPendingEvents();
		disposeShell();
		disposeDisplay();
	}

	public Statement apply(final Statement base, Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				try {
					base.evaluate();
				} finally {
					dispose();
				}
			}
		};
	}

	private void disposeShell() {
		if (shell != null) {
			shell.dispose();
		}
	}

	private void disposeDisplay() {
		if (display != null && displayOwner) {
			if (display.isDisposed()) {
				display.dispose();
			}
			display = null;
		}
	}
}
