package org.eclipse.emf.parsley.tests

import org.eclipse.swtbot.forms.finder.SWTFormsBot
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(SWTBotJunit4ClassRunner))
public class EmfParsleyFormBasedDialogTests extends EmfParsleyAbstractTests {

	@Test
	def void checkOpenDialogOnTreeElements() {
		openEmfEditorOnTestFile(EMF_TREE_EDITOR_OPEN_FORM_DIALOG,
				MY_EXTLIBRARY)
		
		libraryNode.assertDialog(LIBRARY_LABEL) [
			assertFormControlsOfLibraryNode(true)
		]
		
		writerNode.assertDialog(WRITER_LABEL) [
			assertFormControlsOfWriterNode(true)
		]
	}

	@Test
	def void checkDialogDatabinding() {
		openEmfEditorOnTestFile(EMF_TREE_EDITOR_OPEN_FORM_DIALOG,
				MY_EXTLIBRARY)
		
		libraryNode.assertDialogEdit(LIBRARY_LABEL) [
			modifyFormText(LIBRARY_NAME)
		]
		
	}

	def private assertDialog(SWTBotTreeItem item, String dialogTitle, (SWTFormsBot)=>void proc) {
		item.doubleClick
		val shell = bot.shell(dialogTitle)
		shell.activate
		val formbot = new SWTFormsBot(shell.widget)
		proc.apply(formbot)
		bot.button("OK").click()
	}

	def private assertDialogEdit(SWTBotTreeItem item, String dialogTitle, (SWTFormsBot)=>void proc) {
		assertDialog(item, dialogTitle, proc)
		assertEditorDirty()
		saveEditor()
	}
	
	private def assertEditorDirty() {
		EMF_TREE_EDITOR_OPEN_FORM_DIALOG.assertEditorDirty
	}
	
	private def saveEditor() {
		getEditor(EMF_TREE_EDITOR_OPEN_FORM_DIALOG).save
	}
	
	def private getRootOfEditor() {
		getRootOfTreeEditor(EMF_TREE_EDITOR_OPEN_FORM_DIALOG, MY_EXT_LIBRARY_PLATFORM_URI)
	}
	
	def private libraryNode() {
		getLibraryNode(getRootOfEditor)
	}

	def private writerNode() {
		getWriterNode(libraryNode)
	}

}
