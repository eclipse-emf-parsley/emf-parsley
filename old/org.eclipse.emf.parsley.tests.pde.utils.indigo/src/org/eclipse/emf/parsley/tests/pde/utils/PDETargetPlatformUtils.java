package org.eclipse.emf.parsley.tests.pde.utils;
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


import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.osgi.baseadaptor.BaseData;
import org.eclipse.osgi.framework.internal.core.AbstractBundle;
import org.eclipse.pde.internal.core.target.TargetPlatformService;
import org.eclipse.pde.internal.core.target.provisional.IBundleContainer;
import org.eclipse.pde.internal.core.target.provisional.ITargetDefinition;
import org.eclipse.pde.internal.core.target.provisional.ITargetPlatformService;
import org.eclipse.pde.internal.core.target.provisional.LoadTargetDefinitionJob;
import org.osgi.framework.Bundle;

/**
 * Implements workaround suggested here:
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=343156
 * This is required when running SwtBot tests in Tycho
 * that requires the PDE, for example, for testing that the
 * imported projects compile fine, or if they use the DSL, which
 * requires PDE projects dependencies.
 * 
 * @author Lorenzo Bettini - some adaptations
 */
@SuppressWarnings("restriction")
public class PDETargetPlatformUtils {
	
	private static boolean targetPlatformAlreadySet = false;

	/**
	 * Sets a target platform in the test platform to get workspace builds OK
	 * with PDE.
	 * 
	 * @throws Exception
	 */
	public static void setTargetPlatform() throws Exception {
		if (System.getProperty("buildingWithTycho") != null) {
			if (targetPlatformAlreadySet) {
				System.out.println("Target platform already set");
				return;
			}
			targetPlatformAlreadySet = true;
			System.out.println("Generating a target platform");
		} else {
			System.out.println("Using the Workbench's target platform");
			return;
		}
		
		ITargetPlatformService tpService = TargetPlatformService.getDefault();
		ITargetDefinition targetDef = tpService.newTarget();
		targetDef.setName("Tycho platform");
		Bundle[] bundles = Platform.getBundle("org.eclipse.core.runtime").getBundleContext().getBundles();
		List<IBundleContainer> bundleContainers = new ArrayList<IBundleContainer>();
		Set<File> dirs = new HashSet<File>();
		System.out.println("Bundles for the target platform:");
		for (Bundle bundle : bundles) {
			System.out.print(bundle);
			AbstractBundle bundleImpl = (AbstractBundle) bundle;
			BaseData bundleData = (BaseData) bundleImpl.getBundleData();
//			EquinoxBundle bundleImpl = (EquinoxBundle) bundle;
//			Generation generation = (Generation) bundleImpl.getModule().getCurrentRevision().getRevisionInfo();
			File file = bundleData.getBundleFile().getBaseFile();
			File folder = file.getParentFile();
			if (!dirs.contains(folder)) {
				dirs.add(folder);
				bundleContainers.add(tpService.newDirectoryContainer(folder.getAbsolutePath()));
			}
		}
		System.out.println("");
		System.out.println("Bundles added the target platform.");
		targetDef.setBundleContainers(bundleContainers.toArray(new IBundleContainer[bundleContainers.size()]));
		targetDef.setArch(Platform.getOSArch());
		targetDef.setOS(Platform.getOS());
		targetDef.setWS(Platform.getWS());
		targetDef.setNL(Platform.getNL());
		// targetDef.setJREContainer()
		tpService.saveTargetDefinition(targetDef);

		System.out.print("Loading target platform... ");
		Job job = new LoadTargetDefinitionJob(targetDef);
		job.schedule();
		job.join();
		System.out.println("DONE.");
	}
}
