/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.generator.common

/**
 * @author Lorenzo Bettini - initial API and implementation
 */
class EmfParsleyProjectFilesGeneratorUtil {

	protected new() {
		
	}

	def static buildClassNameFromProject(String projectName) {
		var prefixName = projectName
		val dotIndex = projectName.lastIndexOf(".")
		if (dotIndex > 0)
			prefixName = projectName.substring(dotIndex+1)
		return prefixName.toFirstUpper
	}

	def static buildFQNFromProject(String projectName) {
		var packageName = projectName.packageFromProject
		if (!packageName.empty)
			packageName += '.'
		return packageName + projectName.buildClassNameFromProject
	}

	def static stripPackageFromProject(String projectName) {
		val dotIndex = projectName.lastIndexOf(".")
		if (dotIndex > 0)
			return projectName.substring(dotIndex+1)
		return projectName
	}

	def static getPackageFromProject(String projectName) {
		return projectName
	}
}