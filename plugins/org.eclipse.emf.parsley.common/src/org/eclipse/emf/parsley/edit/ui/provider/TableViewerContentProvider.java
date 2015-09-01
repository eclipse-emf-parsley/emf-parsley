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

import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.parsley.util.EcoreUtil2;

import com.google.inject.Inject;

/**
 * Declarative ContentProvider specific for table viewer, implementing
 * getElements retrieving all the contents of a specific {@link EClass}.
 * 
 * The {@link EClass} must be set before this content provider is used.
 * We provide a specific factory for conveniently create instances of
 * this content provider.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 * 
 */
public class TableViewerContentProvider extends ViewerContentProvider {

	private EClass eClass;

	/**
	 * Meant for testing: If you use this constructor, you
	 * then must make sure to inject other members, using, for instance,
	 * injectMembers.
	 */
	public TableViewerContentProvider(EClass eClass) {
		this.eClass = eClass;
	}

	@Inject
	public TableViewerContentProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public EClass getEClass() {
		return eClass;
	}

	public void setEClass(EClass type) {
		this.eClass = type;
	}

	@Override
	public Object[] getElements(Object element) {
		// to deal with the null argument and avoid polymorphic
		// dispatch ambiguous methods
		if (element == null) {
			return new Object[0];
		}
		return super.getElements(element);
	}

	public List<EObject> elements(Resource resource) {
		return EcoreUtil2.getAllContentsOfType(resource, getEClass());
	}

	public List<EObject> elements(EObject ele) {
		return EcoreUtil2.getAllContentsOfType(ele, getEClass());
	}
}
