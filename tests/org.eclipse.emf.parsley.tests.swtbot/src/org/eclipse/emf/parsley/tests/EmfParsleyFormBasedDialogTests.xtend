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

import org.eclipse.swtbot.forms.finder.SWTFormsBot
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(SWTBotJunit4ClassRunner))
public class EmfParsleyFormBasedDialogTests extends EmfParsleySWTBotAbstractTests {

	@Test
	def void checkOpenDialogOnTreeElements() {
		openEmfEditorOnTestFile(org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.EMF_TREE_EDITOR_OPEN_FORM_DIALOG,
				org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.MY_EXTLIBRARY)
		
		libraryNode.assertDialog(org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.LIBRARY_LABEL) [
			assertFormControlsOfLibraryNode(true)
		]
		
		writerNode.assertDialog(org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.WRITER_LABEL) [
			assertFormControlsOfWriterNode(true)
		]
	}

	@Test
	def void checkDialogDatabinding() {
		openEmfEditorOnTestFile(org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.EMF_TREE_EDITOR_OPEN_FORM_DIALOG,
				org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.MY_EXTLIBRARY)
		
		libraryNode.assertDialogEdit(org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.LIBRARY_LABEL) [
			modifyFormText(org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.LIBRARY_NAME)
		]
		
	}

	def private assertDialog(SWTBotTreeItem item, String dialogTitle, (SWTFormsBot)=>void proc) {
		item.doubleClick
		val shell = org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.bot.shell(dialogTitle)
		shell.activate
		val formbot = new SWTFormsBot(shell.widget)
		proc.apply(formbot)
		org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.bot.button("OK").click()
		waitForShellToClose(shell)
	}

	def private assertDialogEdit(SWTBotTreeItem item, String dialogTitle, (SWTFormsBot)=>void proc) {
		assertDialog(item, dialogTitle, proc)
		assertEditorDirty()
		saveEditor()
	}
	
	private def assertEditorDirty() {
		org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.EMF_TREE_EDITOR_OPEN_FORM_DIALOG.assertEditorDirty
	}
	
	private def saveEditor() {
		getEditor(org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.EMF_TREE_EDITOR_OPEN_FORM_DIALOG).save
	}
	
	def private getRootOfEditor() {
		getRootOfTreeEditor(org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.EMF_TREE_EDITOR_OPEN_FORM_DIALOG, org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.MY_EXT_LIBRARY_PLATFORM_URI)
	}
	
	def private libraryNode() {
		getLibraryNode(getRootOfEditor)
	}

	def private writerNode() {
		getWriterNode(libraryNode)
	}

}
