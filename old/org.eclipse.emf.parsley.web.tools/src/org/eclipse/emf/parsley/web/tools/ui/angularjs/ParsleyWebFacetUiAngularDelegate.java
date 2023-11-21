/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Vincenzo Caselli - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.web.tools.ui.angularjs;

import java.util.Properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.parsley.web.tools.Activator;
import org.eclipse.emf.parsley.web.tools.Utils;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.web.operations.WebPropertiesUtil;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * Wizard class for execution of actions for the AngularJS UI component creation
 * 
 * @author Vincenzo Caselli
 * 
 */
@SuppressWarnings("restriction")
public class ParsleyWebFacetUiAngularDelegate implements IDelegate {

	@Override
	public void execute(final IProject iProject, final IProjectFacetVersion fv, final Object config,
			final IProgressMonitor monitor) throws CoreException {
		monitor.beginTask("Processing EMF Parsley UI AngularJS", 2);

		// String webInfLib =
		// WebPropertiesUtil.getWebLibFolder(iProject).getProjectRelativePath().toPortableString();
		String webContentFolder = WebPropertiesUtil.getModuleServerRoot(iProject).getProjectRelativePath()
				.toPortableString();
		System.out.println("webContentFolder): " + webContentFolder);
		String serverContentRoot = J2EEProjectUtilities.getServerContextRoot(iProject);
		System.out.println("serverContentRoot: " + serverContentRoot);

		Properties replaceStrings = new Properties();
		Utils.copyFile(iProject, monitor, "/templates/ui/angularjs/table.html",
				iProject.getFolder(webContentFolder).getFile("table.html"), replaceStrings);
		Utils.copyFile(iProject, monitor, "/templates/ui/angularjs/details.html",
				iProject.getFolder(webContentFolder).getFile("details.html"), replaceStrings);
		Utils.copyFromPluginToWorkspace(Activator.getDefault().getBundle(), new Path("/templates/ui/angularjs/add.png"),
				iProject.getFolder(webContentFolder).getFile("add.png"));

	}

}
