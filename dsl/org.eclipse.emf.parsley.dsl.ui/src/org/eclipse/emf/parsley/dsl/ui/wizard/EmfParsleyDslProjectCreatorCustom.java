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
package org.eclipse.emf.parsley.dsl.ui.wizard;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.parsley.dsl.additional.builder.builder.EmfParsleyDslPluginXmlBuilder;
import org.eclipse.emf.parsley.dsl.additional.builder.builder.EmfParsleyDslPluginXmlNature;
import org.eclipse.emf.parsley.dsl.ui.wizard.template.TemplateWizardConfiguration;
import org.eclipse.emf.parsley.generator.common.EmfParsleyProjectFilesGenerator;
import org.eclipse.emf.parsley.views.EmfParsleyViewsActivator;
import org.eclipse.emf.parsley.wizards.NewEmfParsleyProjectSupport;
import org.eclipse.xtext.ui.util.PluginProjectFactory;

import com.google.common.collect.Lists;

/**
 * @author Lorenzo Bettini
 *
 */
public class EmfParsleyDslProjectCreatorCustom extends EmfParsleyDslProjectCreator {
	private static final int CREATING_PROJECT_WORK_STEPS = 5;

	static EmfParsleyDslNewProjectFiles filesGenerator = new EmfParsleyDslNewProjectFiles();

	static EmfParsleyProjectFilesGenerator projectFilesGenerator = new EmfParsleyProjectFilesGenerator();

	/**
	 * @return the names of the bundles that a new project requires. May not be
	 *         <code>null</code>
	 */
	@Override
	protected List<String> getRequiredBundles() {
		List<String> requiredBundles = Lists.newArrayList("org.eclipse.core.runtime");
		if (getProjectInfo().isRapOption()) {
			//Case RAP (single sourcing)
			requiredBundles.addAll( Lists.newArrayList(
				"org.eclipse.ui;resolution:=optional",
				"org.eclipse.rap.ui;resolution:=optional",
				EmfParsleyViewsActivator.PLUGIN_ID+";resolution:=optional",
				"org.eclipse.emf.parsley.rap.views;resolution:=optional")
			);
		} else {
			//Case RCP
			requiredBundles.addAll( Lists.newArrayList(
				"org.eclipse.ui",
				EmfParsleyViewsActivator.PLUGIN_ID)
			);
		}
		requiredBundles.add("org.eclipse.xtext.xbase.lib");
		return requiredBundles;
		// don't add the components.dsl project dep
		// otherwise the plugin will depend on xtext stuff
		// which are not used by the generated code
	}

	@Override
	protected PluginProjectFactory createProjectFactory() {
		PluginProjectFactory projectFactory = super.createProjectFactory();
		if (getProjectInfo().getSelectedTemplate() != null) {
			projectFactory.setWithPluginXml(true);
		}
		return projectFactory;
	}

	@Override
	protected String getActivatorClassName() {
		String projectName = getFixedProjectName();
		return projectName
				+ "."
				+ projectFilesGenerator.activatorName(
						projectName).toString();
	}

	@Override
	protected String[] getProjectNatures() {
		List<String> natures = Lists.newArrayList(super.getProjectNatures());
		natures.add(EmfParsleyDslPluginXmlNature.NATURE_ID);
		return natures.toArray(new String[natures.size()]);
	}

	@Override
	protected String[] getBuilders() {
		List<String> builders = Lists.newArrayList(super.getBuilders());
		builders.add(EmfParsleyDslPluginXmlBuilder.BUILDER_ID);
		return builders.toArray(new String[builders.size()]);
	}

	@Override
	protected void enhanceProject(final IProject project,
			final IProgressMonitor monitor) throws CoreException {
		String projectName = getFixedProjectName();

		String srcFolder = "src";
		String projectPackagePath = srcFolder + "/"
				+ projectName.replace('.', '/');

		SubMonitor subMonitor = SubMonitor.convert(monitor,
				"Creating project " + projectName,
				CREATING_PROJECT_WORK_STEPS);

		String settingsPath = ".settings";
		String[] paths = { projectPackagePath, settingsPath };
		NewEmfParsleyProjectSupport.addToProjectStructure(project, paths,
				subMonitor.newChild(1));

		NewEmfParsleyProjectSupport.createActivator(project, projectName,
				projectPackagePath, subMonitor.newChild(1));

		String dslFileContents = "";
		TemplateWizardConfiguration selectedTemplate = getProjectInfo().getSelectedTemplate();
		if (selectedTemplate != null) {
			String partClassName = selectedTemplate.getPartClassName(projectName);
			String partContents = selectedTemplate.getContentsForPart(projectName);
			NewEmfParsleyProjectSupport.createProjectFile(project,
					projectPackagePath + "/" + partClassName.concat(".java"),
					partContents, subMonitor.newChild(1));
			dslFileContents = selectedTemplate.getParsleyModuleContents(projectName);
		} else {
			dslFileContents = filesGenerator.genEmptyDslModule(projectName).toString();
		}

		NewEmfParsleyProjectSupport.createDslModule(project, projectName,
				projectPackagePath, dslFileContents, subMonitor.newChild(1));

		NewEmfParsleyProjectSupport.createProjectFile(project,
				settingsPath + "/org.eclipse.core.resources.prefs",
				"eclipse.preferences.version=1\n"
				+ "encoding/<project>=UTF-8\n"
				+ "", subMonitor.newChild(1));

		project.refreshLocal(IResource.DEPTH_INFINITE, subMonitor.newChild(1));
		subMonitor.done();
	}

	protected String getFixedProjectName() {
		return NewEmfParsleyProjectSupport.getValidJavaName(
				getProjectInfo().getProjectName());
	}
}
