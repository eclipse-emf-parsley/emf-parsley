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
package org.eclipse.emf.parsley.dsl.tests;

import static org.junit.Assert.assertNull;

import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider;
import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslPluginXmlGenerator;
import org.eclipse.emf.parsley.dsl.model.ModelFactory;
import org.eclipse.emf.parsley.dsl.model.Module;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.InMemoryFileSystemAccess;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

@RunWith(XtextRunner.class)
@InjectWith(EmfParsleyDslInjectorProvider.class)
public class EmfParsleyDslPluginXmlGeneratorTest extends EmfParsleyDslAbstractTest {

	@Inject
	private EmfParsleyDslPluginXmlGenerator pluginXmlGenerator;

	@Test
	public void testEmptyContents() {
		assertPluginXmlContents("",
"""
<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.4"?>
<plugin>
</plugin>
"""			
		);
	}

	@Test
	public void testViewExtensionPoint() throws Exception {
		assertEqualsStrings("""
<view
      category="org.eclipse.emf.parsley"
      class="my.test.TestExecutableExtensionFactory:org.eclipse.emf.parsley.views.AbstractSaveableTreeView"
      id="my.view.part"
      name="My View"
      restorable="true">
</view>
""",
	pluginXmlGenerator.generateExtensionPoint(
		partSpecification(inputs.nonEmptyViewsSpecifications())
	)
);
	}

	@Test
	public void testNoViewSpecification() throws Exception {
		assertPluginXmlContents(module(inputs.emptyModule()), "");
	}

	@Test
	public void testNoViewSpecification2() throws Exception {
		assertNoPluginXmlGeneration(inputs.emptyModule());
	}

	@Test
	public void testSingleViewSpecification() throws Exception {
		assertPluginXmlContents(module(inputs.nonEmptyViewsSpecifications()),
"""
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
"""
		);
	}

	@Test
	public void testMultipleViewSpecification() throws Exception {
		assertPluginXmlContents(module(inputs.multipleViewsSpecifications()),
"""
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
"""
		);
	}

	@Test
	public void testPluginXmlGen() throws Exception {
		assertPluginXmlGeneration(inputs.multipleViewsSpecifications(),
"""
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
"""				
		);
	}

	@Test
	public void testGenerateExtensionPointWithPartSpecificationNotViewSpecification() {
		assertNull(pluginXmlGenerator.generateExtensionPoint(ModelFactory.eINSTANCE.createPartSpecification()));
	}

	private void assertPluginXmlGeneration(CharSequence input, CharSequence expected) throws Exception {
		var access = new InMemoryFileSystemAccess();
		var parsed = parseAndAssertNoError(input);
		pluginXmlGenerator.doGenerate(parsed.eResource(), access);
		var textFiles = access.getTextFiles();
		var entrySet = textFiles.entrySet();
		// both the plugin.xml_emfparsley_gen and the plugin.xml
		assertEqualsStrings(1, entrySet.size());
		assertEqualsStrings(expected,
			textFiles.get(
				IFileSystemAccess.DEFAULT_OUTPUT +
				"my/test/" + EmfParsleyDslOutputConfigurationProvider.PLUGIN_XML_GEN_FILE
			)
		);
	}

	private void assertNoPluginXmlGeneration(CharSequence input) throws Exception {
		var access = new InMemoryFileSystemAccess();
		var parsed = parseAndAssertNoError(input);
		pluginXmlGenerator.doGenerate(parsed.eResource(), access);
		var textFiles = access.getTextFiles();
		var entrySet = textFiles.entrySet();
		assertEqualsStrings(0, entrySet.size());
	}

	private void assertPluginXmlContents(Module module, CharSequence expected) {
		assertEqualsStrings(expected, pluginXmlGenerator.generatePluginXml(module));
	}

	private void assertPluginXmlContents(CharSequence contents, CharSequence expected) {
		assertEqualsStrings(expected, pluginXmlGenerator.generatePluginXml(contents));
	}
}
