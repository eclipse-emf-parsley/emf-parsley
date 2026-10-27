/*******************************************************************************
 * Copyright (c) 2026 Lorenzo Bettini and others.
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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.parsley.EmfParsleyJavaGuiceModule;
import org.eclipse.emf.parsley.composite.AbstractControlFactory;
import org.eclipse.emf.parsley.composite.DialogControlFactory;
import org.eclipse.emf.parsley.composite.FormControlFactory;
import org.eclipse.emf.parsley.composite.MultipleFeatureControl;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.junit.Before;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * Base class for Junit tests concerning {@link Control}, in particular using an
 * {@link AbstractControlFactory}, specific for EMF Parsley applications.
 *
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public abstract class AbstractEmfParsleyApplicationTest extends
		AbstractEmfParsleyControlBasedTest {

	/**
	 * This will be created on demand using the method getOrCreateInjector
	 */
	private Injector injector = null;

	@Before
	public void setup() {
		this.injector = null;
	}

	protected <T extends Object> T injectMembers(final T o) {
		this.getOrCreateInjector().injectMembers(o);
		return o;
	}

	protected Injector getOrCreateInjector() {
		if ((this.injector == null)) {
			this.injector = createInjector();
		}
		return this.injector;
	}

	/**
	 * Creates an {@link Injector} using {@link EmfParsleyJavaGuiceModule}, which
	 * is appropriate for Junit tests.
	 */
	protected Injector createInjector() {
		return createInjector(new EmfParsleyJavaGuiceModule());
	}

	/**
	 * Creates an {@link Injector} with the specified {@link Module}.
	 *
	 * @param module
	 * @return
	 */
	protected Injector createInjector(Module module) {
		return Guice.createInjector(module);
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

	protected AdapterFactory getAdapterFactory() {
		return this.getOrCreateInjector().getInstance(AdapterFactory.class);
	}

	protected void initialize(final DialogControlFactory controlFactory,
			final EObject obj) {
		initializeCommon(controlFactory, obj);
		controlFactory.init(getEditingDomain(), obj, getShell());
		// shell must be visible since we need to check visibility of some
		// controls
		getShell().open();
	}

	protected Boolean initialize(final FormControlFactory controlFactory,
			final EObject obj) {
		initializeCommon(controlFactory, obj);
		// shell must be visibile since we need to check visibility of some
		// controls
		getShell().open();
		// FormToolkit must be created in the UI thread
		// and the initialization requires databinding, and thus the Realm
		return syncExecInRealm(() -> {
			controlFactory.init(getEditingDomain(), obj, getShell(),
					new FormToolkit(getDisplay()));
			return true;
		});
	}

	/**
	 * @param controlFactory
	 * @param obj the object for which we create the control (unused by default)
	 */
	protected void initializeCommon(
			final AbstractControlFactory controlFactory, final EObject obj) {
		injectMembers(controlFactory);
		getOrCreateInjector().injectMembers(controlFactory);
	}

	protected Control createControl(final AbstractControlFactory factory,
			final EStructuralFeature feature) {
		return syncExecInRealm(() -> factory.create(feature));
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
		syncExecVoid(() -> {
			Button button = mfc.getButton();
			assertEquals("...", button.getText());
			assertEquals(expectedLabelText, mfc.getLabel().getText());
			assertEquals(isButtonVisible, button.isVisible());
			assertEquals(isButtonEnabled, button.isEnabled());
		});
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

}
