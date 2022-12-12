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
package org.eclipse.emf.parsley.validation;

import static com.google.common.collect.Iterables.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;

import com.google.common.base.Predicate;

/**
 * @author Lorenzo Bettini - initial API and implementation
 *
 */
public class DiagnosticUtil {

	private final Map<Integer, String> severityStrings = Map.of(
		Diagnostic.OK, "OK",
		Diagnostic.ERROR, "ERROR",
		Diagnostic.WARNING, "WARNING",
		Diagnostic.INFO, "INFO",
		Diagnostic.CANCEL, "CANCEL"
	);

	/**
	 * Flattens possible nested diagnostics
	 * @param diagnostic
	 * @return
	 */
	public List<Diagnostic> flatten(Diagnostic diagnostic) {
		List<Diagnostic> flattened = new ArrayList<>();
		traverseDiagnostic(flattened, diagnostic);
		return flattened;
	}

	/**
	 * Returns only errors, that is, Diagnostic where {@link Diagnostic#getSeverity()}
	 * is {@link Diagnostic#ERROR}.
	 * @param diagnostic
	 * @return
	 */
	public Iterable<Diagnostic> errors(Diagnostic diagnostic) {
		return filter(flatten(diagnostic), new Predicate<Diagnostic>() {
			@Override
			public boolean apply(Diagnostic input) {
				return input.getSeverity() == Diagnostic.ERROR;
			}
		});
	}

	protected void traverseDiagnostic(List<Diagnostic> diagnostics, Diagnostic diagnostic) {
		if (diagnostic.getChildren().isEmpty()) {
			diagnostics.add(diagnostic);
		} else {
			for (Diagnostic diagnosticChild : diagnostic.getChildren()) {
				traverseDiagnostic(diagnostics, diagnosticChild);
			}
		}
	}

	/**
	 * Formats the {@link Diagnostic} into a String.
	 * @param d
	 * @return
	 */
	public String format(Diagnostic d) {
		return severityStrings.get(d.getSeverity()) + ": " + d.getMessage();
	}

}
