/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.ui.tests

import com.google.inject.Inject
import java.io.IOException
import java.io.InputStreamReader
import java.nio.CharBuffer
import org.eclipse.core.resources.IProject
import org.eclipse.core.runtime.CoreException
import org.eclipse.emf.parsley.dsl.additional.builder.builder.EmfParsleyDslPluginXmlBuilder
import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider
import org.eclipse.emf.parsley.dsl.tests.util.ui.PluginProjectHelper
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.ui.AbstractWorkbenchTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import static org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil.*
import static org.mockito.Mockito.*

/**
 * @author Lorenzo Bettini
 */
@RunWith(typeof(XtextRunner))
@InjectWith(typeof(EmfParsleyDslUiInjectorProvider))
class EmfParsleyDslPluginXmlBuilderTest extends AbstractWorkbenchTest {

	@Inject PluginProjectHelper projectHelper

	public static final String TEST_PROJECT = "TestProject";

	public static final String PLUGIN_XML = "/plugin.xml";

	var IProject project

	val s1 = '''
<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.4"?>
<plugin>
   <extension
         point="org.eclipse.ui.views">
      <view
            category="org.eclipse.emf.parsley"
            class="org.eclipse.emf.parsley.tests.views.ViewsExecutableExtensionFactory:org.eclipse.emf.parsley.tests.views.ViewsSaveableTreeFormView"
            id="org.eclipse.emf.parsley.tests.views"
            name="Test Model Tree Form View"
            restorable="true">
      </view>
      <view
            category="org.eclipse.emf.parsley"
            class="org.eclipse.emf.parsley.tests.views.ViewsExecutableExtensionFactory:org.eclipse.emf.parsley.tests.views.ViewsSaveableTreeView"
            id="org.eclipse.emf.parsley.tests.treeviews"
            name="Test Model Tree View"
            restorable="true">
      </view>
   </extension>
   <extension
         point="org.eclipse.ui.editors">
      <editor
            class="org.eclipse.emf.parsley.tests.factories.OpenDialogMouseEventExecutableExtensionFactory:org.eclipse.emf.parsley.editors.EmfTreeEditor"
            contributorClass="org.eclipse.emf.parsley.tests.factories.OpenDialogMouseEventExecutableExtensionFactory:org.eclipse.emf.parsley.editors.EmfParsleyEditorActionBarContributor"
            default="false"
            id="org.eclipse.emf.parsley.openDialogOnDoubleClick"
            name="EMF Tree Editor Opening Dialog">
         <contentTypeBinding
               contentTypeId="org.eclipse.emf.ecore.xmi">
         </contentTypeBinding>
      </editor>
   </extension>
</plugin>
'''

	val singleView = '''
<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.4"?>
<plugin>
   <extension
         point="org.eclipse.ui.views">
      <view
            category="org.eclipse.emf.parsley"
            class="org.eclipse.emf.parsley.tests.views.ViewsExecutableExtensionFactory:org.eclipse.emf.parsley.tests.views.ViewsSaveableTreeFormView"
            id="viewId"
            name="Test Model Tree Form View"
            restorable="true">
      </view>
   </extension>
</plugin>
'''

	val singleView2 = '''
<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.4"?>
<plugin>
   <extension
         point="org.eclipse.ui.views">
      <view
            category="org.eclipse.emf.parsley"
            class="MyClass"
            id="viewId"
            name="My View">
      </view>
   </extension>
</plugin>
'''

	static class TestableBuilder extends EmfParsleyDslPluginXmlBuilder {
		/**
		 * Accessible for tests
		 */
		override loadFromResource(InputStreamReader reader, String information) throws CoreException {
			super.loadFromResource(reader, information)
		}
		
	}

	@Before
	override setUp() throws Exception {
		super.setUp()
		project = projectHelper.createSimpleProject(TEST_PROJECT)
	}

	@Test def void testCopyGeneratedPluginXmlWhenNoPluginXmlExists() {
		assertFalse(project.getFile(PLUGIN_XML).exists())
		project.createXmlGenFile("afile", s1)
		waitForBuild
		assertPluginXmlContents(s1)
	}

	@Test def void testFullBuild() {
		assertFalse(project.getFile(PLUGIN_XML).exists())
		project.createXmlGenFile("afile", s1)
		fullBuild
		assertPluginXmlContents(s1)
	}

	@Test def void testUpdateExistingPluginXml() {
		project.createPluginXmlFile(singleView2)
		assertTrue(project.getFile(PLUGIN_XML).exists())
		project.createXmlGenFile("afile", singleView)
		waitForBuild
		assertPluginXmlContents(singleView)
	}

	@Test def void testIncrementalBuild() {
		assertFalse(project.getFile(PLUGIN_XML).exists())
		project.createXmlGenFile("afile", singleView2)
		waitForBuild
		assertPluginXmlContents(singleView2)
		project.modifyXmlGenFile("afile", singleView)
		waitForBuild
		assertPluginXmlContents(singleView)
	}

	@Test def void testRemoveGeneratedFileDoesNotModifyPluginXml() {
		assertFalse(project.getFile(PLUGIN_XML).exists())
		val file = project.createXmlGenFile("afile", singleView)
		waitForBuild
		assertPluginXmlContents(singleView)
		file.delete(true, monitor)
		waitForBuild
		assertPluginXmlContents(singleView)
	}

	@Test(expected=CoreException) def void testExceptionWhenReading() {
		val builder = new TestableBuilder()
		val reader = mock(InputStreamReader)
		when(reader.read(any(CharBuffer))).thenThrow(new IOException)
		builder.loadFromResource(reader, "info")
	}

	def private createXmlGenFile(IProject project, String fileName, CharSequence newcontents) {
		val srcFolder = project.getFolder("src")
		val file = srcFolder.getFile(fileName + "." +
			EmfParsleyDslOutputConfigurationProvider.PLUGIN_XML_EMFPARSLEY_GEN_EXTENSION)
		createFile(file.fullPath, newcontents.toString)
		return file
	}

	def private modifyXmlGenFile(IProject project, String fileName, CharSequence newcontents) {
		val srcFolder = project.getFolder("src")
		val file = srcFolder.getFile(fileName + "." +
			EmfParsleyDslOutputConfigurationProvider.PLUGIN_XML_EMFPARSLEY_GEN_EXTENSION)
		assertTrue(file.exists)
		createFile(file.fullPath, newcontents.toString)
		return project
	}

	def private createPluginXmlFile(IProject project, CharSequence newcontents) {
		val file = project.getFile(PLUGIN_XML)
		createFile(file.fullPath, newcontents.toString)
		return project
	}

	def private assertPluginXmlContents(CharSequence expected) {
		val file = project.getFile(PLUGIN_XML)
		assertTrue(file.exists)
		expected.toString.assertEquals(fileToString(file))
	}
}
