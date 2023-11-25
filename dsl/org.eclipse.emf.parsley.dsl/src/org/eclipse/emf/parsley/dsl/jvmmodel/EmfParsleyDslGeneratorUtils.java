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
package org.eclipse.emf.parsley.dsl.jvmmodel;

import static org.eclipse.xtext.EcoreUtil2.getContainerOfType;

import java.beans.Introspector;
import java.util.List;

import org.eclipse.emf.parsley.dsl.model.PartSpecification;
import org.eclipse.emf.parsley.dsl.model.PartsSpecifications;
import org.eclipse.emf.parsley.generator.common.EmfParsleyProjectFilesGenerator;
import org.eclipse.xtext.naming.IQualifiedNameProvider;

import com.google.inject.Inject;

/**
 * @author Lorenzo Bettini
 */
public class EmfParsleyDslGeneratorUtils {
	@Inject
	private IQualifiedNameProvider qualifiedNameProvider;

	@Inject
	private EmfParsleyProjectFilesGenerator projectFilesGenerator;

	public String getPropertyNameForGetterSetterMethod(final String opName) {
		if (opName.startsWith("get") && opName.length() > 3 && Character.isUpperCase(opName.charAt(3))) {
			return Introspector.decapitalize(opName.substring(3));
		}
		if (opName.startsWith("is") && opName.length() > 2 && Character.isUpperCase(opName.charAt(2))) {
			return Introspector.decapitalize(opName.substring(2));
		}
		return opName;
	}

	public String executableExtensionFactoryQN(final org.eclipse.emf.parsley.dsl.model.Module element) {
		return qualifiedNameProvider.getFullyQualifiedName(element).toString() + "."
			+ projectFilesGenerator
				.extFactoryName(qualifiedNameProvider.getFullyQualifiedName(element).toString());
	}

	public String executableExtensionFactoryQN(final PartSpecification element) {
		return executableExtensionFactoryQN(
				getContainerOfType(element,
						org.eclipse.emf.parsley.dsl.model.Module.class));
	}

	public boolean shouldGenerateExtensions(final org.eclipse.emf.parsley.dsl.model.Module module) {
		PartsSpecifications partsSpecifications = null;
		if (module != null) {
			partsSpecifications = module.getPartsSpecifications();
		}
		List<PartSpecification> parts = null;
		if (partsSpecifications != null) {
			parts = partsSpecifications.getParts();
		}
		return parts != null && !parts.isEmpty();
	}
}
