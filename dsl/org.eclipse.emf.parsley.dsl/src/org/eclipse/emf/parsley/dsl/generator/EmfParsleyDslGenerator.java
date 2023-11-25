/**
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 */
package org.eclipse.emf.parsley.dsl.generator;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.compiler.JvmModelGenerator;

import com.google.inject.Inject;

/**
 * This generator calls the standard JvmModelGenerator and the generator for
 * plugin.xml_gen
 */
public class EmfParsleyDslGenerator implements IGenerator {
	@Inject
	private JvmModelGenerator jvmModelGenerator;

	@Inject
	private EmfParsleyDslPluginXmlGenerator pluginXmlGenerator;

	@Override
	public void doGenerate(final Resource input, final IFileSystemAccess fsa) {
		jvmModelGenerator.doGenerate(input, fsa);
		pluginXmlGenerator.doGenerate(input, fsa);
	}
}
