/**
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - initial API and implementation
 */
package org.eclipse.emf.parsley.dsl.typing;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.xbase.typesystem.references.LightweightTypeReference;
import org.eclipse.xtext.xbase.typesystem.references.StandardTypeReferenceOwner;
import org.eclipse.xtext.xbase.typesystem.util.CommonTypeComputationServices;

public class EmfParsleyDslTypeSystem {
	@Inject
	private CommonTypeComputationServices services;

	public boolean isConformant(final EObject context, final Class<?> expected, final JvmTypeReference actual) {
		return toLightweightTypeReference(actual, context).isSubtypeOf(expected);
	}

	public LightweightTypeReference toLightweightTypeReference(final JvmTypeReference typeRef, final EObject context) {
		return newTypeReferenceOwner(context).toLightweightTypeReference(typeRef);
	}

	protected StandardTypeReferenceOwner newTypeReferenceOwner(final EObject context) {
		return new StandardTypeReferenceOwner(services, context);
	}
}
