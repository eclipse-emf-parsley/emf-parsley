/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.internal.databinding;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EValidator.SubstitutionLabelProvider;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.parsley.ui.provider.FeatureLabelCaptionProvider;
import org.eclipse.jface.viewers.ILabelProvider;

/**
 * A substitution label provider for validation error that uses our label and
 * feature caption provider. This is specific of the passed EObject.
 *
 * @author Lorenzo Bettini - initial API and implementation
 *
 * @noextend This class is not intended to be subclassed by clients.
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class DatabindingSubstitutionLabelProvider implements SubstitutionLabelProvider {

	private EObject eObject;

	private ILabelProvider labelProvider;

	private FeatureLabelCaptionProvider featureLabelCaptionProvider;

	public DatabindingSubstitutionLabelProvider(EObject eObject, ILabelProvider labelProvider,
			FeatureLabelCaptionProvider featureLabelCaptionProvider) {
		this.eObject = eObject;
		this.labelProvider = labelProvider;
		this.featureLabelCaptionProvider = featureLabelCaptionProvider;
	}

	@Override
	public String getObjectLabel(EObject eObject) {
		return labelProvider.getText(eObject);
	}

	@Override
	public String getFeatureLabel(EStructuralFeature eStructuralFeature) {
		return featureLabelCaptionProvider.getText(eObject, eStructuralFeature);
	}

	@Override
	public String getValueLabel(EDataType eDataType, Object value) {
		return EcoreUtil.convertToString(eDataType, value);
	}

}
