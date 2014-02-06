/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.emf.parsley.dsl.ui.builder;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.xtext.builder.DerivedResourceMarkers;

/**
 * Custom implementation to deal with the generation in the
 * project root folder: in that output configuration we use "src" and then
 * when generating we use "src/..", thus, when seraching for derived
 * resource markers, we must make sure that, instead of searching in "src"
 * we search in the root folder.
 * 
 * @author Lorenzo Bettini
 *
 */
public class EmfParsleyDslDerivedResourceMarkers extends
		DerivedResourceMarkers {

	@Override
	public Iterable<IMarker> findDerivedResourceMarkers(IResource file,
			String generatorId) throws CoreException {
		IResource resource = file;
		
		String location = file.getLocation().toString();
		if (location.endsWith("/src")) {
			// we won't find derived resources in src:
			// we'll find them in the parent folder
			resource = file.getParent();
		}
		
		return super.findDerivedResourceMarkers(resource, generatorId);
	}
}
