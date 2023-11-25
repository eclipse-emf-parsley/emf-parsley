/**
 * Copyright (c) 2016 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 */
package org.eclipse.emf.parsley.dsl.scoping;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.dsl.model.ViewSpecification;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.xbase.resource.XbaseResourceDescriptionStrategy;

import com.google.inject.Singleton;

/**
 * We need to index part specification ids to avoid duplicate ids across files
 * in the same project.
 * 
 * @author Lorenzo Bettini - initial API and implementation
 */
@Singleton
public class EmfParsleyDslResourceDescriptionStrategy extends XbaseResourceDescriptionStrategy {
	@Override
	public boolean createEObjectDescriptions(final EObject elem, final IAcceptor<IEObjectDescription> acceptor) {
		if (elem instanceof ViewSpecification) {
			var id = ((ViewSpecification) elem).getId();
			if (id != null) {
				acceptor.accept(EObjectDescription.create(id, elem));
				return true;
			}
		}
		return super.createEObjectDescriptions(elem, acceptor);
	}
}
