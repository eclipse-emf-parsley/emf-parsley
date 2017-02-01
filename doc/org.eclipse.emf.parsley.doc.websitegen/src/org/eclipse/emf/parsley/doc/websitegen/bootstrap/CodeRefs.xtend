package org.eclipse.emf.parsley.doc.websitegen.bootstrap

import com.google.inject.Inject
import org.apache.log4j.Logger
import org.eclipse.xtext.common.types.JvmIdentifiableElement
import org.eclipse.xtext.xdoc.generator.util.GitExtensions

class CodeRefs {
	
	static val LOG = Logger::getLogger(typeof(CodeRefs))
	
	@Inject extension GitExtensions 
	
	static val NO_SOURCE_PACKAGE_PREFIXES = newHashSet('java.', 'org.eclipse.', 'javax.', 'com.mongodb.', 'com.google.', 'org.junit.')
	
	def getSourceCodeURI(JvmIdentifiableElement element) {
		val uri = gitLink(element)
		if((uri === null || uri.contains('broken-link')) && element.identifier !== null &&
				!NO_SOURCE_PACKAGE_PREFIXES.exists[element.identifier.startsWith(it)]) {
			LOG.error('Missing sources link for ' + element.identifier)
			return null
		} 
		uri	
	}
}