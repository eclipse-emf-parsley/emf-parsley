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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Dictionary;

import org.eclipse.emf.parsley.runtime.exception.PluginConfigurationException;
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
	 * the singleton instance.  If the specified {@link Bundle} is null, then it simply
	 * returns null.
	 * 
	 * @param bundle
	 * @return
	 * @throws PluginConfigurationException if bundle is not found or if it doesn't contain the getDefault method
	 */
	public static AbstractUIPlugin getPlugin(Bundle bundle){
		if (bundle == null) {
			return null;
		}
		final Dictionary<String, String> headers = bundle.getHeaders();
		String activator = headers.get(Constants.BUNDLE_ACTIVATOR);
			Class<?> activatorClass;
			Object activatorInstance;
			try {
				activatorClass = bundle.loadClass(activator);
				try {
					Method method = activatorClass.getMethod("getDefault");
					activatorInstance = method.invoke(null);
				} catch (NoSuchMethodException e) {
					throw new PluginConfigurationException("Cannot find getDefault method in activator of bundle "+bundle.getBundleId(),e);
				} catch (SecurityException e) {
					throw new PluginConfigurationException("Error accessing getDefault method in activator of bundle "+bundle.getBundleId(),e);
				} catch (IllegalAccessException e) {
					throw new PluginConfigurationException("Error accessing getDefault method in activator of bundle "+bundle.getBundleId(),e);
				} catch (IllegalArgumentException e) {
					throw new PluginConfigurationException("Error accessing getDefault method in activator of bundle "+bundle.getBundleId(),e);
				} catch (InvocationTargetException e) {
					throw new PluginConfigurationException("Error accessing getDefault method in activator of bundle "+bundle.getBundleId(),e);
				}
				return (AbstractUIPlugin) activatorInstance;
			} catch (ClassNotFoundException e) {
				throw new PluginConfigurationException("Cannot find activator for bundle "+bundle.getBundleId(),e);
			}
	}
}
