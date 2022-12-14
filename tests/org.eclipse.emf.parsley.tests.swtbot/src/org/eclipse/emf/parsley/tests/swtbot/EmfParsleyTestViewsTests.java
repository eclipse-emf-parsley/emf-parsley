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
package org.eclipse.emf.parsley.tests.swtbot;

import org.eclipse.swtbot.forms.finder.SWTFormsBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Lorenzo Bettini
 * 
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfParsleyTestViewsTests extends EmfParsleySWTBotAbstractTests {

	@Before
	public void setupProject() {
		createMyTestProject();
	}

	@Test
	public void testEmptyResourceIsInitialized() {
		openTestModelTreeFormView();
		getTestContainerNode();
	}

	@Test
	public void testDialogErrorReporterIsShownWhenSavingResourceWithValidationErrors() {
		openTestModelTreeFormView();
		createNewObjectForValidation();
		saveTestModelTreeFormView();
		getValidationErrorsDialog();
		bot.button("OK").click();
		assertSaveableViewIsDirty(true, TEST_MODEL_TREE_FORM_VIEW);
	}

	@Test
	public void testSaveResourceWithoutValidationErrors() {
		openTestModelTreeFormView();
		createNewObjectForValidation();
		getTestModelTreeFormViewForm().textWithLabel(NOT_EMPTY_LABEL).setText("a");
		assertSaveableViewIsDirty(true, TEST_MODEL_TREE_FORM_VIEW);
		saveTestModelTreeFormView();
		assertSaveableViewIsDirty(false, TEST_MODEL_TREE_FORM_VIEW);
	}

	protected void saveTestModelTreeFormView() {
		saveViewAsync(TEST_MODEL_TREE_FORM_VIEW);
	}

	protected void openTestModelTreeFormView() {
		openTestView(TEST_MODEL_TREE_FORM_VIEW);
	}

	protected SWTBotTreeItem createNewObjectForValidation() {
		clickOnContextMenu(getTestContainerNode(), NEW_CHILD, NEW_OBJECT_FOR_VALIDATION);
		assertSaveableViewIsDirty(true, TEST_MODEL_TREE_FORM_VIEW);
		return getTestContainerNode().getNode(OBJECT_FOR_VALIDATION);
	}
	
	protected SWTBotTreeItem getTestContainerNode() {
		return getRootOfTreeFromView(
				TEST_MODEL_TREE_FORM_VIEW).getTreeItem(TEST_CONTAINER_LABEL);
	}

	protected SWTBotShell getValidationErrorsDialog() {
		SWTBotShell shell = bot.shell("Validation Errors");
		shell.activate();
		return shell;
	}

	protected SWTFormsBot getTestModelTreeFormViewForm() {
		return formBotFromView(bot.viewByTitle(TEST_MODEL_TREE_FORM_VIEW));
	}
}
