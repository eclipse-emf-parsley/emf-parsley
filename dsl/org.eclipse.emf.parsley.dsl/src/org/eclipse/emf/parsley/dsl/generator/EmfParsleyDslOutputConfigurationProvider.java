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

	public static final String PLUGIN_XML_EMFPARSLEY_GEN_EXTENSION = "xml_emfparsley_gen";

	public static final String PLUGIN_XML_GEN_FILE = "plugin." + PLUGIN_XML_EMFPARSLEY_GEN_EXTENSION;

	@Override
	public Set<OutputConfiguration> getOutputConfigurations() {
		Set<OutputConfiguration> outputconfigurations = super
				.getOutputConfigurations();
		OutputConfiguration outputConfiguration = outputconfigurations
				.iterator().next();
		outputConfiguration.setOutputDirectory(EMFPARSLEY_GEN);

		return outputconfigurations;
	}

}