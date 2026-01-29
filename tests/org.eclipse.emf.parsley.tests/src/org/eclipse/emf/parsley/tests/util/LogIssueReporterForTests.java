package org.eclipse.emf.parsley.tests.util;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.parsley.validation.LogIssueReporter;

/**
 * For tests we want to report only errors (though warnings will be logged)
 */
public class LogIssueReporterForTests extends LogIssueReporter {
	
	@Override
	public List<Diagnostic> report(Diagnostic diagnostic) {
		return super.report(diagnostic).stream()
			.filter(d -> d.getSeverity() == Diagnostic.ERROR)
			.collect(Collectors.toList());
	}
	
	@Override
	protected void logInfo(Diagnostic d) {
		// don't log infos
	}

}
