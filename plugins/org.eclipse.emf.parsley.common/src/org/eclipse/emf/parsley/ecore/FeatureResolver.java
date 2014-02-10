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

package org.eclipse.emf.parsley.ecore;


import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.EmfParsleyActivator;

/**
 * @author Lorenzo Bettini
 * 
 */
public class FeatureResolver {

	public List<EStructuralFeature> getFeatures(EClass eClass,
			List<FeatureNamePath> paths) {
		List<EStructuralFeature> features = new LinkedList<EStructuralFeature>();
		collectFeatures(eClass, paths, features);
		return features;
	}

	protected void collectFeatures(EClass eClass, List<FeatureNamePath> paths,
			List<EStructuralFeature> features) {
		for (FeatureNamePath path : paths) {
			EStructuralFeature feature = getFeature(eClass,
					path.getFeatureName());
			if (feature == null)
				continue;
			if (path.getPaths() == null) {
				features.add(feature);
			} else {
				EClassifier type = feature.getEType();
				if (type instanceof EClass) {
					EClass eC = (EClass) type;
					// recursive call
					collectFeatures(eC, path.getPaths(), features);
				} else {
					EmfParsleyActivator.logError("feature '"
							+ feature.getName() + "' in EClass '" + eClass.getName() + "'" +
							" is not an EClass.");
				}
			}
		}
	}

	public EStructuralFeature getFeature(EClass eClass, String featureName) {
		EStructuralFeature feature = eClass.getEStructuralFeature(featureName);
		if (feature != null)
			return feature;
		
		// try to search for the feature ignoring the case
		// see https://bugs.eclipse.org/bugs/show_bug.cgi?id=421998
		for (EStructuralFeature f : eClass.getEAllStructuralFeatures()) {
			if (f.getName().equalsIgnoreCase(featureName))
				return f;
		}
		
		EmfParsleyActivator.logError("cannot find feature '"
					+ featureName + "' in EClass '" + eClass.getName() + "'");
		return null;
	}
}
