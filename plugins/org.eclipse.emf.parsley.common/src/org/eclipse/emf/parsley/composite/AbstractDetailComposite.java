/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.composite;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.inject.AfterInject;
import org.eclipse.emf.parsley.inject.EmfParsleyLifecycle;
import org.eclipse.emf.parsley.inject.InjectableComposite;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameters;
import org.eclipse.emf.parsley.inject.parameters.EObjectParameter;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;

import com.google.inject.Inject;

/**
 * A generic abstract composite for showing the details of an {@link EObject}
 * and editing them.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 * @author Francesco Guidieri - Contributions
 */
@EmfParsleyLifecycle
public abstract class AbstractDetailComposite extends InjectableComposite implements IDetailComposite {

	@Inject
	private FeaturesProvider featuresProvider;

	private EObject object;

	private EditingDomain editingDomain;

	/**
	 * @since 2.0
	 */
	@Inject
	protected AbstractDetailComposite(CompositeParameters compositeParameters, EObjectParameter eObjectParameter) {
		super(compositeParameters);
		this.object = eObjectParameter.getObject();
		this.editingDomain = eObjectParameter.getEditingDomain();
	}

	/**
	 * Creates the editing fields; this is called after all the fields and methods
	 * have been injected, even in subclasses.
	 */
	@AfterInject
	private void createEditingFields() {
		List<EStructuralFeature> features = featuresProvider.getEObjectFeatures(object);

		AbstractControlFactory controlFactory = createControlFactory(object, editingDomain);

		for (final EStructuralFeature feature : features) {
			controlFactory.createEditingField(feature);
		}

		this.layout();
	}

	/**
	 * This is called after all the fields and methods have been injected, even in
	 * subclasses.
	 * 
	 * @param compositeFactory
	 * @since 2.0
	 */
	protected abstract AbstractControlFactory createControlFactory(EObject object, EditingDomain editingDomain);

}
