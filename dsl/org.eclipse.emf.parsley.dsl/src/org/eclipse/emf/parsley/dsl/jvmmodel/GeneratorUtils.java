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

import java.beans.Introspector;

/**
 * @author Lorenzo Bettini
 *
 */
public class GeneratorUtils {

	public String getPropertyNameForGetterSetterMethod(String opName) {
		if (opName.startsWith("get") && opName.length() > 3 && Character.isUpperCase(opName.charAt(3)))
			return Introspector.decapitalize(opName.substring(3));

		if (opName.startsWith("is") && opName.length() > 2 && Character.isUpperCase(opName.charAt(2)))
			return Introspector.decapitalize(opName.substring(2));

		return opName;
	}
}
