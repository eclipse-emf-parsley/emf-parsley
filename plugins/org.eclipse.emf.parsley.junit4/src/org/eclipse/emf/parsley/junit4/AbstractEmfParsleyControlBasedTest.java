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

import static org.eclipse.xtext.xbase.lib.IterableExtensions.join;
import static org.eclipse.xtext.xbase.lib.ListExtensions.map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.composite.AbstractControlFactory;
import org.eclipse.emf.parsley.composite.MultipleFeatureControl;
import org.eclipse.emf.parsley.junit4.ui.util.RunnableWithResult;
import org.eclipse.emf.parsley.junit4.util.TestDefaultRealm;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.junit.After;
import org.junit.Before;

/**
 * Base class for Junit tests concerning {@link Control}, in particular using an
 * {@link AbstractControlFactory}.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public abstract class AbstractEmfParsleyControlBasedTest extends
		AbstractEmfParsleyShellBasedTest {

	private static final Logger LOGGER = Logger.getLogger(AbstractEmfParsleyControlBasedTest.class);

	private TestDefaultRealm realm;

	@Before
	public void setupRealm() {
		this.realm = new TestDefaultRealm();
	}

	@After
	public void disposeRealm() {
		this.realm.dispose();
	}

	/**
	 * This default implementation returns a null {@link EditingDomain},
	 * subclasses can override this method to return an actual
	 * {@link EditingDomain}.
	 * 
	 * @return
	 */
	protected EditingDomain getEditingDomain() {
		return null;
	}

	protected FormToolkit getFormToolkit() {
		// FormToolkit must be created in the UI thread
		// and the initialization requires databinding, and thus the Realm
		return syncExecInRealm(new RunnableWithResult<FormToolkit>() {
			@Override
			public FormToolkit run() {
				return new FormToolkit(getDisplay());
			}
		});
	}

	protected Control createControl(final AbstractControlFactory factory,
			final EStructuralFeature feature) {
		return syncExecInRealm(new RunnableWithResult<Control>() {
			@Override
			public Control run() {
				return factory.create(feature);
			}
		});
	}

	protected void assertCheckbox(final Control control, final boolean checked) {
		assertControlClass(control, Button.class);
		final Button button = ((Button) control);
		syncExecVoid(new Runnable() {
			@Override
			public void run() {
				assertTrue("not a checkbox",
						(button.getStyle() & SWT.CHECK) != 0);
				assertEquals(checked, button.getSelection());
			}
		});
	}

	protected void assertLabel(final Control control, final String expectedText) {
		assertControlClass(control, Label.class);
		final Label label = ((Label) control);
		syncExecVoid(new Runnable() {
			@Override
			public void run() {
				assertEquals(expectedText, label.getText());
			}
		});
	}

	protected void assertStyle(final Control control, final int expectedStyle) {
		syncExecVoid(new Runnable() {
			@Override
			public void run() {
				assertTrue((control.getStyle() & expectedStyle) > 0);
			}
		});
	}

	protected void assertStyle(final ComboViewer comboViewer, final int expectedStyle) {
		assertStyle(comboViewer.getCombo(), expectedStyle);
	}

	protected void assertEnabled(final Control control,
			final boolean expectedEnabled) {
		syncExecVoid(new Runnable() {
			@Override
			public void run() {
				assertEquals(expectedEnabled, control.getEnabled());
			}
		});
	}

	/**
	 * The expectedElements is of the shape (not the space after the comma)
	 * "elem1, elem2, elem3"
	 */
	protected void assertCombo(final Control control,
			final String expectedElements, final int selected) {
		assertControlClass(control, Combo.class);
		final Combo combo = ((Combo) control);
		syncExecVoid(new Runnable() {
			@Override
			public void run() {
				String[] items = combo.getItems();
				@SuppressWarnings("unchecked")
				String join = join(
						map(((List<String>) Conversions.doWrapArray(items)),
								new Function1<String, String>() {
									@Override
									public String apply(final String it) {
										return it.toString();
									}
								}), ", ");
				assertEquals(expectedElements, join);
				assertEquals(selected, combo.getSelectionIndex());
			}
		});
	}

	protected void assertText(final Control control, final String expectedText) {
		assertControlClass(control, Text.class);
		final Text text = ((Text) control);
		syncExecVoid(new Runnable() {
			@Override
			public void run() {
				assertEquals(expectedText, text.getText());
			}
		});
	}

	protected void modifyText(final Control control, final String newText) {
		assertControlClass(control, Text.class);
		final Text text = ((Text) control);
		syncExecVoid(new Runnable() {
			@Override
			public void run() {
				text.setText(newText);
			}
		});
		assertText(control, newText);
	}

	protected void assertMultipleFeatureControl(final Control control,
			final String expectedLabelText, final boolean isButtonVisible) {
		assertMultipleFeatureControl(control, expectedLabelText,
				isButtonVisible, true);
	}

	protected void assertMultipleFeatureControl(final Control control,
			final String expectedLabelText, final boolean isButtonVisible,
			final boolean isButtonEnabled) {
		assertControlClass(control, MultipleFeatureControl.class);
		final MultipleFeatureControl mfc = ((MultipleFeatureControl) control);
		syncExecVoid(new Runnable() {
			@Override
			public void run() {
				Button button = mfc.getButton();
				assertEquals("...", button.getText());
				assertEquals(expectedLabelText, mfc.getLabel().getText());
				assertEquals(isButtonVisible, button.isVisible());
				assertEquals(isButtonEnabled, button.isEnabled());
			}
		});
	}

	protected void setSelectionProgrammatically(
			final ISelectionProvider selectionProvider,
			final EObject... elements) {
		syncExecVoid(new Runnable() {
			@Override
			public void run() {
				selectionProvider
						.setSelection(new StructuredSelection(elements));
			}
		});
	}

	protected void assertTextEditable(final Control control,
			final boolean expectedEditable) {
		assertControlClass(control, Text.class);
		final Text text = ((Text) control);
		syncExecVoid(new Runnable() {
			@Override
			public void run() {
				assertEquals(expectedEditable, text.getEditable());
			}
		});
	}

	protected void assertTextEnabled(final Control control,
			final boolean expectedEnabled) {
		assertControlClass(control, Text.class);
		final Text text = ((Text) control);
		syncExecVoid(new Runnable() {
			@Override
			public void run() {
				assertEquals(expectedEnabled, text.isEnabled());
			}
		});
	}

	protected void assertControlClass(final Control control,
			final Class<? extends Control> clazz) {
		Class<? extends Control> controlClass = control.getClass();
		assertTrue("expected class: " + clazz.getSimpleName()
				+ ", actual class: " + controlClass.getSimpleName(),
				clazz.isAssignableFrom(controlClass));
	}

	/**
	 * Executes the passed {@link RunnableWithResult} in a {@link Display#syncExec(Runnable)}
	 * and {@link Realm#runWithDefault(Realm, Runnable)},
	 * and returns the result; note that possible assertions within the runnable will NOT
	 * make a test fail: the result will be null, and the exception will be logged.
	 * 
	 * @param toExecute
	 * @return
	 */
	protected <T> T syncExecInRealm(final RunnableWithResult<T> toExecute) {
		final ArrayList<T> arrayList = new ArrayList<>();
		getDisplay().syncExec(() -> Realm.runWithDefault(
				DisplayRealm.getRealm(Display.getDefault()),
				() -> {
					try {
						arrayList.add(toExecute.run());
					} catch (Throwable e) {
						LOGGER.error(
								"Exception in runnable: "
										+ e.getMessage(), e);
						arrayList.add(null);
					}
				}));
		return arrayList.get(0);
	}
}
