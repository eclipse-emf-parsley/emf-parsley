/**
 * 
 */
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
