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
import org.eclipse.emf.parsley.dsl.EmfParsleyDslUiInjectorProvider
import org.eclipse.emf.parsley.dsl.tests.util.ui.PluginProjectHelper
import org.eclipse.emf.parsley.dsl.tests.util.ui.TestableEmfParsleyDslNewProjectWizard
import org.eclipse.emf.parsley.tests.pde.utils.PDETargetPlatformUtils
import org.eclipse.jface.viewers.StructuredSelection
import org.eclipse.jface.wizard.Wizard
import org.eclipse.jface.wizard.WizardDialog
import org.eclipse.ui.PlatformUI
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.ui.AbstractWorkbenchTest
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

import static org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil.*
import static org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider.*
import static org.eclipse.emf.parsley.dsl.tests.util.ui.TestableEmfParsleyDslNewProjectWizard.*


/**
 * @author Lorenzo Bettini
 */
@RunWith(typeof(XtextRunner))
@InjectWith(typeof(EmfParsleyDslUiInjectorProvider))
class EmfParsleyDslWorkbenchTest extends AbstractWorkbenchTest {
	
	@Inject Provider<TestableEmfParsleyDslNewProjectWizard> wizardProvider

	@Inject PluginProjectHelper projectHelper
	
	val TEST_MODULE = TestableEmfParsleyDslNewProjectWizard.TEST_PROJECT + "/module.parsley"
	
	/**
	 * Create the wizard dialog, open it and press Finish.
	 */
	def protected int createAndFinishWizardDialog(Wizard wizard) {
		val dialog = new WizardDialog(wizard.shell, wizard) {
			override open() {
				val thread = new Thread("Press Finish") {
					override run() {
						// wait for the shell to become active
						while (getShell() == null) {
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

	@BeforeClass
	def static void beforeClass() {
		PDETargetPlatformUtils.setTargetPlatform();
	}

	@Test def void testEmfParsleyDslNewProjectWizard() {
		createProjectWithNewProjectWizard
		projectHelper.assertNoErrors
	}

//	@Test def void testEmfParsleyDslNewProjectWizard2() {
//		createProjectWithNewProjectWizard
//		projectHelper.assertNoErrors
//	}

	@Test def void testPluginXmlGeneration() {
		val project = modifyParsleyModuleFile(
'''
module «TEST_PROJECT» {
	
	// parts should trigger generation of «PLUGIN_XML_EMFPARSLEY_GEN»
	
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
		waitForAutoBuild
		
		assertTrue(project.getFile("/" + PLUGIN_XML_EMFPARSLEY_GEN).exists())
		
		modifyParsleyModuleFile(
'''
module «TEST_PROJECT» {
	// removed parts
	
	// «PLUGIN_XML_EMFPARSLEY_GEN» should be removed
}
'''		
		)
		waitForAutoBuild

		assertFalse(project.getFile("/" + PLUGIN_XML_EMFPARSLEY_GEN).exists())
	}

	def private createProjectWithNewProjectWizard() {
		val wizard = wizardProvider.get
		wizard.init(PlatformUI.getWorkbench(), new StructuredSelection());
		createAndFinishWizardDialog(wizard)
		val project = root.getProject(TEST_PROJECT)
		assertTrue(project.exists())
		cleanBuild
		waitForAutoBuild
		return project
	}

	def private modifyParsleyModuleFile(CharSequence newcontents) {
		val project = createProjectWithNewProjectWizard
		val srcFolder = project.getFolder("src")
		val file = srcFolder.getFile(TEST_MODULE)
		assertTrue(file.exists())
		createFile(file.fullPath, newcontents.toString)
		return project
	}

}