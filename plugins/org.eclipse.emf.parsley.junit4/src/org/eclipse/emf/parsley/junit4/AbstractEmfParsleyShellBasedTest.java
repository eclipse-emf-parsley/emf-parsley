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

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.parsley.junit4.ui.util.DisplayHelperTestRule;
import org.eclipse.emf.parsley.junit4.ui.util.RunnableWithResult;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TreeItem;
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

	protected static int TAB_INDENT = 2;

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
		final ArrayList<T> arrayList = new ArrayList<>();
		getDisplay().syncExec(() -> {
			try {
				arrayList.add(toExecute.run());
			} catch (Throwable e) {
				LOGGER.error("Exception in runnable: " + e.getMessage(), e);
				arrayList.add(null);
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
		final ArrayList<Throwable> arrayList = new ArrayList<>();
		getDisplay().syncExec(() -> {
			try {
				toExecute.run();
			} catch (Throwable e) {
				arrayList.add(e);
			}
		});
		if (!arrayList.isEmpty()) {
			throw Exceptions.sneakyThrow(arrayList.get(0));
		}
	}

	protected Display getDisplay() {
		return displayHelperTestRule.getDisplay();
	}

	/**
	 * Executes the passed lambda and flushes the pending events before returning.
	 *
	 * @see #flushPendingEvents()
	 *
	 * @param toExecute
	 * @return
	 */
	protected <T> T execAndFlushPendingEvents(final RunnableWithResult<T> toExecute) {
		T result = toExecute.run();
		flushPendingEvents();
		return result;
	}

	/**
	 * Makes sure that all pending events are dispatched and executed; this is
	 * crucial if you want to test viewer updates that are executed with
	 * {@link Display#asyncExec(Runnable)}: for example, you modify an EMF
	 * model, you flush pending events, and you check that a viewer has been
	 * updated as expected.
	 */
	protected void flushPendingEvents() {
		displayHelperTestRule.flushPendingEvents();
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

	/**
	 * A string representation of the table is built; this string representation is then compared
	 * with the expected representation.
	 *
	 * @param tableViewer
	 * @param expected
	 */
	protected void assertTable(TableViewer tableViewer, CharSequence expected) {
		assertEquals(expected.toString().trim().replace("\r", ""),
				tableItemsRepresentation(tableViewer.getTable().getItems()).trim().replace("\r", ""));
	}

	protected String tableItemsRepresentation(TableItem[] items) {
		StringBuffer buffer = new StringBuffer();
		for (TableItem item : items) {
			buffer.append(item.getText().toString() + "\n");
		}
		return buffer.toString();
	}

	/**
	 * A string representation of the tree is built where children are indented
	 * of TAB_INDENT number of tabs; this string representation is then compared
	 * with the expected representation.
	 *
	 * @param treeViewer
	 * @param expected
	 */
	protected void assertAllLabels(TreeViewer treeViewer, CharSequence expected) {
		assertEquals(expected.toString().trim().replace("\r", ""),
				treeItemsRepresentation(getTreeItems(treeViewer)).trim().replace("\r", ""));
	}

	protected TreeItem[] getTreeItems(TreeViewer treeViewer) {
		return treeViewer.getTree().getItems();
	}

	protected String treeItemsRepresentation(TreeItem[] items) {
		StringBuffer buffer = new StringBuffer();
		// skip the root node
		treeItemsRepresentation(items, buffer, 0);
		return buffer.toString();
	}

	private void treeItemsRepresentation(TreeItem[] items, StringBuffer buffer, int tabs) {
		for (TreeItem item : items) {
			treeItemRepresentation(item, buffer, tabs);
		}
	}

	private void treeItemRepresentation(TreeItem item, StringBuffer buffer, int tabs) {
		for (int i = 0; i < tabs; ++i) {
			buffer.append(" ");
		}
		buffer.append(item.getText().toString() + "\n");
		treeItemsRepresentation(item.getItems(), buffer, tabs + TAB_INDENT);
	}
}
