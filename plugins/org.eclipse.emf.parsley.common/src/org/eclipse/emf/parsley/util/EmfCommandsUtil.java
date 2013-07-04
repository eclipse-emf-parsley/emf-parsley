/**
 * 
 */
package org.eclipse.emf.parsley.util;

import java.util.EventObject;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * Utility methods acting on Emf {@link Command}s.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class EmfCommandsUtil {

	public static Command mostRecentCommand(final EventObject event) {
		return ((CommandStack) event.getSource()).getMostRecentCommand();
	}

	public static boolean affectsResource(Command command, Resource resource) {
		for (Object o : command.getAffectedObjects()) {
			if (o instanceof EObject
					&& resource.equals(((EObject) o).eResource())) {
				return true;
			}
		}
		return false;
	}
}
