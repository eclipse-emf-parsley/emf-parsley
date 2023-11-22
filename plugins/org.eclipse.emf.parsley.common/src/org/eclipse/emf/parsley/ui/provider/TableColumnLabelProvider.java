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
import org.eclipse.emf.parsley.inject.parameters.EStructuralFeatureParameter;
import org.eclipse.emf.parsley.runtime.ui.IImageHelper;
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcher;
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcherExtensions;
import org.eclipse.emf.parsley.util.EcoreUtil2;
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
 * Similarly, you can define methods with a prefix 'rowFont', 'rowForeground'
 * and 'rowBackground' for an object to specify the font, foreground color and
 * background color, respectively, for the entire row, or 'font', 'foreground'
 * or 'background' followed by the EClass and EStructuralFeature names separated
 * by an underscore character, for the font, foreground color and background
 * color, respectively, for a specific table cell (the latter has the precedence
 * over the former) for example:
 * 
 * <pre>
 * {@code
 * 
 * public Font text_MyClass_myFeature(Object object)
 *    
 * public Color foreground_MyClass_myFeature(Object object)
 *    
 * public Color background_MyClass_myFeature(Object object)
 *    
 * public Font rowFont(Object object)
 *    
 * public Color rowForeground(Object object)
 *    
 * public Color rowBackground(Object object)
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

	@Inject
	private ILabelProvider labelProvider;

	@Inject
	private IImageHelper imageHelper;

	private PolymorphicDispatcher<Font> fontDispatcher = PolymorphicDispatcher.createForSingleTarget("rowFont", 1, 1,
			this);

	private PolymorphicDispatcher<Color> foregroundDispatcher = PolymorphicDispatcher
			.createForSingleTarget("rowForeground", 1, 1, this);

	private PolymorphicDispatcher<Color> backgroundDispatcher = PolymorphicDispatcher
			.createForSingleTarget("rowBackground", 1, 1, this);

	/**
	 * @since 2.0
	 */
	@Inject
	public TableColumnLabelProvider(EStructuralFeatureParameter eStructuralFeatureParameter) {
		this.eStructuralFeature = eStructuralFeatureParameter.getEStructuralFeature();
	}

	@Override
	public String getText(Object element) {
		if (element == null) {
			return "";
		}

		String ret = polymorphicGetText(element, eStructuralFeature);
		if (ret != null) {
			return ret;
		}
		try {
			return defaultGetTextForFeatureValue(element);
		} catch (AssertionError | RuntimeException e) {
			// avoid exceptions during rendering
			return logErrorAndReturnEmptyString(e);
		} 
	}

	protected String defaultGetTextForFeatureValue(Object element) {
		Object featureValue = getFeatureValue(element);
		return featureValue != null ? labelProvider.getText(featureValue) : "";
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
		return EcoreUtil2.safeEGet(eObject, eStructuralFeature);
	}

	@Override
	public Image getImage(Object element) {
		if (element == null) {
			return null;
		}

		return polymorphicGetImage(element, eStructuralFeature);
	}

	@Override
	public Font getFont(Object element) {
		if (element == null) {
			return null;
		}

		Font forFeature = invokePolymorphicDispatcher(element, eStructuralFeature, "font_");
		if (forFeature != null) {
			return forFeature;
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

		Color forFeature = invokePolymorphicDispatcher(element, eStructuralFeature, "foreground_");
		if (forFeature != null) {
			return forFeature;
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

		Color forFeature = invokePolymorphicDispatcher(element, eStructuralFeature, "background_");
		if (forFeature != null) {
			return forFeature;
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

	private String polymorphicGetText(Object element, EStructuralFeature feature) {
		return invokePolymorphicDispatcher(element, feature, "text_");
	}

	private Image polymorphicGetImage(Object element, EStructuralFeature feature) {
		Object invoke = invokePolymorphicDispatcher(element, feature, "image_");
		if (invoke != null) {
			return imageHelper.convertToImage(invoke);
		}
		return null;
	}

	private <T> T invokePolymorphicDispatcher(Object element, EStructuralFeature feature, String prefix) {
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