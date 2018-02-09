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
import org.eclipse.emf.parsley.edit.EditingDomainFinder;
import org.eclipse.emf.parsley.inject.GenericCompositeFactory;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameter;
import org.eclipse.emf.parsley.inject.parameters.CompositeParameters;
import org.eclipse.emf.parsley.inject.parameters.EClassParameter;
import org.eclipse.emf.parsley.inject.parameters.EObjectParameter;
import org.eclipse.emf.parsley.inject.parameters.FormToolkitParameter;
import org.eclipse.emf.parsley.internal.inject.GenericFactory;
import org.eclipse.emf.parsley.ui.provider.DialogFeatureCaptionProvider;
import org.eclipse.emf.parsley.ui.provider.FormFeatureCaptionProvider;
import org.eclipse.emf.parsley.widgets.DialogWidgetFactory;
import org.eclipse.emf.parsley.widgets.FormWidgetFactory;
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
	private GenericFactory<Object> genericFactory;

	@Inject
	private EditingDomainFinder editingDomainFinder;

	/**
	 * Creates a {@link FormDetailComposite}, the {@link EditingDomain} is retrieved
	 * from the selectedObject with a {@link EditingDomainFinder}.
	 * 
	 * @param parent
	 * @param style
	 * @param object
	 * @return
	 */
	public FormDetailComposite createFormDetailComposite(Composite parent, int style, EObject object) {
		return genericFactory.createInstance(FormDetailComposite.class,
				new CompositeParameters(parent, style),
				new EObjectParameter(object, editingDomainFinder.getEditingDomainFor(object)));
	}

	/**
	 * Creates a {@link FormDetailComposite} with the specified
	 * {@link EditingDomain} that can also be null.
	 * 
	 * @param parent
	 * @param style
	 * @param object
	 * @param editingDomain
	 * @return
	 */
	public FormDetailComposite createFormDetailComposite(Composite parent, int style, EObject object,
			EditingDomain editingDomain) {
		return genericFactory.createInstance(FormDetailComposite.class,
				new CompositeParameters(parent, style), new EObjectParameter(object, editingDomain));
	}

	/**
	 * Creates a {@link FormDetailReadOnlyComposite}, the {@link EditingDomain} is retrieved
	 * from the selectedObject with a {@link EditingDomainFinder}.
	 * 
	 * @param parent
	 * @param style
	 * @param object
	 * @return
	 */
	public FormDetailReadOnlyComposite createFormDetailReadOnlyComposite(Composite parent, int style, EObject object) {
		return genericFactory.createInstance(FormDetailReadOnlyComposite.class,
				new CompositeParameters(parent, style),
				new EObjectParameter(object, editingDomainFinder.getEditingDomainFor(object)));
	}

	public DialogDetailComposite createDialogDetailComposite(Composite parent, int style, EObject object,
			EditingDomain editingDomain) {
		return genericFactory.createInstance(DialogDetailComposite.class,
				new CompositeParameters(parent, style), new EObjectParameter(object, editingDomain));
	}

	public TreeFormComposite createTreeFormComposite(Composite parent, int style) {
		return genericFactory.createInstance(TreeFormComposite.class, new CompositeParameters(parent, style));
	}

	public TreeComposite createTreeComposite(Composite parent, int style) {
		return genericFactory.createInstance(TreeComposite.class, new CompositeParameters(parent, style));
	}

	public TableFormComposite createTableFormComposite(Composite parent, int style, EClass type) {
		return genericFactory.createInstance(TableFormComposite.class,
				new CompositeParameters(parent, style), new EClassParameter(type));
	}

	public TableComposite createTableComposite(Composite parent, int style, EClass eClass) {
		return genericFactory.createInstance(TableComposite.class,
				new CompositeParameters(parent, style), new EClassParameter(eClass));
	}

	public TreeTableFormComposite createTreeTableFormComposite(Composite parent, int style, EClass type) {
		return genericFactory.createInstance(TreeTableFormComposite.class,
				new CompositeParameters(parent, style), new EClassParameter(type));
	}

	public DialogWidgetFactory createDialogWidgetFactory(Composite parent) {
		return genericFactory.createInstance(DialogWidgetFactory.class, new CompositeParameter(parent));
	}

	public FormWidgetFactory createFormWidgetFactory(Composite parent, FormToolkit formToolkit) {
		return genericFactory.createInstance(FormWidgetFactory.class, new CompositeParameter(parent),
				new FormToolkitParameter(formToolkit));
	}

	public DialogFeatureCaptionProvider createFeatureLabelCaptionProvider() {
		return genericFactory.createInstance(DialogFeatureCaptionProvider.class);
	}

	public FormFeatureCaptionProvider createFormFeatureCaptionProvider(FormToolkit formToolkit) {
		return genericFactory.createInstance(FormFeatureCaptionProvider.class,
				new FormToolkitParameter(formToolkit));
	}

	public DialogControlFactory createDialogControlFactory(Composite parent, EObject object,
			EditingDomain editingDomain) {
		return genericFactory.createInstance(DialogControlFactory.class,
				new CompositeParameter(parent), new EObjectParameter(object, editingDomain));
	}

	public FormControlFactory createFormControlFactory(Composite parent, EObject object, EditingDomain editingDomain,
			FormToolkit toolkit) {
		return genericFactory.createInstance(FormControlFactory.class, new CompositeParameter(parent),
				new EObjectParameter(object, editingDomain), new FormToolkitParameter(toolkit));
	}

}
