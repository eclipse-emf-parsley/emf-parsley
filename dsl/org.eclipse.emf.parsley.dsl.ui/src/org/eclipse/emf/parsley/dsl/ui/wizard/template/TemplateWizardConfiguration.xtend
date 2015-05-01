/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - initial API and implementation
 * Lorenzo Bettini - refactored to avoid code duplication
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.ui.wizard.template;

import org.eclipse.emf.parsley.dsl.ui.wizard.EmfParsleyDslNewProjectFiles

import static extension org.eclipse.emf.parsley.generator.common.EmfParsleyProjectFilesGeneratorUtil.*

/**
 * @author Francesco Guidieri
 * @author Lorenzo Bettini - refactored to avoid code duplication
 */
public class TemplateWizardConfiguration {
	protected static final EmfParsleyDslNewProjectFiles projectFilesGenerator= new EmfParsleyDslNewProjectFiles

	/**
	 * The text for the template shown in the list
	 */
	private String label;
	
	/**
	 * The class that must be extended by the generated part, this
	 * must be a fully qualified name.
	 */
	private String superClassName;

	/**
	 * A description for this specific template
	 */
	private CharSequence description;
	
	new(String label, CharSequence description, Class<?> superClass) {
		super();
		this.label = label;
		this.description = description;
		this.superClassName = superClass.name;
	}

	def getLabel() {
		return label;
	}

	def CharSequence getDescription() {
		return description;
	}

	def protected getProjectFilesGenerator() {
		projectFilesGenerator
	}

	def getParsleyModuleContents(String projectName) {
		projectFilesGenerator.genDslModuleWithViewPart(
			projectName, getPartClassFQN(projectName),
			'''
			«getConfiguratorContents(projectName)»
			«getResourceManager»
			'''
		).toString
	}

	def final getConfiguratorContents(String projectName) {
		val elements = getConfiguratorElements(projectName)
		if (!elements.empty) {
			return projectFilesGenerator.genConfigurator(elements).toString
		}
		return ""
	}

	/**
	 * This should return the elements for the configurator section in
	 * the generated Parsley module; the default implementation is empty,
	 * subclasses should override it.
	 */
	def String getConfiguratorElements(String projectName) {
		""
	}

	/**
	 * This should return the resourceManager section in
	 * the generated Parsley module; the default implementation is empty,
	 * subclasses should override it.
	 */
	def String getResourceManager() {
		""
	}

	/**
	 * Builds the class name for the part using the project name and
	 * the super class.
	 */
	def String getPartClassName(String projectName) {
		projectName.buildClassNameFromProject + superClassName.buildClassNameFromProject
	}

	/**
	 * Builds the fully qualified name class name for the part using the project name and
	 * the super class.
	 */
	def String getPartClassFQN(String projectName) {
		projectName.buildFQNFromProject + superClassName.buildClassNameFromProject
	}

	/**
	 * This should return the contents for the Java Part class.
	 */
	def String getContentsForPart(String projectName) {
		projectFilesGenerator.
			genViewClass(
				projectName,
				getPartClassName(projectName),
				superClassName
			).toString
	}
}
