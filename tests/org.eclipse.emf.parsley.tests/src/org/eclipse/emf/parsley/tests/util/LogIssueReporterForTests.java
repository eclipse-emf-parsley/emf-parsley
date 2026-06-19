package org.eclipse.emf.parsley.tests.util;

import java.util.List;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.parsley.validation.LogIssueReporter;

/**
 * For tests we want to report only errors (though warnings will be logged)
 */
public class LogIssueReporterForTests // NOSONAR: this is not a test class
		extends LogIssueReporter {

	@Override
	public List<Diagnostic> report(Diagnostic diagnostic) {
		return super.report(diagnostic).stream()
			.filter(d -> d.getSeverity() == Diagnostic.ERROR)
			.toList();
	}

	@Override
	protected void logInfo(Diagnostic d) {
		// don't log infos
	}

}
