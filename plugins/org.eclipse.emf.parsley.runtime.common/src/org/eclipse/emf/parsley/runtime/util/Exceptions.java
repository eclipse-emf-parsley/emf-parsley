/*******************************************************************************
 * Copyright (c) 2010, 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * itemis AG - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.runtime.util;

import org.eclipse.emf.common.util.WrappedException;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
public class Exceptions {

	public static <T> T throwUncheckedException(Throwable e) {
		if (e instanceof RuntimeException) {
			throw (RuntimeException) e;
		}
		if (e instanceof Error) {
			throw (Error) e;
		}
		if (e instanceof Exception) {
			throw new WrappedException((Exception) e);
		}
		throw new RuntimeException(e);
	}
}
