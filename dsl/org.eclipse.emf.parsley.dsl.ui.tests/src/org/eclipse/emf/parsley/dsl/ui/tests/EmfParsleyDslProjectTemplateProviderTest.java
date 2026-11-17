/**
 * Copyright (c) 2026 Lorenzo Bettini and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.parsley.dsl.ui.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.parsley.dsl.ui.wizard.EmfParsleyDslProjectTemplateProvider;
import org.eclipse.xtext.ui.wizard.template.AbstractProjectTemplate;
import org.junit.Test;

public class EmfParsleyDslProjectTemplateProviderTest {

	@Test
	public void providesEmptyAndAllViewTemplates() {
		AbstractProjectTemplate[] templates = new EmfParsleyDslProjectTemplateProvider().getProjectTemplates();
		assertEquals(11, templates.length);
		assertEquals("Empty EMF Parsley Project", templates[0].getLabel());
		for (AbstractProjectTemplate template : templates) {
			assertEquals(1, template.getVariables().size());
			assertTrue(template.getVariables().get(0).getLabel().contains("RAP"));
		}
	}
}
