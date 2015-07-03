/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri, Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.binding;

import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EEnumImpl;

import com.google.inject.Inject;

public class FeatureHelper {

	@Inject
	private ProposalCreator proposalCreator;
	
	/**
	 * Whether the {@link EStructuralFeature} is considered as editable.
	 * The default behavior is not to consider as editable a derived feature,
	 * an unchangeable feature or any data type which is not serializable.
	 * 
	 * @param f
	 * @return
	 */
	public boolean isEditable(EStructuralFeature f) {
		return !f.isDerived() &&
				f.isChangeable()
				&& (!(f.getEType() instanceof EDataType && !((EDataType) f
						.getEType()).isSerializable()));
	}
	
	public boolean isBooleanFeature(EStructuralFeature feature) {
		return isBooleanEType(feature) || isBooleanDataType(feature);
	}

	private boolean isBooleanEType(EStructuralFeature feature) {
		EClassifier eType = feature.getEType();
		return eType.equals(EcorePackage.Literals.EBOOLEAN)
				|| eType.equals(EcorePackage.Literals.EBOOLEAN_OBJECT);
	}

	private boolean isBooleanDataType(EStructuralFeature feature) {
		Class<?> instanceClass = feature.getEType().getInstanceClass();
		return feature.getEType() instanceof EDataType
				&& (instanceClass == Boolean.class || instanceClass == Boolean.TYPE);
	}

	public boolean hasPredefinedProposals(EStructuralFeature feature) {
		return feature instanceof EReference
				|| feature.getEType() instanceof EEnumImpl;
	}

	public List<Object> createProposals(EObject owner, EStructuralFeature feature) {
		getProposalCreator().setResource(owner.eResource());
		return getProposalCreator().proposals(owner, feature);
	}
	
	public ProposalCreator getProposalCreator() {
		return proposalCreator;
	}

	public void setProposalCreator(ProposalCreator proposalCreator) {
		this.proposalCreator = proposalCreator;
	}

}
