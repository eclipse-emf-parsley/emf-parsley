package org.eclipse.emf.parsley.widgets;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.edit.EditingDomainFinder;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

/**
 * @author Lorenzo Bettini - Initial contribution and API
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
		init(object, object);
	}
	
	/**
	 * Initializes this component for editing the {@link EObject} toBeEdited;
	 * the {@link EObject} original is needed to retrieve additional information
	 * such as the {@link EditingDomain} and the containing {@link Resource}.
	 * 
	 * You cannot assume that original and toBeEdited are the same {@link EObject};
	 * similarly, you cannot assume that toBeEdited is contained in a {@link Resource}.
	 * 
	 * @param original
	 * @param toBeEdited
	 */
	public void init(EObject original, EObject toBeEdited) {
		List<EStructuralFeature> features = featuresProvider.getFeatures(original);

		// when we editing a copy of the original object, we must not
		// use the editing domain, since the copy is not part of
		// any Resource, and the saveable part would save an object
		// with dangling references.
		if (original != toBeEdited)
			initControlFactory(null,
				original.eResource(), toBeEdited);
		else
			initControlFactory(editingDomainFinder.getEditingDomainFor(original),
					original.eResource(), toBeEdited);

		for (final EStructuralFeature feature : features) {
			// derived, unchangeable, container and containment features
			// ignored
			if (feature.isChangeable()
					&& !feature.isDerived()
					&& !(feature instanceof EReference && (((EReference) feature)
							.isContainment()
					// || ((EReference) feature).isContainer()
					))) {

				createControlForFeature(feature);
			}
		}

		// toolkit.paintBordersFor(main);

		this.layout();
	}

	protected abstract void initControlFactory(EditingDomain domain,
			Resource resource, EObject model);

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
