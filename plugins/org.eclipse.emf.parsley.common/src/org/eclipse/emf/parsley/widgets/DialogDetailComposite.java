package org.eclipse.emf.parsley.widgets;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.binding.DialogControlFactory;
import org.eclipse.emf.parsley.ui.provider.DialogPropertyDescriptionProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

public class DialogDetailComposite extends AbstractDetailComposite {

	protected DialogPropertyDescriptionProvider dialogPropertyDescriptionProvider;

	protected DialogControlFactory dialogControlFactory;

	protected ILabelProvider labelProvider;

	public DialogDetailComposite(Composite parent, int style) {
		super(parent, style);

		setLayout(new GridLayout(1, false));
	}

	public DialogPropertyDescriptionProvider getDialogPropertyDescriptionProvider() {
		return dialogPropertyDescriptionProvider;
	}

	@Inject
	public void setDialogPropertyDescriptionProvider(
			DialogPropertyDescriptionProvider formPropertyDescriptionProvider) {
		this.dialogPropertyDescriptionProvider = formPropertyDescriptionProvider;
	}

	public DialogControlFactory getDialogControlFactory() {
		return dialogControlFactory;
	}

	@Inject
	public void setDialogControlFactory(
			DialogControlFactory formControlFactory) {
		this.dialogControlFactory = formControlFactory;
	}

	public ILabelProvider getLabelProvider() {
		return labelProvider;
	}

	@Inject
	public void setLabelProvider(ILabelProvider labelProvider) {
		this.labelProvider = labelProvider;
	}

	protected void initControlFactory(EditingDomain domain, Resource resource, EObject model) {
		dialogControlFactory.init(domain, resource, model, this);
	}

	protected void createControlForFeature(EStructuralFeature feature) {
		dialogPropertyDescriptionProvider.getLabel(this, feature);
		dialogControlFactory.create(feature);
	}

}
