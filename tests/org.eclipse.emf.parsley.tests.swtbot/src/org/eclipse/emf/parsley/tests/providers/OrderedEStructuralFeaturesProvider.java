/**
 * 
 */
package org.eclipse.emf.parsley.tests.providers;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.emf.parsley.util.EStructuralFeatureNameComparator;

import com.google.inject.Inject;


/**
 * @author bettini
 *
 */
public class OrderedEStructuralFeaturesProvider extends FeaturesProvider {

	@Inject
	EStructuralFeatureNameComparator comparator;
	
	@Override
	public List<EStructuralFeature> getFeatures(EClass eClass) {
		List<EStructuralFeature> features = super.getFeatures(eClass);
		Collections.sort(features, new EStructuralFeatureNameComparator());
		return features;
	}

}
