/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.swtbot

import org.eclipse.emf.parsley.wizards.NewEmfParsleyProjectSupport
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(SWTBotJunit4ClassRunner))
class EmfParsleyDslWizardsTests extends EmfParsleyDslUiAbstractTests {

	val TREE_FORM_TEMPLATE = "Saveable Tree Form View"
	val TREE_TEMPLATE = "Saveable Tree View"
	val TREE_WITH_COLUMNS_TEMPLATE = "Saveable Tree With Columns View"
	val TABLE_FORM_TEMPLATE = "Saveable Table Form View"
	val TABLE_TEMPLATE = "Saveable Table View"
	val ONSELECTION_TREE_TEMPLATE = "On selection Tree View"
	val ONSELECTION_TREE_FORM_TEMPLATE = "On selection Tree Form View"
	val ONSELECTION_TABLE_TEMPLATE = "On selection Table View"
	val ONSELECTION_TABLE_FORM_TEMPLATE = "On selection Table Form View"
	val ONSELECTION_FORM_TEMPLATE = "On selection Form View"

	new() {
		// just to avoid code coverage of protected constructor
		new NewEmfParsleyProjectSupport() {
			
		};
	}

	@Test
	def canCreateDslProjectWithWizard() {
		createDslProjectWithWizard
	}

	@Test
	def canCreateDslProjectWithWizardFromBadProjectName1() {
		createDslProjectWithWizardFromBadProjectName1;
		assertNoIssuesInProjectAfterAutoBuild();
	}

	@Test
	def canCreateDslProjectWithWizardAndTreeFormTemplate() {
		createProjectWithTemplateInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_PARSLEY_DSL_PROJECT, TEST_PROJ_NAME, TREE_FORM_TEMPLATE);
		assertNoIssuesInProjectAfterAutoBuild();
	}

	@Test
	def canCreateDslProjectWithWizardAndTreeTemplate() {
		createProjectWithTemplateInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_PARSLEY_DSL_PROJECT, TEST_PROJ_NAME, TREE_TEMPLATE);
		assertNoIssuesInProjectAfterAutoBuild();
	}

	@Test
	def canCreateDslProjectWithWizardAndTableFormTemplate() {
		createProjectWithTemplateInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_PARSLEY_DSL_PROJECT, TEST_PROJ_NAME, TABLE_FORM_TEMPLATE);
		assertNoIssuesInProjectAfterAutoBuild();
	}

	@Test
	def canCreateDslProjectWithWizardAndTableTemplate() {
		createProjectWithTemplateInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_PARSLEY_DSL_PROJECT, TEST_PROJ_NAME, TABLE_TEMPLATE);
		assertNoIssuesInProjectAfterAutoBuild();
	}

	@Test
	def canCreateDslProjectWithWizardAndTreeWithColumnsTemplate() {
		createProjectWithTemplateInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_PARSLEY_DSL_PROJECT, TEST_PROJ_NAME, TREE_WITH_COLUMNS_TEMPLATE);
		assertNoIssuesInProjectAfterAutoBuild();
	}

	@Test
	def canCreateDslProjectWithWizardAndOnselectionTreeTemplate() {
		createProjectWithTemplateInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_PARSLEY_DSL_PROJECT, TEST_PROJ_NAME, ONSELECTION_TREE_TEMPLATE);
		assertNoIssuesInProjectAfterAutoBuild();
	}

	@Test
	def canCreateDslProjectWithWizardAndOnselectionTreeFormTemplate() {
		createProjectWithTemplateInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_PARSLEY_DSL_PROJECT, TEST_PROJ_NAME, ONSELECTION_TREE_FORM_TEMPLATE);
		assertNoIssuesInProjectAfterAutoBuild();
	}

	@Test
	def canCreateDslProjectWithWizardAndOnselectionTableTemplate() {
		createProjectWithTemplateInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_PARSLEY_DSL_PROJECT, TEST_PROJ_NAME, ONSELECTION_TABLE_TEMPLATE);
		assertNoIssuesInProjectAfterAutoBuild();
	}

	@Test
	def canCreateDslProjectWithWizardAndOnselectionFormTemplate() {
		createProjectWithTemplateInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_PARSLEY_DSL_PROJECT, TEST_PROJ_NAME, ONSELECTION_FORM_TEMPLATE);
		assertNoIssuesInProjectAfterAutoBuild();
	}

	@Test
	def canCreateDslProjectWithWizardAndOnselectionTableFormTemplate() {
		createProjectWithTemplateInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_PARSLEY_DSL_PROJECT, TEST_PROJ_NAME, ONSELECTION_TABLE_FORM_TEMPLATE);
		assertNoIssuesInProjectAfterAutoBuild();
	}

}
