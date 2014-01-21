/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.parsley.dsl.scoping;

import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.xbase.annotations.scoping.XbaseWithAnnotationsScopeProvider

/**
 * This class contains custom scoping description. XbaseScopeProvider is
 * NOT used by the Xbase validator: it is used only by the content assist,
 * that's why it is still here, see
 * http://www.eclipse.org/forums/index.php/t/476486/
 * 
 * @author Lorenzo Bettini
 * 
 */
public class EmfParsleyDslScopeProvider extends XbaseWithAnnotationsScopeProvider {
	
	@Inject extension EmfParsleyDslScopeProviderHelper
	
	override getScope(EObject context, EReference reference) {
		val scope = context.createCustomScope(reference)
		
		if (scope != null)
			return scope;
		super.getScope(context, reference);
	}

}
