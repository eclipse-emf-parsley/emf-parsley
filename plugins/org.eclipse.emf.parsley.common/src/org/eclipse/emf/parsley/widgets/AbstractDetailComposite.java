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
package org.eclipse.emf.parsley.widgets;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.edit.EditingDomainFinder;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 * @author Francesco Guidieri - Contributions
 */
public abstract class AbstractDetailComposite extends Composite {

	protected FeaturesProvider featuresProvider;
	protected EditingDomainFinder editingDomainFinder;

	public AbstractDetailComposite(Composite parent, int style) {
		super(parent, style);
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
		List<EStructuralFeature> features = featuresProvider
				.getEObjectFeatures(original);

		initControlFactory(editingDomain, original);

		for (final EStructuralFeature feature : features) {
			createControlForFeature(feature);
		}

		// toolkit.paintBordersFor(main);

		this.layout();
	}
	
	protected abstract void initControlFactory(EditingDomain domain,
			EObject model);

	protected abstract void createControlForFeature(
			final EStructuralFeature feature);

	public FeaturesProvider getFeaturesProvider() {
		return featuresProvider;
	}

	@Inject
	public void setFeaturesProvider(FeaturesProvider eClassFeatureProvider) {
		this.featuresProvider = eClassFeatureProvider;
	}

	public EditingDomainFinder getEditingDomainFinder() {
		return editingDomainFinder;
	}

	@Inject
	public void setEditingDomainFinder(EditingDomainFinder editingDomainFinder) {
		this.editingDomainFinder = editingDomainFinder;
	}

}
