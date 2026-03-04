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
package org.eclipse.emf.parsley.dsl.ui.tests;

import static org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider.PLUGIN_XML_GEN_FILE;
import static org.eclipse.emf.parsley.dsl.tests.util.ui.TestableEmfParsleyDslNewProjectWizard.TEST_PROJECT;
import static org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil.cleanBuild;
import static org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil.createFile;
import static org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil.root;
import static org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil.waitForBuild;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.parsley.dsl.generator.EmfParsleyDslOutputConfigurationProvider;
import org.eclipse.emf.parsley.dsl.tests.util.ui.PluginProjectHelper;
import org.eclipse.emf.parsley.dsl.tests.util.ui.TestableEmfParsleyDslNewProjectWizard;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.ui.testing.AbstractWorkbenchTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Lorenzo Bettini
 */
@RunWith(XtextRunner.class)
@InjectWith(EmfParsleyDslUiInjectorProvider.class)
public class EmfParsleyDslWorkbenchTest extends AbstractWorkbenchTest {

	@Inject
	private Provider<TestableEmfParsleyDslNewProjectWizard> wizardProvider;

	private static final String TEST_MODULE = TestableEmfParsleyDslNewProjectWizard.TEST_PROJECT + "/" +
		capitalize(TestableEmfParsleyDslNewProjectWizard.TEST_PROJECT) + ".parsley";

	private static final String TEST_PLUGIN_XML_GEN =
		EmfParsleyDslOutputConfigurationProvider.EMFPARSLEY_GEN + "/" + TEST_PROJECT + "/" +
		EmfParsleyDslOutputConfigurationProvider.PLUGIN_XML_GEN_FILE;

	/**
	 * Create the wizard dialog, open it and press Finish.
	 */
	protected int createAndFinishWizardDialog(Wizard wizard) {
		var dialog = new WizardDialog(wizard.getShell(), wizard) {
			@Override
			public int open() {
				var thread = new Thread("Press Finish") {
					@Override
					public void run() {
						// wait for the shell to become active
						while (getShell() == null) {
							try {
								Thread.sleep(1000); // NOSONAR we need to give time to the UI thread to open the shell
							} catch (InterruptedException e) {
								// ignore
							}
						}
						getShell().getDisplay().asyncExec(() -> finishPressed());
					}
				};
				thread.start();
				return super.open();
			}
		};
		return dialog.open();
	}

	@Test
	public void testEmfParsleyDslNewProjectWizard() throws Exception {
		createProjectWithNewProjectWizard();
		PluginProjectHelper.assertNoErrors();
	}

	@Test
	public void testPluginXmlGeneration() throws Exception {
		var project = createProjectWithNewProjectWizard();
		modifyParsleyModuleFile(project,
			"""
			module %s {
				
				// parts should trigger generation of %s
				
				parts {
					viewpart id {
						viewname "View Name"
						viewclass org.eclipse.emf.parsley.views.AbstractSaveableTreeView
						// viewcategory my.category
					}
				}
			}
			""".formatted(TEST_PROJECT, PLUGIN_XML_GEN_FILE)
		);
		waitForBuild();
		
		assertTrue(project.getFile(TEST_PLUGIN_XML_GEN).exists());
		assertTrue(project.getFile("/plugin.xml").exists());
		
		modifyParsleyModuleFile(project,
			"""
			module %s {
				// removed parts
				
				// %s should be removed
			}
			""".formatted(TEST_PROJECT, PLUGIN_XML_GEN_FILE)
		);
		waitForBuild();

		assertFalse(project.getFile(TEST_PLUGIN_XML_GEN).exists());
		// plugin.xml should still be there
		assertTrue(project.getFile("/plugin.xml").exists());
	}

	private IProject createProjectWithNewProjectWizard() throws Exception {
		var wizard = wizardProvider.get();
		wizard.init(PlatformUI.getWorkbench(), new StructuredSelection());
		createAndFinishWizardDialog(wizard);
		var project = root().getProject(TEST_PROJECT);
		assertTrue(project.exists());
		cleanBuild();
		waitForBuild();
		return project;
	}

	private IProject modifyParsleyModuleFile(IProject project, CharSequence newcontents) throws Exception {
		var srcFolder = project.getFolder("src");
		var file = srcFolder.getFile(TEST_MODULE);
		assertTrue(file.exists());
		createFile(file.getFullPath(), newcontents.toString());
		return project;
	}

	private static String capitalize(String s) {
		if (s == null || s.isEmpty()) {
			return s;
		}
		return Character.toUpperCase(s.charAt(0)) + s.substring(1);
	}
}
