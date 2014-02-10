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

package org.eclipse.emf.parsley.ecore;

import java.util.List;

/**
 * @author Lorenzo Bettini
 * 
 */
public class FeatureNamePath {

	String featureName;

	List<FeatureNamePath> paths;

	public FeatureNamePath(String featureName) {
		super();
		this.featureName = featureName;
	}

	public FeatureNamePath(String featureName, List<FeatureNamePath> paths) {
		super();
		this.featureName = featureName;
		this.paths = paths;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public List<FeatureNamePath> getPaths() {
		return paths;
	}

	public void setPaths(List<FeatureNamePath> paths) {
		this.paths = paths;
	}

}
