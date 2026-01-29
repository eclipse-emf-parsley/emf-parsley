/**
 * Copyright (c) 2024 Lorenzo Bettini and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 */
package org.eclipse.emf.parsley.dsl.generator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.util.JavaVersion;
import org.eclipse.xtext.xbase.compiler.GeneratorConfig;
import org.eclipse.xtext.xbase.compiler.GeneratorConfigProvider;

/**
 * @author Lorenzo Bettini - initial API and implementation
 */
public class EmfParsleyDslGeneratorConfigProvider extends GeneratorConfigProvider {

	@Override
	public GeneratorConfig get(EObject context) {
		final var generatorConfig = super.get(context);
		generatorConfig.setJavaSourceVersion(JavaVersion.JAVA11);
		return generatorConfig;
	}
}
