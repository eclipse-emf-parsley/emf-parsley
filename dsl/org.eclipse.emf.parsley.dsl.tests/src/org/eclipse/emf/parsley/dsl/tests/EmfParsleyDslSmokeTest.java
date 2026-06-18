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
package org.eclipse.emf.parsley.dsl.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.eclipse.emf.parsley.dsl.model.Model;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.jvmmodel.JvmModelAssociator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

@RunWith(XtextRunner.class)
@InjectWith(EmfParsleyDslInjectorProvider.class)
public class EmfParsleyDslSmokeTest {

	@Inject
	private ParseHelper<Model> parseHelper;

	@Inject
	private ValidationTestHelper validationTestHelper;

	private static final Logger LOG = Logger.getLogger(JvmModelAssociator.class);

	/**
	 * JvmModelAssociator does not throw exceptions but logs possible
	 * errors; we use this class to record possible error events
	 */
	static class LogListener extends ConsoleAppender {

		public final List<LoggingEvent> events = new ArrayList<>();

		@Override
		public synchronized void doAppend(LoggingEvent event) {
			if (event.getLevel() == Level.ERROR) {
				events.add(event);
			}
		}

	}

	private LogListener logListener;

	@Before
	public void createAppender() {
		logListener = new LogListener();
		LOG.addAppender(logListener);
	}

	@After
	public void removeAppender() {
		LOG.removeAppender(logListener);
	}

	@Test
	public void testModuleWithNoName() throws Exception {
		assertNoException("""
			module
		""");
	}

	@Test
	public void testExtendsWithoutType() throws Exception {
		assertNoException("""
			module my.empty extends
		""");
	}

	@Test
	public void testTypeBindingWithNoType() throws Exception {
		assertNoException("""
			module my.empty {
				bindings {
					type
				}
			}
		""");
	}

	@Test
	public void testProviderBindingWithNoType() throws Exception {
		assertNoException("""
			module my.empty {
				bindings {
					provide
				}
			}
		""");
	}

	@Test
	public void testValueBindingWithNoType() throws Exception {
		assertNoException("""
			module my.empty {
				bindings {
					value
				}
			}
		""");
	}

	@Test
	public void testValueBindingWithUnknownType() throws Exception {
		assertNoException("""
			module my.empty {
				bindings {
					value Foo TableColumnWeights
				}
			}
		""");
	}

	@Test
	public void testResourceManagerInitializeWithNoBody() throws Exception {
		assertNoException("""
			module my.empty {
				resourceManager {
					initializeResource
				}
			}
		""");
	}

	@Test
	public void testResourceManagerSaveWithNoBody() throws Exception {
		assertNoException("""
			module my.empty {
				resourceManager {
					saveSave
				}
			}
		""");
	}

	private void assertNoException(CharSequence s) throws Exception {
		validationTestHelper.validate(parseHelper.parse(s));
		// there must be no error in the log either
		assertTrue("Some error was reported in the LOG", logListener.events.isEmpty());
	}
}
