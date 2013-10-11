package org.eclipse.emf.parsley.tests

import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(SWTBotJunit4ClassRunner))
public class EmfParsleyDialogTests extends EmfParsleyAbstractTests {

	@Test
	def void checkOpenDialog() {
		libraryNode.doubleClick
		bot.shell(LIBRARY_LABEL)
		bot.button("OK").click();
	}
	
	def private libraryNode() {
		getLibraryNode(getRootOfEditorTree(EMF_TREE_EDITOR_OPEN_DIALOG,
				MY_EXTLIBRARY, MY_EXT_LIBRARY_PLATFORM_URI))
	}

}
