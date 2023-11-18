/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.junit4.ui.util;

/**
 * A runnable that returns an object of type T.
 *
 * @author Lorenzo Bettini
 *
 */
public interface RunnableWithResult<T> {
	/**
	 * @return an object of type T.
	 */
	T run();
}
