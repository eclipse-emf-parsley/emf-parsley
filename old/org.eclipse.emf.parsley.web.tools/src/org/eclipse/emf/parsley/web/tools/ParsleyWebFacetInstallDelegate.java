/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Vincenzo Caselli - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.web.tools;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.parsley.dsl.additional.builder.builder.EmfParsleyDslPluginXmlNature;
import org.eclipse.emf.parsley.web.tools.ParsleyWebFacetInstallConfig.PERSISTENCE_OPTION;
import org.eclipse.emf.parsley.wizards.NewEmfParsleyProjectSupport;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.common.componentcore.internal.ComponentcoreFactory;
import org.eclipse.wst.common.componentcore.internal.ProjectComponents;
import org.eclipse.wst.common.componentcore.internal.ReferencedComponent;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * Wizard class for execution of actions for the core Parsley-JEE project
 * creation
 * 
 * @author Vincenzo Caselli
 * 
 */
@SuppressWarnings("restriction")
public class ParsleyWebFacetInstallDelegate implements IDelegate {

	// private static Logger LOGGER =
	// Logger.getLogger(ParsleyWebFacetInstallDelegate.class);

	@Override
	public void execute(final IProject iProject, final IProjectFacetVersion fv, final Object config,
			final IProgressMonitor monitor) throws CoreException {
		monitor.beginTask("Processing EMF Parsley Web facet", 2);

		final ParsleyWebFacetInstallConfig wizardUserOptions = (ParsleyWebFacetInstallConfig) config;

		setWebModuleDependencies(iProject, monitor, wizardUserOptions);

		// final IFolder webInfLib = Utils.getFolder(iProject, "WEB-INF/lib");

		// Utils.copyFromPluginToWorkspace(org.eclipse.emf.parsley.hwt.Activator.getDefault().getBundle(),
		// new Path("/target/org.eclipse.emf.parsley.hwt-0.0.1-SNAPSHOT.jar"),
		// webInfLib.getFile("org.eclipse.emf.parsley.hwt.jar"));

		// Utils.copyFromPluginToWorkspace(org.eclipse.emf.parsley.web.servlets.Activator.getDefault().getBundle(),
		// new
		// Path("/target/org.eclipse.emf.parsley.web.servlets-0.0.1-SNAPSHOT.jar"),
		// webInfLib.getFile("emf.parsley.web-core.jar"));

		Utils.copyFile(iProject, monitor, "/templates/pom.xml", iProject.getFile("pom.xml"), new Properties());

		addProjectNature(iProject, monitor, XtextProjectHelper.NATURE_ID);
		addProjectNature(iProject, monitor, EmfParsleyDslPluginXmlNature.NATURE_ID ); 
		// TODO should take it from a predefined Maven plugin constant
		addProjectNature(iProject, monitor, "org.eclipse.m2e.core.maven2Nature"); 

		Properties replaceStrings;
		String mainPackage = NewEmfParsleyProjectSupport.getValidJavaName(iProject.getName());
		IPackageFragment pack = JavaCore.create(iProject).getPackageFragmentRoot(iProject.getFolder("src"))
				.createPackageFragment(mainPackage, true, monitor);
		IFolder folder = iProject.getFolder(pack.getPath().removeFirstSegments(1));

		replaceStrings = new Properties();
		replaceStrings.setProperty("org.eclipse.emf.parsley.web.tools.servlets", mainPackage);
		Utils.copyFile(iProject, monitor, "/templates/module.parsley",
				iProject.getFolder("src").getFile("module.parsley"), replaceStrings);

//		replaceStrings = new Properties();
//		replaceStrings.setProperty("org.eclipse.emf.parsley.web.tools.templates", projectName);
//		Utils.copyFile(iProject, monitor, "/templates/ParsleyWebGuiceModule.java",
//				folder.getFile("ParsleyWebGuiceModule.java"), replaceStrings);

		replaceStrings = new Properties();
		IPath fullPath = folder.getFullPath();
		String lastSegment = fullPath.segment(fullPath.segmentCount()-1);
		lastSegment = lastSegment.substring(0, 1).toUpperCase() + lastSegment.substring(1,lastSegment.length());
		replaceStrings.setProperty("ParsleyWebGuiceModule", lastSegment+"EmfParsleyGuiceModule");
		replaceStrings.setProperty("org.eclipse.emf.parsley.web.tools.templates", mainPackage);
		Utils.copyFile(iProject, monitor, "/templates/ParsleyGuiceServletContextListener.java",
				folder.getFile("ParsleyGuiceServletContextListener.java"), replaceStrings);

		replaceStrings = new Properties();

		replaceStrings.setProperty("org.eclipse.emf.parsley.web.tools.templates", mainPackage);

		Utils.copyFile(iProject, monitor, "/templates/ParsleyContextListener.java",
				folder.getFile("ParsleyContextListener.java"), replaceStrings);

		setupWebInfXml(iProject);

		ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.CLEAN_BUILD, monitor);

