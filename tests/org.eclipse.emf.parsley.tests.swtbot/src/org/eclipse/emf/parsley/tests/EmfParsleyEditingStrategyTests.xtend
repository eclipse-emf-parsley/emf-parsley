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

import org.eclipse.swt.widgets.Display
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(SWTBotJunit4ClassRunner))
public class EmfParsleyEditingStrategyTests extends EmfParsleyDialogAbstractTests {

	@Before
	def void setup() {
		editorName = org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.EMF_TREE_EDITOR_OPEN_DIALOG_UNDOABLE
	}

	@Test
	def void checkDialogCancel() {
		openEmfEditorOnTestFile(editorName, org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.MY_EXTLIBRARY)

		libraryNode.assertDialogEditAndCancel(org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.LIBRARY_LABEL) [ |
			modifyText(org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.LIBRARY_NAME)
		]

		// library label has not changed
		libraryNode
	}

	@Test
	def void checkDialogEditAndUndoAndRedo() {
		openEmfEditorOnTestFile(editorName,
				org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.MY_EXTLIBRARY)
		
		libraryNode.assertDialogEdit(org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.LIBRARY_LABEL) [|
			modifyText(org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.LIBRARY_NAME)
		]
		
		editMenuItemForModifiedLibrary().undo
		
		assertEditorDirty
		saveEditor()

		// library label has not changed
		libraryNode()
		
		editMenuItemForModifiedLibrary().redo
		
		assertEditorDirty
		saveEditor()

		// library label has been changed by Redo
		libraryModifiedNode
	}
	
	protected def editMenuItemForModifiedLibrary() {
		"Edit " + org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.LIBRARY_LABEL + " MODIFIED"
	}

	def protected assertDialogEditAndCancel(SWTBotTreeItem item, String dialogTitle, ()=>void proc) {
		assertDialogCancel(item, dialogTitle, proc)
		assertEditorNotDirty()
	}

	def protected assertDialogCancel(SWTBotTreeItem item, String dialogTitle, ()=>void proc) {
		item.doubleClick
		val shell = org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.bot.shell(dialogTitle)
		shell.activate
		proc.apply()
		// tree refresh (upon Cancel and rollback) is asynchronous so we must
		// wait for refresh to end, with a sync operation
		Display.getDefault().syncExec() [|
			org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.bot.button("Cancel").click()
			shell.waitForShellToClose
		]
	}

	def protected assertEditorNotDirty() {
		editorName.assertEditorNotDirty
	}
}
