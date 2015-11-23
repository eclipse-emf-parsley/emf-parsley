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
import java.nio.charset.Charset;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.parsley.generator.common.EmfParsleyProjectFilesGenerator;

/**
 * @author Lorenzo Bettini
 */
public class NewEmfParsleyProjectSupport {

	private static final String JAVA_EXTENSION = ".java";

	static EmfParsleyProjectFilesGenerator filesGenerator = new EmfParsleyProjectFilesGenerator();

	protected NewEmfParsleyProjectSupport() {
		// hide the implicit one
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

	/**
	 * @param progressMonitor
	 * @return
	 */
	public static IProgressMonitor createSubProgressMonitor(
			IProgressMonitor progressMonitor) {
		return new SubProgressMonitor(progressMonitor, 1);
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

}
