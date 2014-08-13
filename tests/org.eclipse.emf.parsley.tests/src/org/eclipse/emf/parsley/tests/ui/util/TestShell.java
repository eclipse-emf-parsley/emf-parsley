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
package org.eclipse.emf.parsley.tests.ui.util;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * A {@link Shell} used for unit tests.
 * 
 * @author Lorenzo Bettini
 *
 */
public class TestShell {
	protected Shell shell;
	
	/**
	 * Open the window.
	 */
	public void open() {
		Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
	}

	/**
	 * Run the SWT event loop until this shell is disposed.
	 * @param display
	 */
	public void eventLoop(Display display) {
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public Shell getShell() {
		return shell;
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Test Shell");
	}
}
