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

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;

import com.google.inject.Inject;

/**
 * Executes EMF Validation.
 * 
 * @author Lorenzo Bettini - initial API and implementation
 *
 */
public class ValidationRunner {

	@Inject
	private Diagnostician diagnostician;

	public void validate(EObject eObject, IssueReporter reporter) {
		reporter.report(validate(eObject));
	}

	public Diagnostic validate(EObject eObject) {
		return diagnostician.validate(eObject);
	}
}
