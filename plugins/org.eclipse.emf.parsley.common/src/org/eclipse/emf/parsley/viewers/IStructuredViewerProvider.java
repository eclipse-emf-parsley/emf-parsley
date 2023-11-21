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
package org.eclipse.emf.parsley.viewers;

import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.jface.viewers.StructuredViewer;

/**
 * Common interfaces for objects that provide a {@link StructuredViewer}
 *
 * @author Lorenzo Bettini - Initial contribution and API
 *
 * @param <T> The specific type of the {@link StructuredViewer}
 */
public interface IStructuredViewerProvider<T extends StructuredViewer> extends IViewerProvider {

	@Override
	T getViewer();

}
