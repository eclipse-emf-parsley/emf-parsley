package org.eclipse.emf.parsley.tests.util

import org.eclipse.emf.parsley.validation.LogIssueReporter
import org.eclipse.emf.common.util.Diagnostic

/**
 * For tests we want to report only errors (though warnings will be logged)
 */
class LogIssueReporterForTests extends LogIssueReporter {
	
	override report(Diagnostic diagnostic) {
		super.report(diagnostic).filter[d | d.severity == Diagnostic.ERROR].toList
	}
	
	override protected logInfo(Diagnostic d) {
		// don't log infos
	}

}