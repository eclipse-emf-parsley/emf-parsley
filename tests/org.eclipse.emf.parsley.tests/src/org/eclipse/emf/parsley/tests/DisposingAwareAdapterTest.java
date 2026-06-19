package org.eclipse.emf.parsley.tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.parsley.internal.listeners.DisposingAwareAdapter;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class DisposingAwareAdapterTest {

	private EClass o;

	private Widget widget;

	private Display display;

	@Before
	public void setup() {
		o = EcoreFactory.eINSTANCE.createEClass();
		widget = mock(Widget.class);
		display = mock(Display.class);
		when(widget.getDisplay()).thenReturn(display);
	}

	@Test
	public void testConstructorAddsAdapterDisposeRemovesIt() {
		var adapter = new DisposingAwareAdapter(o, widget, () -> {});
		var adapters = o.eAdapters();
		if (!adapters.contains(adapter))
			fail("adapter not found in " + adapters);
		adapter.dispose();
	}

	@Test
	public void testExecutesRunnableOnNotification() {
		var runnable = mock(Runnable.class);
		new DisposingAwareAdapter(o, widget, runnable);
		o.setName("Changed");
		verify(display).syncExec(runnable);
	}

	@Test
	public void testDoesNotExecuteRunnableWhenDisposing() {
		var runnable = mock(Runnable.class);
		var adapter = new DisposingAwareAdapter(o, widget, runnable);
		adapter.dispose();
		adapter.notifyChanged(null);
		verify(display, never()).syncExec(runnable);
	}

	@Test
	public void testDoesNotExecuteRunnableOnNotificationWhenWidgetIsDisposed() {
		var runnable = mock(Runnable.class);
		new DisposingAwareAdapter(o, widget, runnable);
		when(widget.isDisposed()).thenReturn(true);
		o.setName("Changed");
		verify(display, never()).syncExec(runnable);
	}
}
