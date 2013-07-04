/**
 * 
 */
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
		return null;
	}
}
