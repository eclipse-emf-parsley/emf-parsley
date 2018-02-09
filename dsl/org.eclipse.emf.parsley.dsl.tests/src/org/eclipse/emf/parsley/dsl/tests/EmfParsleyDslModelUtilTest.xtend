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
import org.eclipse.emf.parsley.dsl.model.Model
import org.eclipse.emf.parsley.dsl.util.EmfParsleyDslModelUtil
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import static extension org.eclipse.emf.parsley.dsl.util.EmfParsleyDslModelUtil.*
import static extension org.junit.Assert.*

@RunWith(XtextRunner)
@InjectWith(EmfParsleyDslInjectorProvider)
class EmfParsleyDslModelUtilTest {

	@Inject extension ParseHelper<Model>

	@Before
	def void instantiate() {
		new EmfParsleyDslModelUtil
	}

	@Test
	def void testContainingModule() {
		val m = '''
			module Test {}
		'''.parse.module
		m.assertSame(m.containingModule)
	}

	@Test
	def void testContainingEmfFeatureAccess() {
		val m = '''
			module Test {
				formControlFactory {
					control {
						Foo : foo -> {}
					}
				}
			}
		'''.parse.module
		val spec = m.formControlFactory.controls.specifications.head
		spec.assertSame(spec.containingEmfFeatureAccess)
	}
}
