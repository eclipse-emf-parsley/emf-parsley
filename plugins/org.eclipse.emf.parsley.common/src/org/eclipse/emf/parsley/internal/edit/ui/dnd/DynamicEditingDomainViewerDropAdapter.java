/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.internal.edit.ui.dnd;

import java.util.Collection;

import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.emf.parsley.edit.domain.EditingDomainFinderStrategy;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.dnd.DropTargetEvent;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;

/**
 * A specialization of {@link EditingDomainViewerDropAdapter} that retrieves the
 * editing domain dynamically from the object that is being dragged and dropped.
 *
 * @noextend This class is not intended to be subclassed by clients.
 * @noinstantiate This class is not intended to be instantiated by clients.
 *
 * @author Lorenzo Bettini - initial API and implementation
 *
 */
public class DynamicEditingDomainViewerDropAdapter extends EditingDomainViewerDropAdapter {

	@Inject
	private EditingDomainFinderStrategy editingDomainFinderStrategy;

	public DynamicEditingDomainViewerDropAdapter(Viewer viewer) {
		super(null, viewer);
	}

	@Override
	public void drop(DropTargetEvent event) {
		Collection<?> dragSource = extractDragSource(event.data);
		Object firstElement = Iterables.getFirst(dragSource, null);
		editingDomainFinderStrategy.updateEditingDomain(firstElement);
		domain = editingDomainFinderStrategy.getEditingDomain();
		if (domain != null) {
			super.drop(event);
		}
	}
}
