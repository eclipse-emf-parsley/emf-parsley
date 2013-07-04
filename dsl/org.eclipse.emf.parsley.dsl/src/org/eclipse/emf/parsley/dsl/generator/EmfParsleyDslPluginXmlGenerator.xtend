package org.eclipse.emf.parsley.dsl.generator

import com.google.inject.Inject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.parsley.dsl.model.Module
import org.eclipse.emf.parsley.dsl.model.PartSpecification
import org.eclipse.emf.parsley.dsl.model.ViewSpecification
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.emf.parsley.dsl.jvmmodel.EmfParsleyDslJvmModelInferrer

import static org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider.*

class EmfParsleyDslPluginXmlGenerator implements IGenerator {
	
	@Inject EmfParsleyDslJvmModelInferrer inferrer;

	override doGenerate(Resource resource, IFileSystemAccess fsa) {
		for(module: resource.allContents.toIterable.filter(typeof(Module))) {
			val contents = module.generatePluginXml
			if (contents?.length > 0)
				fsa.generateFile(
	            	PLUGIN_XML_EMFPARSLEY_GEN_PATH,
					PROJECT_ROOT_OUTPUT,
	            	contents)
		}
	}

	def generatePluginXml(Module module) {
		val partSpecs = module?.parts
		if (partSpecs == null || partSpecs.empty) {
			return ''''''
		} else {
'''
<extension
      point="org.eclipse.ui.views">
    «partSpecs.map[generateExtensionPoint].join("")»
</extension>
'''.generatePluginXml
		}
	}

	def dispatch generateExtensionPoint(PartSpecification partSpecification) {
		
	}

	def dispatch generateExtensionPoint(ViewSpecification viewSpecification) {
'''
<view
      category="«if (viewSpecification.category == null || viewSpecification.category.empty)
  	"org.eclipse.emf.parsley" else
    viewSpecification.category»"
      class="«inferrer.executableExtensionFactoryQN(viewSpecification)»:«viewSpecification.type.identifier»"
      id="«viewSpecification.id»"
      name="«viewSpecification.viewName»"
      restorable="true">
</view>
'''
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