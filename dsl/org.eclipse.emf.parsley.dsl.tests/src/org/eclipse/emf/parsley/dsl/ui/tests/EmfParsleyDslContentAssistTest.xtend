package org.eclipse.emf.parsley.dsl.ui.tests

import org.eclipse.emf.parsley.dsl.EmfParsleyDslUiInjectorProvider
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.xbase.junit.ui.AbstractContentAssistTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(EmfParsleyDslUiInjectorProvider))
/**
 * @author Lorenzo Bettini
 */
class EmfParsleyDslContentAssistTest extends AbstractContentAssistTest {
	
	@Test def void testImportCompletion() {
		newBuilder.append('import java.util.Da').assertText('java.util.Date')
	}
	
	@Test def void testImportCompletion_1() {
		newBuilder.append('import LinkedHashSet').assertText('java.util.LinkedHashSet')
	}
	
	@Test def void testTypeCompletion() {
		newBuilder.append(
'''
module my.parsley.project {
	
	labelProvider {
		text {
			LinkedHashSet'''			
		).assertText('java.util.LinkedHashSet')
	}
}