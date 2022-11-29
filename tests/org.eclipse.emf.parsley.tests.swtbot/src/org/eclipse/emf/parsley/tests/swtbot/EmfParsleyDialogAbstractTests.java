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

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.xtext.xbase.lib.Exceptions;

public abstract class EmfParsleyDialogAbstractTests extends EmfParsleySWTBotAbstractTests {
	protected String editorName = EMF_TREE_EDITOR_OPEN_DIALOG;

	protected void assertDialog(SWTBotTreeItem item, String dialogTitle, Runnable proc) {
		item.doubleClick();
		SWTBotShell shell = bot.shell(dialogTitle);
		shell.activate();
		proc.run();
		bot.button("OK").click();
		waitForShellToClose(shell);
	}

	protected void assertDialogEdit(SWTBotTreeItem item, String dialogTitle, Runnable proc) {
		assertDialog(item, dialogTitle, proc);
		assertEditorDirty();
		saveEditor();
	}

	protected SWTBotEditor assertEditorDirty() {
		return assertEditorDirty(editorName);
	}

	protected void saveEditor() {
		getEditor(editorName).save();
	}

	protected SWTBotTreeItem getRootOfEditor() {
		try {
			return getRootOfTreeEditor(editorName, MY_EXT_LIBRARY_PLATFORM_URI);
		} catch (Throwable _e) {
			throw Exceptions.sneakyThrow(_e);
		}
	}

	protected SWTBotTreeItem libraryNode() throws Exception {
		return getLibraryNode(getRootOfEditor());
	}

	protected SWTBotTreeItem libraryModifiedNode() {
		return getLibraryModifiedNode(getRootOfEditor());
	}

	protected SWTBotTreeItem writerNode() throws Exception {
		return getWriterNode(libraryNode());
	}
}
