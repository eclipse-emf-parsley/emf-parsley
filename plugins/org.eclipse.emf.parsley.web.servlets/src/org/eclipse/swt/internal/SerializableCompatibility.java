/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Vincenzo Caselli - Initial contribution and API
 * 
 *******************************************************************************/
package org.eclipse.swt.internal;


import java.io.Serializable;

/**
 * This interface is the cross-platform version of the java.io.Serializable interface.
 * <p>
 * It is part of our effort to provide support for both J2SE and J2ME platforms. Under this scheme,
 * classes need to implement SerializableCompatibility instead of java.io.Serializable.
 * </p>
 * <p>
 * Note: java.io.Serializable is not part of CLDC.
 * </p>
 */
public interface SerializableCompatibility extends Serializable {
}
