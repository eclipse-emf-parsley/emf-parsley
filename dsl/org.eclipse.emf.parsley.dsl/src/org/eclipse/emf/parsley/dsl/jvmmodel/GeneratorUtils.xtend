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
package org.eclipse.emf.parsley.dsl.jvmmodel;

import com.google.inject.Inject
import java.beans.Introspector
import org.eclipse.emf.parsley.dsl.model.Module
import org.eclipse.emf.parsley.dsl.model.PartSpecification
import org.eclipse.emf.parsley.generator.common.EmfParsleyProjectFilesGenerator
import org.eclipse.xtext.naming.IQualifiedNameProvider

import static extension org.eclipse.xtext.EcoreUtil2.*

/**
 * @author Lorenzo Bettini
 *
 */
class GeneratorUtils {
	
	@Inject extension IQualifiedNameProvider
	
	@Inject EmfParsleyProjectFilesGenerator projectFilesGenerator

	def String getPropertyNameForGetterSetterMethod(String opName) {
		if (opName.startsWith("get") && opName.length() > 3 && Character.isUpperCase(opName.charAt(3))) {
			return Introspector.decapitalize(opName.substring(3));
		}

		if (opName.startsWith("is") && opName.length() > 2 && Character.isUpperCase(opName.charAt(2))) {
			return Introspector.decapitalize(opName.substring(2));
		}

		return opName;
	}

	def executableExtensionFactoryQN(Module element) {
   		element.fullyQualifiedName.toString + "." +
   		projectFilesGenerator.extFactoryName(element.fullyQualifiedName.toString)
   	}

   	def executableExtensionFactoryQN(PartSpecification element) {
   		element.getContainerOfType(typeof(Module)).executableExtensionFactoryQN
   	}

	
}
