package org.eclipse.emf.parsley.examples.testing.maven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyControlBasedTest;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.junit.Test;

/**
 * Demonstrates reusable control assertions and the SWT Data Binding realm.
 * No EMF Parsley forms or application components are involved.
 */
public class ControlBasedTest extends AbstractEmfParsleyControlBasedTest {

	@Test
	public void assertCheckboxAndTextCheckTheConcreteWidgets() {
		var checkbox = syncExec(() -> new Button(getShell(), SWT.CHECK));
		assertCheckbox(checkbox, false);

		syncExecVoid(() -> checkbox.setSelection(true));
		assertCheckbox(checkbox, true);

		var text = syncExec(() -> new Text(getShell(), SWT.BORDER));
		assertText(text, "");

		syncExecVoid(() -> text.setText("Hello from SWT"));
		assertText(text, "Hello from SWT");
	}

	@Test
	public void assertComboChecksItemsAndSelection() {
		var combo = syncExec(() -> {
			var widget = new Combo(getShell(), SWT.DROP_DOWN | SWT.READ_ONLY);
			widget.setItems("First", "Second", "Third");
			widget.select(1);
			return widget;
		});

		assertCombo(combo, "First, Second, Third", 1);
		syncExecVoid(() -> combo.select(2));
		assertCombo(combo, "First, Second, Third", 2);
	}

	@Test
	public void syncExecInRealmProvidesTheDisplayRealmForObservables() {
		String observed = syncExecInRealm(() -> {
			assertSame(DisplayRealm.getRealm(getDisplay()), Realm.getDefault());

			var text = new Text(getShell(), SWT.BORDER);
			var observable = WidgetProperties.text(SWT.Modify).observe(text);
			try {
				text.setText("Data Binding is active");
				return observable.getValue();
			} finally {
				observable.dispose();
			}
		});

		assertEquals("Data Binding is active", observed);
	}
}