/*******************************************************************************
 * Copyright (c) 2014 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.views;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.parsley.config.Configurator;
import org.eclipse.emf.parsley.examples.library.EXTLibraryPackage;
import org.eclipse.emf.parsley.tests.EmfParsleySWTBotAbstractTests;

/**
 * A {@link Configurator} used in tests to provide the needed configurations for
 * the views in this package.
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 *
 */
public class TestConfigurator extends Configurator {

	public URI resourceURI(TestSaveableTreeView requestor) {
		return URI.createPlatformResourceURI(
				EmfParsleySWTBotAbstractTests.MY_EXTLIBRARY_RELATIVE_PATH, true);
	}

	public URI resourceURI(TestSaveableTreeView2 requestor) {
		return URI.createPlatformResourceURI(
				EmfParsleySWTBotAbstractTests.MY2_EXTLIBRARY_RELATIVE_PATH, true);
	}
	
	public URI resourceURI(TestSaveableTreeViewWithColumns requestor) {
		return URI.createPlatformResourceURI(
				EmfParsleySWTBotAbstractTests.MY_EXTLIBRARY_RELATIVE_PATH, true);
	}

	public URI resourceURI(TestSaveableTreeViewWithSpecificColumns requestor) {
		return URI.createPlatformResourceURI(
				EmfParsleySWTBotAbstractTests.MY_EXTLIBRARY_RELATIVE_PATH, true);
	}
	
	public URI resourceURI(TestSaveableResourceTreeFormView requestor) {
		return URI.createPlatformResourceURI(
				EmfParsleySWTBotAbstractTests.MY_EXTLIBRARY_RELATIVE_PATH, true);
	}

	public EClass eClass(TestSaveableResourceTableView requestor) {
		return EXTLibraryPackage.Literals.BOOK;
	}

	public URI resourceURI(TestSaveableResourceTableView requestor) {
		return URI.createPlatformResourceURI(
				EmfParsleySWTBotAbstractTests.MY_EXTLIBRARY_RELATIVE_PATH, true);
	}

	public EClass eClass(TestSaveableResourceTableFormView requestor) {
		return EXTLibraryPackage.Literals.BOOK;
	}
	
	public URI resourceURI(TestSaveableResourceTableFormView requestor) {
		return URI.createPlatformResourceURI(
				EmfParsleySWTBotAbstractTests.MY_EXTLIBRARY_RELATIVE_PATH, true);
	}

	public EClass eClass(TestOnSelectionLibraryBooksTableView requestor) {
		return EXTLibraryPackage.Literals.BOOK;
	}

	public EClass eClass(TestOnSelectionLibraryBooksTableFormView requestor) {
		return EXTLibraryPackage.Literals.BOOK;
	}
}
