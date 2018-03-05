/*******************************************************************************
 * Copyright (c) 2018 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.composite;

import org.eclipse.jface.viewers.ISelectionChangedListener;

/**
 * Represents a master composite with some contents.
 * 
 * @author Lorenzo Bettini
 * @since 2.0
 * 
 */
public interface IMasterComposite {

	/**
	 * Adss a {@link ISelectionChangedListener} listener to this composite.
	 * @param selectionChangedListener
	 */
	void addSelectionChangedListener(ISelectionChangedListener selectionChangedListener);

	/**
	 * Updates the content of this master component.
	 * @param contents
	 */
	void update(Object contents);

}