/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.emf.parsley.ui.provider;


import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.EmfParsleyActivator;
import org.eclipse.emf.parsley.ecore.FeatureResolver;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

/**
 * Provides the list of {@link EStructuralFeature} of an {@link EClass}. The
 * default is to return the list of all the features in the EClass, but the
 * programmer can customize it (for instance, by returning only a superset, or
 * using a different order) on an EClass-based strategy. The customization can
 * be done redefining buildMap and adding mappings.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class FeaturesProvider {
	
	@Inject
	private FeatureResolver featureResolver;

	public FeatureResolver getFeatureResolver() {
		return featureResolver;
	}

	public void setFeatureResolver(FeatureResolver featureResolver) {
		this.featureResolver = featureResolver;
	}

	public static class EClassToEStructuralFeatureMap extends
			HashMap<EClass, List<EStructuralFeature>> {

		private static final long serialVersionUID = 670116975392207101L;

		public void mapTo(EClass eClass, EStructuralFeature... features) {
			put(eClass, Lists.newArrayList(features));
		}

	}

	protected EClassToEStructuralFeatureMap map = null;

	protected EClassToEStructuralFeatureAsStringsMap stringMap = null;

	public List<EStructuralFeature> getFeatures(EObject eObject) {
		if (eObject == null)
			return Collections.emptyList();

		return getFeatures(eObject.eClass());
	}

	public List<EStructuralFeature> getFeatures(EClass eClass) {
		if (eClass == null)
			return Collections.emptyList();

		List<EStructuralFeature> fromMap = getFromMap(eClass);
		if (fromMap != null)
			return fromMap;
		else {
			fromMap = getFromStringMap(eClass);
			if (fromMap != null)
				return fromMap;
		}

		return new BasicEList<EStructuralFeature>(
				eClass.getEAllStructuralFeatures());
	}

	protected List<EStructuralFeature> getFromMap(EClass eClass) {
		if (map == null)
			buildMapInternal();

		return map.get(eClass);
	}

	protected List<EStructuralFeature> getFromStringMap(EClass eClass) {
		if (stringMap == null)
			buildStringMapInternal();

		List<String> list = stringMap.get(eClass.getInstanceClassName());
		if (list == null)
			return null;

		LinkedList<EStructuralFeature> result = new LinkedList<EStructuralFeature>();

		for (String featureName : list) {
			EStructuralFeature feature = featureResolver.getFeature(eClass, featureName);
			if (feature != null)
				result.add(feature);
			else
				EmfParsleyActivator.logError("cannot find feature '"
						+ featureName + "' in EClass '" + eClass.getName()
						+ "'");
		}

		return result;
	}

	private void buildMapInternal() {
		buildMap(getMap());
	}

	public EClassToEStructuralFeatureMap getMap() {
		if (map == null)
			map = new EClassToEStructuralFeatureMap();
		return map;
	}

	private void buildStringMapInternal() {
		buildStringMap(getStringMap());
	}

	public EClassToEStructuralFeatureAsStringsMap getStringMap() {
		if (stringMap == null)
			stringMap = new EClassToEStructuralFeatureAsStringsMap();
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
	 * name to {@link EStructuralFeature}s' names; the {@link EClass}'s name
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
