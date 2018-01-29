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
import org.eclipse.emf.parsley.inject.EClassParameter;
import org.eclipse.emf.parsley.util.EcoreUtil2;

import com.google.inject.Inject;

/**
 * Declarative ContentProvider specific for table viewer, implementing
 * getElements retrieving all the contents of a specific {@link EClass}.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 */
public class TableViewerContentProvider extends ViewerContentProvider {

	private EClass eClass;

	/**
	 * @param adapterFactory
	 * @param eClassParameter
	 * @since 2.0
	 */
	@Inject
	public TableViewerContentProvider(AdapterFactory adapterFactory, EClassParameter eClassParameter) {
		super(adapterFactory);
		this.eClass = eClassParameter.getEClass();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> elements(Object o) {
		// since we can get an object which is both a Resource and an EObject
		// e.g., CDOResource, we can't have two elements methods since we would
		// get an ambiguous methods exception from the polymorphic dispatcher
		// see https://bugs.eclipse.org/bugs/show_bug.cgi?id=479417
		if (o instanceof Resource) {
			Object result = EcoreUtil2.getAllContentsOfType((Resource) o, eClass);
			return (List<Object>) result;
		}
		if (o instanceof EObject) {
			Object result = EcoreUtil2.getAllContentsOfType((EObject) o, eClass);
			return (List<Object>) result;
		}
		return super.elements(o);
	}

}
