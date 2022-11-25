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
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfParsleyDslWizardsTests extends EmfParsleyDslUiAbstractTests {
	private final String TREE_FORM_TEMPLATE = "Saveable Tree Form View";

	private final String TREE_TEMPLATE = "Saveable Tree View";

	private final String TREE_WITH_COLUMNS_TEMPLATE = "Saveable Tree With Columns View";

	private final String TABLE_FORM_TEMPLATE = "Saveable Table Form View";

	private final String TABLE_TEMPLATE = "Saveable Table View";

	private final String ONSELECTION_TREE_TEMPLATE = "On selection Tree View";

	private final String ONSELECTION_TREE_FORM_TEMPLATE = "On selection Tree Form View";

	private final String ONSELECTION_TABLE_TEMPLATE = "On selection Table View";

	private final String ONSELECTION_TABLE_FORM_TEMPLATE = "On selection Table Form View";

	private final String ONSELECTION_FORM_TEMPLATE = "On selection Form View";

	@Test
	public void canCreateDslProjectWithWizard() throws Exception {
		createDslProjectWithWizard();
	}

	@Test
	public void canCreateDslProjectWithWizardFromBadProjectName1() throws Exception {
		createDslProjectWithWizardFromBadProjectName1();
		assertNoIssuesInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateDslProjectWithWizardAndTreeFormTemplate() throws Exception {
		createProjectWithTemplateInWorkspace(EmfParsleySWTBotAbstractTests.EMF_PARSLEY_CATEGORY,
				EmfParsleySWTBotAbstractTests.NEW_EMF_PARSLEY_DSL_PROJECT, TEST_PROJ_NAME,
				TREE_FORM_TEMPLATE);
		assertNoIssuesInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateDslProjectWithWizardAndTreeTemplate() throws Exception {
		createProjectWithTemplateInWorkspace(EmfParsleySWTBotAbstractTests.EMF_PARSLEY_CATEGORY,
				EmfParsleySWTBotAbstractTests.NEW_EMF_PARSLEY_DSL_PROJECT, TEST_PROJ_NAME, TREE_TEMPLATE);
		assertNoIssuesInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateDslProjectWithWizardAndTableFormTemplate() throws Exception {
		createProjectWithTemplateInWorkspace(EmfParsleySWTBotAbstractTests.EMF_PARSLEY_CATEGORY,
				EmfParsleySWTBotAbstractTests.NEW_EMF_PARSLEY_DSL_PROJECT, TEST_PROJ_NAME,
				TABLE_FORM_TEMPLATE);
		assertNoIssuesInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateDslProjectWithWizardAndTableTemplate() throws Exception {
		createProjectWithTemplateInWorkspace(EmfParsleySWTBotAbstractTests.EMF_PARSLEY_CATEGORY,
				EmfParsleySWTBotAbstractTests.NEW_EMF_PARSLEY_DSL_PROJECT, TEST_PROJ_NAME, TABLE_TEMPLATE);
		assertNoIssuesInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateDslProjectWithWizardAndTreeWithColumnsTemplate() throws Exception {
		createProjectWithTemplateInWorkspace(EmfParsleySWTBotAbstractTests.EMF_PARSLEY_CATEGORY,
				EmfParsleySWTBotAbstractTests.NEW_EMF_PARSLEY_DSL_PROJECT, TEST_PROJ_NAME,
				TREE_WITH_COLUMNS_TEMPLATE);
		assertNoIssuesInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateDslProjectWithWizardAndOnselectionTreeTemplate() throws Exception {
		createProjectWithTemplateInWorkspace(EmfParsleySWTBotAbstractTests.EMF_PARSLEY_CATEGORY,
				EmfParsleySWTBotAbstractTests.NEW_EMF_PARSLEY_DSL_PROJECT, TEST_PROJ_NAME,
				ONSELECTION_TREE_TEMPLATE);
		assertNoIssuesInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateDslProjectWithWizardAndOnselectionTreeFormTemplate() throws Exception {
		createProjectWithTemplateInWorkspace(EmfParsleySWTBotAbstractTests.EMF_PARSLEY_CATEGORY,
				EmfParsleySWTBotAbstractTests.NEW_EMF_PARSLEY_DSL_PROJECT, TEST_PROJ_NAME,
				ONSELECTION_TREE_FORM_TEMPLATE);
		assertNoIssuesInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateDslProjectWithWizardAndOnselectionTableTemplate() throws Exception {
		createProjectWithTemplateInWorkspace(EmfParsleySWTBotAbstractTests.EMF_PARSLEY_CATEGORY,
				EmfParsleySWTBotAbstractTests.NEW_EMF_PARSLEY_DSL_PROJECT, TEST_PROJ_NAME,
				ONSELECTION_TABLE_TEMPLATE);
		assertNoIssuesInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateDslProjectWithWizardAndOnselectionFormTemplate() throws Exception {
		createProjectWithTemplateInWorkspace(EmfParsleySWTBotAbstractTests.EMF_PARSLEY_CATEGORY,
				EmfParsleySWTBotAbstractTests.NEW_EMF_PARSLEY_DSL_PROJECT, TEST_PROJ_NAME,
				ONSELECTION_FORM_TEMPLATE);
		assertNoIssuesInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateDslProjectWithWizardAndOnselectionTableFormTemplate() throws Exception {
		createProjectWithTemplateInWorkspace(EmfParsleySWTBotAbstractTests.EMF_PARSLEY_CATEGORY,
				EmfParsleySWTBotAbstractTests.NEW_EMF_PARSLEY_DSL_PROJECT, TEST_PROJ_NAME,
				ONSELECTION_TABLE_FORM_TEMPLATE);
		assertNoIssuesInProjectAfterAutoBuild();
	}
}
