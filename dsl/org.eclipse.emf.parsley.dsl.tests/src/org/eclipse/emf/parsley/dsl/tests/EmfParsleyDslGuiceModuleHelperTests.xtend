/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.tests

import com.google.inject.Inject
import org.eclipse.emf.parsley.dsl.EmfParsleyDslInjectorProvider
import org.eclipse.emf.parsley.dsl.model.Model
import org.eclipse.emf.parsley.dsl.util.EmfParsleyDslGuiceModuleHelper
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.junit.Test
import org.junit.runner.RunWith

import static extension org.junit.Assert.*

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(EmfParsleyDslInjectorProvider))
class EmfParsleyDslGuiceModuleHelperTests extends EmfParsleyDslAbstractTests {

	@Inject extension ParseHelper<Model>
 	@Inject extension EmfParsleyDslGuiceModuleHelper
 	
	@Test
	def void testModuleWithNoNameInferredType() {
		'''
		module
		'''.parse.module.moduleInferredType.assertNull
	}

	@Test
	def void testModuleWithNoNameGuiceValueBindingsMethodsInSuperclass() {
		assertTrue(
		'''
		module
		'''.parse.module.allGuiceValueBindingsMethodsInSuperclass.empty)
	}

	@Test
	def void testModuleGuiceValueBindingsMethodsInSuperclass() {
		assertEqualsStrings(
		'''
valueContentAssistShortcut
valueIterableStringSeparator
valueIterableStringEllipses
valueIterableStringMaxLength
valueDefaultTableColumnWeight
valueTableColumnWeights
valueTreeFormSashStyle
valueTreeFormSashWeights''',
		'''
		module my.test.mod {}
		'''.parse.module.allGuiceValueBindingsMethodsInSuperclass.map[simpleName].join("\n"))
	}
	
}