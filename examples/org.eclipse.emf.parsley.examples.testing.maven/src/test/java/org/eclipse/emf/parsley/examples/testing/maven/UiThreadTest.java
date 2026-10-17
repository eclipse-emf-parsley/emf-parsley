package org.eclipse.emf.parsley.examples.testing.maven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.emf.parsley.junit4.ui.util.DisplayHelperTestRule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.junit.Rule;
import org.junit.Test;

/** Demonstrates real cross-thread SWT access without starting a workbench. */
public class UiThreadTest {

	@Rule
	public DisplayHelperTestRule displayHelper = new DisplayHelperTestRule();

	@Test
	public void backgroundThreadUsesSyncExecToUpdateAWidget() throws InterruptedException {
		var shell = displayHelper.getShell();
		var display = shell.getDisplay();
		var label = new Label(shell, SWT.NONE);
		label.setText("Waiting");

		Thread uiThread = Thread.currentThread();
		var callbackThread = new AtomicReference<Thread>();
		var failure = new AtomicReference<Throwable>();
		var finished = new CountDownLatch(1);

		// A background thread cannot call label.setText() directly.
		// syncExec blocks it until the display's thread runs the callback.
		Thread worker = new Thread(() -> {
			try {
				display.syncExec(() -> {
					callbackThread.set(Thread.currentThread());
					label.setText("Updated on the UI thread");
				});
			} catch (Throwable e) {
				failure.set(e);
			} finally {
				finished.countDown();
			}
		});
		worker.setDaemon(true);
		worker.start();

		// In a plain-JUnit SWT test, this thread owns the Display and must
		// dispatch its queued events; merely calling worker.join() deadlocks.
		long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(5);
		while (finished.getCount() != 0 && System.nanoTime() < deadline) {
			if (!display.readAndDispatch()) {
				Thread.yield();
			}
		}

		assertEquals(0, finished.getCount());
		worker.join(1000);
		assertNull("Background thread failed: " + failure.get(), failure.get());
		assertSame(uiThread, callbackThread.get());
		assertEquals("Updated on the UI thread", label.getText());
	}
}
