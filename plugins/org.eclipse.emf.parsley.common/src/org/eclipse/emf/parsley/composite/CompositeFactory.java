/*******************************************************************************
 * Copyright (c) 2018 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.composite;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.parsley.inject.GenericCompositeFactory;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameter;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameters;
import org.eclipse.emf.parsley.inject.parameters.EClassParameter;
import org.eclipse.emf.parsley.inject.parameters.EObjectParameter;
import org.eclipse.emf.parsley.inject.parameters.FormToolkitParameter;
import org.eclipse.emf.parsley.internal.inject.GenericFactory;
import org.eclipse.emf.parsley.ui.provider.DialogFeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.FeatureLabelCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider;
import org.eclipse.emf.parsley.widgets.DialogWidgetFactory;
import org.eclipse.emf.parsley.widgets.FormWidgetFactory;
import org.eclipse.emf.parsley.widgets.IWidgetFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * A factory for creating EMF Parsley Composite by injection; if you have custom
 * Composite components you need to create by injection, you should use
 * {@link GenericCompositeFactory}.
 * 
 * @author Lorenzo Bettini
 * @since 2.0
 */
@Singleton
public class CompositeFactory {

	@Inject
	private GenericCompositeFactory genericCompositeFactory;

	@Inject
	private GenericFactory<Composite> genericCompositeFactoryWithAdditionalParams;

	@Inject
	private GenericFactory<IWidgetFactory> factoryForIWidgetFactory;

	@Inject
	private GenericFactory<AbstractControlFactory> factoryForAbstractControlFactory;

	@Inject
	private GenericFactory<FeatureLabelCaptionProvider> featureLabelCaptionProviderFactory;

	public FormDetailComposite createFormDetailComposite(Composite parent, int style) {
		return genericCompositeFactory.create(FormDetailComposite.class, parent, style);
	}

	public FormDetailReadOnlyComposite createFormDetailReadOnlyComposite(Composite parent, int style) {
		return genericCompositeFactory.create(FormDetailReadOnlyComposite.class, parent, style);
	}

	public DialogDetailComposite createDialogDetailComposite(Composite parent, int style) {
		return genericCompositeFactory.create(DialogDetailComposite.class, parent, style);
	}

	public TreeFormComposite createTreeFormComposite(Composite parent, int style) {
		return genericCompositeFactory.create(TreeFormComposite.class, parent, style);
	}

	public TableFormComposite createTableFormComposite(Composite parent, int style, EClass type) {
		return genericCompositeFactoryWithAdditionalParams.createInstance(TableFormComposite.class,
				new CompositeParameters(parent, style), new EClassParameter(type));
	}

	public DialogWidgetFactory createDialogWidgetFactory(Composite parent) {
		return factoryForIWidgetFactory.createInstance(DialogWidgetFactory.class, new CompositeParameter(parent));
	}

	public FormWidgetFactory createFormWidgetFactory(Composite parent, FormToolkit formToolkit) {
		return factoryForIWidgetFactory.createInstance(FormWidgetFactory.class, new CompositeParameter(parent),
				new FormToolkitParameter(formToolkit));
	}

	public DialogFeatureCaptionProvider createFeatureLabelCaptionProvider() {
		return featureLabelCaptionProviderFactory.createInstance(DialogFeatureCaptionProvider.class);
	}

	public FormFeatureCaptionProvider createFormFeatureCaptionProvider(FormToolkit formToolkit) {
		return featureLabelCaptionProviderFactory.createInstance(FormFeatureCaptionProvider.class,
				new FormToolkitParameter(formToolkit));
	}

	public DialogControlFactory createDialogControlFactory(Composite parent, EObject object,
			EditingDomain editingDomain) {
		return factoryForAbstractControlFactory.createInstance(DialogControlFactory.class,
				new CompositeParameter(parent), new EObjectParameter(object, editingDomain));
	}

	public FormControlFactory createFormControlFactory(Composite parent, EObject object, EditingDomain editingDomain,
			FormToolkit toolkit) {
		return factoryForAbstractControlFactory.createInstance(FormControlFactory.class,
				new CompositeParameter(parent), new EObjectParameter(object, editingDomain),
				new FormToolkitParameter(toolkit));
	}
}
