/*******************************************************************************
 * Copyright (c) 2015 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.util;

import static org.eclipse.emf.parsley.util.EmfParsleyUtil.getEObjectOrNull;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;

/**
 * Utility methods for retrieving EMF stuff from events.
 *
 * @author Lorenzo Bettini - Initial contribution and API
 */
public class EmfEventHelper {

	public EObject getEObjectFromMouseEvent(MouseEvent event) {
		Object source = event.getSource();
		if (source instanceof Tree) {
			Tree tree = (Tree) source;
			if (tree.getSelectionCount() == 1) {
				return getEObjectOrNull(tree.getSelection()[0].getData());
			}
		} else if (source instanceof Table) {
			Table table = (Table) source;
			if (table.getSelectionCount() == 1) {
				return getEObjectOrNull(table.getSelection()[0].getData());
			}
		}
		return null;
	}
}
