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

import java.util.List;

import org.eclipse.emf.common.util.Diagnostic;

/**
 * Reports EMF validation issues
 *
 * @author Lorenzo Bettini - initial API and implementation
 *
 */
public interface IssueReporter {

	/**
	 * This method should report the diagnostics, possibly after
	 * filtering, and return the list of the reported diagnostics.
	 *
	 * @param diagnostic
	 * @return
	 */
	List<Diagnostic> report(Diagnostic diagnostic);
}
