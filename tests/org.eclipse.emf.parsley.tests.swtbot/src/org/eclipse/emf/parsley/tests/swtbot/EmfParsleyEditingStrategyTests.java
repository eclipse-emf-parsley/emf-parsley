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

import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfParsleyEditingStrategyTests extends EmfParsleyDialogAbstractTests {
	@Before
	public void setup() {
		editorName = EMF_TREE_EDITOR_OPEN_DIALOG_UNDOABLE;
	}

	@Test
	public void checkDialogCancel() throws Exception {
		openEmfEditorOnTestFile(editorName, MY_EXTLIBRARY);
		assertDialogEditAndCancel(libraryNode(),
				LIBRARY_LABEL,
				() -> {
					modifyText(LIBRARY_NAME);
				});
		// library label has not changed
		libraryNode();
	}

	@Test
	public void checkDialogEditAndUndoAndRedo() throws Exception {
		openEmfEditorOnTestFile(editorName, MY_EXTLIBRARY);
		assertDialogEdit(libraryNode(), LIBRARY_LABEL,
				() -> {
					modifyText(LIBRARY_NAME);
				});
		undo(editMenuItemForModifiedLibrary());
		assertEditorDirty();
		saveEditor();
		// library label has not changed
		libraryNode();
		redo(editMenuItemForModifiedLibrary());
		assertEditorDirty();
		saveEditor();
		// library label has been changed by Redo
		libraryModifiedNode();
	}

	protected String editMenuItemForModifiedLibrary() {
		return "Edit " + LIBRARY_LABEL + " MODIFIED";
	}

	protected SWTBotEditor assertDialogEditAndCancel(SWTBotTreeItem item, String dialogTitle,
			Runnable proc) {
		assertDialogCancel(item, dialogTitle, proc);
		return assertEditorNotDirty();
	}

	protected void assertDialogCancel(SWTBotTreeItem item, String dialogTitle, Runnable proc) {
		item.doubleClick();
		SWTBotShell shell = bot.shell(dialogTitle);
		shell.activate();
		proc.run();
		// tree refresh (upon Cancel and rollback) is asynchronous so we must
		// wait for refresh to end, with a sync operation
		Display.getDefault().syncExec(() -> {
			bot.button("Cancel").click();
			waitForShellToClose(shell);
		});
	}

	protected SWTBotEditor assertEditorNotDirty() {
		return assertEditorNotDirty(editorName);
	}
}
