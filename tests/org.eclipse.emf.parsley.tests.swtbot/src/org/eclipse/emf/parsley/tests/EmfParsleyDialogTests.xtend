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

import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(SWTBotJunit4ClassRunner))
public class EmfParsleyDialogTests extends EmfParsleyDialogAbstractTests {

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

}
