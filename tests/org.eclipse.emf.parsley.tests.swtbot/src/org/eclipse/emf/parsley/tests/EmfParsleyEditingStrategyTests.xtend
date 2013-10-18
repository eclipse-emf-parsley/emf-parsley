package org.eclipse.emf.parsley.tests

import org.eclipse.swt.widgets.Display
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(SWTBotJunit4ClassRunner))
public class EmfParsleyEditingStrategyTests extends EmfParsleyDialogTests {

	@Before
	def void setup() {
		editorName = EMF_TREE_EDITOR_OPEN_DIALOG_UNDOABLE
	}

	@Test
	def void checkDialogCancel() {
		openEmfEditorOnTestFile(editorName, MY_EXTLIBRARY)

		libraryNode.assertDialogEditAndCancel(LIBRARY_LABEL) [ |
			modifyText(LIBRARY_NAME)
		]

		// library label has not changed
		libraryNode
	}

	@Test
	def void checkDialogEditAndUndo() {
		openEmfEditorOnTestFile(editorName,
				MY_EXTLIBRARY)
		
		libraryNode.assertDialogEdit(LIBRARY_LABEL) [|
			modifyText(LIBRARY_NAME)
		]
		
		"Edit Library".undo
		
		assertEditorDirty
		saveEditor()

		// library label has not changed
		libraryNode
	}

	def protected assertDialogEditAndCancel(SWTBotTreeItem item, String dialogTitle, ()=>void proc) {
		assertDialogCancel(item, dialogTitle, proc)
		assertEditorNotDirty()
	}

	def protected assertDialogCancel(SWTBotTreeItem item, String dialogTitle, ()=>void proc) {
		item.doubleClick
		val shell = bot.shell(dialogTitle)
		proc.apply()
		// tree refresh (upon Cancel and rollback) is asynchronous so we must
		// wait for refresh to end, with a sync operation
		Display.getDefault().syncExec() [|
			bot.button("Cancel").click()
			shell.waitForShellToClose
		]
	}

	def protected assertEditorNotDirty() {
		editorName.assertEditorNotDirty
	}
}