		monitor.done();
	}

	private void setWebModuleDependencies(final IProject iProject, final IProgressMonitor monitor,
			ParsleyWebFacetInstallConfig wizardUserOptions) {
		Set<String> pluginJarSet = getPluginJarSet(wizardUserOptions);
		Map<String, Object> pluginInstalledPathMap = getInstalledPluginPathMap(pluginJarSet, monitor);

		StructureEdit se = StructureEdit.getStructureEditForWrite(iProject);
		ProjectComponents root = se.getComponentModelRoot();
		WorkbenchComponent firstComponent = (WorkbenchComponent) root.getComponents().get(0);

		addDependentModules(iProject, pluginInstalledPathMap, firstComponent, wizardUserOptions, monitor);

		addMavenDependentModule(iProject, "guice-servlet-4.0.jar", "com/google/inject/extensions/guice-servlet/4.0",
				firstComponent, monitor);
		addMavenDependentModule(iProject, "json-simple-1.1.1.jar", "com/googlecode/json-simple/json-simple/1.1.1",
				firstComponent, monitor);

		se.save(monitor);
	}

	private void addProjectNature(final IProject iProject, final IProgressMonitor monitor, String natureId)
			throws CoreException {
		IProjectDescription description = iProject.getDescription();
		String[] currentNatures = description.getNatureIds();
		String[] newNatures = Arrays.copyOf(currentNatures, currentNatures.length + 1);
		newNatures[currentNatures.length] = natureId;
		description.setNatureIds(newNatures);
		iProject.setDescription(description, monitor);
	}

	private Set<String> getPluginJarSet(final ParsleyWebFacetInstallConfig wizardUserOptions) {
		return new LinkedHashSet<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add("com.google.guava");
				add("com.google.inject");
				add("javax.inject");
				add("javax.persistence");
				add("org.apache.commons.logging");
				add("org.apache.log4j");
				add("org.eclipse.jface");
				add("org.eclipse.osgi");
				add("org.eclipse.core.commands");
				add("org.eclipse.core.databinding");
				add("org.eclipse.core.databinding.observable");
				add("org.eclipse.core.databinding.property");
				add("org.eclipse.jface.databinding");
				add("org.eclipse.ui.workbench");
				add("org.eclipse.equinox.common");
				add("org.eclipse.ui.views");
				add("org.eclipse.ui.forms");
				// add("org.eclipse.swt.win32.win32.x86_64");
				add("org.eclipse.emf");
				add("org.eclipse.emf.common");
				add("org.eclipse.emf.common.ui");
				add("org.eclipse.emf.databinding");
				add("org.eclipse.emf.ecore");
				add("org.eclipse.emf.ecore.edit");
				add("org.eclipse.emf.ecore.xmi");
				add("org.eclipse.emf.edit");
				add("org.eclipse.emf.edit.ui");
				add("org.eclipse.equinox.registry");
				add("org.eclipse.emf.parsley.common");
				add("org.eclipse.emf.parsley.runtime.common");
				add("org.eclipse.emf.parsley.web.servlets");

				if (wizardUserOptions.getPersistenceOption().equals(PERSISTENCE_OPTION.TENEO)) {
					// Just in case Teneo is installed, then its needed plugins
					// are referenced
					// in plugins
					add("org.eclipse.emf.teneo.hibernate.mapper");
					add("org.eclipse.emf.teneo.annotations");
					add("org.eclipse.emf.teneo.hibernate");
					add("org.eclipse.emf.teneo");
					// Deps of org.hibernate
					add("antlr");
					add("hibernate-commons-annotations");
					add("hibernate-core");
					add("jboss-logging");
					add("jboss-transaction-api_1.2_spec");
					// Deps of org.eclipse.m2e.archetype.common
					add("dom4j");
				}
			}
		};
	}

	private Map<String, Object> getInstalledPluginPathMap(Set<String> pluginJarSet, final IProgressMonitor monitor) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BundleContext ctx = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
			Bundle[] bundles = ctx.getBundles();
			for (Bundle bundle : bundles) {
				String pluginId = bundle.getSymbolicName();
				if (bundle.getLocation().indexOf("plugins/") > -1 || "System Bundle".equals(bundle.getLocation())) {
					if (pluginJarSet.contains(pluginId)) {
						map.put(pluginId, bundle);
						logAdded(pluginId, "plugins", bundle.getLocation());
					} else {
						Dictionary<String, String> deps = bundle.getHeaders();
						Enumeration<String> keysEnum = deps.keys();
						while (keysEnum.hasMoreElements()) {
							String depKey = keysEnum.nextElement();
							String depsValues = deps.get(depKey);
							// System.out.println("\t\t" + depKey + ": " +
							// depsValues);
							if ("Embedded-Artifacts".equals(depKey)) {
								String[] depsArray = depsValues.split(";");
								/*
								 * Structure has the following pattern:
								 * 
								 * archetype-common-2.3.jar,
								 * g="org.apache.maven.archetype",
								 * a="archetype-common", v="2.3",
								 * 
								 * dom4j-1.6.1.jar, g="dom4j", a="dom4j",
								 * v="1.6.1",
								 */
								for (int j = 0; j < depsArray.length; j++) {
									// System.out.println("depsArray[" + j + "]
									// = " + depsArray[j]);
									if (depsArray[j].startsWith("a=\"")) {
										String depId = depsArray[j].substring(3, depsArray[j].length() - 1);
										// System.out.println(depId);
										if (pluginJarSet.contains(depId)) {
											String depIdWithVersion = depsArray[j - 2].split(",")[1];
											map.put(depIdWithVersion, bundle.getSymbolicName() + "_"
													+ bundle.getVersion() + "/" + depIdWithVersion);
											logAdded(depIdWithVersion, "Embedded-Artifacts", bundle.getSymbolicName()
													+ "_" + bundle.getVersion() + "/" + depIdWithVersion);
											break;
										}
									}
								}
								// System.out.println(depsArray);
								break;
							}
							if ("Bundle-ClassPath".equals(depKey)) {
								if (!depsValues.equals(".")) {
									String[] depsArray = depsValues.split(",");
									// System.out.println(depsValues);
									/*
									 * Structure has the following pattern:
									 * 
									 * antlr-2.7.7.jar,c3p0-0.9.2.1.jar,dom4j-1.
									 * 6.1.jar,ehcache-core -2.4 .3.jar, ...
									 */
									for (int j = 0; j < depsArray.length; j++) {
										// System.out.println("depsArray[" + j +
										// "] = " +
										// depsArray[j]);
										if (!depsArray[j].equals(".") && depsArray[j].lastIndexOf(".jar") > -1) {
											String depId = depsArray[j].substring(0, depsArray[j].lastIndexOf(".jar"));
											// System.out.println(depId);
											depId = stripVersionIfExists(depId);
											// String depId =
											// depsArray[j].substring(0,
											// depsArray[j].lastIndexOf("-"));
											// System.out.println(depId);
											if (pluginJarSet.contains(depId)) {
												map.put(depsArray[j], bundle.getSymbolicName() + "_"
														+ bundle.getVersion() + "/" + depsArray[j]);
												logAdded(depsArray[j], "Bundle-ClassPath", bundle.getSymbolicName()
														+ "_" + bundle.getVersion() + "/" + depsArray[j]);
											}
										}
									}
									// System.out.println(depsArray);
								}
								break;
							}
						}
					}
					// Enumeration<String> keys = bundle.getHeaders().keys();
					// while (keys.hasMoreElements()) {
					// String key = keys.nextElement();
					// System.out.println(key + "\t\t" +
					// bundle.getHeaders().get(key));
					// }
				}

			}

			monitor.worked(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	private void logAdded(String pluginId, String from, String location) {
		// System.out.println("Added " + Strings.padEnd(pluginId, 50, ' ') +
		// from + "\t\t" + location);
		// LOGGER.debug("Added " + Strings.padEnd(pluginId, 50, ' ') + from +
		// "\t\t" + location);
	}

	private String stripVersionIfExists(String depId) {
		int posLastDot = depId.lastIndexOf('.');
		int posLastDash = depId.lastIndexOf('-');
		if (posLastDot > -1 && posLastDash > -1) {
			return depId.substring(0, posLastDash);
		}
		return depId;
	}

	@SuppressWarnings("unchecked")
	private void addDependentModules(IProject iProject, Map<String, Object> pluginInstalledPathMap,
			WorkbenchComponent firstComponent, ParsleyWebFacetInstallConfig wizardUserOptions,
			IProgressMonitor monitor) {
		for (String pluginId : pluginInstalledPathMap.keySet()) {
			Object obj = pluginInstalledPathMap.get(pluginId);
			String archiveName;
			String bundleIdVersionJar;
			String bundleUri = null;
			boolean oomphedEclipse = false;
			if (obj instanceof Bundle) {
				Bundle bundle = (Bundle) obj;
				oomphedEclipse = bundle.getLocation().indexOf("/.p2/pool/") > -1;
				if (("org.eclipse.emf.parsley.common".equals(pluginId)
						|| "org.eclipse.emf.parsley.runtime.common".equals(pluginId))
						&& !bundle.getLocation().endsWith(".jar")) {
					checkForImportedPlugins(pluginId, bundle);
					// EMF Parsley Project development case
					bundleUri = "module:/resource/" + pluginId + "/" + pluginId;
				}
				bundleIdVersionJar = oomphedEclipse ? bundle.getLocation().substring("reference:/file:".length())
						: bundle.getSymbolicName() + "_" + bundle.getVersion() + ".jar";
				archiveName = bundle.getSymbolicName() + ".jar";
			} else {
				// String => Bundle dependency
				bundleIdVersionJar = (String) obj;
				archiveName = pluginId;
			}
			ReferencedComponent refC = ComponentcoreFactory.eINSTANCE.createReferencedComponent();
			URI uri;
			if (bundleUri != null) {
				// EMF Parsley Project development case
				uri = URI.createURI(bundleUri);
			} else {
				if (oomphedEclipse) {
					uri = URI.createURI("module:/classpath/lib/" + bundleIdVersionJar);
				} else {
					uri = URI.createURI("module:/classpath/var/ECLIPSE_HOME/plugins/" + bundleIdVersionJar);
				}
			}
			refC.setHandle(uri);
			refC.setArchiveName(archiveName);
			Path newPath = new Path("/WEB-INF/lib");
			refC.setRuntimePath(newPath);
			firstComponent.getReferencedComponents().add(refC);
		}

	}

	private void checkForImportedPlugins(final String pluginId, final Bundle bundle) {
		IProject[] workspaceProjects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		boolean found = false;
		for (IProject iProject : workspaceProjects) {
			if (iProject.getName().equals(pluginId) && iProject.isOpen()) {
				found = true;
				break;
			}
		}
		if (!found) {
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				@Override
				public void run() {
					String importPath = bundle.getLocation().substring("reference:file:".length() + 1);
					MessageDialog.openError(null, "Error", "Please import (or open) plugin '" + pluginId
							+ "' from EMF Parsley first instance environment!\n" + "Path: " + importPath);
				}
			});
		}
	}

	// private void addModelDependentModule(IProject iProject, String
	// modelPlugin,
	// WorkbenchComponent firstComponent, IProgressMonitor monitor) {
	// ReferencedComponent refC =
	// ComponentcoreFactory.eINSTANCE.createReferencedComponent();
	// URI uri = URI.createURI("module:/resource/" + modelPlugin + "/" +
	// modelPlugin);
	// refC.setHandle(uri);
	// refC.setArchiveName(modelPlugin + ".jar");
	// Path newPath = new Path("/WEB-INF/lib");
	// refC.setRuntimePath(newPath);
	// firstComponent.getReferencedComponents().add(refC);
	// }

	@SuppressWarnings("unchecked")
	private void addMavenDependentModule(IProject iProject, String jarName, String jarPathInMavenRepo,
			WorkbenchComponent firstComponent, IProgressMonitor monitor) {
		ReferencedComponent refC = ComponentcoreFactory.eINSTANCE.createReferencedComponent();
		URI uri = URI.createURI("module:/classpath/var/M2_REPO/" + jarPathInMavenRepo + "/" + jarName);
		refC.setHandle(uri);
		refC.setArchiveName(jarName);
		Path newPath = new Path("/WEB-INF/lib");
		refC.setRuntimePath(newPath);
		firstComponent.getReferencedComponents().add(refC);
	}

	private void setupWebInfXml(final IProject iProject) {
		final WebArtifactEdit artifact = WebArtifactEdit.getWebArtifactEditForWrite(iProject);
		final WebApp webAppRoot = artifact.getWebApp();
		try {
			Utils.registerServlet(iProject, webAppRoot, "JsonTableServlet",
					"org.eclipse.emf.parsley.web.servlets.JsonTableServlet", "/JsonTableServlet");
			Utils.registerServlet(iProject, webAppRoot, "JsonDetailsServlet",
					"org.eclipse.emf.parsley.web.servlets.JsonDetailsServlet", "/JsonDetailsServlet");
			Utils.registerServlet(iProject, webAppRoot, "JsonSaveServlet",
					"org.eclipse.emf.parsley.web.servlets.JsonSaveServlet", "/JsonSaveServlet");

			artifact.saveIfNecessary(null);
		} finally {
			artifact.dispose();
		}
	}

}
