/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available underthe terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.edit.ui.provider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.util.EmfParsleyUtil;

import com.google.inject.Inject;
import com.google.inject.MembersInjector;

/**
 * Some utility factory methods to create specific {@link ViewerContentProvider}
 * s that can be reused in some contexts.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class ViewerContentProviderFactory {

	@Inject
	private MembersInjector<ViewerContentProvider> viewerContentProviderMembersInjector;

	/**
	 * Creates a {@link ViewerContentProvider} that implements getElements for a
	 * {@link Resource} by forwarding to the passed
	 * {@link IResourceContentsProvider}.
	 * 
	 * @param resourceContentsProvider
	 */
	public ViewerContentProvider createViewerContentProviderForResource(final IResourceContentsProvider resourceContentsProvider) {
		ViewerContentProvider instance = new ViewerContentProvider() {

			@Override
			public List<Object> elements(Object o) {
				return new ArrayList<Object>(
						EmfParsleyUtil.ensureCollection(resourceContentsProvider.getContents((Resource) o)));
			}
		};
		viewerContentProviderMembersInjector.injectMembers(instance);
		return instance;
	}

	/**
	 * Creates a {@link ViewerContentProvider} that implements getElements returning the value of the given
	 * feature of the input; this assumes that the input is an {@link EObject} and that its class contains
	 * such feature.
	 * 
	 * @param feature
	 */
	public ViewerContentProvider createViewerContentProviderForFeature(final EStructuralFeature feature) {
		ViewerContentProvider instance = new ViewerContentProvider() {

			@Override
			public List<Object> elements(Object o) {
				return new ArrayList<Object>(EmfParsleyUtil.ensureCollection(((EObject) o).eGet(feature)));
			}
		};
		viewerContentProviderMembersInjector.injectMembers(instance);
		return instance;
	}
}
