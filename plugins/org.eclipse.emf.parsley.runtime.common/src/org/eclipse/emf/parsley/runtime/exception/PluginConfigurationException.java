/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.runtime.exception;

import org.eclipse.emf.parsley.runtime.ui.PluginUtil;

/**
 * A {@link RuntimeException} that can be thrown during the reflective operations that are done on plugins.
 * @see {@link PluginUtil#getPlugin(org.osgi.framework.Bundle)} 
 * 
 * @author Francesco Guidieri - Initial contribution and API
 */
public class PluginConfigurationException extends RuntimeException{

	private static final long serialVersionUID = 1213055119380436955L;

	public PluginConfigurationException() {
		super();
	}

	public PluginConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

	public PluginConfigurationException(String message) {
		super(message);
	}

	public PluginConfigurationException(Throwable cause) {
		super(cause);
	}

}
