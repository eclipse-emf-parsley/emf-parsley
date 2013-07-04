package org.eclipse.emf.parsley.widgets;


import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.binding.FormControlFactory;
import org.eclipse.emf.parsley.edit.EditingDomainFinder;
import org.eclipse.emf.parsley.ui.provider.FeaturesProvider;
import org.eclipse.emf.parsley.ui.provider.FormPropertyDescriptionProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import com.google.inject.Inject;

public class FormDetailComposite extends Composite {

	protected FormPropertyDescriptionProvider formPropertyDescriptionProvider;

	protected FormControlFactory formControlFactory;

	protected EditingDomainFinder editingDomainFinder;

	protected FeaturesProvider featuresProvider;

	protected ILabelProvider labelProvider;

	private final Composite main;

	FormToolkit toolkit;

	private final ScrolledForm scrolledForm;

	public FormDetailComposite(Composite parent, int style) {
		super(parent, style);

		toolkit = new FormToolkit(parent.getDisplay());

		toolkit.adapt(this);
		//toolkit.paintBordersFor(this);
		setLayout(new GridLayout(1, false));

		scrolledForm = toolkit.createScrolledForm(this);
		// make sure that the form takes all the space
		scrolledForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));
		//toolkit.paintBordersFor(scrolledForm);
		scrolledForm.getBody().setLayout(new GridLayout(2, false));

		main = scrolledForm.getBody();
	}

	public void init(EObject model) {
		List<EStructuralFeature> features = featuresProvider
				.getFeatures(model);

		scrolledForm.setText(getLabelProvider().getText(model));
		scrolledForm.setImage(getLabelProvider().getImage(model));

		formControlFactory.init(
				editingDomainFinder.getEditingDomainFor(model), model, main,
				toolkit);

		for (final EStructuralFeature feature : features) {
			// derived, unchangeable, container and containment features
			// ignored
			if (feature.isChangeable()
					&& !feature.isDerived()
					&& !(feature instanceof EReference && (((EReference) feature).isContainment() 
//							|| ((EReference) feature).isContainer()
							))) {

				formPropertyDescriptionProvider.getLabel(main, feature);

				formControlFactory.create(feature);
			}
		}

		//toolkit.paintBordersFor(main);

		this.layout();
	}

	@Override
	public void dispose() {
		super.dispose();
		toolkit.dispose();
	}

	public FormPropertyDescriptionProvider getFormPropertyDescriptionProvider() {
		return formPropertyDescriptionProvider;
	}

	@Inject
	public void setFormPropertyDescriptionProvider(
			FormPropertyDescriptionProvider formPropertyDescriptionProvider) {
		this.formPropertyDescriptionProvider = formPropertyDescriptionProvider;
		this.formPropertyDescriptionProvider.setFormToolkit(toolkit);
	}

	public FormControlFactory getFormControlFactory() {
		return formControlFactory;
	}

	@Inject
	public void setFormControlFactory(
			FormControlFactory formControlFactory) {
		this.formControlFactory = formControlFactory;
	}

	public EditingDomainFinder getEditingDomainFinder() {
		return editingDomainFinder;
	}

	@Inject
	public void setEditingDomainFinder(EditingDomainFinder editingDomainFinder) {
		this.editingDomainFinder = editingDomainFinder;
	}

	public FeaturesProvider getFeaturesProvider() {
		return featuresProvider;
	}

	@Inject
	public void setFeaturesProvider(
			FeaturesProvider eClassFeatureProvider) {
		this.featuresProvider = eClassFeatureProvider;
	}

	public ILabelProvider getLabelProvider() {
		return labelProvider;
	}

	@Inject
	public void setLabelProvider(ILabelProvider labelProvider) {
		this.labelProvider = labelProvider;
	}

}
