/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.internal.databinding;

import static com.google.common.collect.Iterables.contains;
import static com.google.common.collect.Iterables.filter;

import java.util.List;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.parsley.validation.DiagnosticUtil;
import org.eclipse.jface.viewers.ILabelProvider;

import com.google.common.base.Predicate;
import com.google.inject.Inject;

/**
 * Some utilities for validation used in databinding.
 * 
 * @author Lorenzo Bettini - initial API and implementation
 *
 */
public class DatabindingValidationUtil {

	private final class CustomDiagnostician extends Diagnostician {
		// TODO this custom Diagnostician should probably become the default Diagnostician
		// implementation and should be bound in the default Guice module
		@Override
		public String getObjectLabel(EObject eObject) {
			return labelProvider.getText(eObject);
		}
	}

	@Inject
	private DiagnosticUtil diagnosticUtil;

	@Inject
	private ILabelProvider labelProvider;

	private CustomDiagnostician diagnostician = new CustomDiagnostician();

	/**
	 * Retrieves the {@link Diagnostic} for the specified object and related to
	 * the specified feature.
	 * 
	 * @param eObject
	 * @param feature
	 * @return
	 */
	public Iterable<Diagnostic> getDiagnostic(final EObject eObject, final EStructuralFeature feature) {
		List<Diagnostic> diagnostics = diagnosticUtil.flatten(diagnostician.validate(eObject));
		Iterable<Diagnostic> filtered = filter(diagnostics, new Predicate<Diagnostic>() {
			@Override
			public boolean apply(Diagnostic d) {
				return contains(d.getData(), feature);
			}
		});
		return filtered;
	}
}
