package org.eclipse.emf.parsley.tests

import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(SWTBotJunit4ClassRunner))
public class EmfParsleyDialogTests extends EmfParsleyAbstractTests {

	@Test
	def void checkOpenDialogOnTreeElements() {
		openEmfEditorOnTestFile(EMF_TREE_EDITOR_OPEN_DIALOG,
				MY_EXTLIBRARY)
		
		libraryNode.assertDialog(LIBRARY_LABEL) [|
			assertDialogControlsOfLibraryNode(true)
		]
		
		writerNode.assertDialog(WRITER_LABEL) [|
			assertDialogControlsOfWriterNode(true)
		]
	}

	@Test
	def void checkDialogDatabinding() {
		openEmfEditorOnTestFile(EMF_TREE_EDITOR_OPEN_DIALOG,
				MY_EXTLIBRARY)
		
		libraryNode.assertDialogEdit(LIBRARY_LABEL) [|
			modifyText(LIBRARY_NAME)
		]
		
	}

	def private assertDialog(SWTBotTreeItem item, String dialogTitle, ()=>void proc) {
		item.doubleClick
		bot.shell(dialogTitle)
		proc.apply()
		bot.button("OK").click()
	}

	def private assertDialogEdit(SWTBotTreeItem item, String dialogTitle, ()=>void proc) {
		assertDialog(item, dialogTitle, proc)
		assertEditorDirty()
		saveEditor()
	}
	
	private def assertEditorDirty() {
		EMF_TREE_EDITOR_OPEN_DIALOG.assertEditorDirty
	}
	
	private def saveEditor() {
		getEditor(EMF_TREE_EDITOR_OPEN_DIALOG).save
	}
	
	def private getRootOfEditor() {
		getRootOfTreeEditor(EMF_TREE_EDITOR_OPEN_DIALOG, MY_EXT_LIBRARY_PLATFORM_URI)
	}
	
	def private libraryNode() {
		getLibraryNode(getRootOfEditor)
	}

	def private writerNode() {
		getWriterNode(libraryNode)
	}

}