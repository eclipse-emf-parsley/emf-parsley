/*******************************************************************************
 * Copyright (c) 2016, 2018 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.internal.databinding;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.databinding.EMFUpdateValueStrategy;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * A custom {@link UpdateValueStrategy} to decorate controls with possible EMF validation errors.
 * 
 * @author Francesco Guidieri - initial API and implementation
 *
 * @noextend This class is not intended to be subclassed by clients.
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class EmfValidationTargetToModelUpdateValueStrategy extends EMFUpdateValueStrategy {

	private EObject owner;

	private EStructuralFeature feature;

	private DatabindingValidationUtil databindingValidationUtil;

	private boolean firstValidateBeforeSet = true;

	public EmfValidationTargetToModelUpdateValueStrategy(EObject owner, EStructuralFeature feature,
			DatabindingValidationUtil databindingValidationUtil) {
		this.owner = owner;
		this.feature = feature;
		this.databindingValidationUtil = databindingValidationUtil;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected IStatus doSet(IObservableValue observableValue, Object value) {
		// the modification must go into the model
		super.doSet(observableValue, (!"".equals(value) ? value : null));
		// since we delegate validation to the EMF validator
		return validationStatus();
	}

	@SuppressWarnings("unchecked")
	@Override
	public IStatus validateBeforeSet(Object value) {
		if (firstValidateBeforeSet) {
			firstValidateBeforeSet = false;
			return validationStatus();
		}
		return super.validateBeforeSet(value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object convert(Object value) {
		if (converter != null) {
			try {
				return converter.convert(value);
			} catch (RuntimeException e) {
				return e;
			}
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IStatus validateAfterConvert(Object value) {
		if (value instanceof RuntimeException) {
			return ValidationStatus.error(value.toString());
		}
		return super.validateAfterConvert(value);
	}

	private IStatus validationStatus() {
		Iterable<Diagnostic> filtered = 
				databindingValidationUtil.getDiagnostic(owner, feature);
		for (Diagnostic d : filtered) {
			int severity = d.getSeverity();
			if (severity == Diagnostic.ERROR) {
				return ValidationStatus.error(d.getMessage());
			}
		}
		return ValidationStatus.ok();
	}
}
