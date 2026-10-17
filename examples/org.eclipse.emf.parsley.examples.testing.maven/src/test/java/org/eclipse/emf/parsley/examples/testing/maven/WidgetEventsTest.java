package org.eclipse.emf.parsley.examples.testing.maven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.emf.parsley.junit4.ui.util.DisplayHelperTestRule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.junit.Rule;
import org.junit.Test;

/** Shows focused SWT event tests without SWTBot or a workbench. */
public class WidgetEventsTest {

	@Rule
	public DisplayHelperTestRule displayHelper = new DisplayHelperTestRule();

	@Test
	public void selectionListenerReceivesADispatchedSwtEvent() {
		var button = new Button(displayHelper.getShell(), SWT.PUSH);
		var calls = new AtomicInteger();
		button.addListener(SWT.Selection, event -> {
			assertSame(button, event.widget);
			calls.incrementAndGet();
		});

		// notifyListeners tests the listener contract, not a native mouse click.
		button.notifyListeners(SWT.Selection, new Event());
		assertEquals(1, calls.get());
	}

	@Test
	public void asyncExecRunsWhenTheDisplayDispatchesEvents() {
		var display = displayHelper.getShell().getDisplay();
		var label = new Label(displayHelper.getShell(), SWT.NONE);
		label.setText("Before");
		var completed = new AtomicBoolean();

		display.asyncExec(() -> {
			label.setText("After");
			completed.set(true);
		});

		// SWT event dispatch is deterministic here: no sleeps or workbench.
		// The guard prevents a broken example from spinning indefinitely.
		for (int i = 0; i < 100 && !completed.get(); i++) {
			display.readAndDispatch();
		}

		assertTrue("The posted UI callback was not dispatched", completed.get());
		assertEquals("After", label.getText());
	}
}
