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

import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem

abstract class EmfParsleyDialogAbstractTests extends EmfParsleySWTBotAbstractTests {

	protected var editorName = EMF_TREE_EDITOR_OPEN_DIALOG

	def protected assertDialog(SWTBotTreeItem item, String dialogTitle, ()=>void proc) {
		item.doubleClick
		val shell = bot.shell(dialogTitle)
		shell.activate
		proc.apply()
		bot.button("OK").click()
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
		getRootOfTreeEditor(editorName, MY_EXT_LIBRARY_PLATFORM_URI)
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
