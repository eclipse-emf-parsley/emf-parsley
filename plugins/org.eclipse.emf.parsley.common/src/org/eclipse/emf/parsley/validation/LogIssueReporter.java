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

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.Diagnostic;

import com.google.inject.Inject;

/**
 * @author Lorenzo Bettini - initial API and implementation
 *
 */
public class LogIssueReporter implements IssueReporter {

	private static final Logger LOGGER = Logger.getLogger(LogIssueReporter.class);

	@Inject
	private DiagnosticUtil diagnosticUtil;

	@Override
	public List<Diagnostic> report(Diagnostic diagnostic) {
		List<Diagnostic> diagnostics = diagnosticUtil.flatten(diagnostic);
		for (Diagnostic d : diagnostics) {
			int severity = d.getSeverity();
			if (severity == Diagnostic.ERROR) {
				logError(d);
			} else if (severity == Diagnostic.WARNING) {
				logWarning(d);
			} else {
				logInfo(d);
			}
		}
		return diagnostics;
	}

	protected void logInfo(Diagnostic d) {
		LOGGER.info(diagnosticUtil.format(d));
	}

	protected void logWarning(Diagnostic d) {
		LOGGER.warn(diagnosticUtil.format(d));
	}

	protected void logError(Diagnostic d) {
		LOGGER.error(diagnosticUtil.format(d));
	}

}
