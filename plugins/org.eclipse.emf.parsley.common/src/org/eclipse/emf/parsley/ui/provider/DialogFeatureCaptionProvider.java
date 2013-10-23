/**
 * 
 */
package org.eclipse.emf.parsley.ui.provider;

import java.lang.reflect.Method;
import java.util.Collections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcher;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.google.common.base.Predicate;
import com.google.inject.Inject;

/**
 * Provides labels for EStructuralFeatures for dialogs. With respect to the
 * superclass {@link FeatureCaptionProvider} you can also specify the Label,
 * besides its text.  If a custom PropertyDescriptionProvider is provided (through
 * injection) then it tries to get the text also from that one, before
 * using the default text.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class DialogFeatureCaptionProvider extends FeatureCaptionProvider {

	@Inject
	protected FeatureCaptionProvider delegate;
	
	private PolymorphicDispatcher.ErrorHandler<Label> errorLabelHandler = new PolymorphicDispatcher.NullErrorHandler<Label>();

	public Label getLabel(Composite parent, EStructuralFeature element) {
		Label lab = polymorphicGetLabel(parent, element);
		if (lab != null)
			return lab;
		return defaultLabel(parent, element);
	}

	protected Label defaultLabel(Composite parent, EStructuralFeature element) {
		return createLabel(parent, element);
	}

	protected Label createLabel(Composite parent, EStructuralFeature element) {
		return createLabel(parent, getText(element));
	}

	protected Label createLabel(Composite parent, String text) {
		Label lab = new Label(parent, SWT.NONE);
		lab.setText(text);
		lab.setLayoutData(new GridData());
		return lab;
	}

	@Override
	protected String polymorphicGetText(EStructuralFeature element) {
		String polymorphicGetText = super.polymorphicGetText(element);
		if (polymorphicGetText == null)
			return delegate.getText(element);
		return polymorphicGetText;
	}

	protected Label polymorphicGetLabel(Composite parent,
			EStructuralFeature element) {
		PolymorphicDispatcher<Label> dispatcher = new PolymorphicDispatcher<Label>(
				Collections.singletonList(this), getLabelPredicate(element),
				errorLabelHandler) {
			@Override
			protected Label handleNoSuchMethod(Object... params) {
				if (PolymorphicDispatcher.NullErrorHandler.class
						.equals(errorLabelHandler.getClass()))
					return null;
				return super.handleNoSuchMethod(params);
			}
		};

		return dispatcher.invoke(parent, element);
	}

	protected Predicate<Method> getLabelPredicate(EStructuralFeature feature) {
		String methodName = "label_" + feature.getEContainingClass().getName()
				+ "_" + feature.getName();
		return PolymorphicDispatcher.Predicates.forName(methodName, 2);
	}

}
