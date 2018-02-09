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
package org.eclipse.emf.parsley.dsl.generator

import com.google.inject.Inject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.parsley.dsl.jvmmodel.EmfParsleyDslGeneratorUtils
import org.eclipse.emf.parsley.dsl.model.Module
import org.eclipse.emf.parsley.dsl.model.PartSpecification
import org.eclipse.emf.parsley.dsl.model.ViewSpecification
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.naming.IQualifiedNameProvider
import static org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider.*

class EmfParsleyDslPluginXmlGenerator implements IGenerator {

	@Inject extension EmfParsleyDslGeneratorUtils
	@Inject extension IQualifiedNameProvider

	override doGenerate(Resource resource, IFileSystemAccess fsa) {
		for (module : resource.allContents.toIterable.filter(typeof(Module))) {
			val contents = module.generatePluginXml
			if (contents.length > 0) {
				fsa.generateFile(
					module.fullyQualifiedName.toString.replace('.', '/') + "/" + PLUGIN_XML_GEN_FILE,
					contents
				)
			}
		}
	}

	def generatePluginXml(Module module) {
		if (!module.shouldGenerateExtensions) {
			return ''''''
		} else {
			'''
<extension
      point="org.eclipse.ui.views">
   «module.partsSpecifications.parts.map[generateExtensionPoint].join("")»
</extension>
'''.generatePluginXml
		}
	}

	def CharSequence generateExtensionPoint(PartSpecification partSpecification) {
		if (partSpecification instanceof ViewSpecification) {
'''
<view
      category="«if (partSpecification.category.nullOrEmpty)
  	"org.eclipse.emf.parsley" else
    partSpecification.category»"
      class="«executableExtensionFactoryQN(partSpecification)»:«partSpecification.type.identifier»"
      id="«partSpecification.id»"
      name="«partSpecification.viewName»"
      restorable="true">
</view>
'''
		}
	}

	def generatePluginXml(CharSequence contents) {
		'''
<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.4"?>
<plugin>
   «contents»
</plugin>
'''
	}
}
