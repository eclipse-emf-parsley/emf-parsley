/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.ui.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcherExtensions;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.google.inject.Inject;

/**
 * Provides {@link Label} elements for EStructuralFeatures of an {@link EObject}
 * . If a custom {@link FeatureCaptionProvider} is provided (through injection)
 * then it tries to get the text polymorphically also from that one, before
 * using the default text. The default label's text is obtained using the
 * {@link AdapterFactory} property descriptions.
 * 
 * You can defines methods with a prefix 'label'
 * followed by the EClass and EStructuralFeature names separated by an
 * underscore character, like in the example:
 * 
 * <pre>
 * {@code
 *    public Label label_MyClass_myFeature(EStructuralFeature feature)
 *      
 * }
 * </pre>
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 */
public class FeatureLabelCaptionProvider extends FeatureCaptionProvider {

	@Inject
	private FeatureCaptionProvider delegate;

	@Inject
	private AdapterFactory adapterFactory;

	public FeatureCaptionProvider getDelegate() {
		return delegate;
	}

	public void setDelegate(FeatureCaptionProvider delegate) {
		this.delegate = delegate;
	}

	public Label getLabel(Composite parent, EObject o, EStructuralFeature feature) {
		Label lab = polymorphicGetLabel(parent, o.eClass(), feature);
		if (lab != null) {
			return lab;
		}
		return defaultLabel(parent, o, feature);
	}

	protected Label defaultLabel(Composite parent, EObject o, EStructuralFeature feature) {
		return createLabel(parent, o, feature);
	}

	protected Label createLabel(Composite parent, EObject o, EStructuralFeature feature) {
		return createLabel(parent, getText(o, feature));
	}

	protected Label createLabel(Composite parent, String text) {
		Label lab = new Label(parent, SWT.NONE);
		lab.setText(text);
		lab.setLayoutData(new GridData());
		return lab;
	}

	@Override
	protected String polymorphicGetText(EClass eClass, EStructuralFeature feature) {
		String polymorphicGetText = super.polymorphicGetText(eClass, feature);
		if (polymorphicGetText == null) {
			return getDelegate().polymorphicGetText(eClass, feature);
		}
		return polymorphicGetText;
	}

	protected Label polymorphicGetLabel(Composite parent, EClass eClass,
			EStructuralFeature feature) {
		return PolymorphicDispatcherExtensions.
				<Label>polymorphicInvokeBasedOnFeature(
					this, eClass, feature, "label_", parent, feature);
	}

	public String getText(EObject o, EStructuralFeature feature) {
		String polymorphicGetText = polymorphicGetText(o.eClass(), feature);
		if (polymorphicGetText != null) {
			return polymorphicGetText;
		}
		return defaultText(o, feature);
	}

	/**
	 * The default text for the object's feature is computed using the {@link IItemPropertyDescriptor}
	 * obtained through the {@link AdapterFactory}; if no property descriptor is found, then
	 * we fall back to the feature's name.
	 * 
	 * @param o
	 * @param feature
	 * @return
	 */
	protected String defaultText(EObject o, EStructuralFeature feature) {
		IItemPropertyDescriptor propertyDescriptor = ((IItemPropertySource) adapterFactory.adapt(o, IItemPropertySource.class)).
			getPropertyDescriptor(o, feature);
		// the property descriptor could be null if the model's edit plugin's item provider
		// does not specify a descriptor for the given feature
		if (propertyDescriptor == null) {
			return defaultText(feature);
		}
		return propertyDescriptor.getDisplayName(o);
	}
}
