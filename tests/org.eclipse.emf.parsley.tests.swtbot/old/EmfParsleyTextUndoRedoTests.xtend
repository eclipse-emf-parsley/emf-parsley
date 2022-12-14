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
package org.eclipse.emf.parsley.tests

import org.eclipse.swtbot.swt.finder.SWTBot
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(SWTBotJunit4ClassRunner))
public class EmfParsleyTextUndoRedoTests extends EmfParsleySWTBotAbstractTests {

	protected var editorName = org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.EMF_TREE_EDITOR_OPEN_DIALOG

	@Test
	def void testUndoRedoOnFormText() {
		val editorRoot = prepareForForms
		getLibraryWriterNode(editorRoot).select()
		assertUndoRedo(formBot)
	}

	@Test
	def void testUndoRedoOnDialogText() {
		val editorRoot = prepareForDialog
		getLibraryWriterNode(editorRoot).select().doubleClick
		org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.bot.shell(org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.WRITER_LABEL).activate
		assertUndoRedo(org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.bot)
		org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.bot.button("OK").click()
	}
	
	def assertUndoRedo(SWTBot bot) {
		bot.text(org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.WRITER_FIRSTNAME).typeText("MODIFIED")
		bot.text("MODIFIED"+org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.WRITER_FIRSTNAME).
			undoShortcut // undo last insertion 'D'
		bot.text("MODIFIE"+org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.WRITER_FIRSTNAME).
			redoShortcut // redo last insertion 'D'
		bot.text("MODIFIED"+org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.WRITER_FIRSTNAME)
		
		bot.text("MODIFIED"+org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.WRITER_FIRSTNAME).
			undoShortcut.undoShortcut.undoShortcut // undo last insertion 'IED'
		bot.text("MODIF"+org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.WRITER_FIRSTNAME).
			redoShortcut.redoShortcut // red last insertion 'IE'
		
		bot.text("MODIFIE"+org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.WRITER_FIRSTNAME)
	}

	def private prepareForForms() {
		openTestView(org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.EMF_DETAIL_VIEW);
		// select on the editor's tree
		openEditorAndGetTreeRoot(org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.EMF_TREE_EDITOR,
				org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.MY_EXTLIBRARY, org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.MY_EXT_LIBRARY_PLATFORM_URI);
	}

	def private prepareForDialog() {
		// select on the editor's tree
		openEditorAndGetTreeRoot(org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.EMF_TREE_EDITOR_OPEN_DIALOG,
				org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.MY_EXTLIBRARY, org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.MY_EXT_LIBRARY_PLATFORM_URI);
	}

	def private formBot() {
		formBotFromView(openTestView(org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.EMF_DETAIL_VIEW))
	}

}
