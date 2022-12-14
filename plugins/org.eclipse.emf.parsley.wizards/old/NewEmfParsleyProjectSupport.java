/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.wizards;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.parsley.generator.common.EmfParsleyProjectFilesGenerator;
import org.eclipse.emf.parsley.generator.common.EmfParsleyViewFilesGenerator;
import org.eclipse.emf.parsley.wizards.EmfParsleyChoiceTemplate.Choice;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.pde.core.project.IBundleProjectDescription;

/**
 * @author Lorenzo Bettini
 */
public class NewEmfParsleyProjectSupport {

	private static final String JAVA_EXTENSION = ".java";

	static EmfParsleyProjectFilesGenerator filesGenerator = new EmfParsleyProjectFilesGenerator();

	static EmfParsleyViewFilesGenerator viewGenerator = new EmfParsleyViewFilesGenerator();
	
	private static final Logger LOGGER = Logger.getLogger(NewEmfParsleyProjectSupport.class);
	
	protected NewEmfParsleyProjectSupport() {
		// hide the implicit one
	}

	/**
	 * We need to: - create the default Eclipse project - add the java and
	 * plugin natures - create the folder structure
	 * 
	 * @param projectName
	 * @param location
	 * @param progressMonitor
	 * @param natureId
	 * @return
	 */
	public static IProject createProject(String projectName, URI location,
			Choice viewType, IProgressMonitor progressMonitor) {
		Assert.isNotNull(projectName);
		Assert.isTrue(projectName.trim().length() > 0);

		progressMonitor.beginTask("Creating project " + projectName, 10);

		String srcFolder = "src";
		String metaInfPath = "META-INF";
		String projectPackagePath = srcFolder + "/"
				+ projectName.replaceAll("\\.", "/");

		IProject project = createBaseProject(projectName, location,
				createSubProgressMonitor(progressMonitor));
		try {
			String[] paths = { srcFolder, projectPackagePath, metaInfPath };
			addToProjectStructure(project, paths, progressMonitor);

			createActivator(project, projectName, projectPackagePath,
					progressMonitor);
			createExecutableExtensionFactory(project, projectName,
					projectPackagePath, progressMonitor);
			createModule(project, projectName, projectPackagePath,
					"EmfParsleyGuiceModule", progressMonitor);

			createProjectFile(project, metaInfPath + "/MANIFEST.MF",
					filesGenerator.generateManifest(projectName).toString(),
					createSubProgressMonitor(progressMonitor));
			createProjectFile(project, "/build.properties", filesGenerator
					.generateBuildProperties(shouldGeneratePluginXml(viewType))
					.toString(), createSubProgressMonitor(progressMonitor));

			String simpleClassName = getSimpleNameProject(projectPackagePath);
			String qualifiedNameView = null;
			CharSequence contents = "";
			switch (viewType) {
			case TREEFORM:
				qualifiedNameView = simpleClassName + "TreeFormView";
				contents = viewGenerator.generateTreeFormView(projectName, simpleClassName);
				break;
			case TABLEFORM:
				qualifiedNameView = simpleClassName + "TableView";
				contents = viewGenerator.generateTableView(projectName, simpleClassName);
				break;
			default:
				// OK, no additional views to create
			}

			if (qualifiedNameView != null) {
				createProjectFile(project, projectPackagePath + "/"
						+ qualifiedNameView.concat(JAVA_EXTENSION),
						contents.toString(),
						createSubProgressMonitor(progressMonitor));

				createProjectFile(
						project,
						"/plugin.xml",
						viewGenerator.generatePluginXml(
								projectName
										.concat(".").
										concat(filesGenerator.extFactoryName(projectName).toString()),
								projectName.concat(".").concat(
										qualifiedNameView)).toString(),
						createSubProgressMonitor(progressMonitor));
			}

			addNatures(project, createSubProgressMonitor(progressMonitor));
		} catch (CoreException e) {
			LOGGER.error("in createProject", e);
			project = null;
		}

		progressMonitor.done();

		return project;
	}

	public static void createActivator(IProject project, String projectName,
			String projectPackagePath, IProgressMonitor progressMonitor)
			throws CoreException {
		createProjectFile(project, projectPackagePath + "/"
				+ filesGenerator.activatorName(projectName) + JAVA_EXTENSION,
				filesGenerator.generateActivator(projectName).toString(),
				createSubProgressMonitor(progressMonitor));
	}

	public static void createExecutableExtensionFactory(IProject project,
			String projectName, String projectPackagePath,
			IProgressMonitor progressMonitor) throws CoreException {
		createProjectFile(project, projectPackagePath
				+ "/"
				+ filesGenerator.extFactoryName(projectName)
				+ JAVA_EXTENSION, filesGenerator
				.generateExecutableExtensionFactory(projectName).toString(),
				createSubProgressMonitor(progressMonitor));
	}

	public static void createModule(IProject project, String projectName,
			String projectPackagePath, String superClass,
			IProgressMonitor progressMonitor) throws CoreException {
		createProjectFile(project, projectPackagePath
				+ "/"
				+ filesGenerator.moduleName(projectName)
				+ JAVA_EXTENSION, filesGenerator
				.generateModule(projectName, superClass).toString(),
				createSubProgressMonitor(progressMonitor));
	}

