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

import static extension org.eclipse.emf.parsley.generator.common.EmfParsleyProjectFilesGeneratorUtil.*

class EmfParsleyDslNewProjectFiles extends EmfParsleyProjectFilesGenerator {

	def genEmptyDslModule(String projectName) {
		genDslModule(projectName, "")
	}
	
	def genDslModule(String projectName, CharSequence additional)
'''
/* «projectName» Emf Parsley Dsl Module file */
module «projectName» {
	
	«additional»
}
'''

	def genDslModuleWithViewPart(String projectName, String viewFQN, String configuratorContents) {
		'''
		import «viewFQN»

		«genDslModule(projectName, 
			'''
			parts {
				viewpart «projectName» {
					viewname "«projectName.buildClassNameFromProject»"
					viewclass «viewFQN.buildClassNameFromProject»
				}
			}
			
			«configuratorContents»
			'''
		)»
		'''
	}

	def genConfigurator(CharSequence contents) {
		'''
		configurator {
			«contents»
		}
		'''
	}

	def genResourceURI(CharSequence className) {
		'''
		resourceURI {
			«className» -> {
				// TODO create and return a org.eclipse.emf.common.util.URI
				return null;
			}
		}
		'''
	}

	def genEClass(CharSequence className) {
		'''
		eClass {
			«className» -> {
				// TODO return the EClass of objects to be shown
			}
		}
		'''
	}

	def genFeaturesEClass(CharSequence className) {
		'''
		eClass {
			«className» -> {
				// TODO return the EClass with the features to be shown
			}
		}
		'''
	}

	def genResourceManager(CharSequence contents) {
		'''
		resourceManager {
			«contents»
		}
		'''
	}

	def genInitializeResource() {
		'''
		initializeResource {
			// Optional: initialize an empty Resource
			// 'it' is of type Resource
			// e.g., it.getContents += myFactory.createMyClass
		}
		'''
	}

	def genViewClass(String projectName, String className, String extendsClass)
'''
package «projectName»;

public class «className» extends «extendsClass» {
	
}
'''

	
}