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
package org.eclipse.emf.parsley.dsl.ui.tests

import com.google.inject.Inject
import com.google.inject.Provider
import org.eclipse.core.resources.IProject
import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider
import org.eclipse.emf.parsley.dsl.tests.util.ui.PluginProjectHelper
import org.eclipse.emf.parsley.dsl.tests.util.ui.TestableEmfParsleyDslNewProjectWizard
import org.eclipse.jface.viewers.StructuredSelection
import org.eclipse.jface.wizard.Wizard
import org.eclipse.jface.wizard.WizardDialog
import org.eclipse.ui.PlatformUI
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.ui.testing.AbstractWorkbenchTest
import org.junit.Test
import org.junit.runner.RunWith

import static org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider.*
import static org.eclipse.emf.parsley.dsl.tests.util.ui.TestableEmfParsleyDslNewProjectWizard.*
import static org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil.*

/**
 * @author Lorenzo Bettini
 */
@RunWith(typeof(XtextRunner))
@InjectWith(typeof(EmfParsleyDslUiInjectorProvider))
class EmfParsleyDslWorkbenchTest extends AbstractWorkbenchTest {

	@Inject Provider<TestableEmfParsleyDslNewProjectWizard> wizardProvider

	val TEST_MODULE = TestableEmfParsleyDslNewProjectWizard.TEST_PROJECT + "/" +
		TestableEmfParsleyDslNewProjectWizard.TEST_PROJECT.toFirstUpper + ".parsley"

	val TEST_PLUGIN_XML_GEN = 
		EmfParsleyDslOutputConfigurationProvider.EMFPARSLEY_GEN + "/" + TEST_PROJECT + "/" +
		EmfParsleyDslOutputConfigurationProvider.PLUGIN_XML_GEN_FILE

	/**
	 * Create the wizard dialog, open it and press Finish.
	 */
	def protected int createAndFinishWizardDialog(Wizard wizard) {
		val dialog = new WizardDialog(wizard.shell, wizard) {
			override open() {
				val thread = new Thread("Press Finish") {
					override run() {
						// wait for the shell to become active
						while (getShell() === null) {
							Thread.sleep(1000)
						}
						getShell().getDisplay().asyncExec[
							finishPressed();
						]					
					}			
				};
				thread.start();
				return super.open();
			}
		};
		return dialog.open();
	}

//	@Before
//	override setUp() throws Exception {
//		super.setUp()
//		projectHelper.clearJdtIndex
//	}

	@Test def void testEmfParsleyDslNewProjectWizard() {
		createProjectWithNewProjectWizard
		PluginProjectHelper.assertNoErrors
	}

	@Test def void testPluginXmlGeneration() {
		val project = createProjectWithNewProjectWizard
		project.modifyParsleyModuleFile(
'''
module «TEST_PROJECT» {
	
	// parts should trigger generation of «PLUGIN_XML_GEN_FILE»
	
	parts {
		viewpart id {
			viewname "View Name"
			viewclass org.eclipse.emf.parsley.views.AbstractSaveableTreeView
			// viewcategory my.category
		}
	}
}
'''
		)
		waitForBuild
		
		assertTrue(project.getFile(TEST_PLUGIN_XML_GEN).exists())
		assertTrue(project.getFile("/plugin.xml").exists())
		
		project.modifyParsleyModuleFile(
'''
module «TEST_PROJECT» {
	// removed parts
	
	// «PLUGIN_XML_GEN_FILE» should be removed
}
'''		
		)
		waitForBuild

		assertFalse(project.getFile(TEST_PLUGIN_XML_GEN).exists())
		// plugin.xml should still be there
		assertTrue(project.getFile("/plugin.xml").exists())
	}

	def private createProjectWithNewProjectWizard() {
		val wizard = wizardProvider.get
		wizard.init(PlatformUI.getWorkbench(), new StructuredSelection());
		createAndFinishWizardDialog(wizard)
		val project = root.getProject(TEST_PROJECT)
		assertTrue(project.exists())
		cleanBuild
		waitForBuild
		return project
	}

	def private modifyParsleyModuleFile(IProject project, CharSequence newcontents) {
		val srcFolder = project.getFolder("src")
		val file = srcFolder.getFile(TEST_MODULE)
		assertTrue(file.exists())
		createFile(file.fullPath, newcontents.toString)
		return project
	}

}