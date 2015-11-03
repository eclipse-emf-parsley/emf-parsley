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
package org.eclipse.emf.parsley.dsl.generator;

import java.util.Set;

import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.generator.OutputConfigurationProvider;

public class EmfParsleyDslOutputConfigurationProvider extends
		OutputConfigurationProvider {

	public static final String EMFPARSLEY_GEN = "./emfparsley-gen";

	public static final String PROJECT_ROOT_OUTPUT = "PROJECT_ROOT";

	public static final String PROJECT_ROOT_ONCE_OUTPUT = "PROJECT_ONCE_ROOT";

	public static final String PLUGIN_XML_EMFPARSLEY_REL_GEN_PATH = "../";

	public static final String PLUGIN_XML_EMFPARSLEY_GEN = "plugin.xml_emfparsley_gen";

	public static final String PLUGIN_XML_EMFPARSLEY_GEN_PATH = PLUGIN_XML_EMFPARSLEY_REL_GEN_PATH
			+ PLUGIN_XML_EMFPARSLEY_GEN;

	public static final String PLUGIN_XML_EMFPARSLEY = "plugin.xml";

	public static final String PLUGIN_XML_EMFPARSLEY_PATH = PLUGIN_XML_EMFPARSLEY_REL_GEN_PATH
			+ PLUGIN_XML_EMFPARSLEY;

	@Override
	public Set<OutputConfiguration> getOutputConfigurations() {
		Set<OutputConfiguration> outputconfigurations = super
				.getOutputConfigurations();
		OutputConfiguration outputConfiguration = outputconfigurations
				.iterator().next();
		outputConfiguration.setOutputDirectory(EMFPARSLEY_GEN);

		OutputConfiguration projectRootOutput = new OutputConfiguration(
				PROJECT_ROOT_OUTPUT);
		projectRootOutput.setDescription("Output Folder for generated "
				+ PLUGIN_XML_EMFPARSLEY_GEN);
		projectRootOutput.setOutputDirectory("src");
		projectRootOutput.setOverrideExistingResources(true);
		projectRootOutput.setCreateOutputDirectory(true);
		projectRootOutput.setCanClearOutputDirectory(false);
		projectRootOutput.setCleanUpDerivedResources(true);
		projectRootOutput.setSetDerivedProperty(true);

		outputconfigurations.add(projectRootOutput);

		// This is for plugin.xml which is generated only once
		projectRootOutput = new OutputConfiguration(
				PROJECT_ROOT_ONCE_OUTPUT);
		projectRootOutput.setDescription("Output Folder for generated "
				+ PLUGIN_XML_EMFPARSLEY);
		projectRootOutput.setOutputDirectory("src");
		projectRootOutput.setOverrideExistingResources(false);
		projectRootOutput.setCreateOutputDirectory(true);
		projectRootOutput.setCanClearOutputDirectory(false);
		projectRootOutput.setCleanUpDerivedResources(false);
		projectRootOutput.setSetDerivedProperty(false);

		outputconfigurations.add(projectRootOutput);

		return outputconfigurations;
	}

}