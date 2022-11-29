/**
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 */
package org.eclipse.emf.parsley.tests.swtbot;

import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public abstract class EmfParsleyDslUiAbstractTests extends EmfParsleySWTBotAbstractTests {
	protected final String TEST_PROJ_NAME = "my.emfparsley.proj";

	protected final String TEST_PROJ_NAME_BAD1 = "My-EmfParsley--project";

	public void createDslProjectWithWizard() throws Exception {
		createProjectWithoutTemplateInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_PARSLEY_DSL_PROJECT, TEST_PROJ_NAME);
		assertNoIssuesInProjectAfterAutoBuild();
	}

	public void createDslProjectWithWizardFromBadProjectName1() throws Exception {
		createProjectWithoutTemplateInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_PARSLEY_DSL_PROJECT, TEST_PROJ_NAME_BAD1);
		assertNoIssuesInProjectAfterAutoBuild();
	}
}
