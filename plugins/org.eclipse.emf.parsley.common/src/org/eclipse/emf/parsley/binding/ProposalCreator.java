/**
 * <copyright> 
 *
 * Copyright (c) 2008 itemis AG and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   itemis AG - Initial API and implementation
 *
 * </copyright>
 *
 */
package org.eclipse.emf.parsley.binding;

import org.eclipse.emf.parsley.runtime.util.PolymorphicDispatcher;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
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

import com.google.common.base.Predicate;

/**
 * @author Dennis H�bner Initial
 * @author Lorenzo Bettini polymorphic proposals
 * 
 */
public class ProposalCreator {
	private ResourceSet resourceSet;
	
	private PolymorphicDispatcher.ErrorHandler<List<?>> proposals_errorHandler = new PolymorphicDispatcher.NullErrorHandler<List<?>>();

	public ResourceSet getResourceSet() {
		return resourceSet;
	}

	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
	
	protected void retrieveResourceSet(EObject eObject) {
		Resource resource = eObject.eResource();
		resourceSet = (resource == null ? null : resource
				.getResourceSet());
	}

	/**
	 * Calculates possible values for given operating object.
	 * 
	 * @param feature
	 * @return possible values
	 */
	public List<Object> proposals(EObject eObject, EStructuralFeature feature) {
		
		List<Object> proposals  = polymorphicCreateProposals(eObject, feature);
		if(proposals != null){
			return proposals;
		}
		
		if (resourceSet == null)
			retrieveResourceSet(eObject);

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
		
		if (resourceSet == null)
			return objects;
		
		TreeIterator<Object> allContents = EcoreUtil.getAllContents(
				resourceSet.getResources(), true);
		while (allContents.hasNext()) {
			Object o = allContents.next();
			if (type.isInstance(o))
				objects.add(o);
		}
		// the following does not seem to be necessary
		// since it iterates on Ecore stuff... Lorenzo
//		for (TreeIterator<Object> iter = EcoreUtil
//				.getAllContents(EcoreUtil.getRootContainer(
//						EcorePackage.eINSTANCE, true), false); iter.hasNext();) {
//			Object next = iter.next();
//			if ((type.isInstance(next)) && !objects.contains(next)) {
//				objects.add(next);
//			}
//		}
		return objects;
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
		return new PolymorphicDispatcher<List<Object>>(
				Collections.singletonList(this), getCreateProposalsPredicate(
						object, feature, numOfParams),
				new PolymorphicDispatcher.NullErrorHandler<List<Object>>()) {
			@Override
			protected List<Object> handleNoSuchMethod(Object... params) {
				if (PolymorphicDispatcher.NullErrorHandler.class
						.equals(proposals_errorHandler.getClass()))
					return null;
				return super.handleNoSuchMethod(params);
			}
		};
	}

	protected Predicate<Method> getCreateProposalsPredicate(
			EObject object, EStructuralFeature feature, int numOfParams) {
		String methodName = "proposals_"
				+ object.eClass().getName() + "_"
				+ feature.getName();
		return PolymorphicDispatcher.Predicates.forName(methodName, numOfParams);
	}
}
