/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.junit4.util;

import static org.eclipse.xtext.xbase.lib.IterableExtensions.join;
import static org.eclipse.xtext.xbase.lib.IterableExtensions.map;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.varia.NullAppender;
import org.junit.Assert;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * A Junit {@link TestRule} for checking that messages are logged.
 *
 * This can be used in Junit test cases, specifying the class that should log messages,
 * and then checking that a specific message has been logged.  Log messages are not shown
 * in the console.
 *
 * <pre>
 * public static class MyTestCase {
 * 	&#064;Rule
 * 	public LogAppenderTestRule logAppender = new LogAppenderTestRule(MyClass.class);
 *
 * 	&#064;Test
 * 	public void testMessageHasBeenLogged() {
 * 		MyClass m = new MyClass();
 * 		m.callMethodThatLogsSomething();
 * 		logAppender.assertContainsMessage("message that should be logged");
 * 		// ...
 * 	}
 * }
 * </pre>
 *
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class LogAppenderTestRule implements TestRule {

	protected Logger logger;

	private Class<?> clazz;

	private LogListener logListener;

	public LogAppenderTestRule(Class<?> clazz) {
		super();
		this.clazz = clazz;
	}

	@Override
	public Statement apply(Statement base, Description description) {
		return statement(base);
	}

	private Statement statement(final Statement base) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				before();
				try {
					base.evaluate();
				} finally {
					after();
				}
			}
		};
	}

	protected void before() {
		logger = Logger.getLogger(clazz);

		logger.removeAllAppenders();
		// avoid print errors on the console
		logger.setAdditivity(false);
		logListener = new LogListener();
		logger.addAppender(logListener);
	}

	protected void after() {
		logger.removeAppender(logListener);
		// reset print errors on the console
		logger.setAdditivity(true);
	}

	public void assertContainsMessage(String messagePart) {
		String eventsToString = eventsToString();

		Assert.assertTrue("No messagePart found in " + eventsToString,
			eventsToString.contains(messagePart)
		);
	}

	protected String eventsToString() {
		return join(map(logListener.getEvents(), it -> it.getMessage().toString()), ",");
	}

	public void assertEmpty() {
		Assert.assertTrue("Found messages: " + eventsToString(),
			logListener.getEvents().size() == 0
		);
	}

	public Logger getLogger() {
		return logger;
	}

	static class LogListener extends NullAppender {

		private List<LoggingEvent> events = new ArrayList<>();

		public List<LoggingEvent> getEvents() {
			return events;
		}

		@Override
		public void doAppend(LoggingEvent event) {
			events.add(event);
		}
	}

}
