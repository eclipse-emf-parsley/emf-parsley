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

import java.util.function.Consumer;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.forms.finder.SWTFormsBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class EmfParsleyFormBasedDialogTests extends EmfParsleySWTBotAbstractTests {
	@Test
	public void checkOpenDialogOnTreeElements() throws Exception {
		openEmfEditorOnTestFile(EMF_TREE_EDITOR_OPEN_FORM_DIALOG,
				MY_EXTLIBRARY);
		assertDialog(libraryNode(), LIBRARY_LABEL, it -> {
			assertFormControlsOfLibraryNode(it, true);
		});
		assertDialog(writerNode(), WRITER_LABEL, it -> {
			assertFormControlsOfWriterNode(it, true);
		});
	}

	@Test
	public void checkDialogDatabinding() throws Exception {
		openEmfEditorOnTestFile(EMF_TREE_EDITOR_OPEN_FORM_DIALOG,
				MY_EXTLIBRARY);
		assertDialogEdit(libraryNode(), LIBRARY_LABEL, it -> {
			modifyFormText(it, LIBRARY_NAME);
		});
	}

	private void assertDialog(SWTBotTreeItem item, String dialogTitle,
			Consumer<SWTFormsBot> proc) {
		item.doubleClick();
		SWTBotShell shell = bot.shell(dialogTitle);
		shell.activate();
		SWTFormsBot formbot = new SWTFormsBot(shell.widget);
		proc.accept(formbot);
		bot.button("OK").click();
		waitForShellToClose(shell);
	}

	private void assertDialogEdit(SWTBotTreeItem item, String dialogTitle,
			Consumer<SWTFormsBot> proc) {
		assertDialog(item, dialogTitle, proc);
		assertEditorDirty();
		saveEditor();
	}

	private SWTBotEditor assertEditorDirty() {
		return assertEditorDirty(EMF_TREE_EDITOR_OPEN_FORM_DIALOG);
	}

	private void saveEditor() {
		getEditor(EMF_TREE_EDITOR_OPEN_FORM_DIALOG).save();
	}

	private SWTBotTreeItem getRootOfEditor() throws Exception {
			return getRootOfTreeEditor(EMF_TREE_EDITOR_OPEN_FORM_DIALOG,
					MY_EXT_LIBRARY_PLATFORM_URI);
	}

	private SWTBotTreeItem libraryNode() throws Exception {
		return getLibraryNode(getRootOfEditor());
	}

	private SWTBotTreeItem writerNode() throws Exception {
		return getWriterNode(libraryNode());
	}
}
