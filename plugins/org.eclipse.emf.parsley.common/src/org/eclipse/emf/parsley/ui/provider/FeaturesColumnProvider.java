package org.eclipse.emf.parsley.ui.provider;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

public class FeaturesColumnProvider extends FeaturesProvider{
	
	@Inject
	protected FeaturesProvider featuresProvider;
	
	private List<Integer> weights;
	

	@Override
	protected List<EStructuralFeature> getFromMap(EClass eClass) {
		List<EStructuralFeature> columnDefinition=super.getFromMap(eClass);
		if(columnDefinition!=null){
			return columnDefinition;
		}
		return featuresProvider.getFromMap(eClass);
	}

	@Override
	protected List<EStructuralFeature> getFromStringMap(EClass eClass) {
		List<EStructuralFeature> columnDefinition=super.getFromStringMap(eClass);
		if(columnDefinition!=null){
			return columnDefinition;
		}
		return featuresProvider.getFromStringMap(eClass);
	}

	public List<Integer> getWeights() {
		return weights;
	}


	public void setWeights(Integer... weights) {
		this.weights=Lists.newArrayList(weights);
	}
}
