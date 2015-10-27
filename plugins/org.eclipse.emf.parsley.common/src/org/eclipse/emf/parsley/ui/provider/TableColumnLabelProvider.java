/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - initial API and implementation
 * Lorenzo Bettini - additional contributions
 *******************************************************************************/
package org.eclipse.emf.parsley.ui.provider;

import java.lang.reflect.Method;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.EmfParsleyActivator;
import org.eclipse.emf.parsley.runtime.ui.IImageHelper;
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcher;
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcherExtensions;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

import com.google.common.base.Predicate;
import com.google.inject.Inject;

/**
 * Provides a column provider for EStructuralFeatures that uses polymorphic
 * dispatch to call methods at runtime.
 * 
 * IMPORTANT: this class, for performance reasons, assumes that the EObject and
 * the EStructuralFeature are consistent: such feature exists in the EObject's
 * EClass. If that is not the case, the corresponding value will be nonsense,
 * and no check is performed for this condition. Uses of this class in the
 * framework respect such assumption since they iterate over the features
 * starting from the EObject's EClass.
 * 
 * If you define methods with a specific signature convention, the framework
 * will select the correct implementation depending on the runtime type of the
 * input.
 * 
 * You can define methods with a prefix 'text' or 'image' followed by the EClass
 * and EStructuralFeature names separated by an underscore character, like in
 * the example:
 * 
 * <pre>
 * {@code
 * 
 * public String text_MyClass_myFeature(Object object)
 *    
 * public Image image_MyClass_myFeature(Object object)
 *    
 * }
 * </pre>
 * 
 * @author Francesco Guidieri
 * @author Lorenzo Bettini
 * 
 */
public class TableColumnLabelProvider extends ColumnLabelProvider {

	private EStructuralFeature eStructuralFeature;

	private ILabelProvider labelProvider;

	@Inject
	private IImageHelper imageHelper;

	private PolymorphicDispatcher<Font> fontDispatcher = PolymorphicDispatcher.createForSingleTarget("rowFont", 1, 1,
			this);

	private PolymorphicDispatcher<Color> foregroundDispatcher = PolymorphicDispatcher
			.createForSingleTarget("rowForeground", 1, 1, this);

	private PolymorphicDispatcher<Color> backgroundDispatcher = PolymorphicDispatcher
			.createForSingleTarget("rowBackground", 1, 1, this);

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
		if (element == null) {
			return "";
		}

		String ret = polymorphicGetText(element, geteStructuralFeature());
		if (ret != null) {
			return ret;
		}
		try {
			return defaultGetTextForFeatureValue(element);
		} catch (AssertionError e) {
			// avoid exceptions during rendering
			return logErrorAndReturnEmptyString(e);
		} catch (RuntimeException e) {
			// avoid exceptions during rendering
			return logErrorAndReturnEmptyString(e);
		}
	}

	protected String defaultGetTextForFeatureValue(Object element) {
		Object featureValue = getFeatureValue(element);
		return featureValue != null ? getLabelProvider().getText(featureValue) : "";
	}

	protected String logErrorAndReturnEmptyString(Throwable e) {
		EmfParsleyActivator.logError("TableColumnLabelProvider.getText", e);
		return "";
	}

	/**
	 * @param element
	 * @return
	 */
	protected Object getFeatureValue(Object element) {
		EObject eObject = (EObject) element;
		// note that we assume that the EStructuralFeature exists in the
		// EObject's EClass, see the comment in the class' documentation.
		Object featureValue = eObject.eGet(geteStructuralFeature());
		return featureValue;
	}

	@Override
	public Image getImage(Object element) {
		if (element == null) {
			return null;
		}

		Image ret = polymorphicGetImage(element, geteStructuralFeature());
		return ret;
	}

	@Override
	public Font getFont(Object element) {
		if (element == null) {
			return null;
		}

		return fontDispatcher.invoke(element);
	}

	/**
	 * This method will be linked at runtime, belonging to the real input type
	 * at runtime.
	 * 
	 * @return the font for the entire row
	 */
	public Font rowFont(Object element) {
		return null;
	}

	@Override
	public Color getForeground(Object element) {
		if (element == null) {
			return null;
		}

		return foregroundDispatcher.invoke(element);
	}

	/**
	 * This method will be linked at runtime, belonging to the real input type
	 * at runtime.
	 * 
	 * @return the foreground color for the entire row
	 */
	public Color rowForeground(Object element) {
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		if (element == null) {
			return null;
		}

		return backgroundDispatcher.invoke(element);
	}

	/**
	 * This method will be linked at runtime, belonging to the real input type
	 * at runtime.
	 * 
	 * @return the background color for the entire row
	 */
	public Color rowBackground(Object element) {
		return null;
	}

	protected String polymorphicGetText(Object element, EStructuralFeature feature) {
		return invokePolymorphicDispatcher(element, feature, "text_");
	}

	protected Image polymorphicGetImage(Object element, EStructuralFeature feature) {
		Object invoke = invokePolymorphicDispatcher(element, feature, "image_");
		if (invoke != null) {
			return imageHelper.convertToImage(invoke);
		}
		return null;
	}

	protected <T> T invokePolymorphicDispatcher(Object element, EStructuralFeature feature, String prefix) {
		if (element instanceof EObject) {
			EObject eObject = (EObject) element;
			return PolymorphicDispatcherExtensions.<T> polymorphicInvokeBasedOnFeature(this, eObject.eClass(), feature,
					prefix, element);
		}
		String methodName = prefix + feature.getEContainingClass().getName() + "_" + feature.getName();
		Predicate<Method> predicate = PolymorphicDispatcher.Predicates.forName(methodName, 1);
		return PolymorphicDispatcherExtensions.<T> createPolymorphicDispatcher(this, predicate).invoke(element);
	}
}