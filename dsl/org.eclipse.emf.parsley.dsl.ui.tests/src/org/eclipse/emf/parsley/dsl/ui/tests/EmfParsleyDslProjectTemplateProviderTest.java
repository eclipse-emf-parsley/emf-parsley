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

	@Test
	public void viewTemplateDescriptionsExplainRequiredChanges() {
		AbstractProjectTemplate[] templates = new EmfParsleyDslProjectTemplateProvider().getProjectTemplates();
		String[] viewTypes = { "tree", "form", "table and a form", "table", "tree and a form",
				"table and a form", "table", "tree and a form", "tree", "tree with columns" };
		for (int i = 1; i < templates.length; i++) {
			String description = templates[i].getDescription();
			assertTrue(templates[i].getLabel(), description.contains("<b>" + viewTypes[i - 1] + "</b>"));
			assertTrue(templates[i].getLabel(), description.contains("This wizard creates a plug-in project"));
			if (i <= 5) {
				assertTrue(templates[i].getLabel(), description.contains("reacts on selection from other components"));
			} else {
				assertTrue(templates[i].getLabel(), description.contains("reads the content from a resource and can save changes"));
				assertTrue(templates[i].getLabel(), description.contains("<li>the resource URI</li>"));
			}
			if (i == 1 || i == 2 || i == 5) {
				assertTrue(templates[i].getLabel(), description.contains("<b>No user change is needed to run the project</b>"));
			} else {
				assertTrue(templates[i].getLabel(), description.contains("<b>The user must specify:</b>"));
			}
			if (i == 3 || i == 4 || i == 6 || i == 7) {
				assertTrue(templates[i].getLabel(), description.contains("<li>the EClass of objects to be shown</li>"));
			} else if (i == 10) {
				assertTrue(description.contains("<li>the EClass with the features to be shown</li>"));
			}
		}
	}
}
