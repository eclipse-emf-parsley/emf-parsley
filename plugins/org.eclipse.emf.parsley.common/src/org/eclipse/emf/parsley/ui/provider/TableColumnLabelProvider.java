/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.ui.provider;

import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcher;

import java.lang.reflect.Method;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;

import com.google.common.base.Predicate;
import com.google.inject.Inject;

/**
 * A column provider based on an Emf feature.
 * 
 * @author Francesco Guidieri
 * 
 */
public class TableColumnLabelProvider extends ColumnLabelProvider {
	protected EStructuralFeature eStructuralFeature;
	
	private PolymorphicDispatcher.ErrorHandler<Image> errorImageHandler = new PolymorphicDispatcher.NullErrorHandler<Image>();
	private PolymorphicDispatcher.ErrorHandler<String> errorLabelHandler = new PolymorphicDispatcher.NullErrorHandler<String>();
	
	protected ILabelProvider labelProvider;

	@Inject
	public TableColumnLabelProvider() {
		
	}

	public EStructuralFeature geteStructuralFeature() {
		return eStructuralFeature;
	}

	public void seteStructuralFeature(EStructuralFeature eStructuralFeature) {
		this.eStructuralFeature = eStructuralFeature;
	}

	public ILabelProvider getLabelProvider() {
		return labelProvider;
	}

	@Inject
	public void setLabelProvider(ILabelProvider labelProvider) {
		this.labelProvider = labelProvider;
	}

	@Override
	public String getText(Object element) {
		String ret=polymorphicGetText(element, geteStructuralFeature());
		if(ret!=null) {
			return ret;
		}
		try {
			Object featureValue = getFeatureValue(element);
			return featureValue != null ? getLabelProvider().getText(featureValue)
					: "";
		} catch (Exception e) {
			// avoid exceptions during rendering
			return "";
		}
	}

	/**
	 * @param element
	 * @return
	 */
	protected Object getFeatureValue(Object element) {
		EObject p = (EObject) element;
		Object featureValue = p.eGet(geteStructuralFeature());
		return featureValue;
	}

	@Override
	public Image getImage(Object element) {
		Image ret=polymorphicGetImage(element, geteStructuralFeature());
		return ret;
	}
	
	protected String polymorphicGetText(Object element,	EStructuralFeature feature) {
		PolymorphicDispatcher<String> dispatcher = new PolymorphicDispatcher<String>(
				Collections.singletonList(this), getTextPredicate(feature),
				errorLabelHandler) {
			@Override
			protected String handleNoSuchMethod(Object... params) {
				if (PolymorphicDispatcher.NullErrorHandler.class
						.equals(errorLabelHandler.getClass()))
					return null;
				return super.handleNoSuchMethod(params);
			}
		};

		return dispatcher.invoke(element);
	}

	protected Predicate<Method> getTextPredicate(EStructuralFeature feature) {
		String methodName = "text_" + feature.getEContainingClass().getName()
				+ "_" + feature.getName();
		return PolymorphicDispatcher.Predicates.forName(methodName, 1);
	}
	
	protected Image polymorphicGetImage(Object element,	EStructuralFeature feature) {
		PolymorphicDispatcher<Image> dispatcher = new PolymorphicDispatcher<Image>(
				Collections.singletonList(this), getImagePredicate(feature),
				errorImageHandler) {
			@Override
			protected Image handleNoSuchMethod(Object... params) {
				if (PolymorphicDispatcher.NullErrorHandler.class
						.equals(errorImageHandler.getClass()))
					return null;
				return super.handleNoSuchMethod(params);
			}
		};

		return dispatcher.invoke(element);
	}

	protected Predicate<Method> getImagePredicate(EStructuralFeature feature) {
		String methodName = "image_" + feature.getEContainingClass().getName()
				+ "_" + feature.getName();
		return PolymorphicDispatcher.Predicates.forName(methodName, 1);
	}
}