package org.eclipse.emf.parsley.tests

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.parsley.internal.listeners.DisposingAwareAdapter
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Widget
import org.junit.Before
import org.junit.Test

import static org.hamcrest.CoreMatchers.*
import static org.hamcrest.MatcherAssert.assertThat
import static org.mockito.Mockito.*

class DisposingAwareAdapterTest {

	var EClass o

	var Widget widget

	var Display display

	var Runnable runnable

	@Before
	def void setup() {
		o = EcoreFactory.eINSTANCE.createEClass
		widget = mock(Widget)
		display = mock(Display)
		when(widget.getDisplay).thenReturn(display)
		runnable = mock(Runnable)
	}

	@Test
	def void testConstructorAddsAdapterDisposeRemovesIt() {
		val adapter = new DisposingAwareAdapter(o, widget, [])
		assertThat(o.eAdapters, hasItem(adapter))
		adapter.dispose
	}

	@Test
	def void testExecutesRunnableOnNotification() {
		val runnable = mock(Runnable)
		new DisposingAwareAdapter(o, widget, runnable)
		o.name = "Changed"
		verify(display).syncExec(runnable)
	}

	@Test
	def void testDoesNotExecuteRunnableWhenDisposing() {
		val runnable = mock(Runnable)
		val adapter = new DisposingAwareAdapter(o, widget, runnable)
		adapter.dispose
		adapter.notifyChanged(null)
		verify(display, never).syncExec(runnable)
	}

	@Test
	def void testDoesNotExecuteRunnableOnNotificationWhenWidgetIsDisposed() {
		val runnable = mock(Runnable)
		new DisposingAwareAdapter(o, widget, runnable)
		when(widget.isDisposed).thenReturn(true)
		o.name = "Changed"
		verify(display, never).syncExec(runnable)
	}
}
