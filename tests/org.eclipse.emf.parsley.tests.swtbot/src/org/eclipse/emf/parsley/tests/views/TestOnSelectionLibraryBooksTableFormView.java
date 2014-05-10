/*******************************************************************************
 * Copyright (c) 2013 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.views;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage;
import org.eclipse.emf.parsley.views.AbstractOnSelectionTableFormView;


/**
 * @author Lorenzo Bettini
 *
 */
public class TestOnSelectionLibraryBooksTableFormView extends
		AbstractOnSelectionTableFormView {

	@Override
	protected EStructuralFeature getEStructuralFeature() {
		return EXTLibraryPackage.Literals.LIBRARY__BOOKS;
	}

}
