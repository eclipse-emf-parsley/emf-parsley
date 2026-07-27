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

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

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

	protected static int TAB_INDENT = 2;

	@Rule
	public DisplayHelperTestRule displayHelperTestRule = new DisplayHelperTestRule();

	protected Shell getShell() {
		return displayHelperTestRule.getShell();
	}

	/**
	 * Executes the specified {@link Supplier} synchronously on the SWT display
	 * thread and returns its result.
	 * <p>
	 * The supplier may contain JUnit assertions. An assertion failure causes the
	 * calling test to fail. Runtime exceptions thrown by the supplier are
	 * propagated as {@link AssertionError}s.
	 *
	 * @param toExecute the operation to execute
	 * @return the result produced by the operation
	 */
	protected <T> T syncExec(final Supplier<T> toExecute) {
		var result = new AtomicReference<T>();
		var failure = new AtomicReference<Exception>();

		getDisplay().syncExec(() -> {
			try {
				result.setPlain(toExecute.get());
			} catch (Exception e) {
				failure.setPlain(e);
			}
		});

		var exception = failure.getPlain();
		if (exception != null) {
			throw new AssertionError("Failure in SWT display thread", exception);
		}

		return result.getPlain();
	}

	/**
	 * Executes the specified {@link Runnable} synchronously on the SWT display
	 * thread.
	 * <p>
	 * The runnable may contain JUnit assertions. An assertion failure causes the
	 * calling test to fail. Runtime exceptions thrown by the runnable are
	 * propagated as {@link AssertionError}s.
	 *
	 * @param toExecute the operation to execute
	 */
	protected void syncExecVoid(final Runnable toExecute) {
		var failure = new AtomicReference<Exception>();

		getDisplay().syncExec(() -> {
			try {
				toExecute.run();
			} catch (Exception e) {
				failure.setPlain(e);
			}
		});

		var exception = failure.getPlain();
		if (exception != null) {
			throw new AssertionError("Failure in SWT display thread", exception);
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
		var buffer = new StringBuilder();
		for (TableItem item : items) {
			buffer.append(item.getText()).append("\n");
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
		var buffer = new StringBuilder();
		// skip the root node
		treeItemsRepresentation(items, buffer, 0);
		return buffer.toString();
	}

	private void treeItemsRepresentation(TreeItem[] items, StringBuilder buffer, int tabs) {
		for (TreeItem item : items) {
			treeItemRepresentation(item, buffer, tabs);
		}
	}

	private void treeItemRepresentation(TreeItem item, StringBuilder buffer, int tabs) {
		for (int i = 0; i < tabs; ++i) {
			buffer.append(" ");
		}
		buffer.append(item.getText() + "\n");
		treeItemsRepresentation(item.getItems(), buffer, tabs + TAB_INDENT);
	}
}
