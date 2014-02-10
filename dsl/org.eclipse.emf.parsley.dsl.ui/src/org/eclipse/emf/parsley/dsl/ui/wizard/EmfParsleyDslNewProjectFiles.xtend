/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.ui.wizard

import org.eclipse.emf.parsley.generator.common.EmfParsleyProjectFilesGenerator

class EmfParsleyDslNewProjectFiles extends EmfParsleyProjectFilesGenerator {
	
	def exampleDslFile(String projectName)
'''
import java.util.*

/* «projectName» Emf Parsley Dsl Module file */
module «projectName» {
	
}
'''

	def dslFileWithView(String projectName, String viewId)
'''
import java.util.*

/* «projectName» Emf Parsley Dsl Module file */
module «projectName» {
	
	parts {
		viewpart «projectName» {
			viewname "«projectName.prefixFromProject»"
			viewclass «viewId»
		}
	}
	
}
'''
}