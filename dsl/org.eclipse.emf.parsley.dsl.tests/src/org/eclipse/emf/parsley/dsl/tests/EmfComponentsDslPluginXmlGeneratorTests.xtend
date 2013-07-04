package org.eclipse.emf.parsley.dsl.tests

import com.google.inject.Inject
import org.eclipse.emf.parsley.dsl.EmfParsleyDslInjectorProvider
import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider
import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslPluginXmlGenerator
import org.eclipse.emf.parsley.dsl.model.Module
import org.eclipse.xtext.generator.InMemoryFileSystemAccess
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(EmfParsleyDslInjectorProvider))
class EmfComponentsDslPluginXmlGeneratorTests extends EmfComponentsDslAbstractTests {

	@Inject EmfParsleyDslPluginXmlGenerator pluginXmlGenerator

	@Test
	def void testEmptyContents() {
		"".assertPluginXmlContents(
'''
<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.4"?>
<plugin>
</plugin>
'''			
		)
	}

	@Test
	def void testViewExtensionPoint() {
'''
<view
      category="org.eclipse.emf.parsley"
      class="my.test.TestExecutableExtensionFactory:org.eclipse.emf.parsley.views.AbstractSaveableTreeView"
      id="my.view.part"
      name="My View"
      restorable="true">
</view>
'''.assertEqualsStrings(
	pluginXmlGenerator.generateExtensionPoint(
		inputs.nonEmptyViewsSpecifications.partSpecification
	)
)
	}

	@Test
	def void testNoViewSpecification() {
		inputs.emptyModule.module.assertPluginXmlContents("")
	}

	@Test
	def void testSingleViewSpecification() {
		inputs.nonEmptyViewsSpecifications.module.assertPluginXmlContents(
'''
<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.4"?>
<plugin>
	<extension
	      point="org.eclipse.ui.views">
	    <view
	          category="org.eclipse.emf.parsley"
	          class="my.test.TestExecutableExtensionFactory:org.eclipse.emf.parsley.views.AbstractSaveableTreeView"
	          id="my.view.part"
	          name="My View"
	          restorable="true">
	    </view>
	</extension>
</plugin>
'''
		)
	}

	@Test
	def void testMultipleViewSpecification() {
		inputs.multipleViewsSpecifications.module.assertPluginXmlContents(
'''
<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.4"?>
<plugin>
	<extension
	      point="org.eclipse.ui.views">
	    <view
	          category="org.eclipse.emf.parsley"
	          class="my.test.TestExecutableExtensionFactory:org.eclipse.emf.parsley.views.AbstractSaveableTreeView"
	          id="my.view.tree.part"
	          name="My Tree View"
	          restorable="true">
	    </view>
	    <view
	          category="my.view.category"
	          class="my.test.TestExecutableExtensionFactory:org.eclipse.emf.parsley.views.AbstractSaveableTreeFormView"
	          id="my.view.form.part"
	          name="My Tree Form View"
	          restorable="true">
	    </view>
	</extension>
</plugin>
'''
		)
	}

	@Test
	def void testPluginXmlGen() {
		inputs.multipleViewsSpecifications.
			assertPluginXmlGeneration(
'''
<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.4"?>
<plugin>
	<extension
	      point="org.eclipse.ui.views">
	    <view
	          category="org.eclipse.emf.parsley"
	          class="my.test.TestExecutableExtensionFactory:org.eclipse.emf.parsley.views.AbstractSaveableTreeView"
	          id="my.view.tree.part"
	          name="My Tree View"
	          restorable="true">
	    </view>
	    <view
	          category="my.view.category"
	          class="my.test.TestExecutableExtensionFactory:org.eclipse.emf.parsley.views.AbstractSaveableTreeFormView"
	          id="my.view.form.part"
	          name="My Tree Form View"
	          restorable="true">
	    </view>
	</extension>
</plugin>
'''				
			)
	}

	def private void assertPluginXmlGeneration(CharSequence input, CharSequence expected) {
		val access = new InMemoryFileSystemAccess();
		val parsed = input.parseAndAssertNoError
		pluginXmlGenerator.doGenerate(parsed.eResource(), access);
		val entrySet = access.getFiles().entrySet()
		assertEqualsStrings(1, entrySet.size)
		val e = entrySet.head
		val name = e.getKey().substring(
				(EmfParsleyDslOutputConfigurationProvider::PROJECT_ROOT_OUTPUT +
					EmfParsleyDslOutputConfigurationProvider::PLUGIN_XML_EMFPARSLEY_REL_GEN_PATH).
					length());
		assertEqualsStrings(
			EmfParsleyDslOutputConfigurationProvider::PLUGIN_XML_EMFPARSLEY_GEN,
				name)
		assertEqualsStrings(expected, e.value)
	}

	def private void assertPluginXmlContents(Module module, CharSequence expected) {
		assertEqualsStrings(expected, pluginXmlGenerator.generatePluginXml(module))
	}

	def private void assertPluginXmlContents(CharSequence contents, CharSequence expected) {
		assertEqualsStrings(expected, pluginXmlGenerator.generatePluginXml(contents))
	}
}