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

import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem

public abstract class EmfParsleyDialogAbstractTests extends EmfParsleySWTBotAbstractTests {

	protected var editorName = org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.EMF_TREE_EDITOR_OPEN_DIALOG

	def protected assertDialog(SWTBotTreeItem item, String dialogTitle, ()=>void proc) {
		item.doubleClick
		val shell = org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.bot.shell(dialogTitle)
		shell.activate
		proc.apply()
		org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.bot.button("OK").click()
		waitForShellToClose(shell)
	}

	def protected assertDialogEdit(SWTBotTreeItem item, String dialogTitle, ()=>void proc) {
		assertDialog(item, dialogTitle, proc)
		assertEditorDirty()
		saveEditor()
	}
	
	def protected assertEditorDirty() {
		editorName.assertEditorDirty
	}
	
	def protected saveEditor() {
		getEditor(editorName).save
	}
	
	def protected getRootOfEditor() {
		getRootOfTreeEditor(editorName, org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests.MY_EXT_LIBRARY_PLATFORM_URI)
	}
	
	def protected libraryNode() {
		getLibraryNode(getRootOfEditor)
	}

	def protected libraryModifiedNode() {
		getLibraryModifiedNode(getRootOfEditor)
	}

	def protected writerNode() {
		getWriterNode(libraryNode)
	}

}
