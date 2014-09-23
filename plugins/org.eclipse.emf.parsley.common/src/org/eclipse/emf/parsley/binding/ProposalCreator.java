/**
 * <copyright> 
 *
 * Copyright (c) 2008, 2013 itemis AG and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   itemis AG - Initial API and implementation
 *   Lorenzo Bettini - refactoring for EmfParsley
 *
 * </copyright>
 *
 */
package org.eclipse.emf.parsley.binding;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EEnumImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcher;
import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcherExtensions;

import com.google.common.base.Predicate;

/**
 * @author Dennis Huebner Initial
 * @author Lorenzo Bettini polymorphic proposals
 * 
 */
public class ProposalCreator {
	private Resource resource;
	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	protected void retrieveResource(EObject eObject) {
		resource = eObject.eResource();
	}

	/**
	 * Calculates possible values for given operating object.
	 * 
	 * @param feature
	 * @return possible values
	 */
	public List<Object> proposals(EObject eObject, EStructuralFeature feature) {
		
		List<Object> proposals  = polymorphicCreateProposals(eObject, feature);
		if (proposals != null) {
			return proposals;
		}
		
		if (getResource() == null) {
			retrieveResource(eObject);
		}

		return defaultProposals(feature);
	}

	protected List<Object> defaultProposals(EStructuralFeature feature) {
		List<Object> retVal = null;
		// TODO Reference[] handle
		if (feature instanceof EReference) {
			retVal = findAllInstances(feature.getEType());
		} else if (feature.getEType() instanceof EEnumImpl) {
			EEnum eEnum = (EEnum) feature.getEType();
			List<Object> enumerators = new ArrayList<Object>();
			for (Iterator<?> iter = eEnum.getELiterals().iterator(); iter
					.hasNext();) {
				Enumerator instance = ((EEnumLiteral) iter.next())
						.getInstance();
				enumerators.add(instance);
			}
			retVal = enumerators;
		}
		return retVal;
	}

	protected List<Object> findAllInstances(EClassifier type) {
		List<Object> objects = new ArrayList<Object>();
		
		if (getResource() == null) {
			return objects;
		}
		
		TreeIterator<Object> allContents = getAllContents();
		while (allContents.hasNext()) {
			Object o = allContents.next();
			if (type.isInstance(o))
				objects.add(o);
		}
		return objects;
	}

	protected TreeIterator<Object> getAllContents() {
		ResourceSet resourceSet = resource.getResourceSet();
		
		if (resourceSet == null) {
			return EcoreUtil.getAllContents(resource, true);
		} else {
			return EcoreUtil.getAllContents(resourceSet.getResources(), true);
		}
	}
	
	private List<Object> polymorphicCreateProposals(EObject element,
			EStructuralFeature feature) {

		// first try with a method with two parameters
		// (EObject, EStructurealFeature)...
		PolymorphicDispatcher<List<Object>> dispatcher = createPolymorphicDispatcher(
				element, feature, 2);

		List<Object> invoke = dispatcher.invoke(element, feature);

		if (invoke == null) {
			// ...then with a method with only EObject
			dispatcher = createPolymorphicDispatcher(element, feature, 1);
			invoke = dispatcher.invoke(element);
		}

		return invoke;
	}

	private PolymorphicDispatcher<List<Object>> createPolymorphicDispatcher(
			EObject object, EStructuralFeature feature, int numOfParams) {
		return PolymorphicDispatcherExtensions.createPolymorphicDispatcher(this, getCreateProposalsPredicate(
						object, feature, numOfParams));
	}

	protected Predicate<Method> getCreateProposalsPredicate(
			EObject object, EStructuralFeature feature, int numOfParams) {
		String methodName = "proposals_"
				+ object.eClass().getName() + "_"
				+ feature.getName();
		return PolymorphicDispatcher.Predicates.forName(methodName, numOfParams);
	}
}
