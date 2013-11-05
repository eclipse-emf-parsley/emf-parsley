package org.eclipse.emf.parsley.tests

import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(SWTBotJunit4ClassRunner))
public class EmfParsleyDialogTests extends EmfParsleyAbstractTests {

	protected var editorName = EMF_TREE_EDITOR_OPEN_DIALOG

	@Test
	def void checkOpenDialogOnTreeElements() {
		openEmfEditorOnTestFile(editorName,
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
		openEmfEditorOnTestFile(editorName,
				MY_EXTLIBRARY)
		
		libraryNode.assertDialogEdit(LIBRARY_LABEL) [|
			modifyText(LIBRARY_NAME)
		]
		
	}

	def protected assertDialog(SWTBotTreeItem item, String dialogTitle, ()=>void proc) {
		item.doubleClick
		bot.shell(dialogTitle).activate
		proc.apply()
		bot.button("OK").click()
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
