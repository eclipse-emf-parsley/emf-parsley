/*******************************************************************************
 * Copyright (c) 2018 RCP Vision (http://www.rcp-vision.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Lorenzo Bettini - Initial contribution and API
 *******************************************************************************/
package org.eclipse.emf.parsley.tests.util;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.parsley.composite.CompositeFactory;
import org.eclipse.emf.parsley.composite.TableFormComposite;
import org.eclipse.emf.parsley.composite.TreeComposite;
import org.eclipse.swt.widgets.Composite;

import com.google.inject.Singleton;

import static org.mockito.Mockito.*;

/**
 * Creates singleton mocks. Only elements used in our tests are mocked; if you
 * need other mocks in tests, then just override the corresponding create
 * method.
 * 
 * @author Lorenzo Bettini
 *
 */
@Singleton
public class MockCompositeFactory extends CompositeFactory {

	private TreeComposite treeComposite = mock(TreeComposite.class);
	private TableFormComposite tableFormComposite = mock(TableFormComposite.class);

	@Override
	public TreeComposite createTreeComposite(Composite parent, int style) {
		return treeComposite;
	}

	@Override
	public TableFormComposite createTableFormComposite(Composite parent, int style, EClass type) {
		return tableFormComposite;
	}

}
