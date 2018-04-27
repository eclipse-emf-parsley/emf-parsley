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
import org.apache.log4j.ConsoleAppender
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.log4j.spi.LoggingEvent
import org.eclipse.emf.parsley.dsl.model.Model
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.eclipse.xtext.xbase.jvmmodel.JvmModelAssociator
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*

@RunWith(XtextRunner)
@InjectWith(EmfParsleyDslInjectorProvider)
class EmfParsleyDslSmokeTest {

	@Inject extension ParseHelper<Model>
	@Inject extension ValidationTestHelper

	final static Logger LOG = Logger.getLogger(JvmModelAssociator);

	/**
	 * JvmModelAssociator does not throw exceptions but logs possible
	 * errors; we use this class to record possible error events
	 */
	static class LogListener extends ConsoleAppender {

		val public events = newArrayList()

		override synchronized doAppend(LoggingEvent event) {
			if (event.getLevel == Level.ERROR) {
				events += event
			}
		}

	}

	var LogListener logListener

	@Before
	def void createAppender() {
		logListener = new LogListener => [
			LOG.addAppender(it)
		]
	}

	@After
	def void removeAppender() {
		LOG.removeAppender(logListener)
	}

	@Test
	def void testModuleWithNoName() {
		'''
			module
		'''.assertNoException
	}

	@Test
	def void testExtendsWithoutType() {
		'''
			module my.empty extends
		'''.assertNoException
	}

	@Test
	def void testTypeBindingWithNoType() {
		'''
			module my.empty {
				bindings {
					type
				}
			}
		'''.assertNoException
	}

	@Test
	def void testProviderBindingWithNoType() {
		'''
			module my.empty {
				bindings {
					provide
				}
			}
		'''.assertNoException
	}

	@Test
	def void testValueBindingWithNoType() {
		'''
			module my.empty {
				bindings {
					value
				}
			}
		'''.assertNoException
	}

	@Test
	def void testValueBindingWithUnknownType() {
		'''
			module my.empty {
				bindings {
					value Foo TableColumnWeights
				}
			}
		'''.assertNoException
	}

	@Test
	def void testResourceManagerInitializeWithNoBody() {
		'''
			module my.empty {
				resourceManager {
					initializeResource
				}
			}
		'''.assertNoException
	}

	@Test
	def void testResourceManagerSaveWithNoBody() {
		'''
			module my.empty {
				resourceManager {
					saveSave
				}
			}
		'''.assertNoException
	}

	def private assertNoException(CharSequence s) {
		s.parse.validate
		// there must be no error in the log either
		assertTrue("Some error was reported in the LOG", logListener.events.empty)
	}
}
