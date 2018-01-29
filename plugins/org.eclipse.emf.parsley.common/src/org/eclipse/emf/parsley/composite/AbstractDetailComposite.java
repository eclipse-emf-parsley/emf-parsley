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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.edit.EditingDomainFinder;
import org.eclipse.emf.parsley.inject.CompositeParameters;
import org.eclipse.emf.parsley.inject.InjectableComposite;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;

import com.google.inject.Inject;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 * @author Francesco Guidieri - Contributions
 */
public abstract class AbstractDetailComposite extends InjectableComposite {

	@Inject
	private FeaturesProvider featuresProvider;

	@Inject
	private EditingDomainFinder editingDomainFinder;

	/**
	 * @since 2.0
	 */
	@Inject
	public AbstractDetailComposite(CompositeParameters params) {
		super(params);
	}

	/**
	 * Initializes this component for editing the {@link EObject} object.
	 * 
	 * @param object
	 */
	public void init(EObject object) {
		init(object, editingDomainFinder.getEditingDomainFor(object));
	}

	/**
	 * Initializes this component for editing the passed {@link EObject} using
	 * the passed {@link EditingDomain}.
	 * 
	 * @param original
	 * @param editingDomain
	 *            it can be null
	 */
	public void init(EObject original, EditingDomain editingDomain) {
		List<EStructuralFeature> features = featuresProvider.getEObjectFeatures(original);

		initControlFactory(editingDomain, original);

		for (final EStructuralFeature feature : features) {
			createControlForFeature(original.eClass(), feature);
		}

		this.layout();
	}

	protected abstract void initControlFactory(EditingDomain domain, EObject model);

	protected abstract void createControlForFeature(final EClass eClass, final EStructuralFeature feature);

}
