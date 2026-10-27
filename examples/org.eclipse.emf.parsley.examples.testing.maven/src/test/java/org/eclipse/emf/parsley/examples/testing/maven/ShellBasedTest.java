package org.eclipse.emf.parsley.examples.testing.maven;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.junit.Test;

/**
 * Demonstrates the shell-based testing class without application components.
 * The inherited JUnit rule manages the SWT Display and Shell automatically.
 */
public class ShellBasedTest extends AbstractEmfParsleyShellBasedTest {

	@Test
	public void syncExecReturnsAWidgetValue() {
		// The base class supplies the SWT shell and UI-thread execution helpers.
		var label = syncExec(() -> {
			var widget = new Label(getShell(), SWT.NONE);
			widget.setText("Ready");
			return widget;
		});

		assertEquals("Ready", syncExec(label::getText));

		syncExecVoid(() -> label.setText("Updated"));
		assertEquals("Updated", syncExec(label::getText));
	}

	@Test
	public void execAndFlushPendingEventsProcessesAnAsyncUpdate() {
		var label = syncExec(() -> new Label(getShell(), SWT.NONE));
		syncExecVoid(() -> label.setText("Before"));

		// The supplier runs in the current context and returns its own value.
		// The inherited helper then dispatches queued SWT events.
		String result = execAndFlushPendingEvents(() -> {
			getDisplay().asyncExec(() -> label.setText("After"));
			return "update scheduled";
		});

		assertEquals("update scheduled", result);
		assertEquals("After", syncExec(label::getText));
	}
}
