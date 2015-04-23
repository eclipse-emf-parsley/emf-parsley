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
package org.eclipse.emf.parsley.tests;


import org.eclipse.emf.parsley.wizards.NewEmfParsleyProjectSupport;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Lorenzo Bettini
 * 
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfParsleyWizardsTests extends EmfParsleySWTBotAbstractTests {
	
	public EmfParsleyWizardsTests() {
		// just to avoid code coverage of protected constructor
		new NewEmfParsleyProjectSupport() {
			
		};
	}

	@Test
	public void canCreateProjectWithWizard() throws Exception {
		createProjectInWorkspace(EMF_PARSLEY_CATEGORY,
				NEW_EMF_PARSLEY_PROJECT, EMF_PARSLEY_PROJECT);
		// bot.sleep(300000);
		assertNoErrorsInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateProjectWithWizardWithNoSelectedView() throws Exception {
		createProjectInWorkspaceWithView(EMF_PARSLEY_CATEGORY,
				NEW_EMF_PARSLEY_PROJECT, EMF_PARSLEY_PROJECT, null);
		assertNoErrorsInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateProjectWithWizardWithSelectedTreeFormView()
			throws Exception {
		createProjectInWorkspaceWithView(EMF_PARSLEY_CATEGORY,
				NEW_EMF_PARSLEY_PROJECT, EMF_PARSLEY_PROJECT,
				"Create a TreeFormView");
		assertNoErrorsInProjectAfterAutoBuild();
	}

	@Test
	public void canCreateProjectWithWizardWithSelectedTableView()
			throws Exception {
		createProjectInWorkspaceWithView(EMF_PARSLEY_CATEGORY,
				NEW_EMF_PARSLEY_PROJECT, EMF_PARSLEY_PROJECT,
				"Create a TableView");
		assertNoErrorsInProjectAfterAutoBuild();
	}

}
