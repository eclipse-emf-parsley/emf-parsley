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
		String ret=polymorphicGetText(element, eStructuralFeature);
		if(ret!=null) {
			return ret;
		}
		try {
			Object featureValue = getFeatureValue(element);
			return featureValue != null ? labelProvider.getText(featureValue)
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
		Object featureValue = p.eGet(eStructuralFeature);
		return featureValue;
	}

	@Override
	public Image getImage(Object element) {
		Image ret=polymorphicGetImage(element, eStructuralFeature);
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