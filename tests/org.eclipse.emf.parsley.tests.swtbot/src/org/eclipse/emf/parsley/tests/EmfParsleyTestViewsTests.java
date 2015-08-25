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
	
	private static final String TEST_MODEL_TREE_FORM_VIEW = "Test Model Tree Form View";

	private static final String TEST_CONTAINER = "Test Container";

	private static final String NEW_OBJECT_FOR_VALIDATION = "Objects For Validation Class For Validation";

	private static final String OBJECT_FOR_VALIDATION = "Class For Validation";

	private static final String NOT_EMPTY_LABEL = "Not Empty";

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
				TEST_MODEL_TREE_FORM_VIEW).getTreeItem(TEST_CONTAINER);
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
