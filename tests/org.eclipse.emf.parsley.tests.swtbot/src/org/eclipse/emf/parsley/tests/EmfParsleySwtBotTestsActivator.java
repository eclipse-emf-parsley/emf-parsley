/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests;

import static com.google.inject.Guice.createInjector;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.parsley.EmfParsleyExtensionFactory;
import org.eclipse.emf.parsley.tests.factories.CustomContentProviderLibraryExecutableExtensionFactory;
import org.eclipse.emf.parsley.tests.factories.CustomContentProviderLibraryModule;
import org.eclipse.emf.parsley.tests.factories.CustomElementsContentProviderLibraryExecutableExtensionFactory;
import org.eclipse.emf.parsley.tests.factories.CustomElementsContentProviderLibraryModule;
import org.eclipse.emf.parsley.tests.factories.CustomLibraryExecutableExtensionFactory;
import org.eclipse.emf.parsley.tests.factories.CustomLibraryModule;
import org.eclipse.emf.parsley.tests.factories.resourcelistening.ResourceListeningLibraryExecutableExtensionFactory;
import org.eclipse.emf.parsley.tests.factories.resourcelistening.ResourceListeningLibraryModule;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.google.inject.Injector;

public class EmfParsleySwtBotTestsActivator extends AbstractUIPlugin {

	// The shared instance
	private static EmfParsleySwtBotTestsActivator plugin;

	public static final String EMF_TREE_EDITOR = "org.eclipse.emf.parsley.editors.treeEditor";

	public static final String EMF_TREE_EDITOR_NO_MOUSE_ID = "org.eclipse.emf.parsley.noMouseEvents";
	
	public static final String EMF_TREE_EDITOR_OPEN_FORM_DIALOG_ID = "org.eclipse.emf.parsley.openFormDialogOnDoubleClick";

	public static final String EMF_TREE_EDITOR_OPEN_DIALOG_ID = "org.eclipse.emf.parsley.openDialogOnDoubleClick";

	public static final String EMF_TREE_EDITOR_OPEN_DIALOG_UNDOABLE_ID = "org.eclipse.emf.parsley.openDialogUndoable";

	public static final String EMF_TREE_EDITOR_CUSTOM_LABEL = "org.eclipse.emf.parsley.customLabel";

	public static final String EMF_TREE_EDITOR_FOR_XTEXT = "org.eclipse.emf.parsley.xtext";

	public static final String EMF_EDITOR_FOR_LIBRARY = "org.eclipse.emf.parsley.customLibrary";

	public static final String EMF_EDITOR_FOR_MENU_LIBRARY = "org.eclipse.emf.parsley.customMenuLibrary";

	/**
	 * This will only simulate generated code by the wizard to make sure a
	 * singleton injector is used.
	 */
	private Map<String, Injector> injectorsMap = new HashMap<String, Injector>();

	public EmfParsleySwtBotTestsActivator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static EmfParsleySwtBotTestsActivator getDefault() {
		return plugin;
	}

	public Injector getInjector(
			Class<? extends EmfParsleyExtensionFactory> cName) {
		Injector injector = injectorsMap.get(cName.getCanonicalName());
		if (injector == null) {
			if (CustomLibraryExecutableExtensionFactory.class.equals(cName)) {
				injector = createInjector(new CustomLibraryModule(this));
				injectorsMap.put(cName.getCanonicalName(), injector);
			} else if (CustomContentProviderLibraryExecutableExtensionFactory.class
					.equals(cName)) {
				injector = createInjector(new CustomContentProviderLibraryModule(
						this));
				injectorsMap.put(cName.getCanonicalName(), injector);
			} else if (CustomElementsContentProviderLibraryExecutableExtensionFactory.class
					.equals(cName)) {
				injector = createInjector(new CustomElementsContentProviderLibraryModule(
						this));
				injectorsMap.put(cName.getCanonicalName(), injector);
			} else if (ResourceListeningLibraryExecutableExtensionFactory.class
					.equals(cName)) {
				injector = createInjector(new ResourceListeningLibraryModule(	
						this));
				injectorsMap.put(cName.getCanonicalName(), injector);
			}
		}

		if (injector != null)
			return injector;

		throw new IllegalArgumentException(cName.getCanonicalName());
	}

	public static String localFileContents(String filename) throws IOException {
		File f = localFile(filename);
		if (f.exists()) {
			return readFileAsString(f);
		}
		return f.getAbsolutePath();
	}

	protected static File localFile(String filename) throws IOException {
		IPath path = new Path("models/" + filename);
		URL url = FileLocator.find(getDefault().getBundle(), path, null);
		url = FileLocator.resolve(url);
		url.toExternalForm();
		File f = new File(url.getFile());
		return f;
	}

	private static String readFileAsString(File file) throws IOException {
		byte[] buffer = new byte[(int) file.length()];
		BufferedInputStream f = null;
		try {
			f = new BufferedInputStream(new FileInputStream(file));
			f.read(buffer);
		} finally {
			if (f != null)
				try {
					f.close();
				} catch (IOException ignored) {
				}
		}
		return new String(buffer);
	}

}
