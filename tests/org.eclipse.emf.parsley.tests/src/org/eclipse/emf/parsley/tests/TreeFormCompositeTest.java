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
package org.eclipse.emf.parsley.tests;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.eclipse.emf.parsley.composite.CompositeFactory;
import org.eclipse.emf.parsley.composite.TreeFormComposite;
import org.eclipse.emf.parsley.junit4.AbstractEmfParsleyShellBasedTest;
import org.eclipse.swt.SWT;
import org.junit.Before;
import org.junit.Test;

/**
 * Make sure we can instantiate all our composites via dependency injection.
 */
public class TreeFormCompositeTest extends AbstractEmfParsleyShellBasedTest {

	private TreeFormComposite treeFormComposite;

	@Before
	public void setupComposite() {
		var injector = getOrCreateInjector();
		treeFormComposite = injector
			.getInstance(CompositeFactory.class)
			.createTreeFormComposite(getShell(), SWT.NONE);
	}

	@Test
	public void testUpdateWithNullObject() {
		treeFormComposite.update(null);
		assertNull(treeFormComposite.getViewer().getInput());
	}

	@Test
	public void testUpdateWithObject() {
		var arg = new Object();
		treeFormComposite.update(arg);
		assertSame(arg, treeFormComposite.getViewer().getInput());
	}
}
