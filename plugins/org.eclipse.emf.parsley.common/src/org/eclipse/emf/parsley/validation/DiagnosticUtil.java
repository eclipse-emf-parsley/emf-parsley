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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;

/**
 * @author Lorenzo Bettini - initial API and implementation
 *
 */
public class DiagnosticUtil {

	private final Map<Integer, String> severityStrings = new HashMap<Integer, String>() {
		{
			put(Diagnostic.OK, "OK");
			put(Diagnostic.ERROR, "ERROR");
			put(Diagnostic.WARNING, "WARNING");
			put(Diagnostic.INFO, "INFO");
			put(Diagnostic.CANCEL, "CANCEL");
		}
	};

	public List<Diagnostic> flatten(Diagnostic diagnostic) {
		List<Diagnostic> flattened = new ArrayList<Diagnostic>();
		traverseDiagnostic(flattened, diagnostic);
		return flattened;
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

	public String format(Diagnostic d) {
		return severityStrings.get(d.getSeverity()) + ": " + d.getMessage();
	}

}
