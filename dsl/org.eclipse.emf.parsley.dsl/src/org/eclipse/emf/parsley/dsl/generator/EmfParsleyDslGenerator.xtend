package org.eclipse.emf.parsley.dsl.generator

import com.google.inject.Inject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.xbase.compiler.JvmModelGenerator

/**
 * This generator calls the standard JvmModelGenerator and
 * the generator for plugin.xml_gen
 */
class EmfParsleyDslGenerator implements IGenerator {

	@Inject JvmModelGenerator jvmModelGenerator
	
	@Inject EmfParsleyDslPluginXmlGenerator pluginXmlGenerator

	override doGenerate(Resource input, IFileSystemAccess fsa) {
		jvmModelGenerator.doGenerate(input, fsa);
		pluginXmlGenerator.doGenerate(input, fsa);
	}
	
}