	private static boolean shouldGeneratePluginXml(Choice viewType) {
		return viewType != Choice.NONE;
	}

	private static String getSimpleNameProject(String projectName) {
		String simpleNameProject = null;
		if (projectName.contains(".")) {
			simpleNameProject = projectName.substring(projectName
					.lastIndexOf('.') + 1);
		} else {
			simpleNameProject = projectName.substring(projectName
					.lastIndexOf('/') + 1);
		}
		simpleNameProject = simpleNameProject.substring(0, 1).toUpperCase()
				.concat(simpleNameProject.substring(1));
		return simpleNameProject;
	}

	/**
	 * @param progressMonitor
	 * @return
	 */
	public static IProgressMonitor createSubProgressMonitor(
			IProgressMonitor progressMonitor) {
		return new SubProgressMonitor(progressMonitor, 1);
	}

	/**
	 * Just do the basics: create a basic project.
	 * 
	 * @param location
	 * @param projectName
	 * @param progressMonitor
	 */
	private static IProject createBaseProject(String projectName, URI location,
			IProgressMonitor progressMonitor) {
		progressMonitor.subTask("Creating project resource");

		// it is acceptable to use the ResourcesPlugin class
		IProject newProject = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(projectName);

		if (!newProject.exists()) {
			URI projectLocation = location;
			IProjectDescription desc = newProject.getWorkspace()
					.newProjectDescription(newProject.getName());
			if (location != null
					&& location.equals(ResourcesPlugin.getWorkspace().getRoot()
							.getLocationURI())) {
				projectLocation = null;
			}

			desc.setLocationURI(projectLocation);
			try {
				newProject.create(desc,
						createSubProgressMonitor(progressMonitor));
				if (!newProject.isOpen()) {
					newProject.open(createSubProgressMonitor(progressMonitor));
				}
			} catch (CoreException e) {
				LOGGER.error("in createBaseProject", e);
			}
		}

		progressMonitor.done();

		return newProject;
	}

	/**
	 * Create a folder structure with a parent root, overlay, and a few child
	 * folders.
	 * 
	 * @param newProject
	 * @param paths
	 * @param progressMonitor
	 * @throws CoreException
	 */
	public static void addToProjectStructure(IProject newProject,
			String[] paths, IProgressMonitor monitor) throws CoreException {
		IProgressMonitor progressMonitor = createSubProgressMonitor(monitor);
		progressMonitor.subTask("Creating project folders");
		for (String path : paths) {
			IFolder etcFolders = newProject.getFolder(path);
			createFolder(etcFolders);
		}
		progressMonitor.done();
	}

	private static void createFolder(IFolder folder) throws CoreException {
		IContainer parent = folder.getParent();
		if (parent instanceof IFolder) {
			createFolder((IFolder) parent);
		}
		if (!folder.exists()) {
			folder.create(false, true, null);
		}
	}

	/**
	 * @param project
	 * @param fileName
	 * @param contents
	 * @param progressMonitor
	 * @throws CoreException
	 */
	public static void createProjectFile(IProject project, String fileName,
			String contents, IProgressMonitor progressMonitor)
			throws CoreException {
		progressMonitor.subTask("Creating file " + fileName);
		IFile iFile = project.getFile(fileName);
		iFile.create(
				new ByteArrayInputStream(contents.getBytes(Charset
						.forName(iFile.getCharset()))), true, null);
		progressMonitor.done();
	}

	private static void addNatures(IProject project,
			IProgressMonitor progressMonitor) throws CoreException {
		progressMonitor.subTask("Adding natures");
		List<IClasspathEntry> classpathEntries = new UniqueEList<IClasspathEntry>();

		IJavaProject javaProject = JavaCore.create(project);
		IProjectDescription projectDescription = project.getDescription();

		projectDescription.setNatureIds(new String[] { JavaCore.NATURE_ID,
				IBundleProjectDescription.PLUGIN_NATURE });

		IProgressMonitor monitor = null;
		project.setDescription(projectDescription, monitor);

		IPath projectPath = project.getFullPath();
		IPath javaSource = projectPath.append("src");

		IClasspathEntry sourceClasspathEntry = JavaCore
				.newSourceEntry(javaSource);
		for (Iterator<IClasspathEntry> i = classpathEntries.iterator(); i
				.hasNext();) {
			IClasspathEntry classpathEntry = i.next();
			if (classpathEntry.getPath().isPrefixOf(javaSource)) {
				i.remove();
			}
		}
		classpathEntries.add(0, sourceClasspathEntry);

		classpathEntries.add(JavaRuntime.getDefaultJREContainerEntry());

		classpathEntries.add(JavaCore.newContainerEntry(new Path(
				"org.eclipse.pde.core.requiredPlugins")));

		javaProject.setRawClasspath(classpathEntries
				.toArray(new IClasspathEntry[classpathEntries.size()]),
				createSubProgressMonitor(progressMonitor));

		progressMonitor.done();
	}

}
