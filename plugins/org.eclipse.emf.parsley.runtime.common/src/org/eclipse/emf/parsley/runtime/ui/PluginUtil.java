/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.runtime.ui;

import java.lang.reflect.Method;
import java.util.Dictionary;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;

/**
 * Utility methods for plug-ins.
 * 
 * @author Lorenzo Bettini - initial API and implementation
 *
 */
public class PluginUtil {

	protected PluginUtil() {

	}

	public static Bundle getBundle(Class<?> classFromBundle) {
		return FrameworkUtil.getBundle(classFromBundle);
	}

	/**
	 * Retrieves the {@link AbstractUIPlugin} activator from the specified {@link Bundle},
	 * via reflection, assuming that the activator has a static method getDefault returning
	 * the singleton instance.
	 * 
	 * @param bundle
	 * @return
	 * @throws Exception
	 */
	public static AbstractUIPlugin getPlugin(Bundle bundle) throws Exception {
		final Dictionary<String, String> headers = bundle.getHeaders();
		String activator = headers.get(Constants.BUNDLE_ACTIVATOR);
		Class<?> activatorClass = bundle.loadClass(activator);
		Method method = activatorClass.getMethod("getDefault");
		Object activatorInstance = method.invoke(null);
		return (AbstractUIPlugin) activatorInstance;
	}
}
