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
package org.eclipse.emf.parsley.edit.ui.provider;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.IViewerNotification;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcher;
import org.eclipse.emf.parsley.util.EmfParsleyUtil;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;

import com.google.inject.Inject;

/**
 * Declarative ContentProvider inheriting from {@link AdapterFactoryContentProvider}.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 * 
 */
public class ViewerContentProvider extends AdapterFactoryContentProvider {

	private PolymorphicDispatcher<Object> childrenDispatcher = PolymorphicDispatcher
			.createForSingleTarget("children", 1, 1, this);

	private PolymorphicDispatcher<Object> elementsDispatcher = PolymorphicDispatcher
			.createForSingleTarget("elements", 1, 1, this);

	/**
	 * Keeps track of the parents for all the root elements (so that we
	 * can issue a full viewer refresh when there is a notification that
	 * concerns such a parent; the refresh is required otherwise the root
	 * elements won't be refreshed) - This is used only when getElements is
	 * customized.
	 */
	private Set<Object> customElementsParents = new HashSet<Object>();

	@Inject
	public ViewerContentProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This implements {@link ITreeItemContentProvider#hasChildren
	 * ITreeItemContentProvider.hasChildren}. The default, non-optimized
	 * approach simply tests whether whether {@link #getChildren getChildren}
	 * returns any children.
	 */
	@Override
	public boolean hasChildren(Object element) {
		// Lorenzo: we need to call the super method anyway
		// if the super method is not invoked the tree is not
		// refreshed correctly
		super.hasChildren(element); // ignore it.
		return getChildren(element).length > 0;
	}

	/**
	 * The default implementation
	 * 
	 * @param o
	 * @return null
	 */
	public List<Object> children(Object o) {
		return null;
	}

	/**
	 * The default implementation
	 * 
	 * @param o
	 * @return null
	 */
	public List<Object> elements(Object o) {
		return null;
	}

	@Override
	public Object[] getChildren(Object element) {
		Object children = childrenDispatcher.invoke(element);
		if (children != null) {
			return EmfParsleyUtil.ensureCollection(children).toArray();
		}
		return super.getChildren(element);
	}

	@Override
	public Object[] getElements(Object element) {
		Object polymorphicElements = elementsDispatcher.invoke(element);
		if (polymorphicElements != null) {
			Object[] elements = EmfParsleyUtil.ensureCollection(polymorphicElements).toArray();
			customElementsParents.clear();
			addListenersToTheModel(elements, element);
			return elements;
		}
		return super.getElements(element);
	}

	/**
	 * This will ensure that this content provider will get notifications from
	 * all the parents/containers in the model.
	 * 
	 * @param elements
	 * @param main
	 */
	protected void addListenersToTheModel(Object[] elements, Object main) {
		// this will make sure that this adapter factory content provider is
		// installed also on parents,
		// and this will allow this content provider to get notifications when
		// elements are added to parent/containter objects.
		// This way, refresh will be handled even when top level elements are
		// added
		addListenersToTheModel(elements);
		if (elements.length == 0) {
			// this is the case when custom getElements returns an empty array:
			// no root elements are still available; in this case we must
			// install listeners on the elements returned by the standard
			// implementation, so that when new root elements are added
			// we can refresh the viewer
			Object[] defaultElements = super.getElements(main);
			addListenersToTheModel(defaultElements);
			for (Object object : defaultElements) {
				// although these are not actually parents, we need to
				// treat them as such, since notifications concerning them
				// must issue a full refresh
				customElementsParents.add(object);
			}
		}
	}

	/**
	 * This will ensure that this content provider will get notifications from
	 * all the parents/containers in the model.
	 * 
	 * @param elements
	 */
	protected void addListenersToTheModel(Object[] elements) {
		for (Object object : elements) {
			addListenersToTheModel(object);
		}
	}

	/**
	 * This will ensure that this content provider will get notifications from
	 * all the parents/containers in the model.
	 * 
	 * @param object
	 */
	protected void addListenersToTheModel(Object object) {
		Object parent = getParent(object);
		while (parent != null) {
			customElementsParents.add(parent);
			parent = getParent(parent);
		}
	}

	@Override
	public Object getParent(Object object) {
		/* 
		 * This is necessary in the CDO Resource case: getParent returns itself.
		 * This leads AbstractTreeViewer to a loop during the internalExpand method. 
		 */
		Object parent = super.getParent(object);
		if(parent==object){
			return null;
		}
		return parent;
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (viewerRefresh == null) {
			viewerRefresh = new ViewerContentProviderViewerRefresh(viewer, customElementsParents);
		}

		super.notifyChanged(notification);
	}

	public static class ViewerContentProviderViewerRefresh extends ViewerRefresh {

		private Viewer viewer;

		private Set<Object> customElementsParents;

		public ViewerContentProviderViewerRefresh(Viewer viewer, Set<Object> customElementsParents) {
			super(viewer);
			this.viewer = viewer;
			this.customElementsParents = customElementsParents;
		}

		@Override
		protected void refresh(IViewerNotification notification) {
			if (viewer instanceof StructuredViewer) {
				StructuredViewer structuredViewer = (StructuredViewer) viewer;
				Object element = notification.getElement();

				if (customElementsParents.contains(element)) {
					// this requires a full refresh, since the event concerns
					// a root element's parent; this means that new root elements
					// might be ready to be displayed.
					structuredViewer.refresh(notification.isLabelUpdate());
					return;
				}
			}

			super.refresh(notification);
		}
	}
}
