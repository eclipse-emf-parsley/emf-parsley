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
package org.eclipse.emf.parsley.util;

import static com.google.common.collect.Iterables.filter;

import java.util.Collection;
import java.util.EventObject;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Utility methods acting on EMF {@link Command}s.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class EmfCommandsUtil {

	protected EmfCommandsUtil() {
	}

	/**
	 * Returns the most recent {@link Command} in the {@link CommandStack}; the
	 * returned command could be null in case an exception was thrown during the
	 * execution of the command.
	 * 
	 * @param event
	 * @return
	 */
	public static Command mostRecentCommand(final EventObject event) {
		return ((CommandStack) event.getSource()).getMostRecentCommand();
	}

	/**
	 * Whether the any of the {@link EObject}s affected by the command belongs
	 * to the passed resource (which is assumed not null).
	 * 
	 * @param command
	 * @param resource assumed not null
	 * @return
	 */
	public static boolean affectsResource(final Command command, final Resource resource) {
		Collection<?> affectedObjects = command.getAffectedObjects();
		return Iterables.any(filter(affectedObjects, EObject.class), new Predicate<EObject>() {
			@Override
			public boolean apply(EObject input) {
				return resource.equals(input.eResource());
			}
		});
	}
}
