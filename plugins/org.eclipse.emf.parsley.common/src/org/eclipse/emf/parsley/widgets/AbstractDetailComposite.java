package org.eclipse.emf.parsley.widgets;


import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 */
public abstract class AbstractDetailComposite extends Composite {

	protected FeaturesProvider featuresProvider;

	public AbstractDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	public void init(EObject model) {
		List<EStructuralFeature> features = featuresProvider
				.getFeatures(model);

		initControlFactory(model);

		for (final EStructuralFeature feature : features) {
			// derived, unchangeable, container and containment features
			// ignored
			if (feature.isChangeable()
					&& !feature.isDerived()
					&& !(feature instanceof EReference && (((EReference) feature).isContainment() 
//							|| ((EReference) feature).isContainer()
							))) {

				createControlForFeature(feature);
			}
		}

		//toolkit.paintBordersFor(main);

		this.layout();
	}

	protected abstract void initControlFactory(EObject model);

	protected abstract void createControlForFeature(final EStructuralFeature feature);

	public FeaturesProvider getFeaturesProvider() {
		return featuresProvider;
	}

	@Inject
	public void setFeaturesProvider(FeaturesProvider eClassFeatureProvider) {
		this.featuresProvider = eClassFeatureProvider;
	}

}
