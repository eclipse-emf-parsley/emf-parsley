package org.eclipse.emf.parsley.examples.testing.maven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.List;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyControlBasedTest;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
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

	@Test
	public void controlHelpersCheckStateAndModifyText() {
		var label = syncExec(() -> {
			var widget = new Label(getShell(), SWT.NONE);
			widget.setText("Name");
			return widget;
		});
		assertLabel(label, "Name");

		var text = syncExec(() -> new Text(getShell(), SWT.BORDER));
		assertStyle(text, SWT.BORDER);
		assertEnabled(text, true);
		assertTextEditable(text, true);

		// modifyText changes the real widget and verifies its new text.
		modifyText(text, "Ada");
		assertText(text, "Ada");

		syncExecVoid(() -> {
			text.setEditable(false);
			text.setEnabled(false);
		});
		assertTextEditable(text, false);
		assertTextEnabled(text, false);
		assertEnabled(text, false);
	}

	@Test
	public void programmaticSelectionUsesEmfObjectsWithoutAWorkbench() {
		EClass first = EcoreFactory.eINSTANCE.createEClass();
		first.setName("First");
		EClass second = EcoreFactory.eINSTANCE.createEClass();
		second.setName("Second");

		var viewer = syncExec(() -> {
			var result = new TableViewer(getShell(), SWT.BORDER | SWT.SINGLE);
			result.setContentProvider(ArrayContentProvider.getInstance());
			result.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					return ((EClass) element).getName();
				}
			});
			result.setInput(List.of(first, second));
			return result;
		});

		// EClass implements EObject, so it can be passed to this helper.
		setSelectionProgrammatically(viewer, second);
		var selection = syncExec(() -> (IStructuredSelection) viewer.getSelection());
		assertSame(second, selection.getFirstElement());
	}
}