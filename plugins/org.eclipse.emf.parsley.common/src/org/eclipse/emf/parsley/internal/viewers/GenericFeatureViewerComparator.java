/*******************************************************************************
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Francesco Guidieri - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.parsley.internal.viewers;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.util.EmfParsleyUtil;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

/**
 * @since 1.1
 * @author Francesco Guidieri
 */
public class GenericFeatureViewerComparator extends ViewerComparator {

	private int propertyIndex;
	private int direction;

	private List<EStructuralFeature> features;

	public GenericFeatureViewerComparator() {
		this.propertyIndex = 0;
		this.direction = SWT.NONE;
	}

	public void init(List<EStructuralFeature> features) {
		this.features = features;
	}

	public int getDirection() {
		return direction;
	}

	public int getPropertyIndex() {
		return propertyIndex;
	}

	public void setPropertyIndex(int column) {
		if (column == this.propertyIndex) {
			// Same column as last sort; toggle the direction
			switch (direction) {
			case SWT.NONE:
				direction = SWT.UP;
				break;
			case SWT.UP:
				direction = SWT.DOWN;
				break;
			default:
				direction = SWT.NONE;
			}
		} else {
			// New column; do an ascending sort
			propertyIndex = column;
			direction = SWT.UP;
		}
	}

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		EObject p1 = (EObject) e1;
		EObject p2 = (EObject) e2;
		// If none, no compare
		if (direction == SWT.NONE) {
			return 0;
		}

		EStructuralFeature feature = features.get(propertyIndex);
		int rc = compareValue(p1, p2, feature);
		// If descending order, flip the direction
		if (direction == SWT.DOWN) {
			rc = -rc;
		}
		return rc;
	}

	protected int compareValue(EObject p1, EObject p2, EStructuralFeature feature) {
		Object value1 = p1.eGet(feature);
		Object value2 = p2.eGet(feature);
		return EmfParsleyUtil.compareValues(value1, value2);
	}

}
