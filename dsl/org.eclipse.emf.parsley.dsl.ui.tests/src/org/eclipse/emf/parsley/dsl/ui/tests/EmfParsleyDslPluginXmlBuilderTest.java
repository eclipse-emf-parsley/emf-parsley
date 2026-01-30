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
package org.eclipse.emf.parsley.dsl.ui.tests;

import static org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil.createFile;
import static org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil.fileToString;
import static org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil.fullBuild;
import static org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil.monitor;
import static org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil.waitForBuild;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.parsley.dsl.additional.builder.builder.EmfParsleyDslPluginXmlBuilder;
import org.eclipse.emf.parsley.dsl.additional.builder.builder.EmfParsleyDslPluginXmlBuilder.UtilityIFileReader;
import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider;
import org.eclipse.emf.parsley.dsl.tests.util.ui.ProjectImportUtil;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.ui.testing.AbstractWorkbenchTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Lorenzo Bettini
 */
@RunWith(XtextRunner.class)
@InjectWith(EmfParsleyDslUiInjectorProvider.class)
public class EmfParsleyDslPluginXmlBuilderTest extends AbstractWorkbenchTest {

	public static final String TEST_PROJECT = "org.eclipse.emf.parsley.dsl.ui.tests.project";

	public static final String PLUGIN_XML = "/plugin.xml";

	private IProject project;

	private final String s1 = """
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
			""";

	private final String singleView = """
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
			""";

	private final String singleView2 = """
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
			""";

	static class TestableBuilder extends EmfParsleyDslPluginXmlBuilder {
		/**
		 * Accessible for tests
		 */
		@Override
		protected String loadFromResource(UtilityIFileReader iFileReader, String information) throws CoreException {
			return super.loadFromResource(iFileReader, information);
		}
	}

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		project = ProjectImportUtil.importJavaProject(TEST_PROJECT).getProject();
	}

	@Test
	public void testCopyGeneratedPluginXmlWhenNoPluginXmlExists() throws Exception {
		assertFalse(project.getFile(PLUGIN_XML).exists());
		createXmlGenFile(project, "afile", s1);
		waitForBuild();
		assertPluginXmlContents(s1);
	}

	@Test
	public void testFullBuild() throws Exception {
		assertFalse(project.getFile(PLUGIN_XML).exists());
		createXmlGenFile(project, "afile", s1);
		fullBuild();
		assertPluginXmlContents(s1);
	}

	@Test
	public void testUpdateExistingPluginXml() throws Exception {
		createPluginXmlFile(project, singleView2);
		assertTrue(project.getFile(PLUGIN_XML).exists());
		createXmlGenFile(project, "afile", singleView);
		waitForBuild();
		assertPluginXmlContents(singleView);
	}

	@Test
	public void testIncrementalBuild() throws Exception {
		assertFalse(project.getFile(PLUGIN_XML).exists());
		createXmlGenFile(project, "afile", singleView2);
		waitForBuild();
		assertPluginXmlContents(singleView2);
		modifyXmlGenFile(project, "afile", singleView);
		waitForBuild();
		assertPluginXmlContents(singleView);
	}

	@Test
	public void testRemoveGeneratedFileDoesNotModifyPluginXml() throws Exception {
		assertFalse(project.getFile(PLUGIN_XML).exists());
		var file = createXmlGenFile(project, "afile", singleView);
		waitForBuild();
		assertPluginXmlContents(singleView);
		file.delete(true, monitor());
		waitForBuild();
		assertPluginXmlContents(singleView);
	}

	@Test(expected = CoreException.class)
	public void testExceptionWhenReading() throws Exception {
		var builder = new TestableBuilder();
		var reader = mock(UtilityIFileReader.class);
		when(reader.readFromResource()).thenThrow(new IOException());
		builder.loadFromResource(reader, "info");
	}

	private IFile createXmlGenFile(IProject project, String fileName, CharSequence newcontents) throws Exception {
		var srcFolder = project.getFolder("src");
		var file = srcFolder.getFile(fileName + "." +
			EmfParsleyDslOutputConfigurationProvider.PLUGIN_XML_EMFPARSLEY_GEN_EXTENSION);
		createFile(file.getFullPath(), newcontents.toString());
		return file;
	}

	private IProject modifyXmlGenFile(IProject project, String fileName, CharSequence newcontents) throws Exception {
		var srcFolder = project.getFolder("src");
		var file = srcFolder.getFile(fileName + "." +
			EmfParsleyDslOutputConfigurationProvider.PLUGIN_XML_EMFPARSLEY_GEN_EXTENSION);
		assertTrue(file.exists());
		createFile(file.getFullPath(), newcontents.toString());
		return project;
	}

	private IProject createPluginXmlFile(IProject project, CharSequence newcontents) throws Exception {
		var file = project.getFile(PLUGIN_XML);
		createFile(file.getFullPath(), newcontents.toString());
		return project;
	}

	private void assertPluginXmlContents(CharSequence expected) throws Exception {
		var file = project.getFile(PLUGIN_XML);
		assertTrue(file.exists());
		assertEquals(expected.toString(), fileToString(file));
	}
}
