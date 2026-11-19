/**
 * Copyright (c) 2026 Lorenzo Bettini and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.parsley.dsl.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.parsley.dsl.jvmmodel.EmfParsleyDslNaming;
import org.junit.Test;

public class EmfParsleyDslNamingTest {

	@Test
	public void testClassNameFromProject() {
		assertEquals("Name",
			EmfParsleyDslNaming.buildClassNameFromProject("my.project.name")
		);
	}

	@Test
	public void testFQNFromProject() {
		assertEquals("my.project.name.Name",
			EmfParsleyDslNaming.buildFQNFromProject("my.project.name")
		);
		assertEquals("name.Name",
			EmfParsleyDslNaming.buildFQNFromProject("name")
		);
	}
}
