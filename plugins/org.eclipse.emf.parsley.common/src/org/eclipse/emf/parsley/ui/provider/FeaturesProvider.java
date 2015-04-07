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
package org.eclipse.emf.parsley.ui.provider;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.ecore.FeatureResolver;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

/**
 * Provides the list of {@link EStructuralFeature} of an {@link EClass}. The
 * default is to return the list of all the features in the EClass, but the
 * programmer can customize it (for instance, by returning only a superset, or
 * using a different order) on an EClass-based strategy.
 * 
 * The customization can be done redefining {@link #buildMap} or
 * {@link #buildStringMap} and adding mappings.
 * 
 * In any case, the computation of features is cached, thus, the list of
 * features for a given EClass will always be the same after the first invocation.
 * 
 * @author Lorenzo Bettini - initial API and implementation
 * 
 */
public class FeaturesProvider {
	
	@Inject
	private FeatureResolver featureResolver;

	private Map<EClass, List<EStructuralFeature>> eClassFeaturesCache = new HashMap<EClass, List<EStructuralFeature>>();

	protected EClassToEStructuralFeatureMap map = null;
	
	protected EClassToEStructuralFeatureAsStringsMap stringMap = null;

	public FeatureResolver getFeatureResolver() {
		return featureResolver;
	}

	public static class EClassToEStructuralFeatureMap extends
			HashMap<EClass, List<EStructuralFeature>> {

		private static final long serialVersionUID = 670116975392207101L;

		public void mapTo(EClass eClass, EStructuralFeature... features) {
			put(eClass, Lists.newArrayList(features));
		}

	}

	/**
	 * Returns the list of features of the {@link EClass} of the specified
	 * {@link EObject}, by calling {@link #getFeatures(EClass)}
	 * @param eObject
	 * @return if the eObject is null it returns an empty list
	 */
	public List<EStructuralFeature> getEObjectFeatures(EObject eObject) {
		if (eObject == null) {
			return Collections.emptyList();
		}

		return getFeatures(eObject.eClass());
	}

	/**
	 * Returns the list of features of the {@link EClass}, using possible customizations
	 * @param eClass
	 * @return if the eClass is null it returns an empty list
	 */
	public List<EStructuralFeature> getFeatures(EClass eClass) {
		if (eClass == null) {
			return Collections.emptyList();
		}

		List<EStructuralFeature> features = eClassFeaturesCache.get(eClass);
		if (features != null) {
			return features;
		}

		features = getFromMap(eClass);
		if (features != null) {
			eClassFeaturesCache.put(eClass, features);
			return features;
		} else {
			features = getFromStringMap(eClass);
			if (features != null) {
				eClassFeaturesCache.put(eClass, features);
				return features;
			}
		}

		// default behavior
		features = defaultFeatures(eClass);
		eClassFeaturesCache.put(eClass, features);

		return defaultFeatures(eClass);
	}

	protected List<EStructuralFeature> defaultFeatures(EClass eClass) {
		EList<EStructuralFeature> eAllStructuralFeatures = eClass.getEAllStructuralFeatures();
		Collection<EStructuralFeature> features = Collections2.filter(eAllStructuralFeatures, new Predicate<EStructuralFeature>() {

			@Override
			public boolean apply(EStructuralFeature feature) {
				// derived, unchangeable, container and containment features ignored
				return feature.isChangeable()
						&& !feature.isDerived()
						&& !(feature instanceof EReference && (((EReference) feature)
								.isContainment()
						// || ((EReference) feature).isContainer()
						));
			}
		});
		return new BasicEList<EStructuralFeature>(features);
	}

	protected List<EStructuralFeature> getFromMap(EClass eClass) {
		if (map == null) {
			buildMapInternal();
		}
		
		return map.get(eClass);
	}

	protected List<EStructuralFeature> getFromStringMap(EClass eClass) {
		if (stringMap == null) {
			buildStringMapInternal();
		}

		List<String> list = stringMap.get(eClass.getInstanceClassName());
		if (list == null) {
			return null;
		}

		List<EStructuralFeature> result = new LinkedList<EStructuralFeature>();

		for (String featureName : list) {
			EStructuralFeature feature = getFeatureResolver().getFeature(eClass, featureName);
			if (feature != null) {
				result.add(feature);
			}
		}

		return result;
	}

	private void buildMapInternal() {
		buildMap(getMap());
	}

	public EClassToEStructuralFeatureMap getMap() {
		if (map == null) {
			map = new EClassToEStructuralFeatureMap();
		}
		return map;
	}

	private void buildStringMapInternal() {
		buildStringMap(getStringMap());
	}

	public EClassToEStructuralFeatureAsStringsMap getStringMap() {
		if (stringMap == null) {
			stringMap = new EClassToEStructuralFeatureAsStringsMap();
		}
		return stringMap;
	}

	/**
	 * Derived classes should redefine this method to map an {@link EClass} to
	 * {@link EStructuralFeature}s.
	 * 
	 * @param map
	 */
	protected void buildMap(EClassToEStructuralFeatureMap map) {
		// default implementation is empty
	}

	/**
	 * Derived classes should redefine this method to map an {@link EClass}'s
	 * instanceClassName to {@link EStructuralFeature}s' names; the {@link EClass}'s name
	 * should be obtained using getInstanceClassName().
	 * 
	 * @param stringMap
	 */
	protected void buildStringMap(
			EClassToEStructuralFeatureAsStringsMap stringMap) {
		// default implementation is empty
	}

	public void addToStringMap(EClass eClass, String...featuresNames) {
		getStringMap().mapTo(eClass.getInstanceClassName(), featuresNames);
	}

}
