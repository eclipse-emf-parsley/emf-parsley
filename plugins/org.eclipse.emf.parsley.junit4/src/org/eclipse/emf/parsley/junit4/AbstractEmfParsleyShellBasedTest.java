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
package org.eclipse.emf.parsley.junit4;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.parsley.junit4.ui.util.DisplayHelperTestRule;
import org.eclipse.emf.parsley.junit4.ui.util.RunnableWithResult;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.junit.Rule;

/**
 * Base class for Junit tests that require a Shell.
 * 
 * Inherited classes can use {@link #getShell()} and {@link #getDisplay()};
 * note that by default the returned {@link Shell} will NOT be visible: if your tests
 * need to check visibility of controls, then you need to call {@link Shell#open()}
 * explicitly.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public abstract class AbstractEmfParsleyShellBasedTest extends AbstractEmfParsleyTest {
	
	private static final Logger LOGGER = Logger.getLogger(AbstractEmfParsleyShellBasedTest.class);

	@Rule
	public DisplayHelperTestRule displayHelperTestRule = new DisplayHelperTestRule();

	protected Shell getShell() {
		return displayHelperTestRule.getShell();
	}

	/**
	 * Executes the passed {@link RunnableWithResult} in a {@link Display#syncExec(Runnable)},
	 * and returns the result; note that possible assertions within the runnable will NOT
	 * make a test fail: the result will be null, and the exception will be logged.
	 * 
	 * @param toExecute
	 * @return
	 */
	protected <T> T syncExec(final RunnableWithResult<T> toExecute) {
		final ArrayList<T> arrayList = new ArrayList<T>();
		getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				try {
					arrayList.add(toExecute.run());
				} catch (Throwable e) {
					LOGGER.error("Exception in runnable: " + e.getMessage(), e);
					arrayList.add(null);
				}
			}
		});
		return arrayList.get(0);
	}

	/**
	 * Executes the passed {@link RunnableWithResult} in a {@link Display#syncExec(Runnable)};
	 * In the runnable you can assert with Junit and if an assertion fails this method will
	 * make the test fail, propagating the failure.
	 * 
	 * @param toExecute
	 * @return
	 */
	protected void syncExecVoid(final Runnable toExecute) {
		final ArrayList<Throwable> arrayList = new ArrayList<Throwable>();
		getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				try {
					toExecute.run();
				} catch (Throwable e) {
					arrayList.add(e);
				}
			}
		});
		if (!arrayList.isEmpty()) {
			throw Exceptions.sneakyThrow(arrayList.get(0));
		}
	}

	protected Display getDisplay() {
		return displayHelperTestRule.getDisplay();
	}

	protected Image getEMFImageFromObject(final Object object) {
		return ExtendedImageRegistry.INSTANCE.getImage(object);
	}

	protected URI getEMFImage(final EObject eObject) {
		return URI.createURI(this.getEMFResourceLocator()
				.getImage("full/obj16/Item").toString()
				+ "#" + eObject.eClass().getName());
	}

	protected EMFEditPlugin getEMFResourceLocator() {
		return EMFEditPlugin.INSTANCE;
	}
}
