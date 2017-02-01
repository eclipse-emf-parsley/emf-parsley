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
package org.eclipse.emf.parsley.dsl.tests

import com.google.inject.Inject
import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider
import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslPluginXmlGenerator
import org.eclipse.emf.parsley.dsl.model.Module
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.InMemoryFileSystemAccess
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(XtextRunner)
@InjectWith(EmfParsleyDslInjectorProvider)
class EmfParsleyDslPluginXmlGeneratorTest extends EmfParsleyDslAbstractTest {

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
		val textFiles = access.getTextFiles()
		val entrySet = textFiles.entrySet()
		// both the plugin.xml_emfparsley_gen and the plugin.xml
		assertEqualsStrings(1, entrySet.size)
		assertEqualsStrings(expected,
			textFiles.get(
				IFileSystemAccess.DEFAULT_OUTPUT +
				"my/test/" + EmfParsleyDslOutputConfigurationProvider.PLUGIN_XML_GEN_FILE
			)
		)
	}

	def private void assertPluginXmlContents(Module module, CharSequence expected) {
		assertEqualsStrings(expected, pluginXmlGenerator.generatePluginXml(module))
	}

	def private void assertPluginXmlContents(CharSequence contents, CharSequence expected) {
		assertEqualsStrings(expected, pluginXmlGenerator.generatePluginXml(contents))
	}
}