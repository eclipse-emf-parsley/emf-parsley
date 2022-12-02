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

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public abstract class EmfParsleyDslUiAbstractTests extends EmfParsleySWTBotAbstractTests {
	protected final String TEST_PROJ_NAME = "my.emfparsley.proj";

	protected final String TEST_PROJ_NAME_BAD1 = "My-EmfParsley--project";

	@Override
	public void runAfterEveryTest() throws Exception {
		super.runAfterEveryTest();
		clearJdtIndex();
	}

	protected void clearJdtIndex() {
		var jdtMetadata = JavaCore.getPlugin().getStateLocation().toFile();
		bot.waitUntil(new DefaultCondition() {
			
			@Override
			public boolean test() {
				System.err.println("Clean up index " + jdtMetadata.getAbsolutePath());
				try {
					FileUtils.deleteDirectory(jdtMetadata);
				} catch (IOException e) {
					System.err.println("retrying due to exception while cleaning");
					return false;
				}
				return true;
			}
			
			@Override
			public String getFailureMessage() {
				return "cannot delete " + jdtMetadata;
			}
		});
	}

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
