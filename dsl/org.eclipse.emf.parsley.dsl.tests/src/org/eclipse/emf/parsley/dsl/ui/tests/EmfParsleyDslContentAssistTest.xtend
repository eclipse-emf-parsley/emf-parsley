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

	@Test def void testEmfFeatureForFeatureProvider() {
		newBuilder.append(
'''
import java.util.List

module my.test.proj {

	featuresProvider {
		features {
			List -> '''			
		).assertText('class', 'empty')
		// these correspond to getClass and isEmpty
	}

	@Test def void testEmfFeatureForPropertyDescriptionProvider() {
		newBuilder.append(
'''
import java.util.List

module my.test.proj {

	featureCaptionProvider {
		text {
			List : '''			
		).assertText('class', 'empty')
		// these correspond to getClass and isEmpty
	}

	@Test def void testEmfFeatureForFormControlFactory() {
		newBuilder.append(
'''
import java.util.List

module my.test.proj {

	formControlFactory {
		control {
			List : '''			
		).assertText('class', 'empty')
		// these correspond to getClass and isEmpty
	}

	@Test def void testEmfFeatureForProposalSpecification() {
		newBuilder.append(
'''
import java.util.List

module my.test.proj {

	proposals {
			List : '''			
		).assertText('class', 'empty')
		// these correspond to getClass and isEmpty
	}

}