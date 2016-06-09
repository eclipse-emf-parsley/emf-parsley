/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.internal.databinding;

import static com.google.common.collect.Iterables.filter;

import java.util.List;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.parsley.validation.DiagnosticUtil;

import com.google.common.base.Predicate;

/**
 * @author Francesco Guidieri - initial API and implementation
 *
 */
public class EmfValidationTargetToModelUpdateValueStrategy extends UpdateValueStrategy {

	private EStructuralFeature feature;
	private Diagnostician diagnostician;
	private DiagnosticUtil diagnosticUtil;
	private EObject owner;
	private boolean firstValidateBeforeSet = true;

	public EmfValidationTargetToModelUpdateValueStrategy(EObject owner, EStructuralFeature feature,
			Diagnostician diagnostician, DiagnosticUtil diagnosticUtil) {
		this.owner = owner;
		this.feature = feature;
		this.diagnostician = diagnostician;
		this.diagnosticUtil = diagnosticUtil;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected IStatus doSet(IObservableValue observableValue, Object value) {
		IStatus afterSetStatus = super.doSet(observableValue, (!"".equals(value) ? value : null));
		if (afterSetStatus.isOK()) {
			return validationStatus(feature);
		}
		return afterSetStatus;
	};

	@Override
	public IStatus validateBeforeSet(Object value) {
		if (firstValidateBeforeSet) {
			IStatus validationStatus = validationStatus(feature);
			firstValidateBeforeSet = false;
			if (!validationStatus.isOK()) {
				return validationStatus;
			}
			return super.validateBeforeSet(value);
		}
		return Status.OK_STATUS;
	}

	private IStatus validationStatus(final EStructuralFeature feature) {
		Diagnostic diagnostic = diagnostician.validate(owner);
		List<Diagnostic> diagnostics = diagnosticUtil.flatten(diagnostic);
		Iterable<Diagnostic> filtered = filter(diagnostics, new Predicate<Diagnostic>() {
			@Override
			public boolean apply(Diagnostic d) {
				return filter(filter(d.getData(), EStructuralFeature.class), new Predicate<EStructuralFeature>() {
					@Override
					public boolean apply(EStructuralFeature input) {
						return input.equals(feature);
					}
				}).iterator().hasNext();
			}
		});
		for (Diagnostic d : filtered) {
			int severity = d.getSeverity();
			if (severity == Diagnostic.ERROR) {
				return ValidationStatus.error(d.getMessage());
			}
		}
		return ValidationStatus.ok();
	}
}
